package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;
import hr.mlinx.visuals.Visual;

public class MergeSortInPlace extends SortingAlgorithm {

	public MergeSortInPlace(SortVisualiser sv) {
		super(sv);
	}
	
	private void merge(int min, int max, int mid) {
		try {
			int i = min;
			while (i <= mid) {
				stats.addAccesses(2);
				stats.addComps(1);
				if (array[i] > array[mid + 1]) {
					sv.swap(i, mid + 1);
					push(mid + 1, max);
				}
				++i;
			}
		} catch (Exception e) {
		}
	}
	
	private void push(int s, int e) {
		for (int i = s; i < e; ++i) {
			stats.addAccesses(2);
			stats.addComps(1);
			if (array[i] > array[i + 1]) {
				swap(i, i + 1);
			}
		}
	}
	
	private void swap(int i, int j) {
		stats.addSwaps(1);
		
		int temp = array[i];
		setSingle(i, array[j]);
		setSingle(j, temp);
	}
	
	private void setSingle(int idx, int value) {
		array[idx] = value;
		
		if (sv.getVc().getIdx() == 0) {
				sv.getVc().get().setGreen(idx,  Visual.BAR_COLOR_INTENSITY);
				sv.getVc().get().setBlue(idx,  Visual.BAR_COLOR_INTENSITY);
		}
		sv.getSoundPlayer().play(idx, sv.getVc().get().getSortSize());
		
		sv.update(sv.getSleep() / 50.0);
	}
	
	private void mergeSort(int min, int max) {
		if (max - min == 0) {
			sv.update(1.0);
		} else if (max - min == 1) {
			stats.addAccesses(2);
			stats.addComps(1);
			if (array[min] > array[max])
				sv.swap(min, max);
		} else {
			int mid = ((int) Math.floor((min + max) / 2));
			
			mergeSort(min, mid);
			mergeSort(mid + 1, max);
			
			merge(min, max, mid);
		}
	}
	
	@Override
	public void sort() {
		mergeSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Merge Sort In-Place";
	}

}
