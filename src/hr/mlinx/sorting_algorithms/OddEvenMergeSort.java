package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class OddEvenMergeSort extends SortingAlgorithm {

	public OddEvenMergeSort(SortVisualiser sv) {
		super(sv);
	}
	
	private int nextGap(int gap) {
		if (gap <= 1)
			return 0;
		
		return (int) Math.ceil(gap / 2.0);
	}
	
	private void merge(int start, int end) {
		int gap = end - start + 1;
		for (gap = nextGap(gap); gap > 0; gap = nextGap(gap)) {
			for (int i = start; i + gap <= end; ++i) {
				int j = i + gap;
				stats.addComps(1);
            	stats.addAccesses(2);
				if (array[i] > array[j])
					sv.swap(i, j);
			}
		}
	}
	
	private void mergeSort(int s, int e) {
		if (s == e)
			return;
		
		int mid = (s + e) / 2;
		
		mergeSort(s, mid);
		mergeSort(mid + 1, e);
		
		merge(s, e);
	}
	
	@Override
	public void sort() {
		mergeSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Odd-Even Merge Sort";
	}

}
