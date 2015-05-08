package com.maxNiebergall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

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
	JMenuItem menuAbout;
	JMenuItem menuBack;
	JMenuItem menuBackFromFunctionList;
	FunctionObject fo;
	FunctionObject[] foArray;
	final String VERSION= "0.1.0";
	final String NAME_OF_BUSINESS="MFJ Inc.";
	
	Base(){
		int graphScope = 200;
		vo = new VariablesObject[2];
		vo[0] = new VariablesObject("Slope", 1.00, 'm', -graphScope / 10, graphScope / 10);
		vo[1] = new VariablesObject("Y intercept", 0.00, 'b', -graphScope, graphScope);
		fo = new FunctionObject("Linear Function", vo, "y=m*x+b", "The Linear function is a straight line that can be manipulated by Slope and Y-Intercept");
		foArray=new FunctionObject[10];
		for(int i=0; i<10; i++){
			foArray[i]= new FunctionObject("Linear Function", vo, "y=m*x+b", "The Linear function is a straight line that can be manipulated by Slope and Y-Intercept");
		}
	
		
		Graph graph = new Graph(fo);
		graph.setScope(graphScope);
		graph.setSize(graphScope, graphScope);
		
		frame = new JFrame();
		JMenuBar menuBar = new JMenuBar();
		menuBack = new JMenuItem("Back");
		menuBack.setIcon(new ImageIcon("back.png"));
		menuAbout = new JMenuItem("                                                           About");
		menuAbout.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBack.setHorizontalAlignment(SwingConstants.LEFT);
		menuBack.addActionListener(this);
		menuAbout.addActionListener(this);
		menuBar.add(menuBack);
		menuBar.add(menuAbout);
		JPanel bottomPane = new JPanel(new FlowLayout());
		JPanel pane = new JPanel(new BorderLayout());
		JPanel summaryPane = new JPanel(new BorderLayout());
		JTextArea summaryArea = new JTextArea();
		JLabel stringFunction = new JLabel("Equation: " + fo.getStringFunction());
		JLabel nameOfFunction = new JLabel(fo.getNameOfFunction());
		JPanel labelsPane = new JPanel();
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
		summaryArea.setText(fo.getSummary());
		summaryArea.setLineWrap(true);
		summaryPane.setOpaque(true);
		summaryPane.setBackground(Color.white);
		summaryPane.setBorder(BorderFactory.createLineBorder(Color.black));
		stringFunction.setBorder(BorderFactory.createLineBorder(Color.black));
		nameOfFunction.setBorder(BorderFactory.createLineBorder(Color.black));
		// TODO make the summary wrap properly. Fix the formating
		
		bottomPane.add(graph);
		bottomPane.add(inputsPane);
		pane.add(bottomPane, BorderLayout.SOUTH);
		labelsPane.add(stringFunction);
		labelsPane.add(nameOfFunction);
		summaryPane.add(summaryArea, BorderLayout.NORTH);
		summaryPane.add(labelsPane, BorderLayout.SOUTH);
		pane.add(summaryPane);
		frame.add(pane);
		
		frame.setJMenuBar(menuBar);
		frame.setSize(450, 400);
		frame.setResizable(false);
		frame.addMouseWheelListener(this);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle(fo.getNameOfFunction());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.validate();
		frame.repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent aE){
		// input items
		for(int i = 0; i < vo.length; i++){
			if(aE.getSource() == variableInputs[i]){
				updateGraph(i);
				return;
			}
		}
		// Menu Buttons
		if(aE.getSource().equals(menuBack)){
			System.out.println("menuBack");
			backFromGraphing();
		}
		else if(aE.getSource().equals(menuAbout)){
			System.out.println("menuAbout");
			about();
		}
		else if(aE.getSource().equals(menuBackFromFunctionList)){
			
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e){
		for(int i = 0; i < vo.length; i++){
			if(variableInputs[i].hasFocus()){
				Double d = (Double.parseDouble(variableInputs[i].getText()) + -(e.getWheelRotation() / 10.0));
				System.out.println("varIn " + variableInputs[i].getText());
				System.out.println("wheelroation " + e.getWheelRotation() / 10.0);
				System.out.println("d " + d);
				
				variableInputs[i].setText("" + new DecimalFormat("0.00").format(d));
				updateGraph(i);
			}
		}
	}
	
	private void updateGraph(int i){
		vo[i].setValueOfVariable(Double.parseDouble(variableInputs[i].getText()));
		frame.validate();
		frame.repaint();
	}
	private void about(){//TODO use a custom JDialog instead. This will allow for freezing other frames when About is opened
		JFrame aboutFrame = new JFrame();
		//TODO finish implementing the about page
		aboutFrame.setDefaultCloseOperation(aboutFrame.DISPOSE_ON_CLOSE);
		aboutFrame.setTitle("About NAME_OF_PROGRAM");
		String HTML=("<html>"+"Version: "+ VERSION+"<br> Program By:<br>Max Niebergall<br>Faizan Nadeem<br>and Jovan Panduric<br>"+"&copy; 2015 "+NAME_OF_BUSINESS+"<hr><br> To edit the function list, Click</html>");
		JPanel aboutPane = new JPanel(new BorderLayout());
		JLabel textPane = new JLabel();
		JButton here = new JButton("Here");
		here.addActionListener(this); //TODO Add functionality
		textPane.setText(HTML);
		aboutPane.add(here, BorderLayout.SOUTH);
		aboutPane.add(textPane);
		aboutFrame.add(aboutPane);
		aboutFrame.setAlwaysOnTop(true);
		aboutFrame.setLocation(frame.getLocation());
		aboutFrame.setSize(frame.getSize());
		aboutFrame.setResizable(false);
		aboutFrame.setVisible(true);
		aboutFrame.pack();
		
		aboutFrame.validate();
		aboutFrame.repaint();
	}
	private void backFromGraphing(){
		functionList();
	}
	private void functionList(){
		JFrame flFrame = new JFrame();
		JPanel flPane = new JPanel();
		//TODO finish implementing the about page
		flFrame.setDefaultCloseOperation(flFrame.EXIT_ON_CLOSE);
		flFrame.setTitle("NAME_OF_PROGRAM -> Function Selection");
	
		JMenuBar menuBar = new JMenuBar();
		menuBackFromFunctionList = new JMenuItem("Back");
//		try{
//			menuBackFromFunctionList.setIcon(new ImageIcon(new URL("back.png")));
//		}catch(MalformedURLException e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		menuAbout = new JMenuItem("                                                           About");
		menuAbout.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBackFromFunctionList.setHorizontalAlignment(SwingConstants.LEFT);
		menuBackFromFunctionList.addActionListener(this);
		menuAbout.addActionListener(this);
		menuBar.add(menuBackFromFunctionList);
		menuBar.add(menuAbout);
		
		final JList<FunctionObject> list = new JList<FunctionObject>();
		list.setListData(foArray);
		JLabel summary = new JLabel();
		
		
		 MouseListener mouseListener = new MouseAdapter() {
		     public void mouseClicked(MouseEvent e) {
		    	 //TODO add single click display summary: summary.setText(list.getSelectedValue().getSummary());
		         if (e.getClickCount() == 2) {
		             int index = list.locationToIndex(e.getPoint());
		             System.out.println("Double clicked on Item " + index);
		          }
		     }
		 };
		 list.addMouseListener(mouseListener);
		
		flPane.add(list);
		flFrame.add(flPane);
		flFrame.setJMenuBar(menuBar);
		flFrame.setAlwaysOnTop(true);
		flFrame.setLocation(frame.getLocation());
		flFrame.setSize(frame.getSize());
		flFrame.setResizable(false);
		flFrame.setVisible(true);
		flFrame.pack();
		frame.setVisible(false);
		
		flFrame.validate();
		flFrame.repaint();
	}
	
}
