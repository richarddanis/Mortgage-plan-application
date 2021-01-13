package report;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Unit test for {@link ReportWriter}.
 */
public class ReportWriterTest {

    private File actualFile;

    @AfterEach
    public void clean() throws IOException {
        actualFile.delete();
    }

    @Test
    public void testShouldGenerateReportFile() throws IOException {
        final List<Report> reportList = Collections.singletonList(new Report("test", "test"));

        new ReportWriter().writeOutFile(reportList);
        Path actualFilePath = Paths.get("test-report.txt");
        actualFile = actualFilePath.toFile();

        Assertions.assertEquals("test", new String(Files.readAllBytes(actualFilePath)));
    }
}
