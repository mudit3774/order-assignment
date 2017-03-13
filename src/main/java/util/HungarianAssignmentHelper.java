package util;

import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import org.jblas.DoubleMatrix;

import java.util.*;

import static java.util.Collections.min;

@Singleton
class HungarianAssignmentHelper {

	final static Logger logger = Logger.getLogger(HungarianAssignmentHelper.class);

	public Map<Integer,Integer> getAssignments(DoubleMatrix colReduced) {
		Set<Integer> assignedRows = new HashSet<>();
		Set<Integer> crossedCols = new HashSet<>();
		Map<Integer, Integer> assignmentMap =  new HashMap<>();

		for(int row=0;row<colReduced.rows;row++){
			for(int columns=0;columns<colReduced.columns;columns++){
				if(colReduced.get(row,columns) == 0.0) {
					if(!crossedCols.contains(columns) && !assignedRows.contains(row))
					{
						assignedRows.add(row);
						crossedCols.add(columns);
						assignmentMap.put(row, columns);
						logger.debug("Assigned : " + row +  "," + columns);
					}
					else
						logger.debug("Unassigned : " + row + "," + columns);
				}
			}
		}
		return assignmentMap;
	}

	public DoubleMatrix reduceRemainingMatrix(Map<Integer, Integer> assignmentMap, DoubleMatrix matrix) {
		List<Set<Integer>> markedEntities = getMarkedRowsAndColumns(matrix, getUnassignedRows(assignmentMap, matrix));
		Set<Integer> markedRows = markedEntities.get(0);
		Set<Integer> markedCols = markedEntities.get(1);
		List<Double> uncoveredValues = new ArrayList<>();
		for(int row=0;row<matrix.rows;row++){
			for(int col=0;col<matrix.columns;col++){
				if(!markedRows.contains(row) || markedCols.contains(col)){
					logger.debug("Covered : " + row + "," + col);
				}
				else {
					logger.debug("Uncovered : " + row + ", " + col);
					uncoveredValues.add(matrix.get(row, col));
				}
			}
		}

		Double uncoveredValueMin = min(uncoveredValues);
		logger.debug(uncoveredValueMin);

		for(int row=0;row<matrix.rows;row++){
			for(int col=0;col<matrix.columns;col++){
				if(!markedRows.contains(row) && markedCols.contains(col)){
					logger.debug("Intersection : " + row + ", " + col);
					matrix.put(row, col, matrix.get(row, col) + uncoveredValueMin);
				}
				else if(!markedRows.contains(row) || markedCols.contains(col)){
					logger.debug("Covered : " + row + "," + col);
				}
				else {
					logger.debug("Uncovered : " + row + "," + col);
					matrix.put(row, col, matrix.get(row, col) - uncoveredValueMin);
				}
			}
		}

		return matrix;
	}

	public List<Integer> getUnassignedRows(Map<Integer, Integer> assignmentMap, DoubleMatrix matrix){
		List<Integer> unAssignedRows = new ArrayList<>();
		for(int row=0;row < matrix.rows; row++)
			if(!assignmentMap.keySet().contains(row))
				unAssignedRows.add(row);
		return unAssignedRows;
	}

	//TODO : Have a betteer return type
	public List<Set<Integer>> getMarkedRowsAndColumns(DoubleMatrix matrix, List<Integer> unAssignedRows) {
		Set<Integer> markedRows = new HashSet<>();
		Set<Integer> markedCols = new HashSet<>();

		markedRows.addAll(unAssignedRows);
		List<Integer> markedColsTemp;
		for(Integer unAssignedRow:unAssignedRows) {
			markedColsTemp = new ArrayList<>();
			for (int col = 0; col < matrix.columns; col++) {
				if (matrix.get(unAssignedRow, col) == 0.0)
				{
					markedColsTemp.add(col);
				}
			}
			for(Integer col:markedColsTemp){
				for(int row=0;row<matrix.rows;row++){
					if(matrix.get(row, col) == 0.0){
						markedRows.add(row);
					}
				}
			}
			markedCols.addAll(markedColsTemp);
		}

		logger.debug(markedRows);
		logger.debug(markedCols);
		List<Set<Integer>> markedRowsAndCols = new ArrayList<>();
		markedRowsAndCols.add(markedRows);
		markedRowsAndCols.add(markedCols);
		return markedRowsAndCols;
	}

	public Integer minLinesCoveringAllZeros(DoubleMatrix matrix, List<Integer> unAssignedRows) {
		List<Set<Integer>> markedEntities = getMarkedRowsAndColumns(matrix, unAssignedRows);
		Set<Integer> markedRows = markedEntities.get(0);
		Set<Integer> markedCols = markedEntities.get(1);
		return (matrix.rows - markedRows.size()) + markedCols.size();
	}

}
