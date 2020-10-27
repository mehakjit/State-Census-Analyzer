package com.capg;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder <E>{
	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws StateCensusAnalyserException {
		try {CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true)
				.build();
		return csvToBean.iterator();
			}catch (IllegalStateException e) {
				throw new StateCensusAnalyserException("Unable to parse", StateCensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
			}
	}
}
