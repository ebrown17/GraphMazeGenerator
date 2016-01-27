package graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;


import graphUtil.GraphNode;

public class Base extends JFrame {	
	
    public Base(int tileSize,int width, int height,ArrayList<GraphNode> maze) {

        initUI(tileSize,width,height,maze);
    }

    private void initUI(final int tileSize,int width, int height,ArrayList<GraphNode> maze) {
    	Surface surface = new Surface(tileSize,width,height,maze);
    	
    	surface.addMouseMotionListener(new MouseAdapter() {
    		 public void mouseMoved(MouseEvent e) {    			 
    			 	setTitle("X : " + (e.getX()/tileSize) + " Y : " + e.getY()/tileSize);    		      
    		    }
    	});
    	
        add(surface);
        
        pack();               
        getContentPane().setPreferredSize(new Dimension(width,height));
        setTitle("Practice");
        pack();        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
}
