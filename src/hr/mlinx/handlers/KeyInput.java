package hr.mlinx.handlers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.FrameCreator;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.visuals.Visual;

public class KeyInput extends KeyAdapter {
	
	private TopPanel tp;
	private SortVisualiser sv;
	private BottomPanel bp;
	
	public KeyInput(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super();
		
		this.tp = tp;
		this.sv = sv;
		this.bp = bp;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_H) {
			FrameCreator.message();
		} else if (key == KeyEvent.VK_ENTER) {
			try {
				double sleep = Double.parseDouble(JOptionPane.showInputDialog(
						"Enter \"Sleep\" value (" 
					    + BottomPanel.SPINNER_MIN_VALUE 
					    + " - " 
					    + (int) BottomPanel.SPINNER_MAX_VALUE 
					    +"): ", 
					    (float) sv.getSleep()));
				if (sleep >= BottomPanel.SPINNER_MIN_VALUE && sleep <= BottomPanel.SPINNER_MAX_VALUE) {
					sv.setSleep(sleep);
					bp.getSleepSpinner().setValue(sleep);
				}
			} catch (NumberFormatException | NullPointerException ex) {
			}
		} else if (key == KeyEvent.VK_UP) {
			if (sv.getSleep() < BottomPanel.SPINNER_MAX_VALUE) {
				sv.setSleep(sv.getSleep() + BottomPanel.SPINNER_MIN_VALUE);
				bp.getSleepSpinner().setValue(sv.getSleep());	
			}
		} else if (key == KeyEvent.VK_DOWN) {
			/*
			 * sometimes you would be able to go below min. value 
			 * because sleep would for example be 0.0100000000001 
			 * or something which is bigger than 0.01 so you could go to 0
			 * which is not intended so that's why they're rounded first here;
			 * haven't yet encountered these problems elsewhere
			 */
			if (Math.round(sv.getSleep() * 100.0) > Math.round(BottomPanel.SPINNER_MIN_VALUE * 100.0)) {
				sv.setSleep(sv.getSleep() - BottomPanel.SPINNER_MIN_VALUE);
				bp.getSleepSpinner().setValue(sv.getSleep());
			}
		} else if (key == KeyEvent.VK_RIGHT) {
			bp.getVolumeControlSlider().setValue(bp.getVolumeControlSlider().getValue() + 1);
		} else if (key == KeyEvent.VK_LEFT) {
			bp.getVolumeControlSlider().setValue(bp.getVolumeControlSlider().getValue() - 1);
		} else if (key == KeyEvent.VK_M) {
			bp.getVolumeControlButton().doClick();
		}
		
		if (tp.isActive() && bp.isActive()) {
			if (key == KeyEvent.VK_R) {
				bp.getShuffleButton().doClick();
			} else if (key == KeyEvent.VK_SPACE) {
				bp.getSortButton().doClick();
			} else if (key == KeyEvent.VK_W) {
				JComboBox<String> as = tp.getAlgoBox();
				int selection = as.getSelectedIndex();
				if (selection == 0) {
					as.setSelectedItem(sv.getSorts()[sv.getSorts().length - 1].getName());
				} else {
					as.setSelectedIndex(selection - 1);
				}
			} else if (key == KeyEvent.VK_S) {
				JComboBox<String> as = tp.getAlgoBox();
				int selection = as.getSelectedIndex();
				if (selection == (sv.getSorts().length - 1)) {
					as.setSelectedItem(sv.getSorts()[0].getName());
				} else {
					as.setSelectedIndex(selection + 1);
				}
			} else if (key == KeyEvent.VK_A) {
				new Thread(() -> {
					tp.getLeftButton().doClick();
				}).start();
			} else if (key == KeyEvent.VK_D) {
				new Thread(() -> {
					tp.getRightButton().doClick();
				}).start();
			} else if (key == KeyEvent.VK_L) {
				tp.getLockSizeButton().doClick();
			}
			
			if (!tp.sizeLocked()) {
				if (key == KeyEvent.VK_2) {
					if ((int) tp.getSizeSpinner().getValue() < Visual.INPUT_MAX_SIZE)
						tp.getSizeSpinner().setValue((int) tp.getSizeSpinner().getValue() + 1);
				} else if (key == KeyEvent.VK_1) {
					if ((int) tp.getSizeSpinner().getValue() > 1)
						tp.getSizeSpinner().setValue((int) tp.getSizeSpinner().getValue() - 1);
				} else if (key == KeyEvent.VK_BACK_SPACE) {
					try {
						int len = Integer.parseInt(JOptionPane.showInputDialog(
								"Enter array size: (" 
							    + 1
							    + " - " 
							    + Visual.INPUT_MAX_SIZE
							    +"): ", 
							    (int) sv.getVisualController().getCurrentVisual().getSortSize()));
						if (len >= 1 && len <= Visual.INPUT_MAX_SIZE) {
							tp.getSizeSpinner().setValue(len);
							tp.updateForSizeSpinner();
						}	
					} catch (NumberFormatException | NullPointerException ex) {
					}
				}
			}
		} else {
			if (key == KeyEvent.VK_A) {
				new Thread(() -> {
					tp.getLeftButton().doClick();
				}).start();
			} else if (key == KeyEvent.VK_D) {
				new Thread(() -> {
					tp.getRightButton().doClick();
				}).start();
			}
		}
	}
	
}
