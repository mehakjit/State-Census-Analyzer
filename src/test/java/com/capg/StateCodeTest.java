package com.capg;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class StateCodeTest {
	 public static final String STATE_CODE_FILE_PATH = "IndiaStateCode.csv";
	private static final String WrongCSVFilePath = "jds.csv";
	private static final String WrongTypeCSV_FilePath = "IndiaWrongTypeStateData.txt";
	private static final String WrongDelimiterCSV_FilePath = "IndianStateWrongDelimiter.csv";
	private static final String WrongHeaderCSV_FilePath = "IndianStateWrongHeader.csv";
	
	
	 private StateCensusAnalyser stateCensusAnalyser;
	 @Before
	   public void initialize() {
		  	stateCensusAnalyser = new StateCensusAnalyser(); 
	   }

	   @Test
		public void givenStateCodeCSVFile_ShouldReturnNumberOfRecords() throws CSVException {
			int noOfEntries = stateCensusAnalyser.loadStateCode(STATE_CODE_FILE_PATH);
			Assert.assertEquals(37, noOfEntries);
		}
	   
	   @Test
	   public void givenWrongFilePath_ShouldReturn_Exception() {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
				try {
					stateCensusAnalyser.loadStateCode(WrongCSVFilePath); 
		   }catch(CSVException e) {
			   Assert.assertEquals(CSVException.ExceptionType.FILE_NOT_EXIST, e.type);
		   }
		}

		@Test
		public void givenStateCensus_WrongType_ShouldThrowException() {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			try {
				stateCensusAnalyser.loadStateCode(WrongTypeCSV_FilePath);
			   }catch(CSVException e) {
				   Assert.assertEquals(CSVException.ExceptionType.WRONG_TYPE, e.type);
			   }
		}
		@Test
		public void givenFilePath_WrongDelimiter_ShouldThrowException() {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			try{
				stateCensusAnalyser.loadStateCode(WrongDelimiterCSV_FilePath);
			}catch (CSVException e) {
				Assert.assertEquals(CSVException.ExceptionType.CSV_INTERNAL_ISSUE, e.type);
			}
		}
		@Test
		public void givenFilePath_WrongHeaderCSV_ShouldThrowCustomException() {
			StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			try{
				stateCensusAnalyser.loadStateCode(WrongHeaderCSV_FilePath);
			}catch (CSVException e) {
				Assert.assertEquals(CSVException.ExceptionType.CSV_INTERNAL_ISSUE, e.type);
			}
		}
		 @Test
		   public void censusSortedOnStateCode() throws CSVException, IOException {
			 StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
			 stateCensusAnalyser.loadStateCode(STATE_CODE_FILE_PATH);
			 String sortedCensusData = stateCensusAnalyser.getStateCodeWiseSortedCensusData();
			 CsvStateCode[] censusCsv = new Gson().fromJson(sortedCensusData, CsvStateCode[].class);
			 Assert.assertEquals("AD", censusCsv[0].stateCode);
		   }
}
