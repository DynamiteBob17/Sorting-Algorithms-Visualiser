package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class BitonicSort extends SortingAlgorithm {
	
	private int up;
	
	// size of array must be a power of 2 so the sort works
	public BitonicSort(SortVisualiser sv, int up) {
		super(sv);
		this.up = up;
	}
	
	private void compAndSwap(int i, int j, int dir) {
		stats.addAccesses(2);
		if ((array[i] > array[j] && dir == 1) || 
			(array[i] < array[j] && dir == 0)) {
			if (dir == 1)
				stats.addComps(1);
			else if (dir == 0)
				stats.addComps(2);
			sv.swap(i, j);
			return;
		}
		stats.addComps(2);
	}
	
	private void bitonicMerge(int low, int n, int dir) {
		if (n > 1) {
			int k = n / 2;
			for (int i = low; i < low + k; ++i) {
				compAndSwap(i, i + k, dir);
			}
			bitonicMerge(low, k, dir);
			bitonicMerge(low + k, k, dir);
		}
	}
	
	private void bitonicSort(int low, int n, int dir) {
		if (n > 1) {
			int k = n / 2;
			
			bitonicSort(low, k , 1);
			bitonicSort(low + k, k, 0);
			
			bitonicMerge(low, n, dir);
		}
	}
	
	@Override
	public void sort() {
		bitonicSort(0, len, up);
	}

	@Override
	public String getName() {
		return "Bitonic Sort";
	}

}
