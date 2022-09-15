package hr.mlinx.handlers;

import java.awt.Graphics2D;
import java.util.LinkedList;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.visuals.BarGraph;
import hr.mlinx.visuals.DisparityPoints;
import hr.mlinx.visuals.Hoops;
import hr.mlinx.visuals.Visual;

public class VisualController {
	
	private LinkedList<Visual> visuals;
	private int idx;
	private int lockedSize;
	
	public VisualController(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super();
		
		visuals = new LinkedList<>();
		visuals.add(new BarGraph(tp, sv, bp));
		visuals.add(new Hoops(tp, sv, bp, false));
		visuals.add(new Hoops(tp, sv, bp, true));
		visuals.add(new DisparityPoints(tp, sv, bp, false));
		visuals.add(new DisparityPoints(tp, sv, bp, true));
	}
	
	public void next() {
		if (idx < visuals.size() - 1)
			++idx;
		else
			idx = 0;
	}
	
	public void previous() {
		if (idx > 0)
			--idx;
		else
			idx = visuals.size() - 1;
	}
	
	public void visualise(Graphics2D g2) {
		visuals.get(idx).visualise(g2);
	}
	
	public Visual get() {
		return visuals.get(idx);
	}
	
	public int getIdx() {
		return idx;
	}
	
	public int getLockedSize() {
		return lockedSize;
	}
	
	public void setLockedSize() {
		lockedSize = visuals.get(idx).getSortSize();
	}
	
	public void setRgb() {
		visuals.get(idx).setRgb();
	}
	
	public void changeVals() {
		visuals.get(idx).changeVals();
	}
	
	public void setTopPanels(TopPanel tp) {
		visuals.forEach(v -> {
			v.setTopPanel(tp);
		});
	}
	 
}
