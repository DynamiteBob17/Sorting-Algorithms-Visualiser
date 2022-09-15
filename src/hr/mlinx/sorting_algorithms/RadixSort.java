package hr.mlinx.sorting_algorithms;

import java.util.Arrays;

import hr.mlinx.components.SortVisualiser;

public class RadixSort extends SortingAlgorithm {
	
	private int base;
	
	public RadixSort(SortVisualiser sv, int base) {
		super(sv);
		this.base = base;
	}
	
	private void countSort(int exp) {
		int output[] = new int[len];
		int count[] = new int[base];
		
		for (int i = 0; i < len; ++i) {
			++count[(array[i] / exp) % base];
        	stats.addAccesses(1);
			sv.highlight(i);
		}
		for (int i = 1; i < base; ++i) {
			count[i] += count[i - 1];
		}
		for (int i = len - 1; i >= 0; --i) {
			output[count[(array[i] / exp) % base] - 1] = array[i];
			--count[(array[i] / exp) % base];
        	stats.addAccesses(1);
			sv.highlight(i);
		}
		for (int i = 0; i < len; ++i) {
        	stats.addAccesses(1);
			sv.setSingle(i, output[i]);
		}
	}

	@Override
	public void sort() {
		int max = Arrays.stream(array).max().getAsInt();
		stats.addComps(len - 1);
    	stats.addAccesses(len - 1);
		
		for (int exp = 1; max / exp > 0; exp *= base) {
			countSort(exp);
		}
	}

	@Override
	public String getName() {
		return "LSD Radix Sort Base " + base;
	}

}
