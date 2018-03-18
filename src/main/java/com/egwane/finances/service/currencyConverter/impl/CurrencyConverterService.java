package com.egwane.finances.service.currencyConverter.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.egwane.finances.service.currencyConverter.impl.builders.CurrencyRateBuilder;
import com.external.tools.StopWatch;

public class CurrencyConverterService {
    static final Logger logger = Logger.getLogger(CurrencyConverterService.class);
    static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    public static TreeMap<String, BigDecimal> getRange(Period period, String fromCurrency, String toCurrency)
	    throws IOException, ParseException {
	logger.debug("Begin getRange()");
	StopWatch s = new StopWatch();
	s.start();
	TreeMap<String, BigDecimal> values = new TreeMap<String, BigDecimal>();
	URL url = new URL(buildServiceAddress(period, fromCurrency, toCurrency));
	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

	String inputLine;
	CurrencyRate currencyRate;
	while ((inputLine = in.readLine()) != null) {
	    System.out.println(inputLine);
	    currencyRate = CurrencyRateBuilder.currencyRateBuilder().withLine(inputLine).build();
	    values.put(dateFormatter.format(currencyRate.getDate()), currencyRate.getRate());
	}
	in.close();
	logger.debug("End getRange() - elapsed time : " + s.getElapsedTime() + " milliseconds.");
	return values;
    }

    /**
     * @param period
     * @param fromCurrency
     * @param toCurrency
     */
    private static String buildServiceAddress(Period period, String fromCurrency, String toCurrency) {
	StringBuilder serviceAddress = new StringBuilder("http://currencies.apps.grandtrunk.net/getrange/");
	serviceAddress.append(dateFormatter.format(period.getBeginDate()));
	serviceAddress.append("/");
	serviceAddress.append(dateFormatter.format(period.getEndDate()));
	serviceAddress.append("/");
	serviceAddress.append(fromCurrency);
	serviceAddress.append("/");
	serviceAddress.append(toCurrency);
	return serviceAddress.toString();
    }
}
