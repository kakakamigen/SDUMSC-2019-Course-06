/**
 * @class: Label.java 
 * @extends: javax.swing.JLabel
 * @author: Kamigen
 * @submit: 2019/11/22
 * @description: Superclass Label of labels
 **/
package org.sdumsc.course201907.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import org.sdumsc.course201907.controller.GraphicsController;
import org.sdumsc.course201907.ui.interfaces.Controllable;;

@SuppressWarnings("serial")
public class Label extends JLabel implements Controllable, MouseListener{

	private int radius = 0;
	private int shade = 0;
	SimpleDateFormat showTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String time = showTime.format(new Date());
	private String text = "";
	private boolean isClicked = false;
	
	public Label(){
		super();
		this.initialize(0, 0, 50, 50, "Default");
	}
	
	public Label(int x, int y, int width, int height, String text) {
		super();
		this.initialize(x, y, width, height, text);
	}

	protected void initialize(int x, int y, int width, int height, String text) {
		this.text = text;
		this.setOpaque(false);
        this.setBackground(GraphicsController.DEFAULT_COLOR);
        
        this.setBounds(x, y, width, height);
        this.setText(text + time);
	}
	
	public void update() {
		if(isClicked) 
			showTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");//12小时制
		else
			showTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
		this.time = showTime.format(new Date());
		this.setText(this.text + time);
	}
	
	public void mouseClicked(MouseEvent e) {
		isClicked = !isClicked;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
		this.resetBorder();
	}
	
	public void setShade(int shade) {
		int delta = shade - this.shade;
		this.setBounds(this.getX() - delta, this.getY() - delta, this.getWidth() + delta*2, this.getHeight() + delta * 2);
		this.shade = shade;
		this.resetBorder();
	}
	
	public void resetBorder() {
		int border = this.radius + this.shade + 5;
		this.setBorder(BorderFactory.createEmptyBorder(0, border, 0, border));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		GraphicsController.setHint(graphics);
		this.paint(graphics);
        super.paintComponent(g);
    }

	protected void paint(Graphics2D graphics) {
		this.paintShade(graphics);
		this.paintBackground(graphics);
	}
	
	protected void paintShade(Graphics2D graphics) {
		int i = 0;
		if(this.shade != 0) {
			graphics.setColor(new Color(0, 0, 0, GraphicsController.SHADE_ALPHA / this.shade));
			for(i = 0;i < this.shade;i++) {
				graphics.fillRoundRect(i, i, this.getWidth() - i*2, this.getHeight() - i*2, (this.radius + this.shade - i) * 2, (this.radius + this.shade - i) * 2);
			}
		}
	}
	
	protected void paintBackground(Graphics2D graphics) {
		graphics.setColor(this.getBackground());
		graphics.fillRoundRect(this.shade, this.shade, this.getWidth() - this.shade * 2, this.getHeight() - this.shade * 2, this.radius * 2, this.radius * 2);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
