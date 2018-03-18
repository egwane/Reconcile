package com.egwane.finances.service.currencyConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.TreeMap;

import com.egwane.finances.service.currencyConverter.impl.Period;

public interface ICurrencyConverterService {

    public TreeMap<String, BigDecimal> getRange(Period period, String fromCurrency, String toCurrency)
	    throws IOException, ParseException;
}
