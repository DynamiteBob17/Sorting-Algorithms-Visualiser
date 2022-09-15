package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class RadixSortInPlace extends SortingAlgorithm {
	
	private int base;

	public RadixSortInPlace(SortVisualiser sv, int base) {
		super(sv);
		this.base = base;
	}

	@Override
	public void sort() {
		int pos = 0;
		int[] vregs = new int[base - 1];
		int maxpower = analyze();
		double smul = Math.sqrt(base);
		for (int p = 0; p <= maxpower; ++p) {
			for (int i = 0; i < vregs.length; ++i) {
				vregs[i] = len - 1;
			}
			pos = 0;
			for (int i = 0; i < len; ++i) {
            	stats.addAccesses(1);
				int digit = getDigit(array[pos], p);
				if (digit == 0) {
					++pos;
					sv.highlight(pos);
				} else {
					for (int j = 0; j < vregs.length; ++j) {
						sv.highlight(vregs[j]);
					}
					swapUpToNM(pos, vregs[digit - 1], 0.0011 * smul);
					for (int j = digit - 1; j > 0; --j) {
						--vregs[j - 1];
					}
				}
			}
		}
	}
	
	private int analyze() {
		int a = 0;
		for (int i = 0; i < len; ++i) {
			stats.addComps(1);
			stats.addAccesses(1);
			sv.highlight(i);
			if((int)(Math.log(array[i])/Math.log(base))>a){
                a=(int)(Math.log(array[i])/Math.log(base));
            }
		}
		return a;
	}
	
	private int getDigit(int a, int power) {
		return (int) (a / Math.pow(base, power)) % base;
	}
	
	private void swapUpToNM(int pos, int to, double sleep) {
		if (to - pos > 0) {
			for (int i = pos; i < to; ++i) {
				swap(i, i + 1);
			}
			sv.update(sleep);
		} else {
			for (int i = pos; i > to; --i) {
				swap(i, i - 1);
			}
			sv.update(sleep);
		}
	}
	
	private void swap(int i, int j) {
		stats.addSwaps(1);
		
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	@Override
	public String getName() {
		return "LSD R.S. In-Place Base " + base;
	}

}
