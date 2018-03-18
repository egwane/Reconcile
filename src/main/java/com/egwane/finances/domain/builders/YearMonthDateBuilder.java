package com.egwane.finances.domain.builders;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.egwane.finances.domain.YearMonthDate;

public class YearMonthDateBuilder implements Builder<YearMonthDate> {
    static final SimpleDateFormat yearFormatter = new SimpleDateFormat("yyyy");
    static final SimpleDateFormat monthFormatter = new SimpleDateFormat("MM");

    private int year = 0;
    private int month = 0;

    private YearMonthDateBuilder() {
    }

    public static YearMonthDateBuilder yearMonthDateBuilder() {
	return new YearMonthDateBuilder();
    }

    public YearMonthDateBuilder withDate(Date date) {
	this.year = Integer.parseInt(new StringBuilder(yearFormatter.format(date)).toString());
	this.month = Integer.parseInt(new StringBuilder(monthFormatter.format(date)).toString());
	return this;
    }

    public YearMonthDate build() throws Exception {
	YearMonthDate yearMonthDate = new YearMonthDate();
	yearMonthDate.setMonth(month);
	yearMonthDate.setYear(year);
	return yearMonthDate;
    }
}
