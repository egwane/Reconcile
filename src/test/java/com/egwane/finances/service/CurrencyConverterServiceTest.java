package com.egwane.finances.service;

import java.math.BigDecimal;
import java.util.TreeMap;

import junit.framework.TestCase;

import org.junit.Test;

import com.egwane.finances.service.currencyConverter.impl.CurrencyConverterService;
import com.egwane.finances.service.currencyConverter.impl.Period;
import com.egwane.finances.service.currencyConverter.impl.builders.PeriodBuilder;

public class CurrencyConverterServiceTest extends TestCase {
    private TreeMap<String, BigDecimal> values = new TreeMap<String, BigDecimal>();
    private String fromCurrency = "eur";
    private String toCurrency = "cad";

    @Test
    public void testCurrencyConverterService_1() throws Exception {
	System.out.println("testCurrencyConverterService_1");

	Period period = PeriodBuilder.periodBuilder().withBeginDate("2013-06-01").withEndDate("2013-06-06").build();

	values = CurrencyConverterService.getRange(period, fromCurrency, toCurrency);

	assertEquals("Nombre d'entrées", 6, values.size());
    }

    @Test
    public void testCurrencyConverterService_2() throws Exception {
	System.out.println("testCurrencyConverterService_2");

	Period period = PeriodBuilder.periodBuilder().withBeginDate("2013-07-01").withEndDate("2013-07-31").build();

	values = CurrencyConverterService.getRange(period, fromCurrency, toCurrency);

	assertEquals("Nombre d'entrées", 31, values.size());
    }

    @Test
    public void testCurrencyConverterService_3() throws Exception {
	System.out.println("testCurrencyConverterService_3");

	Period period = PeriodBuilder.periodBuilder().withBeginDate("2013-06-01").withEndDate("2013-08-31").build();

	values = CurrencyConverterService.getRange(period, fromCurrency, toCurrency);

	assertEquals("Nombre d'entrées", 92, values.size());
    }
}
