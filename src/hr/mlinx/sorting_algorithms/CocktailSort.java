package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class CocktailSort extends SortingAlgorithm {

	public CocktailSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
		boolean swapped = true;
		int start = 0, end = len;
		
		while (swapped) {
			swapped = false;
			
			for (int i = start; i < end - 1; ++i) {
				stats.addComps(1);
				stats.addAccesses(2);
				if (array[i] > array[i + 1]) {
					sv.swap(i, i + 1);
					swapped = true;
				}
			}
			
			if (!swapped)
				break;
			swapped = false;
			
			end = end - 1;
			for (int i = end - 1; i >= start; --i) {
				stats.addComps(1);
				stats.addAccesses(2);
				if (array[i] > array[i + 1]) {
					sv.swap(i, i + 1);
					swapped = true;
				}
			}
			
			start = start + 1;
		}
	}

	@Override
	public String getName() {
		return "Cocktail Sort";
	}

}
