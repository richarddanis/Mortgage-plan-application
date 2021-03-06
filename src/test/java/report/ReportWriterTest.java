package report;

import model.MortgageCalculation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Unit test for {@link ReportWriter}.
 */
public class ReportWriterTest {

    private File actualFile;

    @AfterEach
    public void clean() {
        actualFile.delete();
    }

    @Test
    public void testShouldGenerateReportFile() throws IOException {
        new ReportWriter().writeOutFile(Collections.singletonList(new MortgageCalculation("test", 200.5, 3, 2.5)));
        Path actualFilePath = Paths.get("test-report.txt");
        actualFile = actualFilePath.toFile();

        Assertions.assertEquals("test wants to borrow 200.5 € for a period of 3 years and pay 2.5 € each month", new String(Files.readAllBytes(actualFilePath)));
    }
}
