package com.capg;

import org.junit.Test;

import org.junit.Assert;
import org.junit.Before;

import com.google.gson.Gson;

public class StateCensusTest {
	public static final String CSVFilePath = "IndiaStateCensusData.csv";
	public static final String WrongCSVFilePath = "IndiaCensusData.csv";
	private static final String WrongTypeCSV_FilePath = "IndiaWrongTypeCensusData.txt";
	private static final String WrongDelimiterCSV_FilePath = "IndianCensusWrongDelimiter.csv";
	private static final String WrongHeaderCSV_FilePath = "IndianCensusWrongHeader.csv";
	
	private StateCensusAnalyser stateCensusAnalyser;
	
	@Before
	public void initiate() {
	stateCensusAnalyser = new StateCensusAnalyser();
	}
	
	@Test
	public void givenFilePath_CheckNoOfEntries_WhenEquals_ReturnTrue() {
		int count = 0;
		try{
			count = stateCensusAnalyser.readCSVFile(CSVFilePath);
		}catch(CSVException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(count,29);
	}
	@Test
	public void givenWrongFilePath_ShouldReturn_Exception() {
		try {	
			stateCensusAnalyser.readCSVFile(WrongCSVFilePath);
	   }catch(CSVException e) {
		   Assert.assertEquals(CSVException.ExceptionType.FILE_NOT_EXIST, e.type);
	   }
	}
	@Test
	public void givenStateCensus_WrongType_ShouldThrowException() {
		try {
			stateCensusAnalyser.readCSVFile(WrongTypeCSV_FilePath);
		   }catch(CSVException e) {
			   Assert.assertEquals(CSVException.ExceptionType.WRONG_TYPE, e.type);
		   }
	}
	@Test
	public void givenFilePath_WrongDelimiter_ShouldThrowException() {
		try{
			stateCensusAnalyser.readCSVFile(WrongDelimiterCSV_FilePath);
		}catch (CSVException e) {
			Assert.assertEquals(CSVException.ExceptionType.CSV_INTERNAL_ISSUE, e.type);
		}
	}
	@Test
	public void givenFilePath_WrongHeaderCSV_ShouldThrowCustomException() {
		try{
			stateCensusAnalyser.readCSVFile(WrongHeaderCSV_FilePath);
		}catch (CSVException e) {
			Assert.assertEquals(CSVException.ExceptionType.CSV_INTERNAL_ISSUE, e.type);
		}
	}
//	@Test
//	   public void whenSortedOnState_ShouldReturn_TrueOnAlphabeticallyFirstState() throws CSVException {
//		stateCensusAnalyser.readCSVFile(CSVFilePath);
//		   String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
//		  CsvStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CsvStateCensus[].class);
//		  Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
//	   }
}
