package com.capg;

@SuppressWarnings("serial")
public class CSVException extends Exception{
	
		enum ExceptionType{
			FILE_NOT_EXIST, WRONG_TYPE, CSV_INTERNAL_ISSUE, UNABLE_TO_PARSE;
		}
		
		ExceptionType type;
		
		public CSVException(String message, ExceptionType type) {
			super(message);
			this.type = type;
		}

}
