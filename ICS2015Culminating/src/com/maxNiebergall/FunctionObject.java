package com.maxNiebergall;

import java.util.LinkedList;

public class FunctionObject{
	private String nameOfFunction="";
	private String stringFunction="";
	private LinkedList<VariablesObject> variables = new LinkedList<VariablesObject>();
	private String summary="";
	
	
	/**
	 * 
	 * @param nameOfFunction
	 * @param other
	 * @param stringFunction
	 * @param summary
	 */
	FunctionObject(String nameOfFunction, VariablesObject[] other, String stringFunction, String summary){
		this.nameOfFunction=nameOfFunction;
		this.stringFunction=stringFunction;
		this.summary=summary;
		
		for(int i=0; i<other.length; i++){
			variables.add(other[i]);
		}
	}


	public String getNameOfFunction(){
		return nameOfFunction;
	}


	public void setNameOfFunction(String nameOfFunction){
		this.nameOfFunction = nameOfFunction;
	}


	public LinkedList<VariablesObject> getVariables(){
		return variables;
	}


	public void setVariables(LinkedList<VariablesObject> variables){
		this.variables = variables;
	}


	public String getStringFunction(){
		return stringFunction;
	}


	public void setStringFunction(String stringFunction){
		this.stringFunction = stringFunction;
	}


	public String getSummary(){
		return summary;
	}


	public void setSummary(String summary){
		this.summary = summary;
	}
	 
	public String toString(){
		return getNameOfFunction();
	}
	
	
	
}
