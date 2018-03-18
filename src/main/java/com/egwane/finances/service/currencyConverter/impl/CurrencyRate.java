package com.egwane.finances.service.currencyConverter.impl;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyRate {
    private Date date;
    private BigDecimal rate;

    /**
     * @return the date
     */
    public Date getDate() {
	return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
	this.date = date;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
	return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(BigDecimal rate) {
	this.rate = rate;
    }
}