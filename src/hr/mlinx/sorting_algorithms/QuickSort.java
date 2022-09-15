package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class QuickSort extends SortingAlgorithm {

	public QuickSort(SortVisualiser sv) {
		super(sv);
	}
	
	private int partition(int low, int high) {
    	stats.addAccesses(1);
		int pivot = array[high];
		int i = (low - 1);
		
		for (int j = low; j <= high - 1; ++j) {
			stats.addComps(1);
        	stats.addAccesses(1);
			if (array[j] < pivot) {
				++i;
            	stats.addAccesses(1);
				sv.swap(i, j);
			}
		}
    	stats.addAccesses(2);
		sv.swap(i + 1, high);
		return (i + 1);
	}
	
	private void quickSort(int low, int high) {
		if (low < high) {
			int pi = partition(low, high);
			
			quickSort(low, pi - 1);
			quickSort(pi + 1, high);
		}
	}

	@Override
	public void sort() {
		quickSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Quick Sort";
	}
	
}
