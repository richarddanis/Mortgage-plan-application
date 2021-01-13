package report;

import model.MortgageCalculation;

import java.util.List;

public class ReportFacade {

    /**
     * Facade class which handles the report generation and file write.
     *
     * @param mortgageCalculationList lost of calculated mortgage
     */
    public void generateReport(List<MortgageCalculation> mortgageCalculationList) {
        List<Report> generate = new ReportGenerator().generate(mortgageCalculationList);
        new ReportWriter().writeOutFile(generate);
    }
}
