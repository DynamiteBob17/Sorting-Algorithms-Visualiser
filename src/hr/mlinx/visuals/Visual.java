package hr.mlinx.visuals;

import java.awt.Graphics2D;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public abstract class Visual {
	
	public static final int INPUT_MAX_SIZE = 1920 * 2;
	protected static final int DEFAULT_SIZE = 128;
	private static final int INSERTION_SIZE = 240;
	public static final int BAR_COLOR_INTENSITY = 180;
	public static final int DEFAULT_COLOR_SUBTRACTION_STEP = 15;
	
	protected TopPanel tp;
	protected SortVisualiser sv;
	protected BottomPanel bp;
	
	protected int[] array;
	protected int sortSize;
	protected int bitonicSize;
	protected int maxSize;
	protected int red[];
	protected int green[];
	protected int blue[];
	protected int colorSubtractionStep;
	
	public Visual(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super();
		
		this.tp = tp;
		this.sv = sv;
		this.bp = bp;
		
		array = sv.getArray();
		sortSize = DEFAULT_SIZE;
		colorSubtractionStep = DEFAULT_COLOR_SUBTRACTION_STEP;
		
		setRgb();
		setBitonicAndMaxArraySizes();
		setOtherSizes();
	}
	
	public void changeVals() {
		if (!tp.sizeLocked()) {
			String selection = (String) tp.getAlgoBox().getSelectedItem();
			if (selection.equals("Selection Sort") || selection.contains("Double Selection")
					|| selection.contains("Bubble") || selection.contains("Cocktail")
					|| selection.equals("Odd-Even Sort")) {
				sortSize = DEFAULT_SIZE;
			} else if (selection.contains("Insertion")) {
				sortSize = INSERTION_SIZE;
			} else if (selection.contains("Bitonic")) {
				sortSize = bitonicSize;
			} else {
				sortSize = maxSize;
			}
			tp.getSizeSpinner().setValue(sortSize);
		} else {
			sortSize = sv.getVc().getLockedSize();
			if (tp.getAlgoBox().getSelectedItem().equals("Bitonic Sort")) {
				if (sortSize > BarGraph.BITONIC_SIZE) 
					sortSize = BarGraph.BITONIC_SIZE;
				else
					sortSize = findClosestPowerOfTwo(sortSize);
			}
		}
		setOtherSizes();
	}
	
	private static int findClosestPowerOfTwo(int n) {
		return (int) Math.pow(2, Math.round(Math.log(n) / Math.log(2)));
	}
	
	protected void subtractColors(int idx) {
		if (red[idx] > 0)
			red[idx] -= colorSubtractionStep;
		if (green[idx] > 0)
			green[idx] -= colorSubtractionStep;
		if (blue[idx] > 0)
			blue[idx] -= colorSubtractionStep;
	}
	
	public abstract void visualise(Graphics2D g2);
	public abstract void setRgb();
	protected abstract void setBitonicAndMaxArraySizes();
	public abstract void setOtherSizes();
	public abstract String getName();
	
	public void setTopPanel(TopPanel tp) {
		this.tp = tp;
	}
	
	public int getSortSize() {
		return sortSize;
	}
	
	public void setSortSize(int sortSize) {
		this.sortSize = sortSize;
	}
	
	public void setRed(int idx, int value) {
		red[idx] = value;
	}
	
	public void setGreen(int idx, int value) {
		green[idx] = value;
	}
	
	public void setBlue(int idx, int value) {
		blue[idx] = value;
	}
	
	public int getColorSubtractionStep() {
		return colorSubtractionStep;
	}

	public void setColorSubtractionStep(int colorSubtractionStep) {
		this.colorSubtractionStep = colorSubtractionStep;
	}
	
}
