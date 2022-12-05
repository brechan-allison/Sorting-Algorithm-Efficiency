import javax.swing.*;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SortAlgorithms {
	private static final int QUICKSORT = 1;
	private static final int MERGE_SORT = 2;
	private static final int RADIX_SORT_10 = 3;
	private static final int RADIX_SORT_2 = 4;
	private static final int RADIX_SORT_16 = 5;
	private static final int RADIX_SORT_256 = 6;
	private static final int JAVA_SORT = 7;
	private static final int JAVA_PARALLEL_SORT = 8;

	String dataFilePath;
	String dataFileName;
	JFileChooser chooser;
	int n;
	int[] data = new int[1_000_000];
	int[] sortedData;
	int[] b;
	Queue[] q;
	// statistics

	long[] elapsedTime = new long[9];
	long[] nDataMovements = new long[9];
	long[] nComparisons = new long[9];

	public void openDataFile() {
		dataFilePath = null;
		dataFileName = null;

		chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setDialogTitle("Open Data File");

		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			dataFilePath = chooser.getSelectedFile().getPath();
			dataFileName = chooser.getSelectedFile().getName();
		}
		try {
			readFileIntoArray();
			for (int i = 0; i < elapsedTime.length; i++)
				elapsedTime[i] = nDataMovements[i] = nComparisons[i] = 0;
		} catch (IOException ioe) {
			System.exit(0);
		}

	}

	public void readFileIntoArray() throws IOException {
		if (dataFilePath != null) {
			int index = 0;
			Scanner integerTextFile = new Scanner(new File(dataFilePath));
			while (integerTextFile.hasNext()) {
				// read the next integer
				data[index] = integerTextFile.nextInt();
				index++;
			}
			// end of file detected
			integerTextFile.close();
			n = index;
		} else
			n = 0;
	}

	public void insertionSort() {
		int k = 0;
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);

		long startTime = System.nanoTime();
		for (int i = 1; i < sortedData.length; i++) {
			int j = i;
			int relocate = sortedData[i];
			while ((j > 0) && (sortedData[j - 1] > relocate)) {
				nDataMovements[k]++;
				nComparisons[k] += 2;
				sortedData[j] = sortedData[j - 1];
				j--;
			}
			sortedData[j] = relocate;
		}
		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Insertion Sort successful");
	}

	public void quickSort() {
		// int k = 1;
		int k = QUICKSORT;
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);
		long startTime = System.nanoTime();

		qSort(0, n - 1);

		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Quick Sort successful");
	}

	public void qSort(int lo, int hi) {
		if (lo >= hi || lo < 0) {
			return;
		}
		int p = partition(lo, hi);
		qSort(lo, p - 1);
		qSort(p + 1, hi);
	}

	private int partition(int lo, int hi) {
		int pivot = sortedData[hi];
		int i = lo - 1;
		for (int j = lo; j < hi; j++) {
			nComparisons[QUICKSORT]++;
			if (sortedData[j] <= pivot) {
				i++;
				swap(i, j, 1);
			}
		}
		i++;
		swap(i, hi, 1);
		return i;
	}

	private void swap(int i, int j, int algorithm) {
		nDataMovements[algorithm] += 2;
		int temp = sortedData[i];
		sortedData[i] = sortedData[j];
		sortedData[j] = temp;
	}

	public void mergeSort() {
		// int k = 2;
		int k = MERGE_SORT;
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);
		long startTime = System.nanoTime();
		b = new int[n];

		mSort(0, n);

		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Merge Sort successful");
	}

	public void mSort(int lo, int hi) {
		if (hi - lo <= 1) {
			return;
		}
		int split = lo + (hi - lo) / 2;
		mSort(lo, split);
		mSort(split, hi);
		// nDataMovements[2] += split - lo;
		System.arraycopy(sortedData, lo, b, 0, split - lo);
		int m1 = 0;
		int m2 = split;
		int i = lo;
		while (i < m2 && m2 < hi) {
			nComparisons[MERGE_SORT]++;
			nDataMovements[MERGE_SORT]++;
			if (b[m1] <= sortedData[m2]) {
				sortedData[i++] = b[m1++];
			} else {
				sortedData[i++] = sortedData[m2++];
			}
		}
		while (i < m2) {
			nDataMovements[MERGE_SORT]++;
			sortedData[i++] = b[m1++];
		}
	}

	private static int getK(int radix) {
		int k;
		if (radix == 10) {
			k = RADIX_SORT_10;
		} else if (radix == 2) {
			k = RADIX_SORT_2;
		} else if (radix == 16) {
			k = RADIX_SORT_16;
		} else {
			k = RADIX_SORT_256;
		}
		return k;
	}

	public void radixSort(int r) {
		int k = getK(r);
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);
		long startTime = System.nanoTime();
		b = new int[n];

		rSort(r, 0, n - 1);

		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Radix " + r + " Sort successful");
	}

	private void rSort(int radix, int lo, int hi) {
		q = new Queue[radix];
		for (int i = 0; i < radix; i++) {
			q[i] = new Queue();
		}
		int k = getK(radix);
		int size = hi + 1;
		initializeQueues(radix);
		int max = Integer.MIN_VALUE;
		for (int i = lo; i < size; i++) {
			if (sortedData[i] > max) {
				max = sortedData[i];
			}
		}
		int maxDigits = (int) (Math.ceil(Math.log(max) / Math.log(radix)));
		for (int i = 0; i < maxDigits; i++) {
			int p = (int) Math.pow(radix, i);
			for (int num : sortedData) {
				int sigDig = (num / p) % radix;
				q[sigDig].enQueue(num);
			}
			int j = 0;
			for (int b = 0; b < radix; b++) {
				while (!q[b].isEmpty()) {
					nDataMovements[k]++;
					sortedData[j++] = q[b].deQueue();
				}
			}
		}
	}

	public void initializeQueues(int r) {
		for (int i = 0; i < r; i++) {
			q[i].front = null;
			q[i].rear = null;
		}
	}

	public boolean isSorted() {
		for (int i = 0; i < n - 1; i++) {
			if (sortedData[i] > sortedData[i + 1])
				return false;
		}
		return true;
	}

	public void javaSort() {
		int k = JAVA_SORT;
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);
		long startTime = System.nanoTime();

		Arrays.sort(sortedData);

		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Java Sort successful");
	}

	public void javaParallelSort() {
		int k = JAVA_PARALLEL_SORT;
		elapsedTime[k] = nDataMovements[k] = nComparisons[k] = 0;

		sortedData = new int[n];
		System.arraycopy(data, 0, sortedData, 0, n);
		long startTime = System.nanoTime();

		Arrays.parallelSort(sortedData);

		elapsedTime[k] = (System.nanoTime() - startTime) / 1000;
		if (isSorted())
			System.out.println("Java Parallel Sort successful");
	}

}