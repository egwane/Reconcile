package com.egwane.finances.service.currencyConverter.impl.builders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.egwane.finances.domain.builders.Builder;
import com.egwane.finances.service.currencyConverter.impl.Period;

public class PeriodBuilder implements Builder<Period> {
    // private static final Logger logger =
    // Logger.getLogger(PeriodBuilder.class);
    static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    private Date beginDate;
    private Date endDate;

    /**
     * Empty constructor
     */
    private PeriodBuilder() {
    }

    public static PeriodBuilder periodBuilder() {
	return new PeriodBuilder();
    }

    public PeriodBuilder withBeginDate(String beginDate) throws ParseException {
	this.beginDate = dateFormatter.parse(beginDate);
	return this;
    }

    public PeriodBuilder withEndDate(String endDate) throws ParseException {
	this.endDate = dateFormatter.parse(endDate);
	return this;
    }

    public Period build() {
	Period period = new Period();
	period.setBeginDate(beginDate);
	period.setEndDate(endDate);
	return period;
    }
}
