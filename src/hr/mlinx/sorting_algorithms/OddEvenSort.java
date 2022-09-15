package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class OddEvenSort extends SortingAlgorithm {

	public OddEvenSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		boolean isSorted = false;
		
		while (!isSorted) {
			isSorted = true;
			
			for (int i = 1; i <= len - 2; i += 2) {
				stats.addComps(1);
            	stats.addAccesses(2);
				if (array[i] > array[i + 1]) {
					sv.swap(i, i + 1);
					isSorted = false;
				}
			}
			
			for (int i = 0; i <= len - 2; i += 2) {
				stats.addComps(1);
            	stats.addAccesses(2);
				if (array[i] > array[i + 1]) {
					sv.swap(i, i + 1);
					isSorted = false;
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Odd-Even Sort";
	}

}
