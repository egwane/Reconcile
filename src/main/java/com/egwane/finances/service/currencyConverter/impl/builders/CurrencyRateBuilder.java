package com.egwane.finances.service.currencyConverter.impl.builders;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.egwane.finances.domain.builders.Builder;
import com.egwane.finances.service.currencyConverter.impl.CurrencyRate;

public class CurrencyRateBuilder implements Builder<CurrencyRate> {
    // private static final Logger logger =
    // Logger.getLogger(CurrencyRateBuilder.class);
    static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private Date date;
    private BigDecimal rate;

    /**
     * Empty constructor
     */
    private CurrencyRateBuilder() {
    }

    public static CurrencyRateBuilder currencyRateBuilder() {
	return new CurrencyRateBuilder();
    }

    public CurrencyRateBuilder withLine(String line) throws ParseException {
	// logger.debug("Begin withCSV(), csv = " + csv);

	StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
	this.date = dateFormatter.parse(stringTokenizer.nextToken());
	this.rate = new BigDecimal(stringTokenizer.nextToken());

	// logger.debug("End withCSV()");
	return this;
    }

    public CurrencyRate build() {
	CurrencyRate currencyRate = new CurrencyRate();
	currencyRate.setDate(date);
	currencyRate.setRate(rate);
	return currencyRate;
    }
}
