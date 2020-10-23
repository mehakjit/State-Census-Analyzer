package com.capg;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensus {

	public String state;
	
	public String population;
	
	public String areaInSqKm;
	
	public String densityPerSqKm;
	@Override
	public String toString() {
		return "CsvStateCensus [" +"state='" + state + '\''+", population='" + population + '\''+ ", areaInSqKm='" + areaInSqKm+ '\''+
			   ", densityPerSqKm='" + densityPerSqKm + '\''+ ']';
	}
	
	
}
