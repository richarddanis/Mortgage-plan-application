package report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Writer for the report generation.
 */
public class ReportWriter {

    private static final String FILE_FORMAT = ".txt";

    public ReportWriter() {
    }

    /**
     * Write the given report into file under the /resource folder.
     *
     * @param report list of {@link Report}.
     */
    public void writeOutFile(List<Report> report) {
        report.forEach(ReportWriter::writeToTextFile);
    }

    private static void writeToTextFile(Report report) {
        try {
            Files.write(Paths.get(report.getFileName() + FILE_FORMAT), report.getReport().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.printf("Could not write the given report into file: %s exception: %s%n", report, e);
        }
    }
}
