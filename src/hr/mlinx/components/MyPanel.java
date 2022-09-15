package hr.mlinx.components;

import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import hr.mlinx.util.Util;

public abstract class MyPanel extends JPanel {
	private static final long serialVersionUID = 8439029238710876152L;
	
	protected SortVisualiser sv;
	protected boolean active;
	
	public MyPanel(SortVisualiser sv) {
		super();
		
		this.sv = sv;
		active = true;
	}
	
	public abstract void toggle();
	
	public boolean isActive() {
		return active;
	}
	
	protected void loseFocus(Component ... comps1) {
		for (Component c1 : comps1) {
			if (c1 instanceof JSpinner) {
				Component[] comps2 = ((JSpinner) c1).getEditor().getComponents();
				for (Component c2 : comps2) {
				    c2.setFocusable(false);
				}
			} else {
				c1.setFocusable(false);
			}
		}
	}
	
	protected void addMultiple(Component ... comps) {
		for (Component c : comps) {
			add(c);
		}
	}
	
	protected ImageIcon createIcon(String fileName) {
		try (InputStream is = MyPanel.class.getResourceAsStream("/" + fileName)) {
			ImageIcon icon = new ImageIcon(ImageIO.read(is));
			Image image = icon.getImage();
			Image newImage = image.getScaledInstance((int) (25 * Util.SCALE),
													 (int) (25 * Util.SCALE),
													 Image.SCALE_SMOOTH);
			return new ImageIcon(newImage);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
