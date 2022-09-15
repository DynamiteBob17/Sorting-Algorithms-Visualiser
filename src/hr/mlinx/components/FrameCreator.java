package hr.mlinx.components;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameCreator {
	
	public static void createFrame(String title, TopPanel tp, SortVisualiser sv, BottomPanel bp, ComponentAdapter ca) {
		JFrame frame = new JFrame(title);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponentListener(ca);
		frame.setLayout(new BorderLayout());
		frame.add(tp, BorderLayout.NORTH);
		frame.add(sv, BorderLayout.CENTER);
		frame.add(bp, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		message();
	}
	
	public static void message() {
		JOptionPane.showMessageDialog(null, "*WARNING*\n\r"
				+ "The following contains bright, flashing lights that may cause discomfort and/or "
				+ "seizures for those with photosensitive epilepsy!\n\r"
				+ "Viewer discretion is advised!\n\r"
				+ " \n\r"
				+ "Welcome!\n\r"
				+ " \n\r"
				+ "ABOUT THE PROGRAM AND TIPS ON HOW TO USE:\n\r"
				+ " \n\r"
				+ "-This is a sorting algorithm visualisation program with different visualisations.\n\r"
				+ " \n\r"
				+ "-\"SLEEP\" (no. of milliseconds) determines the speed of the sorting and shuffling such that smaller values increase the speed!\n\r"
				+ " \n\r"
				+ "-The \"WATCH\" button starts shuffling and sorting every algorithm from the currently selected one to the last one on the list."
				+ " Also, it plays Radix In-Place, Weave Merge, Heap and Bitonic Sorts twice - from a shuffled and then a sorted array.\n\r"
				+ " \n\r"
				+ "-You can also choose which MIDI instrument will be playing the sounds.\n\r"
				+ " \n\r"
				+ "-Key Bindings:\n\r"
				+ "W/S - previous/next sorting algorithm on the list,\n\r"
				+ "A/D - previous/next visualisation,\n\r"
				+ "R - shuffle,\n\r"
				+ "H - bring up this message window,\n\r"
				+ "M - mute/unmute sound\n\r"
				+ "L - lock/unlock array size\n\r"
				+ "SHIFT/CONTROL - increase/decrease array size\n\r"
				+ "BACKSPACE - directly input array size\n\r"
				+ "SPACE - sort,\n\r"
				+ "ENTER - directly input value of \"SLEEP\",\n\r"
				+ "Arrow Keys UP/DOWN - increase/decrease \"SLEEP\" value,\n\r"
				+ "Arrow Keys LEFT/RIGHT - decrease/increase sound volume.\n\r"
				+ " \n\r"
				+ "Enjoy!\n\r");
	}
	
}
