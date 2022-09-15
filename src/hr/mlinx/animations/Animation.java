package hr.mlinx.animations;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public abstract class Animation extends Thread {
	
	protected TopPanel tp;
	protected SortVisualiser sv;
	protected BottomPanel bp;
	
	public Animation(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super();
		
		this.tp = tp;
		this.sv = sv;
		this.bp = bp;
	}
	
	@Override
	public abstract void run();
	
}
