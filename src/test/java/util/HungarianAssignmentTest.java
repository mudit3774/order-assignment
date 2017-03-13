package util;

import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;
import util.exception.CannotApplyHungarianAssignment;

public class HungarianAssignmentTest {

	private HungarianAssignment assignment = new HungarianAssignment(new HungarianAssignmentHelper());

	@Before
	public void setUp() throws Exception {
	}


	@Test(expected = CannotApplyHungarianAssignment.class)
	public void assignException() throws Exception {
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
		System.out.println(assignment.assign(TEST_MATRIX));
	}
}