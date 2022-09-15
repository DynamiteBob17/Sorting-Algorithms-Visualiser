package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class MinHeapSort extends SortingAlgorithm {
	
	public MinHeapSort(SortVisualiser sv) {
		super(sv);
	}
	
	private void siftDown(int root, int dist, int start) {
		while (root <= dist / 2) {
			int leaf = 2 * root;
			
			if (leaf < dist) {
				stats.addAccesses(2);
				stats.addComps(1);
				
				if (array[start + leaf - 1] > array[start + leaf]) {
					++leaf;
				}
			}
			
			stats.addAccesses(1);
			stats.addComps(1);
			if (array[start + root - 1] > array[start + leaf - 1]) {
				sv.swap(start + root - 1, start + leaf - 1);
				root = leaf;
			} else {
				break;
			}
		}
	}
	
	private void heapify(int low, int high) {
		int length = high - low;
		for (int i = len / 2; i >= 1; --i) {
			siftDown(i, length, low);
		}
	}
	
	private void heapSort(int start, int length) {
		heapify(start, length);
		
		for (int i = length - start; i > 1; --i) {
			stats.addAccesses(2);
			sv.swap(start, start + i - 1);
			siftDown(1, i - 1, start);
		}
		
		reversal(start, start + length - 1);
	}
	
	private void reversal(int start, int length) {
		for (int i = start; i < start + ((length - start + 1) / 2); ++i) {
			stats.addAccesses(2);
			sv.swap(i, start + length - i);
		}
	}

	@Override
	public void sort() {
		heapSort(0, len);
	}

	@Override
	public String getName() {
		return "Min Heap Sort";
	}

}
