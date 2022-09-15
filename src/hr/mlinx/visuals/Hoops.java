package hr.mlinx.visuals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.util.Util;

public class Hoops extends Visual {
	
	private static final int MAX_SIZE = 512;
	private static final double BASE_STROKE = 1.1 * Util.SCALE;
	private static final double RADIUS_RATIO = 14.0 / 15.0;
	
	private boolean isDisparity;
	private float colorStart;
	private double stroke;

	public Hoops(TopPanel tp, SortVisualiser sv, BottomPanel bp, boolean isDisparity) {
		super(tp, sv, bp);
		
		this.isDisparity = isDisparity;
		colorStart = Util.R.nextFloat();
		stroke = BASE_STROKE * MAX_SIZE / DEFAULT_SIZE;
	}

	@Override
	public void visualise(Graphics2D g2) {
		g2.setStroke(new BasicStroke((float) stroke));
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, 
				  			RenderingHints.VALUE_STROKE_PURE);
		
		int dist = 0;
		double disparity = 0.0;
		double size;
		
		double maxHoopSize = RADIUS_RATIO * Math.min(sv.getWidth() - stroke, sv.getHeight() - stroke);
    	
    	for (int i = sortSize - 1; i >= 0; --i) {
    		try {
        		g2.setColor(Color.getHSBColor((float) array[i] / sortSize + colorStart, 1.0f, 1.0f));
        		
        		if (array[i] < sv.getAnimator().getSorting().getConfirmIdx()
        			&& sv.getAnimator().getSorting().getConfirmIdx() != -1)
        			g2.setColor(Color.GREEN);
        		
        		disparity = 1.0;
        		if (isDisparity) {
        			dist = Math.abs(i - array[i]);
        			disparity -= (double) Math.min(dist, sortSize - dist)
        						 / ((double) sortSize / 2.0);
        		}
        		
        		size = ((i + 1) / (double) sortSize) * maxHoopSize;
        		
        		g2.draw(new Ellipse2D.Double((sv.getWidth() - size * disparity) / 2.0, 
        									 (sv.getHeight() - size) / 2.0, 
        									 size * disparity, 
        									 size * disparity));
    		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
    			e.printStackTrace();
    		}
    	}
	}

	@Override
	public void setRgb() {
	}

	@Override
	protected void setBitonicAndMaxArraySizes() {
		bitonicSize = MAX_SIZE;
		maxSize = MAX_SIZE;
	}

	@Override
	public void setOtherSizes() {
		stroke = BASE_STROKE * MAX_SIZE / sortSize;
	}

	@Override
	public String getName() {
		return (isDisparity ? "DISPARITY " : "") + "HOOPS";
	}
	
}
