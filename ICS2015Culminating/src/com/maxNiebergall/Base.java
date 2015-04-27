package com.maxNiebergall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Base implements ActionListener, ChangeListener{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	VariablesObject vo[];
	JSlider sliders[];
	JFrame frame;
	
	Base(){
		int graphScope=200;
		vo = new VariablesObject[2];
		vo[0] = new VariablesObject("Multiplyer", 1, 'm', -graphScope, graphScope);
		vo[1] = new VariablesObject("Y intercept", 0, 'b', -graphScope, graphScope);
		FunctionObject fo = new FunctionObject("Linear Function", vo, "y=m*x+b", "");
		Graph graph = new Graph(fo);
		graph.setScope(graphScope);
		graph.setSize(graphScope, graphScope);
		
		frame = new JFrame();
		JPanel bottomPane = new JPanel(new GridLayout(1,2));
		JPanel pane = new JPanel(new BorderLayout());
		JPanel slidersPane = new JPanel();
		sliders = new JSlider[vo.length];
		for(int i = 0; i < vo.length; i++){
			sliders[i] = new JSlider(SwingConstants.VERTICAL, vo[i].getMin(), vo[i].getMax(), vo[i].getValueOfVariable());
			sliders[i].addChangeListener(this);
		}
		JLabel sliderLabels[] = new JLabel[vo.length];
		for(int i = 0; i < vo.length; i++){
			sliderLabels[i] = new JLabel(vo[i].getNameOfVariable());
		}
		
		for(int i = 0; i < vo.length; i++){
			slidersPane.add(sliders[i]);
			slidersPane.add(sliderLabels[i]);
		}
		
		bottomPane.add(graph);
		bottomPane.add(slidersPane);
		pane.add(bottomPane, BorderLayout.SOUTH);
		frame.add(pane);
		
		frame.setSize(400, 600);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		// frame window closer
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		frame.validate();
		frame.repaint();
		
		
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e){
		for(int i = 0; i < vo.length; i++){
			if(e.getSource()==sliders[i]){
				vo[i].setValueOfVariable(sliders[i].getValue());
			}
		}
		frame.validate();
		frame.repaint();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0){
		// TODO Auto-generated method stub
		
	}
	
}
