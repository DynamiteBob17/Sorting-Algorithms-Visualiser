package hr.mlinx.components;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import hr.mlinx.util.SoundPlayer;
import hr.mlinx.util.Util;

public class BottomPanel extends MyPanel {
	private static final long serialVersionUID = 2610692738395814756L;
	
	public static final double SPINNER_MIN_VALUE = 0.01;
	public static final double SPINNER_MAX_VALUE = (double) Integer.MAX_VALUE;
	
	private JButton shuffleButton;
	private JButton sortButton;
	private JLabel sleepLabel;
	private JSpinner sleepSpinner;
	private JLabel midiLabel;
	private JComboBox<String> midiBox;
	private JButton volumeControlButton;
	private JSlider volumeControlSlider;
	
	private ImageIcon volumeIcon;       // icon made with VistaCreate™ at create.vista.com
	private ImageIcon mutedVolumeIcon;  // icon made with VistaCreate™ at create.vista.com
	private int prevVolume;
	
	public BottomPanel(SortVisualiser sv) {
		super(sv);
		
		shuffleButton = new JButton("SHUFFLE");
		sortButton = new JButton("SORT");
		sleepLabel = new JLabel("SLEEP:", SwingConstants.RIGHT);
		sleepSpinner = new JSpinner();
		midiLabel = new JLabel("SOUNDS:", SwingConstants.RIGHT);
		midiBox = new JComboBox<>();
		volumeIcon = createIcon("volume.png");
		mutedVolumeIcon = createIcon("mutedVolume.png");
		prevVolume = sv.getSoundPlayer().getVolume();
		volumeControlButton = new JButton(prevVolume > 0 ?
										  volumeIcon : mutedVolumeIcon);
		volumeControlButton.setBackground(this.getBackground());
		volumeControlButton.setBorderPainted(false);
		volumeControlSlider = new JSlider(0, 100, SoundPlayer.DEFAULT_VOLUME);
		
		sleepSpinner.setModel(new SpinnerNumberModel(sv.getSleep(), SPINNER_MIN_VALUE, 
				  SPINNER_MAX_VALUE, SPINNER_MIN_VALUE));
		midiBox.setModel(new DefaultComboBoxModel<String>(sv.getSoundPlayer().getSounds()));
		
		shuffleButton.addActionListener(e -> {
			sv.getAnimator().shuffle();
		});
		sortButton.addActionListener(e -> {
			sv.getAnimator().sort();
		});
		sleepSpinner.addChangeListener(e -> {
			sv.setSleep((double) sleepSpinner.getValue());
		});
		midiBox.addActionListener(e -> {
			sv.getSoundPlayer().changeMidi(midiBox.getSelectedIndex());
		});
		volumeControlButton.addActionListener(e -> {
			toggleVolumeControlButton();
		});
		volumeControlSlider.addChangeListener(e -> {
			if (sv.getSoundPlayer().getVolume() > 0 && volumeControlSlider.getValue() == 0
				|| sv.getSoundPlayer().getVolume() == 0 && volumeControlSlider.getValue() > 0) {
				toggleVolumeControlButton();
			} else {
				sv.getSoundPlayer().setVolume(volumeControlSlider.getValue());	
			}
		});
		
		loseFocus(shuffleButton, sortButton, sleepSpinner, midiBox,
				  volumeControlButton, volumeControlSlider);
		
		sleepSpinner.setPreferredSize(new Dimension(
				(int) (90 * Util.SCALE),
				(int) (26 * Util.SCALE)));
		volumeControlSlider.setPreferredSize(new Dimension(
				(int) (200 * Util.SCALE),
				(int) (26 * Util.SCALE)));
		
		addMultiple(shuffleButton, sortButton, sleepLabel, sleepSpinner, midiLabel, midiBox);
		addMultiple(volumeControlButton, volumeControlSlider);
	}

	@Override
	public void toggle() {
		active = active ? false : true;
		
		shuffleButton.setEnabled(active);
		sortButton.setEnabled(active);
	}
	
	private void toggleVolumeControlButton() {
		if (sv.getSoundPlayer().getVolume() > 0) {
			prevVolume = sv.getSoundPlayer().getVolume();
			sv.getSoundPlayer().setVolume(0);
			sv.getSoundPlayer().notesOff();
			volumeControlButton.setIcon(mutedVolumeIcon);
		} else if (sv.getSoundPlayer().getVolume() == 0 && prevVolume != 0) {
			sv.getSoundPlayer().setVolume(prevVolume);
			volumeControlButton.setIcon(volumeIcon);
		}
	}
	
	public JButton getShuffleButton() {
		return shuffleButton;
	}

	public JSpinner getSleepSpinner() {
		return sleepSpinner;
	}

	public JSlider getVolumeControlSlider() {
		return volumeControlSlider;
	}

	public JButton getVolumeControlButton() {
		return volumeControlButton;
	}

	public JButton getSortButton() {
		return sortButton;
	}
	
}
