package com.maxNiebergall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Base{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	
	Base(){
		Graph graph = new Graph(new FunctionObject("sin(x)", new variablesObject[]{new variablesObject("amplitude", 20, 'a'), new variablesObject("phase shift", 0, 'p'), new variablesObject("frequency", 2, 'f')}, "y=x+a", "DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE;DEFAULT SUMMARY TEXT HERE"));
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		JTextArea summary = new JTextArea(graph.getFo().getSummary());
		JSlider sliders[] = new JSlider[graph.getFo().getVariables().size()];
		JPanel sliderPane = new JPanel(new BorderLayout());
		JLabel label[] = new JLabel[graph.getFo().getVariables().size()];
		for(int i=0; i<sliders.length; i++){
			sliders[i]= new JSlider(SwingConstants.VERTICAL, 0, graph.getScope(), graph.getFo().getVariables().get(i).getValueOfVariable());
		}
		for(int i=0; i<label.length; i++){
			label[i]= new JLabel(graph.getFo().getVariables().get(i).getNameOfVariable());
			label[i].setOpaque(true);
		}
		sliderPane.setOpaque(true);
		sliderPane.setBackground(Color.cyan);
		for(int i=0; i<label.length; i++){
			sliderPane.add(label[i], BorderLayout.NORTH);
		}
		for(int i=0; i<sliders.length; i++){
			sliderPane.add(sliders[i], BorderLayout.SOUTH);
		}
			
		
		frame.setSize(800, 600);
		frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        graph.setScope(200);
        graph.setBackground(Color.GREEN);
        graph.setVisible(true);
        graph.setSize(200,200);
        
		// frame window closer
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		frame.add(graph);
        frame.add(sliderPane);
        frame.validate();
		frame.repaint();
        
	}
	
}
