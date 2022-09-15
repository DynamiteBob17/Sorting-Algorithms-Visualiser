package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class InsertionSort extends SortingAlgorithm {

	public InsertionSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		int key, j;
		boolean condPassedAtLeastOnce;
		
		for (int i = 1; i < len; ++i) {
        	stats.addAccesses(1);
			key = array[i];
			j = i - 1;
			
			stats.addComps(1);
        	stats.addAccesses(1);
			condPassedAtLeastOnce = false;
			while (j >= 0 && array[j] > key) {
				if (condPassedAtLeastOnce) {
					stats.addComps(1);
                	stats.addAccesses(2);
				}
				else {
					condPassedAtLeastOnce = true;
                	stats.addAccesses(1);
				}
				sv.setSingle(j + 1, array[j]);
				j = j - 1;
			}
        	stats.addAccesses(1);
			sv.setSingle(j + 1, key);
		}
	}

	@Override
	public String getName() {
		return "Insertion Sort";
	}

}
