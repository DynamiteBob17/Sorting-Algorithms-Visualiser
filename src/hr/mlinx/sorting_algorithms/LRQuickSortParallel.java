package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class LRQuickSortParallel extends SortingAlgorithm {
	
	private class QS extends Thread {
		private int p, r;
		QS(int p, int r) {
			this.p = p;
			this.r = r;
		}
		@Override
		public void run() {
			LRQuickSortParallel.this.quickSort(p, r);
		}
	}

	public LRQuickSortParallel(SortVisualiser sv) {
		super(sv);
	}
	
	private void quickSort(int p, int r) {
		if (p < r) {
			int pivot = p + (r - p + 1) / 2;
			stats.addAccesses(1);
			int x = array[pivot];
			
			int i = p;
			int j = r;
			
			sv.highlight(pivot);
			
			while (i <= j) {
				boolean passedOnce = false;
				stats.addAccesses(1);
				stats.addComps(1);
				while (array[i] < x) {
					if (passedOnce) {
						stats.addAccesses(1);
						stats.addComps(1);
					} else {
						passedOnce = true;
					}
					++i;
					sv.highlight(i);
				}
				passedOnce = false;
				stats.addAccesses(1);
				stats.addComps(1);
				while (array[j] > x) {
					if (passedOnce) {
						stats.addAccesses(1);
						stats.addComps(1);
					} else {
						passedOnce = true;
					}
					--j;
					sv.highlight(j);
				}
				
				if (i <= j) {
					if (i == pivot) {
						sv.highlight(j);
					}
					if (j == pivot) {
						sv.highlight(i);
					}
					
					stats.addAccesses(2);
					sv.swap(i, j);
					
					++i;
					--j;
				}
			}
			
			QS left = new QS(p, j);
			QS right = new QS(i, r);
			left.start();
			right.start();
			
			try {
				left.join();
				right.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void sort() {
		quickSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Quick Sort Parallel";
	}
	
}
