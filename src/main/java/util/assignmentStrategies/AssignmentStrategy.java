package util.assignmentStrategies;

import org.jblas.DoubleMatrix;

import java.util.Map;

//Note : This is a generic interface for AssignmentStrategy Algorithms.
public interface AssignmentStrategy {
	public Map<Integer, Integer> assign(DoubleMatrix matrix);
}
