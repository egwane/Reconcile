package com.egwane.finances.domain.builders;

import java.util.Set;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WriteException;

import org.springframework.context.ApplicationContext;

import com.egwane.context.AppContext;

@SuppressWarnings("unchecked")
public class HeaderLineBuilder implements Builder<HeaderLineBuilder> {
    private static final int FIRST_ROW = 0;
    private static ApplicationContext context = AppContext.getApplicationContext();
    private static Set<String> headerLabels = (Set<String>) context.getBean("headerLabels");

    private WritableSheet writableSheet;

    private HeaderLineBuilder() {
    }

    public static HeaderLineBuilder headerLineBuilder() {
	return new HeaderLineBuilder();
    }

    public HeaderLineBuilder withSheet(WritableSheet writableSheet) {
	this.writableSheet = writableSheet;
	return this;
    }

    public HeaderLineBuilder build() throws Exception {
	int column = 0;
	for (String headerLabel : headerLabels) {
	    Label label = new Label(column++, FIRST_ROW, headerLabel, buildHeaderFormat());
	    this.writableSheet.addCell(label);
	}
	return null;
    }

    /**
     * @return
     * @throws WriteException
     */
    private static final WritableCellFormat buildHeaderFormat() throws WriteException {
	WritableFont arial10ptBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
	WritableCellFormat arial10BoldFormat = new WritableCellFormat(arial10ptBold);
	arial10BoldFormat.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM);
	arial10BoldFormat.setAlignment(Alignment.CENTRE);
	return arial10BoldFormat;
    }
}
