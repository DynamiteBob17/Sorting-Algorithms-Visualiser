package hr.mlinx.sorting_algorithms;

import java.util.Arrays;

import hr.mlinx.components.SortVisualiser;

public class CountingSort extends SortingAlgorithm {

	public CountingSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		int max = Arrays.stream(array).max().getAsInt();
		stats.addComps(len - 1);
		stats.addAccesses(len - 1);
		int min = Arrays.stream(array).min().getAsInt();
		stats.addComps(len - 1);
		stats.addAccesses(len - 1);
		int range = max - min + 1;
		int count[] = new int[range];
		int output[] = new int[len];
		for (int i = 0; i < len; ++i) {
			++count[array[i] - min];
			stats.addAccesses(1);
			sv.highlight(i);
		}
		for (int i = 1; i < count.length; ++i) {
			count[i] += count[i - 1];
		}
		for (int i = len - 1; i >= 0; --i) {
			output[count[array[i] - min] - 1] = array[i];
			--count[array[i] - min];
			stats.addAccesses(1);
			sv.highlight(i);
		}
		for (int i = 0; i < len; ++i) {
			stats.addAccesses(1);
			sv.setSingle(i, output[i]);
		}
	}

	@Override
	public String getName() {
		return "Counting Sort";
	}
	
}
