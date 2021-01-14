package report;

import model.MortgageCalculation;

import java.util.List;

public class ReportFacade {

    private final ReportWriter reportWriter;
    private final ReportGenerator reportGenerator;

    public ReportFacade(ReportWriter reportWriter, ReportGenerator reportGenerator) {
        this.reportWriter = reportWriter;
        this.reportGenerator = reportGenerator;
    }

    /**
     * Facade class which handles the report generation and file write.
     *
     * @param mortgageCalculationList lost of calculated mortgage
     */
    public void generateReport(List<MortgageCalculation> mortgageCalculationList) {
        final List<Report> generate = reportGenerator.generate(mortgageCalculationList);
        reportWriter.writeOutFile(generate);
    }
}
