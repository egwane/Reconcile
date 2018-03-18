package com.egwane.finances.domain.builders;

import java.text.SimpleDateFormat;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;

import com.egwane.finances.domain.Transaction;

public class RowBuilder implements Builder<RowBuilder> {
    static final SimpleDateFormat dayFormatter = new SimpleDateFormat("dd");

    private WritableSheet writableSheet;
    private Transaction transaction;
    private int amountColumn;
    private int rowIndex;

    private RowBuilder() {
    }

    public static RowBuilder rowBuilder() {
	return new RowBuilder();
    }

    public RowBuilder withSheet(WritableSheet writableSheet) {
	this.writableSheet = writableSheet;
	return this;
    }

    public RowBuilder withTransaction(Transaction transaction) {
	this.transaction = transaction;
	return this;
    }

    public RowBuilder withAmountColumn(int amountColumn) {
	this.amountColumn = amountColumn;
	return this;
    }

    public RowBuilder withRowIndex(int rowIndex) {
	this.rowIndex = rowIndex;
	return this;
    }

    public RowBuilder build() throws Exception {

	Number date = new Number(0, this.rowIndex, Integer.parseInt(dayFormatter.format(this.transaction.getDate())));
	this.writableSheet.addCell(date);

	Number amount = new Number(this.amountColumn, this.rowIndex, transaction.getAmount(), buildDollarCellFormat());
	this.writableSheet.addCell(amount);

	Label payee = new Label(3, this.rowIndex, transaction.getPayee());
	this.writableSheet.addCell(payee);

	Label category = new Label(4, this.rowIndex, transaction.getCategory());
	this.writableSheet.addCell(category);

	Label memo = new Label(5, this.rowIndex, transaction.getMemo());
	this.writableSheet.addCell(memo);

	return null;
    }

    /**
     * @return
     */
    private static final WritableCellFormat buildDollarCellFormat() {
	NumberFormat dollarCurrency = new NumberFormat("#,###.00 " + NumberFormat.CURRENCY_DOLLAR,
		NumberFormat.COMPLEX_FORMAT);
	WritableCellFormat dollarFormat = new WritableCellFormat(dollarCurrency);
	WritableCellFormat dollarCellFormat = new WritableCellFormat(dollarFormat);
	return dollarCellFormat;
    }
}
