package com.capg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser<E> {
	public static final String STATE_CODE_FILE_PATH = "IndiaStateCode.csv";

	ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
	

	public int readCSVFile(String file) throws CSVException {
		if (!file.contains(".csv")) {
			throw new CSVException("Not .csv file",
					CSVException.ExceptionType.WRONG_TYPE);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(file))) {
			Iterator<IndiaStateCensus> csvStateCensusIterator = (Iterator<IndiaStateCensus>) csvBuilder
					.getCSVFileIterator(reader, IndiaStateCensus.class);
			return noOfEntries(csvStateCensusIterator);
		} catch (IOException e) {
			throw new CSVException("File Doesn't Exist",
					CSVException.ExceptionType.FILE_NOT_EXIST);
		} catch (RuntimeException e) {
			throw new CSVException("File Internal Error",
					CSVException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}

	public int loadStateCode(String csvFilePath) throws CSVException {
		if (!csvFilePath.contains(".csv")) {
			throw new CSVException("Not .csv file",
					CSVException.ExceptionType.WRONG_TYPE);
		}
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			Iterator<CsvStateCode> csvStateCodeIterator = (Iterator<CsvStateCode>) csvBuilder.getCSVFileIterator(reader,
					CsvStateCode.class);
			return noOfEntries(csvStateCodeIterator);
		} catch (IOException e) {
			throw new CSVException("File Doesn't Exist",
					CSVException.ExceptionType.FILE_NOT_EXIST);
		} catch (RuntimeException e) {
			throw new CSVException("File Internal Error",
					CSVException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}

	private <E> int noOfEntries(Iterator<E> CSVFileIterator) {
		Iterable<E> csvIterable = () -> CSVFileIterator;
		int no_of_Entries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		return no_of_Entries;
	}

	public String getStateWiseSortedCensusData(String csvFilePath) throws CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			List csvStateCensusList = csvBuilder.getCSVFileList(reader, IndiaStateCensus.class);
			Comparator<IndiaStateCensus> censusComparator = Comparator.comparing(census -> census.state);
			this.sort(csvStateCensusList, censusComparator);
			String sortedJsonStateCensusList = new Gson().toJson(csvStateCensusList);
			return sortedJsonStateCensusList;
		} catch (IOException e) {
			throw new CSVException("File Doesn't Exist",
					CSVException.ExceptionType.FILE_NOT_EXIST);
		} catch (RuntimeException e) {
			throw new CSVException("File Internal Error",
					CSVException.ExceptionType.CSV_INTERNAL_ISSUE);
		}
	}

	private void sort(List<IndiaStateCensus> csvStateCensusList, Comparator<IndiaStateCensus> censusComparator) {
		for (int i = 0; i < csvStateCensusList.size()-1; i++) {
			for (int j = 0; j < csvStateCensusList.size() - 1 - i; j++) {
				IndiaStateCensus c1 = csvStateCensusList.get(j);
				IndiaStateCensus c2 = csvStateCensusList.get(j + 1);
				if (censusComparator.compare(c1, c2) > 0) {
					csvStateCensusList.set(j, c2);
					csvStateCensusList.set(j + 1, c1);
				}	
			}
		}
	}

	public String getStateCodeWiseSortedCensusData() throws CSVException, IOException {
		try (Reader reader = Files.newBufferedReader(Paths.get(STATE_CODE_FILE_PATH))) {
			List<CsvStateCode> csvStateCodeList = csvBuilder.getCSVFileList(reader, CsvStateCode.class);
		Collections.sort(csvStateCodeList, Comparator.comparing(code -> code.stateCode));
		return new Gson().toJson(csvStateCodeList);
		}
	}
}
