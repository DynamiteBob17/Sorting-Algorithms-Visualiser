package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class ShellSort extends SortingAlgorithm {
	
	public ShellSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		for (int gap = len / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < len; ++i) {
            	stats.addAccesses(1);
				int temp = array[i];
				int j;
				for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
					stats.addComps(1);
                	stats.addAccesses(2);
					sv.setSingle(j, array[j - gap]);
				}
            	stats.addAccesses(1);
				sv.setSingle(j, temp);
			}
		}
	}

	@Override
	public String getName() {
		return "Shell Sort";
	}
	
}
