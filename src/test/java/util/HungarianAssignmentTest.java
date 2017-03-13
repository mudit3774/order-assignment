package util;

import com.google.common.collect.ImmutableMap;
import org.jblas.DoubleMatrix;
import org.junit.Test;
import util.exception.CannotApplyHungarianAssignment;

import java.util.Map;

import static org.junit.Assert.assertEquals;

//TODO : Refactor tests to move objects to Object Mother.
//TODO : Use Guice Injectors
public class HungarianAssignmentTest {

	private HungarianAssignment assignment = new HungarianAssignment(new HungarianAssignmentHelper());

	@Test(expected = CannotApplyHungarianAssignment.class)
	public void assignNotASquareMatrixException() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{82.0, 83.0, 69.0},
			{77.0, 37.0, 49.0},
			{11.0, 69.0, 5.0},
			{8.0, 9.0, 98.0}
		});
		assignment.assign(TEST_MATRIX);
	}

	@Test
	public void assign() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{82.0, 83.0, 69.0, 92.0},
			{77.0, 37.0, 49.0, 92.0},
			{11.0, 69.0, 5.0, 86.0},
			{8.0, 9.0, 98.0, 23.0}
		});

		Map<Integer, Integer> EXPECTED_ASSIGNMENT_MAP = ImmutableMap.<Integer, Integer>builder()
			.put(0,1)
			.put(1,1)
			.put(2,0)
			.put(3,3)
			.build();

		assertEquals(EXPECTED_ASSIGNMENT_MAP, assignment.assign(TEST_MATRIX));
	}

}