package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class SelectionSort extends SortingAlgorithm {

	public SelectionSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		int minIdx;
		
		for (int i = 0; i < len - 1; ++i) {
			minIdx = i;
			for (int j = i + 1; j < len; ++j) {
				stats.addComps(1);
            	stats.addAccesses(2);
			    sv.highlight(j);
				if (array[j] < array[minIdx])
					minIdx = j;
			}
			
        	stats.addAccesses(1);
			sv.swap(minIdx, i);
		}
	}

	@Override
	public String getName() {
		return "Selection Sort";
	}
	
}
