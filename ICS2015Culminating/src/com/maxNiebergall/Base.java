package com.maxNiebergall;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Base implements ActionListener, MouseWheelListener{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	
	VariablesObject vo[];
	JTextField variableInputs[];
	JFrame frame;
	
	Base(){
		int graphScope = 200;
		vo = new VariablesObject[2];
		vo[0] = new VariablesObject("Slope", 1.00, 'm', -graphScope / 10, graphScope / 10);
		vo[1] = new VariablesObject("Y intercept", 0.00, 'b', -graphScope, graphScope);
		FunctionObject fo = new FunctionObject("Linear Function", vo, "y=m*x+b", "The Linear function is a straight line that can be manipulated by Slope and Y-Intercept");
		Graph graph = new Graph(fo);
		graph.setScope(graphScope);
		graph.setSize(graphScope, graphScope);
		
		frame = new JFrame();
		JPanel bottomPane = new JPanel(new FlowLayout());
		JPanel pane = new JPanel(new BorderLayout());
		JPanel summaryPane = new JPanel();
		JPanel inputsPane = new JPanel();
		variableInputs = new JTextField[vo.length];
		for(int i = 0; i < vo.length; i++){
			variableInputs[i] = new JTextField("" + vo[i].getValueOfVariable());
			variableInputs[i].addActionListener(this);
		}
		JLabel inputsLabels[] = new JLabel[vo.length];
		for(int i = 0; i < vo.length; i++){
			inputsLabels[i] = new JLabel(vo[i].getNameOfVariable());
		}
		JPanel inputsPanels[] = new JPanel[vo.length];
		for(int i = 0; i < vo.length; i++){
			inputsPanels[i] = new JPanel();
			inputsPanels[i].add(variableInputs[i]);
			inputsPanels[i].add(inputsLabels[i]);
		}
		
		for(int i = 0; i < vo.length; i++){
			inputsPane.add(inputsPanels[i]);
		}
		summaryPane.add(new JTextArea(fo.getSummary()));
		//TODO make the summary wrap properly. Fix the formmating
		
		bottomPane.add(graph);
		bottomPane.add(inputsPane);
		pane.add(bottomPane, BorderLayout.SOUTH);
		pane.add(summaryPane);
		frame.add(pane);
		
		frame.setSize(450, 600);
		frame.setResizable(false);
		frame.addMouseWheelListener(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.validate();
		frame.repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent aE){
		for(int i = 0; i < vo.length; i++){
			if(aE.getSource() == variableInputs[i]){
				updateGraph(i);
			}
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		for(int i = 0; i < vo.length; i++){
			if(variableInputs[i].hasFocus()){
				Double d = (Double.parseDouble(variableInputs[i].getText()) + -(e.getWheelRotation()/10.0));
				System.out.println("varIn "+variableInputs[i].getText());
				System.out.println("wheelroation "+e.getWheelRotation()/10.0);
				System.out.println("d "+d);
				
				variableInputs[i].setText(""+new DecimalFormat("0.00").format(d));
				updateGraph(i);
			}
		}
	}
	
	private void updateGraph(int i){
		vo[i].setValueOfVariable(Double.parseDouble(variableInputs[i].getText()));
		frame.validate();
		frame.repaint();
	}
	
}
