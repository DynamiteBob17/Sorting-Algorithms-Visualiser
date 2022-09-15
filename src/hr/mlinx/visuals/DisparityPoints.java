package hr.mlinx.visuals;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.util.Util;

public class DisparityPoints extends Visual {
	
	private static final int BITONIC_SIZE = 2048;
	private static final int MAX_SIZE = 1920;
	private static final double RADIUS_RATIO = 19.0 / 20.0;
	private static final double STROKE = 12.0 * Util.SCALE;
	private static final double LINKED_STROKE = 2.0 * Util.SCALE;
	
	private boolean linked;
	private double stroke;
	
	public DisparityPoints(TopPanel tp, SortVisualiser sv, BottomPanel bp, boolean linked) {
		super(tp, sv, bp);
		
		this.linked = linked;
		stroke = STROKE;
	}

	@Override
	public void visualise(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		
		double h = (double) sv.getWidth() / 2.0;
		double k = (double) sv.getHeight() / 2.0;
		double r = Math.min(h - stroke, k - stroke) * RADIUS_RATIO;
		
		int dist = Math.abs(0 - array[0]);
		double disparity = 1.0 - ((double) Math.min(dist, sortSize - dist)
				/ ((double) sortSize / 2.0));
		
		double phi = 2.0 * Math.PI * 0 / sortSize - (Math.PI / 2.0);
		double x = h + (r * disparity) * Math.cos(phi);
		double y = k + (r * disparity) * Math.sin(phi);
		
		double firstX = x;
		double firstY = y;
		
		double prevX = x, prevY = y;
		
		try {
			for (int i = 0; i < sortSize; ++i) {
				g2.setColor(Color.getHSBColor((float) array[i] / sortSize, 1.0f, 1.0f));
        		g2.setStroke(new BasicStroke((float) stroke));
        		
        		if (array[i] < sv.getAnimator().getSorting().getConfirmIdx()
            			&& sv.getAnimator().getSorting().getConfirmIdx() != -1) {
            			g2.setColor(Color.GREEN);
            	}
        		
        		dist = Math.abs(i - array[i]);
        		disparity = 1.0 - ((double) Math.min(dist, sortSize - dist)
        				/ ((double) sortSize / 2.0));
        		
        		phi = 2.0 * Math.PI * i / sortSize - (Math.PI / 2.0);
				x = h + (r * disparity) * Math.cos(phi);
				y = k + (r * disparity) * Math.sin(phi);
				g2.fill(new Ellipse2D.Double(x - stroke / 2.0,
						y - stroke / 2.0, 
						stroke,
						stroke));
				
				if (linked) {
					g2.setColor(Color.getHSBColor((float) array[i] / sortSize, 1.0f, 1.0f));
				}
				
				if (array[i] < sv.getAnimator().getSorting().getConfirmIdx()
	        			&& sv.getAnimator().getSorting().getConfirmIdx() != -1) {
	        			g2.setColor(Color.GREEN);
	        			g2.setStroke(new BasicStroke((float)(2.0 * stroke)));
				}
				
				if (linked) {
					g2.draw(new Line2D.Double(x, y, prevX, prevY));
					
					prevX = x;
					prevY = y;
				}
			}
			if (linked) {
				g2.setColor(Color.getHSBColor((float) array[sortSize - 1] / sortSize, 1.0f, 1.0f));
				g2.draw(new Line2D.Double(x, y, firstX, firstY));
			}
		} catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRgb() {
	}

	@Override
	protected void setBitonicAndMaxArraySizes() {
		bitonicSize = BITONIC_SIZE;
		maxSize = MAX_SIZE;
	}

	@Override
	public void setOtherSizes() {
		if (linked) {
			if (sortSize >= 1024) { // from 1024 to INPUT_MAX_SIZE scale stroke from LINKED_STROKE down to 1.0
				stroke = LINKED_STROKE - (sortSize - 1024.0) / (INPUT_MAX_SIZE - 1024.0);
			} else {
				stroke = LINKED_STROKE;
			}
		} else {
			stroke = STROKE;
		}
	}

	@Override
	public String getName() {
		return (linked ? "LINKED " : "") + "DISPARITY POINTS";
	}

}
