package util.assignmentStrategies;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import org.jblas.DoubleMatrix;
import util.exception.CannotApplyHungarianAssignment;

import java.util.Map;

@Singleton
public class HungarianAssignment implements AssignmentStrategy {

	final static Logger logger = Logger.getLogger(HungarianAssignment.class);
	private final HungarianAssignmentHelper hungarianAssignmentHelper;

	@Inject
	public HungarianAssignment(HungarianAssignmentHelper hungarianAssignmentHelper) {
		this.hungarianAssignmentHelper = hungarianAssignmentHelper;
	}

	public Map<Integer, Integer> assign(DoubleMatrix matrix) {
		if(!matrix.isSquare()){
			throw new CannotApplyHungarianAssignment("not a square matrix");
		}
		DoubleMatrix rowReduced = matrix.subColumnVector(matrix.rowMins());
		DoubleMatrix colReduced = rowReduced.subRowVector(rowReduced.columnMins());
		Map<Integer, Integer> assignmentMap = hungarianAssignmentHelper.getAssignments(colReduced);
		while(hungarianAssignmentHelper.minLinesCoveringAllZeros(colReduced,
			hungarianAssignmentHelper.getUnassignedRows(assignmentMap, colReduced)) != matrix.rows){
			colReduced = hungarianAssignmentHelper.reduceRemainingMatrix(assignmentMap, colReduced);
			assignmentMap = hungarianAssignmentHelper.getAssignments(colReduced);
		}
		return hungarianAssignmentHelper.getAssignments(colReduced);
	}
}
