package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class BubbleSort extends SortingAlgorithm {

	public BubbleSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		for (int i = 0; i < len - 1; ++i) {
			for (int j = 0; j < len - i - 1; ++j) {
				stats.addComps(1);
				stats.addAccesses(2);
				if (array[j] > array[j + 1])
					sv.swap(j, j + 1);
			}
		}
	}

	@Override
	public String getName() {
		return "Bubble Sort";
	}
	
}
