package com.capg;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder <E> implements ICSVBuilder<E>{
	
	public Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVException  {
		return this.getCSVBean(reader, csvClass).iterator();
	}

	@Override
	public List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVException {
		return this.getCSVBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws CSVException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
					.withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean;
		} catch (IllegalStateException e) {
			throw new CSVException("Unable to parse", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}	
}
