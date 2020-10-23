package com.capg;

import org.junit.Test;

import org.junit.Assert;

public class StateCensusTest {
	public static final String CSVFilePath = "IndiaStateCensusData.csv";
 
	@Test
	public void givenFilePath_CheckNoOfEntries_WhenEquals_ReturnTrue() {
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int count = stateCensusAnalyser.readCSVFile(CSVFilePath);
		Assert.assertEquals(count,29);
	}
}
