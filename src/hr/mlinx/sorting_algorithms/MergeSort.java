package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class MergeSort extends SortingAlgorithm {

	public MergeSort(SortVisualiser sv) {
		super(sv);
	}
	
	private void merge(int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for (int i = 0; i < n1; ++i) {
        	stats.addAccesses(1);
			L[i] = array[l + i];
		}
		for (int j = 0; j < n2; ++j) {
        	stats.addAccesses(1);
			R[j] = array[m + 1 + j];
		}
		
		int i = 0, j = 0;
		int k = l;
		while (i < n1 && j < n2) {
			stats.addComps(1);
        	stats.addAccesses(1);
			if (L[i] <= R[j]) {
				sv.setSingle(k, L[i]);
				++i;
			} else {
				sv.setSingle(k, R[j]);
				++j;
			}
			++k;
		}
		
		while (i < n1) {
			stats.addAccesses(1);
			sv.setSingle(k, L[i]);
			++i;
			++k;
		}
		
		while (j < n2) {
			stats.addAccesses(1);
			sv.setSingle(k, R[j]);
			++j;
			++k;
		}
	}
	
	private void mergeSort(int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			
			mergeSort(l, m);
			mergeSort(m + 1, r);
			
			merge(l, m, r);
		}
	}

	@Override
	public void sort() {
		mergeSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Merge Sort";
	}
	
}
