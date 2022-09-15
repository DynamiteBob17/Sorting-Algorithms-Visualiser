package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class DoubleSelectionSort extends SortingAlgorithm {

	public DoubleSelectionSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		for (int i = 0, j = len - 1; i < j; ++i, --j) {
			int min = i, max = i;
			for (int k = i; k <= j; ++k) {
				if (array[k] > array[max]) {
					max = k;
					stats.addComps(1);
                	stats.addAccesses(2);
				} else if (array[k] < array[min]) {
					min = k;
					stats.addComps(2);
                	stats.addAccesses(3);
				} else {
					stats.addComps(2);
                	stats.addAccesses(3);
				}
				sv.highlight(k);
			}
			
        	stats.addAccesses(1);
			sv.swap(i, min);
			
			stats.addComps(1);
        	stats.addAccesses(1);
			if (i == max) {
				max = min;
			}
			sv.swap(len - i - 1, max);
		}
	}

	@Override
	public String getName() {
		return "Double Selection Sort";
	}
	
}
