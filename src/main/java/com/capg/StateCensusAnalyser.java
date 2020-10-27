package com.capg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser<E> {
	public static final String STATE_CODE_FILE_PATH = "IndiaStateCode.csv";
	
	ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
	
	public int readCSVFile(String file) throws StateCensusAnalyserException {
		if( ! file.contains(".csv")){
			throw new StateCensusAnalyserException("Not .csv file", StateCensusAnalyserException.ExceptionType.WRONG_TYPE);
		}
		try(Reader reader = Files.newBufferedReader(Paths.get(file))){
			Iterator<IndiaStateCensus> csvStateCensusIterator = (Iterator<IndiaStateCensus>) csvBuilder.getCSVFileIterator(reader, IndiaStateCensus.class);
			return noOfEntries(csvStateCensusIterator);
		} catch (IOException e) {
			throw new StateCensusAnalyserException("File Doesn't Exist",StateCensusAnalyserException.ExceptionType.FILE_NOT_EXIST);
		}catch (RuntimeException e) {
			throw new StateCensusAnalyserException("File Internal Error", StateCensusAnalyserException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}
	
	public int loadStateCode(String csvFilePath) throws StateCensusAnalyserException{
		if(! csvFilePath.contains(".csv")){
			throw new StateCensusAnalyserException("Not .csv file", StateCensusAnalyserException.ExceptionType.WRONG_TYPE);
		}
		try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))){
			Iterator<CsvStateCode> csvStateCodeIterator = (Iterator<CsvStateCode>) csvBuilder.getCSVFileIterator(reader, CsvStateCode.class);
			return noOfEntries(csvStateCodeIterator);
		}  catch (IOException e) {
			throw new StateCensusAnalyserException("File Doesn't Exist",StateCensusAnalyserException.ExceptionType.FILE_NOT_EXIST);
		}catch (RuntimeException e) {
			throw new StateCensusAnalyserException("File Internal Error", StateCensusAnalyserException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}
	
	private <E> int noOfEntries(Iterator<E> CSVFileIterator) {
		Iterable<E> csvIterable = () -> CSVFileIterator;
		int no_of_Entries = (int) StreamSupport.stream(csvIterable.spliterator(),false).count();
		return no_of_Entries;
	}
}
