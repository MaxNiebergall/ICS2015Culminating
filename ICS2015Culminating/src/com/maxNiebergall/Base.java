package com.maxNiebergall;

import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Base{
	
	/**
	 * @param args
	 */
	public static void main(String[] args){
		new Base();
		
	}
	
	Base(){
		JFrame frame = new JFrame();
		frame.add(new Graph(new FunctionObject("sin(x)", new variablesObject[]{new variablesObject("amplitude", 0, 'a'), new variablesObject("phase shift", 0, 'p'), new variablesObject("frequency", 2, 'f')}, null)));
	}
	
}
