package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class VIAPanel extends JPanel{

	private Color color1 = Color.ORANGE;
	private Color color2 = Color.WHITE;
	
	public VIAPanel() {
		super();
	};
	
	public VIAPanel(LayoutManager layout) {
		super(layout);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		
		GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
		g2.setPaint(gp);
		
		g2.fillRect(0, 0, width, height);
	}	
}
