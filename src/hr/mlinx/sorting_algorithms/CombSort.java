package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class CombSort extends SortingAlgorithm {

	public CombSort(SortVisualiser sv) {
		super(sv);
	}
	
	private int getNextGap(int gap) {
		// shrink gap by some shrink factor
		gap = (gap * 10) / 13;
		
		return gap < 1 ? 1 : gap;
	}
	
	@Override
	public void sort() {
		int gap = len;
		boolean swapped = true;
		
		while (gap != 1 || swapped == true) {
			gap = getNextGap(gap);
			swapped = false;
			
			for (int i = 0; i < len - gap; ++i) {
				stats.addComps(1);
				stats.addAccesses(2);
				if (array[i] > array[i + gap]) {
					sv.swap(i, i + gap);
					swapped = true;
				}
			}
		}
	}

	@Override
	public String getName() {
		return "Comb Sort";
	}
	
}
