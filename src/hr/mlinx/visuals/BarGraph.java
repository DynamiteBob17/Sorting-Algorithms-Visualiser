package hr.mlinx.visuals;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public class BarGraph extends Visual {
	
	public static final int BITONIC_SIZE = 2048;
	private static final int MAX_SIZE = 1920;
	
	private double barWidth;
	private double barHeight;

	public BarGraph(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super(tp, sv, bp);
	}

	@Override
	public void visualise(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				            RenderingHints.VALUE_ANTIALIAS_ON);
		
		double x, y, drawWidth, drawHeight;
    	
    	for (int i = 0; i < sortSize; ++i) {
    		x = i * barWidth;
			y = (double) sv.getHeight() - (array[i] + 1) * barHeight;
			drawWidth = barWidth;
			drawHeight = (array[i] + 1) * barHeight;
			
			g2.setColor(new Color(255 - red[i],
					              255 - green[i],
					              255 - blue[i]));
			
			g2.fill(new Rectangle2D.Double(x, y, drawWidth, drawHeight));
			
			subtractColors(i);
    	}
	}

	@Override
	public void setRgb() {
		red = new int[sortSize];
		green = new int[sortSize];
		blue = new int[sortSize];
	}

	@Override
	protected void setBitonicAndMaxArraySizes() {
		bitonicSize = BITONIC_SIZE;
		maxSize = MAX_SIZE;
	}

	@Override
	public void setOtherSizes() {
		barWidth = (double) sv.getWidth() / sortSize;
		barHeight = (double) sv.getHeight() / sortSize;
	}

	@Override
	public String getName() {
		return "BAR GRAPH";
	}

}
