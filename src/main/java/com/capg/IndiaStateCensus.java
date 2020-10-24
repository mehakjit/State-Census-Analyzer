package com.capg;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensus {

	@CsvBindByName(column = "State", required = true)
	public String state;
	@CsvBindByName(column = "Population", required = true)
	public String population;
	@CsvBindByName(column = "AreaInSqKm", required = true)
	public String areaInSqKm;
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public String densityPerSqKm;
	@Override
	public String toString() {
		return "CsvStateCensus [" +"state='" + state + '\''+", population='" + population + '\''+ ", areaInSqKm='" + areaInSqKm+ '\''+
			   ", densityPerSqKm='" + densityPerSqKm + '\''+ ']';
	}
	
	
}
