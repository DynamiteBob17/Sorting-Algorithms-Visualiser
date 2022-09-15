package hr.mlinx.handlers;

import hr.mlinx.animations.Animation;
import hr.mlinx.animations.Shuffling;
import hr.mlinx.animations.Sorting;
import hr.mlinx.animations.Watching;
import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public class Animator {
	
	private TopPanel tp;
	private SortVisualiser sv;
	private BottomPanel bp;
	
	private Animation animation;
	private Sorting sorting;
	private Shuffling shuffling;
	private Watching watching;
	
	public Animator(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super();
		
		this.tp = tp;
		this.sv = sv;
		this.bp = bp;
		
		sorting = new Sorting(tp, sv, bp);
		shuffling = new Shuffling(tp, sv, bp);
		watching = new Watching(tp, sv, bp);
		animation = sorting;
	}
	
	public void sort() {
		if (!watching.isAlive()) {
			tp.toggle();
			bp.toggle();
		}
		
		sorting = new Sorting(tp, sv, bp);
		animation = sorting;
		
		sorting.start();
	}
	
	public void shuffle() {
		if (!watching.isAlive()) {
			tp.toggle();
			bp.toggle();
		}
		
		shuffling = new Shuffling(tp, sv, bp);
		animation = shuffling;
		
		shuffling.start();
	}
	
	public void watch() {
		watching = new Watching(tp, sv, bp);
		
		watching.start();
	}
	
	public Animation getAnimation() {
		return animation;
	}
	
	public Sorting getSorting() {
		return sorting;
	}
	
	public Shuffling getShuffling() {
		return shuffling;
	}
	
	public Watching getWatching() {
		return watching;
	}
	
}
