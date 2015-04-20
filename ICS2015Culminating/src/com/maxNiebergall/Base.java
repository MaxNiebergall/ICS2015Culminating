package com.maxNiebergall;

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
		frame.add(new Graph("y=x"));
	}
	
}
