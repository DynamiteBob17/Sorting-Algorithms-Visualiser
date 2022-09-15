package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class MaxHeapSort extends SortingAlgorithm {

	public MaxHeapSort(SortVisualiser sv) {
		super(sv);
	}
	
	private void heapify(int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		
		stats.addComps(1);
    	stats.addAccesses(2);
		if (l < n && array[l] > array[largest])
			largest = l;
		
		stats.addComps(1);
    	stats.addAccesses(1);
		if (r < n && array[r] > array[largest])
			largest = r;
		
		stats.addComps(1);
    	stats.addAccesses(1);
		if (largest != i) {
			sv.swap(i, largest);
			
			heapify(n, largest);
		}
	}

	@Override
	public void sort() {
		for (int i = len / 2 - 1; i >= 0; --i)
			heapify(len, i);
		
		for (int i = len - 1; i > 0; --i) {
        	stats.addAccesses(2);
			sv.swap(0, i);
			
			heapify(i, 0);
		}
	}

	@Override
	public String getName() {
		return "Max Heap Sort";
	}

}
