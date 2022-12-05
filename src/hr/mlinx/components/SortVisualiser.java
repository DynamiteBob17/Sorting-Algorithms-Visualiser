package hr.mlinx.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import hr.mlinx.handlers.Animator;
import hr.mlinx.handlers.KeyInput;
import hr.mlinx.handlers.VisualController;
import hr.mlinx.sorting_algorithms.SortingAlgorithm;
import hr.mlinx.util.SoundPlayer;
import hr.mlinx.util.Stats;
import hr.mlinx.util.Util;
import hr.mlinx.visuals.Visual;

public class SortVisualiser extends JPanel {
	private static final long serialVersionUID = -381768546438319968L;
	
	private static final int DEFAULT_SLEEP = 2;
	private static final double INITIAL_MINIMIZED_RATIO = 0.8;
	private static final Color BACKGROUND_COLOR = new Color(25, 25, 25);
	
	private int[] array;
	private double sleep;
	private VisualController visualController;
	private Stats stats;
	private SortingAlgorithm[] sorts;
	private TopPanel topPanel;
	private SoundPlayer soundPlayer;
	private BottomPanel bottomPanel;
	private Animator animator;
	
	private boolean isUnix;
	private Toolkit tk;
	
	public SortVisualiser() {
		super();
		
		Util.setUIPresets();
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(new Dimension((int) (Util.RES.width * INITIAL_MINIMIZED_RATIO),
									   (int) (Util.RES.height * INITIAL_MINIMIZED_RATIO)));
		
		array = new int[Visual.INPUT_MAX_SIZE];
		sleep = DEFAULT_SLEEP;
		visualController = new VisualController(null, this, bottomPanel);
		stats = new Stats();
		sorts = Util.fillSorts(this);
		topPanel = new TopPanel(this);
		visualController.setTopPanels(topPanel);
		soundPlayer = new SoundPlayer();
		bottomPanel = new BottomPanel(this);
		animator = new Animator(topPanel, this, bottomPanel);
		isUnix = Util.isUnix();
		if (isUnix) tk = Toolkit.getDefaultToolkit();
		fillArray();
		
		FrameCreator.createFrame("Sorting Algorithm Visualiser", topPanel, this, bottomPanel,
							new ComponentAdapter() {
			   					@Override
			   					public void componentResized(ComponentEvent e) {
			   						visualController.getCurrentVisual().setOtherSizes();
			   					}
							}
		);
		addKeyListener(new KeyInput(topPanel, this, bottomPanel));
		requestFocus();
	}
	
	public void fillArray() {
		for (int i = 0; i < array.length; ++i) {
			array[i] = i;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		visualController.visualise(g2);
		stats.drawStats(g2, topPanel, this);
		if (isUnix) tk.sync();
	}
	
	public void update(double sleep) {
		repaint();
		
		long nano = (long) (sleep * 1_000_000.0);
		long start = System.nanoTime();
	    long end = 0;
	    do{
	        end = System.nanoTime();
	    } while (start + nano >= end);
	}
	
	public void setSingle(int idx, int value) {
		array[idx] = value;
		
		if (visualController.getIdx() == 0) { // 0 should be barGraph
			if (animator.getSorting().isAlive()) {
				if (animator.getSorting().getConfirmIdx() == -1) {
					visualController.getCurrentVisual().setGreen(idx,  Visual.BAR_COLOR_INTENSITY); // red
					visualController.getCurrentVisual().setBlue(idx,  Visual.BAR_COLOR_INTENSITY);
				} else {
					visualController.getCurrentVisual().setRed(idx,  Visual.BAR_COLOR_INTENSITY); // green
					visualController.getCurrentVisual().setBlue(idx,  Visual.BAR_COLOR_INTENSITY);
				}
			} else if (animator.getShuffling().isAlive()) { // blue
				visualController.getCurrentVisual().setRed(idx,  Visual.BAR_COLOR_INTENSITY);
				visualController.getCurrentVisual().setGreen(idx, Visual.BAR_COLOR_INTENSITY);
			}
		}
		
		soundPlayer.play(idx, visualController.getCurrentVisual().getSortSize());
		update(sleep);
	}
	
	public void swap(int firstIdx, int secondIdx) {
		if (animator.getSorting().isAlive())
			stats.addSwaps(1);
		
		int temp = array[firstIdx];
		setSingle(firstIdx, array[secondIdx]);
		setSingle(secondIdx, temp);
	}
	
	public void highlight(int idx) {
		setSingle(idx, array[idx]);
	}
	
	public void finishAnimation() {
		visualController.setRgb();
		repaint();
		soundPlayer.notesOff();
	}
	
	public void reset() {
		if (!animator.getAnimation().isAlive()) {
			validateArray();
		}
		
		visualController.getCurrentVisual().setOtherSizes();
		
		if (!topPanel.sizeLocked()) {
			fillArray();
			stats.resetStats();
		}
		
		visualController.setRgb();
		repaint();
	}
	
	private void validateArray() {
		int maxValue = visualController.getCurrentVisual().getSortSize() - 1;
		
		for (int i = 0; i < array.length; ++i) {
			if (array[i] > maxValue) {
				for (int j = i; j < array.length; ++j) {
					if (array[j] <= maxValue) {
						int temp = array[i];
						array[i] = array[j];
						array[j] = temp;
						break;
					}
				}
			}
		}
	}
	
	public int[] getArray() {
		return array;
	}
	
	public void setArrayElement(int idx, int value) {
		array[idx] = value;
	}
	
	public double getSleep() {
		return sleep;
	}
	
	public void setSleep(double sleep) {
		this.sleep = sleep;
	}
	
	public Animator getAnimator() {
		return animator;
	}
	
	public VisualController getVisualController() {
		return visualController;
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public SoundPlayer getSoundPlayer() {
		return soundPlayer;
	}
	
	public SortingAlgorithm[] getSorts() {
		return sorts;
	}
	
	public static void main(String[] args) {
		if (Util.isUnix()) {
			System.setProperty("sun.java2d.opengl", "true");
		}
		
		new SortVisualiser();
	}
	
}
