package com.maxNiebergall;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JComponent;

public class Graph extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2776772502240664345L;
	BufferedImage bi = new BufferedImage(areguments_here);

	String stringFunction="";
	int x1=0;
	int y1=0;

	
    // create a script engine manager
    ScriptEngineManager factory = new ScriptEngineManager();
    // create a JavaScript engine
    ScriptEngine engine = factory.getEngineByName("JavaScript");    
    
	Graph(String other){
		stringFunction=other;
		
	    // evaluate JavaScript code from String
	    try{
	    	int scopeX=10, scopeY=10;
	    	engine.put("y", 0);
	    	
	    	
	    	for(int i=0; i<scopeX; i++){
		    	engine.put("x", 5);
	    		engine.eval(stringFunction);
	    		engine.get("y");
	    	}
	    	//TODO draw the graph to the component

	    }catch(ScriptException e){
			e.printStackTrace();
		}
	}
	
	public Dimension getPreferedSize(){
		return new Dimension(200,200);
	}
	
	

}
