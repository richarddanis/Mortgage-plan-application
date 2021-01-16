package report;

import model.MortgageCalculation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

/**
 * Writer for the report generation.
 */
public class ReportWriter {

    private static final Logger LOGGER = Logger.getLogger(ReportWriter.class.getName());
    private static final String FILE_FORMAT = ".txt";

    public ReportWriter() {
    }

    /**
     * Write the given report into txt files separated by user name.
     */
    public void writeOutFile(List<MortgageCalculation> mortgages) {
        mortgages.forEach(mortgage -> writeToTextFile(mortgage.getReportFileName(), mortgage.getReportSentence()));
    }

    private void writeToTextFile(String fileName, String sentence) {
        try {
            Files.write(Paths.get(fileName + FILE_FORMAT), sentence.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOGGER.warning(String.format("Could not write the given report into file: %s , sentence: %s,  exception: %s%n", fileName, sentence, e));
        }
    }
}
