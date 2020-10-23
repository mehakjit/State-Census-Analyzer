package com.capg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;



public class StateCensusAnalyser {
	private static final String CSV_FILE_LOCATION = "F:\\mehak\\capgemini\\IndiaCensus\\IndiaStateCensusData.csv"; 
	
	public static int readCSVFile(String file) throws FileNotFoundException{
		try(Reader reader = Files.newBufferedReader(Paths.get(file))){
			CsvToBeanBuilder<CsvStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<CsvStateCensus> csvToBean = csvToBeanBuilder.withType(CsvStateCensus.class)
					                             .withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CsvStateCensus> csvStateCensusIterator = csvToBean.iterator();
			List<CsvStateCensus> stateList = new ArrayList<CsvStateCensus>();
			while(csvStateCensusIterator.hasNext()) {
				stateList.add(csvStateCensusIterator.next());
			}
			return stateList.size();
		} catch (IOException e) {}
		return 0;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Welocome to Indian State Census Analyzer");
		int noOfEntries = StateCensusAnalyser.readCSVFile(CSV_FILE_LOCATION);
		System.out.println("Total Entries = " + noOfEntries);
	}        
}
