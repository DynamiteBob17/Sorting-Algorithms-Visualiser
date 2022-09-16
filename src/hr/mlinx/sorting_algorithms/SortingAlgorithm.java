package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;
import hr.mlinx.util.Stats;

public abstract class SortingAlgorithm {
	
	protected SortVisualiser sv;
	protected int[] array;
	protected int len;
	protected Stats stats;
	
	public SortingAlgorithm(SortVisualiser sv) {
		super();
		
		this.sv = sv;
		this.array = sv.getArray();
		this.len = sv.getVisualController().getCurrentVisual().getSortSize();
		this.stats = sv.getStats();
	}
	
	public abstract void sort();
	
	public abstract String getName();
	
	public void setLen(int len) {
		this.len = len;
	}
	
}
