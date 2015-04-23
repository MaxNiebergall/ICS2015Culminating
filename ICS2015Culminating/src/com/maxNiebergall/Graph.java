package com.maxNiebergall;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
	private int scopeX = 500, scopeY = scopeX;

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

	public Point cartesianConvert(int x, int y, int width, int height) {
		return new Point((width / 2) + x, (height / 2) - y);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		putVariablesInEngine();

		g.setColor(Color.black);

		// Draw the Cartesian Grid
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		for (int i = -(getHeight()); i < getHeight(); i += 10) {
			Point temp = cartesianConvert(0, i, getWidth(), getHeight());
			g.drawString("" + i, (int) temp.getX(), (int) temp.getY());
		}
		for (int i = -(getWidth()); i < getWidth(); i += 25) {
			Point temp = cartesianConvert(i, 0, getWidth(), getHeight());
			g.drawString("" + i, (int) temp.getX(), (int) temp.getY());
		}
		int xPoints[] = new int[scopeX], yPoints[] = new int[scopeX];

		for (int x = (-(scopeX / 2)) + 1; x < (scopeX / 2); x++) {
			engine.put("x", x);
			try {
				engine.eval(fo.getStringFunction());
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(engine.get("y"));
			g.setColor(Color.red);
			Point temp = cartesianConvert(x, ((Double) engine.get("y")).intValue(), getWidth(), getHeight());
			
			xPoints[x + scopeX / 2] = (int) temp.getX();
			yPoints[x + scopeX / 2] = (int) temp.getY();
		}
		g.drawPolyline(xPoints, yPoints, scopeX);
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
