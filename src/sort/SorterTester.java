package sort;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit-testing for sorting Algos!!
 * 
 * @author shaun.viguerie
 *
 */
public class SorterTester {

	private int[] numbers;
	private final static int SIZE = 25;
	private final static int MAX = 50;

	@Before
	public void setUp() throws Exception {
		numbers = new int[SIZE];
		Random generator = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = generator.nextInt(MAX);
		}
	}

	@Test
	public void testMergeSort() {

		long startTime = System.currentTimeMillis();

		ISorter sorter = new MergeSort();
		sorter.sort(numbers);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("MergeSort " + elapsedTime);

		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i + 1]) {
				fail("Should not happen");
			}
		}

		assertTrue(true);
	}

	@Test
	public void testQuickSort() {

		long startTime = System.currentTimeMillis();

		ISorter sorter = new QuickSort();
		sorter.sort(numbers);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("QuickSort " + elapsedTime);

		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i] > numbers[i + 1]) {
				fail("Should not happen");
			}
		}

		assertTrue(true);
	}

}
