package com.maxNiebergall;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Base implements ActionListener, MouseWheelListener{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	
	private VariablesObject vo[];
	private JTextField variableInputs[];
	private JButton here;
	private JButton addFunction;
	private JButton removeFunction;
	private JButton editFunction;
	private JFrame frame;
	private JMenuItem menuAbout;
	private JMenuItem menuBack;
	private FunctionObject fo;
	private LinkedList<FunctionObject> foList;
	private final String VERSION = "0.1.0";
	private final String NAME_OF_BUSINESS = "Baller Unlimited";
	private int itemSelected = -1;
	private Scanner sc = new Scanner("functions.txt");
	private BufferedWriter bw;
	
	Base(){
		 try{
			bw= new BufferedWriter(new FileWriter("functions.txt"));
		}catch(IOException e){
			e.printStackTrace();
		}
		 functionsOut(new FunctionObject());//FIXME print out the default functions
		 
		 
		foList = new LinkedList<FunctionObject>();
		for(int i = Integer.parseInt(sc.nextLine()); i > 0; i--){
			foList.add(readInFunction());
		}
		
		functionList();
		
	}
	
	public FunctionObject readInFunction(){
		String name = sc.nextLine();
		String equation = sc.nextLine();
		String summary = sc.nextLine();
		int numOfVariables = Integer.parseInt(sc.nextLine());
		VariablesObject[] tempVO = new VariablesObject[numOfVariables];
		for(int i = 0; i < numOfVariables; i++){
			tempVO[i] = new VariablesObject(sc.nextLine(), Double.parseDouble(sc.nextLine()), sc.nextLine().charAt(0), true, Double.parseDouble(sc.nextLine()), Double.parseDouble(sc.nextLine()));
		}
		sc.close();
		return new FunctionObject(name, tempVO, equation, summary);
	}
	
	public void functionsOut(FunctionObject other){
		String name = sc.nextLine();
		String equation = sc.nextLine();
		String summary = sc.nextLine();
		int numOfVariables = Integer.parseInt(sc.nextLine());
		VariablesObject[] tempVO = new VariablesObject[numOfVariables];
		for(int i = 0; i < numOfVariables; i++){
			tempVO[i] = new VariablesObject(sc.nextLine(), Double.parseDouble(sc.nextLine()), sc.nextLine().charAt(0), Double.parseDouble(sc.nextLine()), Double.parseDouble(sc.nextLine()));
		}
		try{
			bw.write(other.getNameOfFunction());

		bw.write(other.getStringFunction());
		bw.write(other.getSummary());
		bw.write(other.getVariables().size());
		for(int i = other.getVariables().size(); i > 0; i--){
			bw.write(""+other.getVariables().get(i).getNameOfVariable());
			bw.write(""+other.getVariables().get(i).getValueOfVariable());
			bw.write(""+other.getVariables().get(i).getCharVariable());
			bw.write(""+other.getVariables().get(i).getMin());
			bw.write(""+other.getVariables().get(i).getMax());
		}
		}catch(IOException e){
			e.printStackTrace();
		}
		bw.close();
	}
	
	// String nameOfVariable, double valueOfVariable, char charVariable, double min,
	// double max
	
	@Override
	public void actionPerformed(ActionEvent aE){
		// input items
		for(int i = 0; variableInputs != null && i < variableInputs.length; i++){
			if(aE.getSource() == variableInputs[i]){
				updateGraph(i);
				return;
			}
		}
		// Menu Buttons
		if(aE.getSource().equals(menuBack)){
			System.out.println("menuBack");
			frame.dispose();
			functionList();
		}else if(aE.getSource().equals(menuAbout)){
			System.out.println("menuAbout");
			about();
			
			// Other Buttons
		}else if(aE.getSource().equals(here)){
			System.out.println("here");
			updateFunctionList();
		}else if(aE.getSource().equals(addFunction)){
			System.out.println("addFunction");
			foList.add(functionEdit(new FunctionObject("", new VariablesObject[0], "", "")));// TODO
																								// Make
																								// these
																								// actually
																								// do
																								// stuff
			
		}else if(aE.getSource().equals(removeFunction)){
			System.out.println("removeFunction");
			foList.remove(itemSelected);
			
		}else if(aE.getSource().equals(editFunction)){
			System.out.println("editFunction");
			foList.add(functionEdit(foList.remove(itemSelected)));
			
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
	
	private void about(){// TODO use a custom JDialog instead. This will allow for
							// freezing other frames when About is opened
		JFrame aboutFrame = new JFrame();
		// TODO finish implementing the about page
		aboutFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		aboutFrame.setTitle("About NAME_OF_PROGRAM");
		String HTML = ("<html>" + "Version: " + VERSION + "<br> Program By:<br>Max Niebergall<br>Faizan Nadeem<br>and Jovan Panduric<br>" + "&copy; 2015 " + NAME_OF_BUSINESS + "<hr><br> To edit the function list, Click</html>");
		JPanel aboutPane = new JPanel(new BorderLayout());
		JLabel textPane = new JLabel();
		here = new JButton("Here");
		here.addActionListener(this); // TODO Add functionality
		textPane.setText(HTML);
		aboutPane.add(here, BorderLayout.SOUTH);
		aboutPane.add(textPane);
		aboutFrame.add(aboutPane);
		aboutFrame.setLocationRelativeTo(null);
		aboutFrame.setResizable(false);
		aboutFrame.setVisible(true);
		aboutFrame.pack();
		
		aboutFrame.validate();
		aboutFrame.repaint();
	}
	
	private void functionList(){
		final JFrame flFrame = new JFrame();
		JPanel flPane = new JPanel(new GridLayout(1, 2));
		// TODO finish implementing the about page
		flFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		flFrame.setTitle("NAME_OF_PROGRAM -> Function Selection");
		
		JMenuBar menuBar = new JMenuBar();
		// try{
		// menuBackFromFunctionList.setIcon(new ImageIcon(new URL("back.png")));
		// }catch(MalformedURLException e){
		// e.printStackTrace();
		// }
		menuAbout = new JMenuItem("                                                                                                                                                                                        About");
		menuAbout.setHorizontalAlignment(SwingConstants.RIGHT);
		menuAbout.addActionListener(this);
		menuBar.add(menuAbout);
		
		final JList<FunctionObject> list = new JList<FunctionObject>();
		list.setListData(foList.toArray(new FunctionObject[0]));
		final JTextArea summary = new JTextArea();
		summary.setLineWrap(true);
		summary.setBackground(Color.getColor("d3d3d3"));
		
		MouseListener mouseListener = new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
					int index = list.locationToIndex(e.getPoint());
					System.out.println("Double clicked on Item " + index);
					flFrame.dispose();
					graphingFrame();
					
				}else if(e.getClickCount() == 1){
					int index = list.locationToIndex(e.getPoint());
					summary.setText(list.getSelectedValue().getSummary());
					System.out.println("Single clicked on Item " + index);
				}
			}
		};
		list.addMouseListener(mouseListener);
		flPane.add(list);
		flPane.add(summary);
		flFrame.add(flPane);
		flFrame.setJMenuBar(menuBar);
		flFrame.setLocationRelativeTo(null);
		flFrame.setSize(600, 400);
		flFrame.setResizable(false);
		flFrame.setVisible(true);
		// flFrame.pack();
		
		flFrame.validate();
		flFrame.repaint();
	}
	
	private void graphingFrame(){
		
		Graph graph = new Graph(fo);
		// graph.setScope(graphScope);
		// graph.setSize(graphScope, graphScope);
		
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
	
	private void updateFunctionList(){
		JFrame UFLFrame = new JFrame();
		JPanel pane = new JPanel(new BorderLayout());
		JPanel buttonsPane = new JPanel();
		
		// TODO finish implementing the about page
		UFLFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		UFLFrame.setTitle("NAME_OF_PROGRAM -> Update Function Lists");
		
		addFunction = new JButton("Add New");
		removeFunction = new JButton("Remove");
		editFunction = new JButton("Edit");
		
		addFunction.addActionListener(this);
		removeFunction.addActionListener(this);
		editFunction.addActionListener(this);
		
		final JList<FunctionObject> list = new JList<FunctionObject>();
		list.setListData(foList.toArray(new FunctionObject[0]));
		
		MouseListener mouseListener = new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2){
					itemSelected = list.locationToIndex(e.getPoint());
					System.out.println("Double clicked on Item " + itemSelected);
					
				}else if(e.getClickCount() == 1){
					itemSelected = list.locationToIndex(e.getPoint());
					System.out.println("Single clicked on Item " + itemSelected);
				}
			}
		};
		list.addMouseListener(mouseListener);
		
		buttonsPane.add(addFunction);
		buttonsPane.add(removeFunction);
		buttonsPane.add(editFunction);
		
		pane.add(buttonsPane, BorderLayout.SOUTH);
		pane.add(list);
		
		UFLFrame.add(pane);
		UFLFrame.setLocationRelativeTo(null);
		UFLFrame.setSize(600, 400);
		UFLFrame.setResizable(false);
		UFLFrame.setVisible(true);
		UFLFrame.pack();
		
		UFLFrame.validate();
		UFLFrame.repaint();
		
	}
	
	String nameOfFunction;
	String stringFunction;
	String summary;
	JList<VariablesObject> list;
	DefaultListModel<VariablesObject> listModel;
	
	private FunctionObject functionEdit(FunctionObject other){ // TODO Finished this
																// popupMenu
		JFrame frame = new JFrame();
		JPanel pane = new JPanel();
		nameOfFunction = other.getNameOfFunction();
		stringFunction = other.getStringFunction();
		summary = other.getStringFunction();
		listModel = new DefaultListModel<VariablesObject>();
		for(int i = 0; i < other.getVariables().size(); i++){
			listModel.addElement(other.getVariables().get(i));
		}
		list = new JList<VariablesObject>(listModel);
		
		JButton name = new JButton("Name of Function");
		JButton string = new JButton("Equation of Function");
		JButton buttSummary = new JButton("Summary");
		JButton addVar = new JButton("Add Variable");
		JButton remVar = new JButton("Remove Variable");
		
		JPanel varPane = new JPanel();
		varPane.add(addVar);
		varPane.add(remVar);
		
		pane.add(list);
		pane.add(varPane);
		pane.add(name);
		pane.add(string);
		pane.add(buttSummary);
		
		name.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nameOfFunction = JOptionPane.showInputDialog("Please input the name of the function\neg. \"Linear Function\"\nThe current name is: " + nameOfFunction);
			}// end of actionPerformed
		});// end of saveFile
		
		string.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				stringFunction = JOptionPane.showInputDialog("Please input the equation of the function\neg. \"y=mx+b\"\nThe current eqution is: " + stringFunction + "\nNote: Trigenometric functions such as sin(x) or tan(x) must be written as Math.Sin(x) or Math.Tan(x)").toLowerCase();
			}// end of actionPerformed
		});// end of saveFile
		
		buttSummary.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				summary = JOptionPane.showInputDialog("Please input the summary for the function\neg. \"Linear Functions are straight lines with a defined slope\"\nThe current summary is: " + summary);
			}// end of actionPerformed
		});// end of saveFile
		
		addVar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String nameOfVariable = "";
				double valueOfVariable = 0;
				char charVariable = '~';
				boolean useRadians = false;
				boolean useMin = false, useMax = false;
				double min = 0, max = 0;
				
				nameOfVariable = JOptionPane.showInputDialog("Please input the name of the Variable\neg. \"Slope\"\n");
				valueOfVariable = Double.parseDouble(JOptionPane.showInputDialog("Please input a number that is the defualt value of the variable \nNote: value must be a number (can inlude numeric characters and a maximum of one period charater only"));
				charVariable = JOptionPane.showInputDialog("Please input the character representing the variable\neg. \"x\"\nNote: input must be exactly one character, else the fist charcter will be used").charAt(0);
				// useRadians = JOptionPane.showConfirmDialog(varPane,
				// "Use Radians ")
				min = Double.parseDouble(JOptionPane.showInputDialog("Please input a number that is the minimum value of the variable \nNote: value must be a number (can inlude numeric characters and a maximum of one period charater only)\nNote: if no minimum is nessasary use 0"));
				max = Double.parseDouble(JOptionPane.showInputDialog("Please input a number that is the maximum value of the variable \nNote: value must be a number (can inlude numeric characters and a maximum of one period charater only)\nNote: if no maximum is nessasary use 0"));
				
				VariablesObject varO = new VariablesObject(nameOfVariable, valueOfVariable, charVariable, useRadians, min, max);
				
				listModel.addElement(varO);
				itemSelected = -1;
			}// end of actionPerformed
		});// end of saveFile
		
		remVar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				listModel.remove(list.getSelectedIndex());
				itemSelected = -1;
			}// end of actionPerformed
		});// end of saveFile
		
		frame.add(pane);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		frame.validate();
		frame.repaint();
		
		return other;
		
	}
	
}
