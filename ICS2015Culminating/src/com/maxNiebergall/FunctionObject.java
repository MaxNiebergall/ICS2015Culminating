package com.maxNiebergall;

import java.util.Collection;
import java.util.LinkedList;
import com.maxNiebergall.variablesObject;

public class FunctionObject{
	private String nameOfFunction="";
	private String stringFunction="";
	private LinkedList<variablesObject> variables = new LinkedList<variablesObject>();
	private String summary="";
	
	
	@SuppressWarnings("unchecked")
	FunctionObject(String nameOfFunction, variablesObject[] other, String stringFunction, String summary){
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


	public LinkedList<variablesObject> getVariables(){
		return variables;
	}


	public void setVariables(LinkedList<variablesObject> variables){
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
	
	
	
	
}
