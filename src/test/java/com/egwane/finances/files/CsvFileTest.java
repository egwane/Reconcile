package com.egwane.finances.files;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.egwane.finances.domain.IReconcileTransaction;

@RunWith(MockitoJUnitRunner.class)
public class CsvFileTest {
    private final String    fileName = "dummy fileName";
    @Mock
    private TextFileScanner scanner;
    @Spy
    private CsvFile         csvFile  = new CsvFile(fileName);

    @Test
    public void testParseCsvFile_validLine() throws Exception {
        // doReturn... cause spy
        doReturn(scanner).when(csvFile).createScanner();

        // when... cause mock
        when(scanner.nextLine()).thenReturn("first line is ignored").thenReturn(
                "\"Sushi Shop\", 11/29/2013, -29.320000, \"Food: Restaurant\", \"\", \"\", 0\"");
        when(scanner.hasNextLine()).thenReturn(true).thenReturn(false);

        List<IReconcileTransaction> list = csvFile.parseCsvFile();

        assertEquals("list has 1 element", 1, list.size());
    }

    @Test
    public void testParseCsvFile_validLine_withNullChar() throws Exception {
        // doReturn... cause spy
        doReturn(scanner).when(csvFile).createScanner();

        // when... cause mock
        when(scanner.nextLine()).thenReturn("first line is ignored").thenReturn(
                "\"R�no" + '0' + "D�p�t\", 12/7/2012, -23.070000, \"House\", \"Accessoires pompe puisard\", \"\", 1\"");
        when(scanner.hasNextLine()).thenReturn(true).thenReturn(false);

        List<IReconcileTransaction> list = csvFile.parseCsvFile();

        assertEquals("list has 1 element", 1, list.size());
    }

    @Test
    public void testParseCsvFile_invalidLine() throws Exception {
        // doReturn... cause spy
        doReturn(scanner).when(csvFile).createScanner();

        // when... cause mock
        when(scanner.nextLine()).thenReturn("first line is ignored").thenReturn("incorrect line");
        when(scanner.hasNextLine()).thenReturn(true).thenReturn(false);

        List<IReconcileTransaction> list = csvFile.parseCsvFile();

        assertEquals("list has 0 element", 0, list.size());
    }

}
