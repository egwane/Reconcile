package com.egwane.finances.files;

import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ TextFileScanner.class })
public class TextFileScannerTest {

    @Mock
    Scanner                 scanner;

//    @InjectMocks
//    private TextFileScanner textFileScanner = new TextFileScanner("file");

    @Test
    public final void testNextLine() {
//        textFileScanner.nextLine();

//        Mockito.verify(scanner).nextLine();
    }

}
