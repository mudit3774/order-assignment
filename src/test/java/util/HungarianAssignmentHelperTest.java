package util;

import com.google.common.collect.ImmutableMap;
import org.jblas.DoubleMatrix;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class HungarianAssignmentHelperTest {

	private HungarianAssignmentHelper hungarianAssignmentHelper;
	private final ObjectMother om = new ObjectMother();

	@Before
	public void setUp() throws Exception {
		hungarianAssignmentHelper = new HungarianAssignmentHelper();
	}

	@Test
	public void getAssignments() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{82.0, 83.0, 0.0, 92.0},
			{77.0, 0.0, 49.0, 92.0},
			{11.0, 69.0, 0.0, 0.0},
			{0.0, 9.0, 98.0, 23.0}
		});

		Map<Integer, Integer> EXPECTED_ASSIGNMENT = ImmutableMap.<Integer, Integer>builder()
			.put(0,2)
			.put(1,1)
			.put(2,3)
			.put(3,0)
			.build();

		assertEquals(EXPECTED_ASSIGNMENT, hungarianAssignmentHelper.getAssignments(TEST_MATRIX));
	}

	@Test
	public void minLinesCoveringAllZeros() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{13.0, 14.0, 0.0, 8.0},
			{40.0, 0.0, 12.0, 40.0},
			{6.0, 64.0, 0.0, 66.0},
			{0.0, 1.0, 90.0, 0.0}
		});

		List<Integer> TEST_UNASSIGNED_ROWS = new ArrayList<>();
		TEST_UNASSIGNED_ROWS.add(2);

		Integer EXPECTED_LINES = 3;

		assertEquals(EXPECTED_LINES, hungarianAssignmentHelper.minLinesCoveringAllZeros(TEST_MATRIX, TEST_UNASSIGNED_ROWS));
	}

	@Test
	public void minLinesCoveringAllZerosCompleteAssignment() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{7.0, 8.0, 0.0, 2.0},
			{40.0, 0.0, 18.0, 40.0},
			{0.0, 58.0, 0.0, 60.0},
			{0.0, 1.0, 96.0, 0.0}
		});

		List<Integer> TEST_UNASSIGNED_ROWS = new ArrayList<>();

		Integer EXPECTED_LINES = 4;

		assertEquals(EXPECTED_LINES, hungarianAssignmentHelper.minLinesCoveringAllZeros(TEST_MATRIX, TEST_UNASSIGNED_ROWS));
	}

	@Test
	public void getMarkedRowsAndColumns() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{13.0, 14.0, 0.0, 8.0},
			{40.0, 0.0, 12.0, 40.0},
			{6.0, 64.0, 0.0, 66.0},
			{0.0, 1.0, 90.0, 0.0}
		});

		List<Integer> TEST_UNASSIGNED_ROWS = new ArrayList<>();
		TEST_UNASSIGNED_ROWS.add(2);

		Set<Integer> EXPECTED_ROWS = new HashSet<>();
		EXPECTED_ROWS.add(0);
		EXPECTED_ROWS.add(2);

		Set<Integer> EXPECTED_COLS = new HashSet<>();
		EXPECTED_COLS.add(2);

		List<Set<Integer>> result = hungarianAssignmentHelper.getMarkedRowsAndColumns(TEST_MATRIX, TEST_UNASSIGNED_ROWS);
		assertEquals(result.get(0), EXPECTED_ROWS);
		assertEquals(result.get(1), EXPECTED_COLS);
	}

	@Test
	public void getUnassignedRows() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{13.0, 14.0, 0.0, 8.0},
			{40.0, 0.0, 12.0, 40.0},
			{6.0, 64.0, 0.0, 66.0},
			{0.0, 1.0, 90.0, 0.0}
		});

		Map<Integer, Integer> TEST_ASSIGNMENT = ImmutableMap.<Integer, Integer>builder()
			.put(0,2)
			.put(1,1)
			.put(3,0)
			.build();

		List<Integer> EXPECTED_UNASSIGNED_ROWS = new ArrayList<>();
		EXPECTED_UNASSIGNED_ROWS.add(2);

		assertEquals(EXPECTED_UNASSIGNED_ROWS, hungarianAssignmentHelper.getUnassignedRows(TEST_ASSIGNMENT, TEST_MATRIX));

	}

	@Test
	public void reduceRemainingMatrix() throws Exception {
		DoubleMatrix TEST_MATRIX = new DoubleMatrix(new double[][]{
			{13.0, 14.0, 0.0, 8.0},
			{40.0, 0.0, 12.0, 40.0},
			{6.0, 64.0, 0.0, 66.0},
			{0.0, 1.0, 90.0, 0.0}
		});

		DoubleMatrix EXPECTED_MATRIX = new DoubleMatrix(new double[][]{
			{7.0, 8.0, 0.0, 2.0},
			{40.0, 0.0, 18.0, 40.0},
			{0.0, 58.0, 0.0, 60.0},
			{0.0, 1.0, 96.0, 0.0}
		});

		Map<Integer, Integer> TEST_ASSIGNMENT = ImmutableMap.<Integer, Integer>builder()
			.put(0,2)
			.put(1,1)
			.put(3,0)
			.build();

		System.out.println(hungarianAssignmentHelper.reduceRemainingMatrix(TEST_ASSIGNMENT, TEST_MATRIX));
	}
}