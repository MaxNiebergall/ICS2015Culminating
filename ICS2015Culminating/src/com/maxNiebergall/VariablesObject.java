package com.maxNiebergall;

public class VariablesObject{
	private String nameOfVariable="";
	private double valueOfVariable=0;
	private char charVariable='~';
	private boolean useRadians=false;
	private boolean useMin=false, useMax=false;
	private double min=0, max=0; 
	
	
	/**
	 * @param nameOfVariable
	 * @param valueOfVariable
	 * @param charVariable
	 * @param min
	 * @param max if equal to zero, there is no maximum
	 */
	VariablesObject(String nameOfVariable, double valueOfVariable, char charVariable, double min, double max){
		this.nameOfVariable=nameOfVariable;
		this.valueOfVariable=valueOfVariable;
		this.charVariable=charVariable;
		this.min=min;
		this.useMin=true;
		this.max=max;
		this.useMax=true;
	}
	
	/**
	 * @param nameOfVariable
	 * @param valueOfVariable
	 * @param charVariable
	 * @param useRadians
	 * @param min
	 * @param max
	 */
	VariablesObject(String nameOfVariable, double valueOfVariable, char charVariable, Boolean useRadians, double min, double max){
		this.nameOfVariable=nameOfVariable;
		this.valueOfVariable=valueOfVariable;
		this.charVariable=charVariable;
		this.useRadians=useRadians;
		this.min=min;
		this.useMin=true;
		this.max=max;
		this.useMax=true;
	}	

	public String getNameOfVariable(){
		return nameOfVariable;
	}

	public void setNameOfVariable(String nameOfVariable){
		this.nameOfVariable = nameOfVariable;
	}

	public double getValueOfVariable(){
		return valueOfVariable;
	}

	public void setValueOfVariable(double valueOfVariable){
		this.valueOfVariable = valueOfVariable;
	}

	public char getCharVariable(){
		return charVariable;
	}

	public void setCharVariable(char charVariable){
		this.charVariable = charVariable;
	}

	public boolean isUseRadians(){
		return useRadians;
	}

	public void setUseRadians(boolean useRadians){
		this.useRadians = useRadians;
	}

	public double getMin(){
		return min;
	}

	public void setMin(double min){
		this.min = min;
	}

	public double getMax(){
		return max;
	}

	public void setMax(double max){
		this.max = max;
	}

	public boolean isUseMin(){
		return useMin;
	}

	public void setUseMin(boolean useMin){
		this.useMin = useMin;
	}

	public boolean isUseMax(){
		return useMax;
	}

	public void setUseMax(boolean useMax){
		this.useMax = useMax;
	}
	
}
