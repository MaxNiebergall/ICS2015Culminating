package com.maxNiebergall;

import java.awt.Color;
import java.awt.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Base{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	
	Base(){
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		frame.setSize(400, 600);
		frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        Graph graph = new Graph(new FunctionObject("sin(x)", new variablesObject[]{new variablesObject("amplitude", 20, 'a'), new variablesObject("phase shift", 0, 'p'), new variablesObject("frequency", 2, 'f')}, "y=a*Math.sin(x-p)"));
        graph.setScope(100);
        
        graph.setBackground(Color.WHITE);
        graph.setVisible(true);
        
		// frame window closer
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		//pane.add(graph);
		frame.add(graph);
		frame.pack();
        frame.add(pane);
		frame.validate();
		frame.repaint();
        
	}
	
}
