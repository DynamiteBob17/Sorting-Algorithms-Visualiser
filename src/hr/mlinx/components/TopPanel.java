package hr.mlinx.components;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import hr.mlinx.util.Util;
import hr.mlinx.visuals.BarGraph;
import hr.mlinx.visuals.Visual;

public class TopPanel extends MyPanel {
	private static final long serialVersionUID = 4893574351273918541L;
	
	private JLabel algoLabel;
	private JComboBox<String> algoBox;
	private JLabel sizeLabel;
	private JSpinner sizeSpinner;
	private JButton lockSizeButton;
	private JButton leftButton;
	private JLabel modeLabel;
	private JButton rightButton;
	private JButton watchButton;
	
	private ImageIcon lockedIcon;   // https://icons8.com/icon/K5bOaoSkbg1n/locked-with-key
	private ImageIcon unlockedIcon; // https://icons8.com/icon/iUUaSEBDu3Rw/unlocked
	
	private boolean sizeLocked;
	private boolean endWatching;
	
	public TopPanel(SortVisualiser sv) {
		super(sv);
		
		endWatching = false;
		sizeLocked = false;
		
		algoLabel = new JLabel("SORTS:", SwingConstants.RIGHT);
		algoBox = new JComboBox<>();
		sizeLabel = new JLabel("SIZE:", SwingConstants.RIGHT);
		sizeSpinner = new JSpinner();
		lockedIcon = createIcon("locked.png");
		unlockedIcon = createIcon("unlocked.png");
		lockSizeButton = new JButton(sizeLocked ? 
						 			 lockedIcon :
						 		     unlockedIcon);
		leftButton = new JButton("<");
		modeLabel = new JLabel(sv.getVc().get().getName(), SwingConstants.CENTER);
		rightButton = new JButton(">");
		watchButton = new JButton("WATCH");
		
		String[] sorts = new String[sv.getSorts().length];
		for (int i = 0; i < sorts.length; ++i) {
			sorts[i] = sv.getSorts()[i].getName();
		}
		algoBox.setModel(new DefaultComboBoxModel<String>(sorts));
		sizeSpinner.setModel(new SpinnerNumberModel(sv.getVc().get().getSortSize(), 1, 
				  Visual.INPUT_MAX_SIZE, 1));
		
		algoBox.addActionListener(e -> {
			sv.getVc().changeVals();
			sv.reset();
		});
		sizeSpinner.addChangeListener(e -> {
			updateForSizeSpinner();	
		});
		lockSizeButton.addActionListener(e -> {
			if (sizeLocked) {
				sizeLocked = false;
				lockSizeButton.setIcon(unlockedIcon);
				sizeSpinner.setEnabled(true);
			} else {
				sizeLocked = true;
				lockSizeButton.setIcon(lockedIcon);
				sizeSpinner.setEnabled(false);
				sv.getVc().setLockedSize();
			}
		});
		leftButton.addActionListener(e -> {
			sv.getVc().previous();
			modeLabel.setText(sv.getVc().get().getName());
			sv.getVc().changeVals();
			sv.reset();
		});
		rightButton.addActionListener(e -> {
			sv.getVc().next();
			modeLabel.setText(sv.getVc().get().getName());
			sv.getVc().changeVals();
			sv.reset();
		});
		watchButton.addActionListener(e -> {
			if (watchButton.getText().equals("WATCH"))
				sv.getAnimator().watch();
			else if (watchButton.getText().equals("END WATCHING")) {
				endWatching = true;
				sv.setSleep(0);
			}
		});
		
		loseFocus(algoBox, sizeSpinner, lockSizeButton, leftButton,
				  rightButton, watchButton);
		
		sizeSpinner.setPreferredSize(new Dimension(
				(int) (75 * Util.SCALE),
				(int) (26 * Util.SCALE)));
		modeLabel.setPreferredSize(new Dimension(
				(int) (210 * Util.SCALE),
				(int) (26 * Util.SCALE)));
		
		addMultiple(algoLabel, algoBox, sizeLabel, sizeSpinner, lockSizeButton);
		addMultiple(leftButton, modeLabel, rightButton, watchButton);
	}

	public void toggle() {
		active = active ? false : true;
		
		algoBox.setEnabled(active);
		if (!sizeLocked)
			sizeSpinner.setEnabled(active);
		lockSizeButton.setEnabled(active);
		if (watchButton.getText().equals("WATCH"))
			watchButton.setEnabled(active);
		if (!sizeLocked) {
			leftButton.setEnabled(active);
			rightButton.setEnabled(active);
		}
	}
	
	public void toggleWatch() {
		watchButton.setText(watchButton.getText().equals("WATCH") ? "END WATCHING" : "WATCH");
	}
	
	public void updateForSizeSpinner() {
		int val = (int) sizeSpinner.getValue();
		int size = sv.getVc().get().getSortSize();
		if (algoBox.getSelectedItem().equals("Bitonic Sort")) {
			if (size <= BarGraph.BITONIC_SIZE) {
				if (val > size && size < BarGraph.BITONIC_SIZE) {
					val = size * 2;
					sv.getVc().get().setSortSize(val);
					sizeSpinner.setValue(val);
				} else if (size > val) {
					val = size / 2;
					sv.getVc().get().setSortSize(val);
					sizeSpinner.setValue(val);
				} else {
					val = size;
				}
				sv.getVc().get().setSortSize(val);
				sizeSpinner.setValue(val);
				sv.reset();
			}
		} else {
			sv.getVc().get().setSortSize(val);
			sv.reset();
		}
	}
	
	public boolean sizeLocked() {
		return sizeLocked;
	}
	
	public boolean endWatching() {
		return endWatching;
	}

	public JComboBox<String> getAlgoBox() {
		return algoBox;
	}

	public JSpinner getSizeSpinner() {
		return sizeSpinner;
	}

	public void setEndWatching(boolean endWatching) {
		this.endWatching = endWatching;
	}

	public JButton getLeftButton() {
		return leftButton;
	}

	public JButton getRightButton() {
		return rightButton;
	}

	public JButton getLockSizeButton() {
		return lockSizeButton;
	}
	
}
