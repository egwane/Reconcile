package com.egwane.finances.domain.builders;

import java.util.Map;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.egwane.context.AppContext;
import com.egwane.finances.domain.MonthlyTransactions;
import com.egwane.finances.domain.Transaction;

@SuppressWarnings("unchecked")
public class WritableSheetBuilder implements Builder<WritableSheet> {
    static final Logger logger = Logger.getLogger(WritableSheetBuilder.class);

    private static ApplicationContext context = AppContext.getApplicationContext();
    private static Map<String, String> months = (Map<String, String>) context.getBean("months");
    private MonthlyTransactions monthlyTransactions;
    private WritableWorkbook writableWorkbook;
    private int amountColumn;
    private int sheetIndex;

    private WritableSheetBuilder() {
    }

    public static WritableSheetBuilder writableSheetBuilder() {
	return new WritableSheetBuilder();
    }

    public WritableSheetBuilder withWritableWorkbook(WritableWorkbook writableWorkbook) {
	this.writableWorkbook = writableWorkbook;
	return this;
    }

    public WritableSheetBuilder withMonthlyTransactions(MonthlyTransactions monthlyTransactions) {
	this.monthlyTransactions = monthlyTransactions;
	return this;
    }

    public WritableSheetBuilder withAmountColumn(int amountColumn) {
	this.amountColumn = amountColumn;
	return this;
    }

    public WritableSheetBuilder withSheetIndex(int sheetIndex) {
	this.sheetIndex = sheetIndex;
	return this;
    }

    public WritableSheet build() throws Exception {
	WritableSheet writableSheet = writableWorkbook.createSheet(buildSheetName(), sheetIndex);
	HeaderLineBuilder.headerLineBuilder().withSheet(writableSheet).build();
	logger.debug("Building worksheet : " + buildSheetName());

	// Set columns width.
	writableSheet.setColumnView(0, 8);
	writableSheet.setColumnView(1, 11);
	writableSheet.setColumnView(2, 11);
	writableSheet.setColumnView(3, 30);
	writableSheet.setColumnView(4, 30);
	writableSheet.setColumnView(5, 50);

	int rowIndex = 1;
	for (Transaction transaction : monthlyTransactions.getTransactions()) {
	    // logger.debug("Write transaction to output file : " +
	    // transaction.getAmount());
	    RowBuilder.rowBuilder().withSheet(writableSheet).withRowIndex(rowIndex++).withAmountColumn(amountColumn)
		    .withTransaction(transaction).build();
	}

	return writableSheet;
    }

    /**
     * @return
     */
    private String buildSheetName() {
	StringBuilder string = new StringBuilder();
	string.append(WritableSheetBuilder.months.get(Integer.toString(monthlyTransactions.getYearMonthDate()
		.getMonth())));
	string.append(" ");
	string.append(monthlyTransactions.getYearMonthDate().getYear());
	return string.toString();
    }
}
