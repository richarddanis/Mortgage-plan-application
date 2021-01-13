package report;

import model.MortgageCalculation;

import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {

    private static final String REPORT_SENTENCE = "%s wants to borrow %d € for a period of %d years and pay %d € each month";

    public ReportGenerator() {
    }

    /**
     * Generate a report object with the report sentence and the folder name.
     *
     * @param mortgageCalculations mortgage calculation list
     * @return list of generated report
     */
    public List<Report> generate(List<MortgageCalculation> mortgageCalculations){
        return mortgageCalculations.stream()
                .map(ReportGenerator::generateReport).collect(Collectors.toList());
    }

    private static Report generateReport(MortgageCalculation mortgageCalculation) {
        return new Report(String.format(REPORT_SENTENCE, mortgageCalculation.getCustomerName(), mortgageCalculation.getPeriod(), mortgageCalculation.getBorrow(), mortgageCalculation.getPaymentPerMonth()), mortgageCalculation.getCustomerName());
    }
}
