package com.maxNiebergall;

public class variablesObject{
	private String nameOfVariable="";
	private int valueOfVariable=0;
	private char charVariable='~';
	
	/**
	 * @param nameOfVariable
	 * @param valueOfVariable
	 * @param charVariable
	 */
	variablesObject(String nameOfVariable, int valueOfVariable, char charVariable){
		this.nameOfVariable=nameOfVariable;
		this.valueOfVariable=valueOfVariable;
		this.charVariable=charVariable;
	}

	public String getNameOfVariable(){
		return nameOfVariable;
	}

	public void setNameOfVariable(String nameOfVariable){
		this.nameOfVariable = nameOfVariable;
	}

	public int getValueOfVariable(){
		return valueOfVariable;
	}

	public void setValueOfVariable(int valueOfVariable){
		this.valueOfVariable = valueOfVariable;
	}

	public char getCharVariable(){
		return charVariable;
	}

	public void setCharVariable(char charVariable){
		this.charVariable = charVariable;
	}
	
}
