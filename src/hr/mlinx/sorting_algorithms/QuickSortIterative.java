package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class QuickSortIterative extends SortingAlgorithm {

	public QuickSortIterative(SortVisualiser sv) {
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
	
	private void quickSort(int l, int h) {
		int[] stack = new int[h - l + 1];
		
		int top = 0;
		stack[top] = l;
		stack[++top] = h;
		
		while (top >= 0) {
			h = stack[top--];
			l = stack[top--];
			
			int p = partition(l, h);
			
			if (p - 1 > l) {
				stack[++top] = l;
				stack[++top] = p - 1;
			}
			
			if (p + 1 < h) {
				stack[++top] = p + 1;
				stack[++top] = h;
			}
		}
	}

	@Override
	public void sort() {
		quickSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Quick Sort Iterative";
	}
	
}
