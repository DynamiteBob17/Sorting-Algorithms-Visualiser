package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;
import hr.mlinx.visuals.Visual;

public class WeaveMergeSort extends SortingAlgorithm {

	public WeaveMergeSort(SortVisualiser sv) {
		super(sv);
	}
	
	private void weaveInsert(int start, int end) {
        int pos;

        for(int j = start; j < end; j++){
            pos = j;

            sv.highlight(j);
            
            while(pos > start && array[pos] <= array[pos - 1]) {
            	stats.addComps(1);
                swap(pos, pos - 1);
                pos--;
            }
        }
    }
	
	private void weaveMerge(int min, int max, int mid) {
        int i = 1;
        int target = (mid - min);

        while(i <= target) {
            multiSwap(mid + i, min + (i * 2) - 1);
            i++;
        }

        this.weaveInsert(min, max + 1);
    }
	
	private void multiSwap(int pos, int to) {
		if (to - pos > 0) {
            for (int i = pos; i < to; i++) {
            	stats.addAccesses(2);
                swap(i, i + 1);
            }
        } else {
            for (int i = pos; i > to; i--) {
            	stats.addAccesses(2);
                swap(i, i - 1);
            }
        }
	}
	
	private void swap(int i, int j) {
		stats.addSwaps(1);
		
		int temp = array[i];
		setSingle(i, array[j]);
		setSingle(j, temp);
		
		sv.update(sv.getSleep() / 10.0);
	}
	
	private void setSingle(int idx, int value) {
		array[idx] = value;
		
		if (sv.getVc().getIdx() == 0) {
			sv.getVc().get().setGreen(idx,  Visual.BAR_COLOR_INTENSITY);
			sv.getVc().get().setBlue(idx,  Visual.BAR_COLOR_INTENSITY);
		}
		sv.getSoundPlayer().play(idx, sv.getVc().get().getSortSize());
	}
	
	private void weaveMergeSort(int min, int max) {
        if(max - min == 0) {
        	sv.update(1.0);
        }
        else if(max - min == 1) {
        	stats.addAccesses(2);
        	stats.addComps(1);
            if(array[min] > array[max]) {
            	sv.swap(min, max);
            }
        }
        else {
            int mid = (int) Math.floor((min + max) / 2);

            this.weaveMergeSort(min, mid);
            this.weaveMergeSort(mid + 1, max);
            this.weaveMerge(min, max, mid);
        }
    }

	@Override
	public void sort() {
		weaveMergeSort(0, len - 1);
	}

	@Override
	public String getName() {
		return "Weave Merge Sort";
	}

}
