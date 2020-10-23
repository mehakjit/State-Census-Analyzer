package com.capg;

import org.junit.Test;

import org.junit.Assert;

public class StateCensusTest {
	public static final String CSVFilePath = "IndiaStateCensusData.csv";
	public static final String WrongCSVFilePath = "IndiaCensusData.csv";
 
	@Test
	public void givenFilePath_CheckNoOfEntries_WhenEquals_ReturnTrue() {
		int count = 0;
		try{
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			count = stateCensusAnalyser.readCSVFile(CSVFilePath);
		}catch(StateCensusAnalyserException e) {
			e.printStackTrace();
		}
		System.out.println(count);
		Assert.assertEquals(count,29);
		
	}
	@Test
	public void givenWrongFilePath_ShouldReturn_Exception() throws StateCensusAnalyserException {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {	
			stateCensusAnalyser.readCSVFile(WrongCSVFilePath);
	   }catch(StateCensusAnalyserException e) {
		   Assert.assertEquals(StateCensusAnalyserException.ExceptionType.FILE_NOT_EXIST, e.type);
	   }
	}
}
