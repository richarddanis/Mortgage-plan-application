import calculator.MortgageCalculator;
import filereader.ProspectReader;
import filereader.ProspectResolver;
import report.ReportWriter;

import java.util.Optional;

/**
 * Entry point of the application.
 */
public class Main {

    private static final String FILE_PATH = "prospects.txt";

    public static void main(String[] args) {
        Optional.ofNullable(FILE_PATH)
                .map(path -> new ProspectReader(new ProspectResolver()).fromFile(path))
                .map(prospects -> new MortgageCalculator().calculate(prospects))
                .ifPresent(mortgages -> new ReportWriter().writeOutFile(mortgages));
    }
}
