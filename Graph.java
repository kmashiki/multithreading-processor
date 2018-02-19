import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Graph extends JFrame{

	private DrawArea drawArea;
	private JPanel canvas;
	
	int [] heights = new int[25];

    public Graph(int[] nums) {
    	for(int i=0; i<25; i++)
    		heights[i] = nums[i];

		drawArea = new DrawArea();
		canvas = new JPanel();
		canvas.add(drawArea);
		drawArea.requestFocus();
		this.setContentPane(canvas);

    }
     
	public class DrawArea extends JPanel
	{
		private final int HEIGHT = 650;
		private final int WIDTH = 1050;
		private final int RIGHT_SHIFT = 50;
	
		public DrawArea()
	
		{
			this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
			this.setBackground(Color.WHITE);
		}
	
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			Font f = new Font("default", Font.BOLD, 14);
			
			g.setFont(f);
			g.setColor(Color.BLACK);
			
			//draw y Axes
			g.drawLine(RIGHT_SHIFT, 0, RIGHT_SHIFT, HEIGHT- 50);
			
			//draw y Axes
			g.drawLine(RIGHT_SHIFT, HEIGHT- 50, WIDTH-50, HEIGHT- 50);
			
			//draw ticks on Y axis
			for(int k=0; k<12; k++){
				if(k%2==1)
					g.drawLine(RIGHT_SHIFT-10, HEIGHT-50 - ((k+1)*50), 
							RIGHT_SHIFT+10, HEIGHT-50 - ((k+1)*50));
				else
					g.drawLine(RIGHT_SHIFT-5, HEIGHT-50 - ((k+1)*50), 
							RIGHT_SHIFT+5, HEIGHT-50 - ((k+1)*50));
				
				if(k!=11)
					g.drawString("" + ((k+1)*50), 0, HEIGHT-50 - ((k+1)*50)+5);
			}
		
			//writes height of bars
			g.drawString("" + 12*50, 0, HEIGHT-50 - (12*50) + 15);
			
			//draws bars
			for(int i=0; i<25; i++){
				g.drawRect(20 * (i) + (i*15) + RIGHT_SHIFT+20, 
						HEIGHT - heights[i] - 50, 10, heights[i]);
				g.fillRect(20 * (i) + (i*15) + RIGHT_SHIFT+20, 
						HEIGHT - heights[i] - 50, 10, heights[i]);
				
				g.drawString("" + heights[i], 20 * (i) + (i*15) + 
						RIGHT_SHIFT+12, HEIGHT - heights[i] - 60);
				
				if(i<10)
					g.drawString("" + (i+1), 20 * (i) + (i*15) +
							RIGHT_SHIFT+20, HEIGHT - 30);
				else
					g.drawString("" + (i+1), 20 * (i) + (i*15) + 
							RIGHT_SHIFT+20 - 4, HEIGHT - 30);
				
					
			}
	
		}
	
	}	

}
