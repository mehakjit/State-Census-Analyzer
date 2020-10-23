package com.capg;

import org.junit.Test;

import org.junit.Assert;

public class StateCensusTest {
	public static final String CSVFilePath = "IndiaStateCensusData.csv";
	public static final String WrongCSVFilePath = "IndiaCensusData.csv";
	private static final String WrongTypeCSV_FilePath = "IndiaWrongTypeCensusData.txt";
	private static final String WrongDelimiterCSV_FilePath = "IndianCensusWrongDelimiter.csv";	
 
	@Test
	public void givenFilePath_CheckNoOfEntries_WhenEquals_ReturnTrue() {
		int count = 0;
		try{
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			count = stateCensusAnalyser.readCSVFile(CSVFilePath);
		}catch(StateCensusAnalyserException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(count,29);
	}
	@Test
	public void givenWrongFilePath_ShouldReturn_Exception() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {	
			stateCensusAnalyser.readCSVFile(WrongCSVFilePath);
	   }catch(StateCensusAnalyserException e) {
		   Assert.assertEquals(StateCensusAnalyserException.ExceptionType.FILE_NOT_EXIST, e.type);
	   }
	}
	@Test
	public void givenStateCensus_WrongType_ShouldThrowException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try {
			stateCensusAnalyser.readCSVFile(WrongTypeCSV_FilePath);
		   }catch(StateCensusAnalyserException e) {
			   Assert.assertEquals(StateCensusAnalyserException.ExceptionType.WRONG_TYPE, e.type);
		   }
	}
	@Test
	public void givenFilePath_WrongDelimiter_ShouldThrowException() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		try{
			stateCensusAnalyser.readCSVFile(WrongDelimiterCSV_FilePath);
		}catch (StateCensusAnalyserException e) {
			Assert.assertEquals(StateCensusAnalyserException.ExceptionType.CSV_INTERNAL_ISSUE, e.type);
		}
	}
	
}
