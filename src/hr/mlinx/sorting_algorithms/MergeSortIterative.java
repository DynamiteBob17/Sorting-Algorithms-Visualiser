package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class MergeSortIterative extends SortingAlgorithm {

	public MergeSortIterative(SortVisualiser sv) {
		super(sv);
	}
	
	private void merge(int l, int m, int r) {
		int i, j, k;
		int n1 = m - l + 1;
		int n2 = r - m;
		
		int L[] = new int[n1];
		int R[] = new int[n2];
		
		for (i = 0; i < n1; ++i) {
        	stats.addAccesses(1);
			L[i] = array[l + i];
		}
		for (j = 0; j < n2; ++j) {
        	stats.addAccesses(1);
			R[j] = array[m + 1 + j];
		}
		
		i = j = 0;
		k = l;
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
 
	@Override
	public void sort() {
		int currLen;
		int leftStart;
		
		for (currLen = 1; currLen <= len - 1; currLen *= 2) {
			for (leftStart = 0; leftStart < len - 1; leftStart += 2 * currLen) {
				int mid = Math.min(leftStart + currLen - 1, len - 1);
				int rightEnd = Math.min(leftStart + 2 * currLen - 1, len - 1);
				
				merge(leftStart, mid, rightEnd);
			}
		}
	}

	@Override
	public String getName() {
		return "Merge Sort Iterative";
	}

}
