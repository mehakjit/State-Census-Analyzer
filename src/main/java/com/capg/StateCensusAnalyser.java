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
	private static final String CSV_FILE_LOCATION = "IndiaStateCensusData.csv"; 
	
	public int readCSVFile(String file) throws StateCensusAnalyserException {
		if( ! file.contains(".csv")){
			throw new StateCensusAnalyserException("Not .csv file", StateCensusAnalyserException.ExceptionType.WRONG_TYPE);
		}
		try(Reader reader = Files.newBufferedReader(Paths.get(file))){
			CsvToBeanBuilder<IndiaStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			CsvToBean<IndiaStateCensus> csvToBean = csvToBeanBuilder.withType(IndiaStateCensus.class)
					                             .withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IndiaStateCensus> csvStateCensusIterator = csvToBean.iterator();
			List<IndiaStateCensus> stateList = new ArrayList<IndiaStateCensus>();
			while(csvStateCensusIterator.hasNext()) {
				stateList.add(csvStateCensusIterator.next());
			}
			return stateList.size();
		} catch (IOException e) {
			throw new StateCensusAnalyserException("File Doesn't Exist",StateCensusAnalyserException.ExceptionType.FILE_NOT_EXIST);
		}catch (RuntimeException e) {
			throw new StateCensusAnalyserException("File Internal Error", StateCensusAnalyserException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, StateCensusAnalyserException {
		System.out.println("Welocome to Indian State Census Analyzer");
		StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();
		int noOfEntries = stateCensusAnalyser.readCSVFile(CSV_FILE_LOCATION);
		System.out.println("Total Entries = " + noOfEntries);
	}        
}
