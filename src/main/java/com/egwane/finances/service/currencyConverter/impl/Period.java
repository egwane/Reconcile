package com.egwane.finances.service.currencyConverter.impl;

import java.util.Date;

public class Period {
    private Date beginDate;
    private Date endDate;

    /**
     * @return the beginDate
     */
    public Date getBeginDate() {
	return beginDate;
    }

    /**
     * @param beginDate
     *            the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
	return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }
}
