package hr.mlinx.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public class Stats {
	
	private static final Color STATS_COLOR = new Color(0, 200, 200);
	
	private int swaps;
	private int comparisons;
	private int accesses;
	
	public Stats() {
		super();
		
		resetStats();
	}
	
	public int getSwaps() {
		return swaps;
	}
	
	public int getComparisons() {
		return comparisons;
	}
	
	public int getAccesses() {
		return accesses;
	}
	
	public void addSwaps(int added) {
		swaps += added;
	}
	
	public void addComps(int added) {
		comparisons += added;
	}
	
	public void addAccesses(int added) {
		accesses += added;
	}
	
	public void resetStats() {
		swaps = 0;
		comparisons = 0;
		accesses = 0;
	}
	
	public void drawStats(Graphics2D g2, TopPanel tp, SortVisualiser sv) {
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(STATS_COLOR);
		g2.setFont(new Font("Helvetica", Font.PLAIN, (int) (Util.FONT_SIZE + 3 * Util.SCALE)));
		
		g2.drawString((String) (tp.getAlgoBox().getSelectedItem()) 
				+ (!sv.getAnimator().getWatching().isAlive() ? "" : " (#" 
						+ Integer.toString(tp.getAlgoBox().getSelectedIndex() + 1) 
						+ " of " + sv.getSorts().length + ")"), 
				10f, 
				tp.getHeight() / 2f);
		g2.drawString("Array Size: " + Integer.toString(sv.getVc().get().getSortSize()), 
				10f, 
				tp.getHeight() / 2f + g2.getFont().getSize());
		g2.drawString("Swaps: " + Integer.toString(swaps), 
				10f, 
				tp.getHeight() / 2f + 2 * (g2.getFont().getSize()));
		g2.drawString("Comparisons: " + Integer.toString(comparisons), 
				10f, 
				tp.getHeight() / 2f + 3 * (g2.getFont().getSize()));
		g2.drawString("Array Accesses: " + Integer.toString(accesses), 
				10f, 
				tp.getHeight() / 2f + 4 * (g2.getFont().getSize()));
	}
	
}
