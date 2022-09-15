package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class GravitySort extends SortingAlgorithm {

	public GravitySort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		int max = 0;
		for (int i = 0; i < len; ++i) {
			if (array[i] > max)
				max = array[i];
			stats.addComps(1);
        	stats.addAccesses(1);
			sv.highlight(i);
		}
		int[][] abacus = new int[len][max];
		for (int i = 0; i < len; ++i) {
			for (int j = 0; j < array[i]; ++j) {
				abacus[i][abacus[0].length - j - 1] = 1;
			}
		}
		
		for (int i = 0; i < abacus[0].length; ++i) {
			for (int j = 0; j < abacus.length; ++j) {
				if (abacus[j][i] == 1) {
					int droppos = j;
					while ((droppos + 1) < abacus.length && abacus[droppos][i] == 1) {
						++droppos;
					}
					if (abacus[droppos][i] == 0) {
						abacus[j][i] = 0;
						abacus[droppos][i] = 1;
					}
				}
			}
			
			int count = 0, x;
			for (x = 0; x < abacus.length; ++x) {
				count = 0;
				for (int y = 0; y < abacus[0].length; ++y) {
					count += abacus[x][y];
				}
            	stats.addAccesses(1);
				array[x] = count;
			}
			sv.highlight(len - i - 1);
		}
	}

	@Override
	public String getName() {
		return "Gravity Sort";
	}

}
