package com.maxNiebergall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Graph extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2776772502240664345L;

	private FunctionObject fo;
	private int scopeX = 200, scopeY = scopeX;

	// create a script engine manager
	ScriptEngineManager factory = new ScriptEngineManager();
	// create a JavaScript engine
	ScriptEngine engine = factory.getEngineByName("JavaScript");

	Graph(FunctionObject other) {
		setBorder(BorderFactory.createLineBorder(Color.black));

		fo=other;
		//fo = new FunctionObject("y=x+c", new variablesObject[] { new variablesObject("verticle trasnform", 100, 'c') }, "y=100*Math.sin(x-20)+c");

		engine.put("y", 0);

		putVariablesInEngine();

	}

	public Dimension getPreferredSize() {
		return new Dimension(scopeX, scopeY);
	}

	public void putVariablesInEngine() {
		for (int i = 0; i < fo.getVariables().size(); i++) {
			engine.put("" + fo.getVariables().get(i).getCharVariable(), fo.getVariables().get(i).getValueOfVariable());
		}
	}

	public Point2D.Double cartesianConvert(double x, double y, int width, int height) {
		return new Point2D.Double((width / 2) + x, (height / 2) - y);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		putVariablesInEngine();

		g.setColor(Color.black);

		// Draw the Cartesian Grid
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		for (int i = -(getHeight()/2); i < getHeight()/2; i += 10) {
			Point2D.Double temp = cartesianConvert(0, i, getWidth(), getHeight());
			g.drawString("" + i, (int) temp.getX(), (int) temp.getY());
		}
		for (int i = -(getWidth()/2); i < getWidth()/2; i += 25) {
			Point2D.Double temp = cartesianConvert(i, 0, getWidth(), getHeight());
			g.drawString("" + i, (int) temp.getX(), (int) temp.getY());
		}
		//--------------------------------------------------------------------------
		
		//Draw the equation
		double xPoints[] = new double[scopeX], yPoints[] = new double[scopeX];
		

		for (int x = (-(scopeX / 2)) + 1; x < (scopeX / 2); x++) {
			engine.put("x", x);
			try {
				engine.eval(fo.getStringFunction());
			} catch (ScriptException e) {
				e.printStackTrace();
			}
			System.out.println(engine.get("y"));
			g.setColor(Color.red);
			Point2D.Double temp = cartesianConvert(x, ((Double) engine.get("y")).doubleValue(), getWidth(), getHeight());
			
			xPoints[x + scopeX / 2] = temp.getX();
			yPoints[x + scopeX / 2] = temp.getY();
		}
		
		Path2D p = new Path2D.Double();
		p.moveTo(xPoints[0], yPoints[0]);
		for(int x=1; x<xPoints.length; x++){
	        p.lineTo(xPoints[x], yPoints[x]);
		}
		
		((Graphics2D) g).draw(p);
		//--------------------------------------------------------------------------
	}

	public FunctionObject getFo() {
		return fo;
	}

	public void setFo(FunctionObject fo) {
		this.fo = fo;
	}

	public int getScope() {
		return scopeX;
	}

	public void setScope(int scopeX) {
		this.scopeX = scopeX;
		this.scopeY=scopeX;
	}

	public ScriptEngineManager getFactory() {
		return factory;
	}

	public void setFactory(ScriptEngineManager factory) {
		this.factory = factory;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
