package com.egwane.finances.domain;

public class YearMonthDate {
    private int year = 0;
    private int month = 0;

    /**
     * @return the year
     */
    public int getYear() {
	return year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear(int year) {
	this.year = year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
	return month;
    }

    /**
     * @param month
     *            the month to set
     */
    public void setMonth(int month) {
	this.month = month;
    }

    public boolean equals(Object o) {
	if (o == null)
	    return false;
	if (!(o instanceof YearMonthDate))
	    return false;

	YearMonthDate other = (YearMonthDate) o;
	if (this.year != other.year)
	    return false;
	if (this.month != other.month)
	    return false;

	return true;
    }

    public int hashCode() {
	return (int) this.year + this.month;
    }
}
