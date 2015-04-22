package com.maxNiebergall;

import java.util.Collection;
import java.util.LinkedList;
import com.maxNiebergall.variablesObject;

public class FunctionObject{
	private String nameOfFunction="";
	private String stringFunction="";
	private LinkedList<variablesObject> variables = new LinkedList<variablesObject>();
	
	
	@SuppressWarnings("unchecked")
	FunctionObject(String nameOfFunction, variablesObject[] other, String stringFunction){
		this.nameOfFunction=nameOfFunction;
		//variables.addAll(variablesObject) other);
		this.stringFunction=stringFunction;
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
	
	
	
	
}
