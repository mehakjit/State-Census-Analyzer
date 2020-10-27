package com.capg;

public class CSVBuilderFactory<E> {
	public static ICSVBuilder createCSVBuilder() {
		return new OpenCSVBuilder<>();
	}
}
