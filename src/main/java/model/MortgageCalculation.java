package model;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class for representing an mortgage calculation.
 */
public class MortgageCalculation {

    private static final String REPORT_SENTENCE = "%s wants to borrow %s € for a period of %d years and pay %s € each month";
    private static final String FILE_NAME_PLACE_HOLDER = "-report";
    private static final DecimalFormat DOUBLE_DECIMAL_FORMAT = new DecimalFormat("#.##");

    private final String customerName;
    private final double borrow;
    private final int period;
    private final double paymentPerMonth;

    public MortgageCalculation(String customerName, double borrow, int period, double paymentPerMonth) {
        this.customerName = customerName;
        this.borrow = borrow;
        this.period = period;
        this.paymentPerMonth = paymentPerMonth;
    }

    /**
     * Retrieve the mortgage report sentence.
     */
    public String getReportSentence(){
        return String.format(REPORT_SENTENCE, customerName, DOUBLE_DECIMAL_FORMAT.format(borrow), period, DOUBLE_DECIMAL_FORMAT.format(paymentPerMonth));
    }

    /**
     * Retrieve the report file name.
     * Example: user-report
     */
    public String getReportFileName(){
        return customerName + FILE_NAME_PLACE_HOLDER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MortgageCalculation that = (MortgageCalculation) o;
        return borrow == that.borrow && period == that.period && paymentPerMonth == that.paymentPerMonth && Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, borrow, period, paymentPerMonth);
    }

    @Override
    public String toString() {
        return "MortgageReport{" +
                "customerName='" + customerName + '\'' +
                ", borrow=" + borrow +
                ", period=" + period +
                ", paymentPerMonth=" + paymentPerMonth +
                '}';
    }
}
