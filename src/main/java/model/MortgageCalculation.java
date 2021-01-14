package model;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class for representing an mortgage report.
 */
public class MortgageCalculation {

    private static final String REPORT_SENTENCE = "%s wants to borrow %s € for a period of %d years and pay %s € each month";
    private static final String FILE_NAME_PLACE_HOLDER = "-report";

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

    public String getCustomerName() {
        return customerName;
    }

    public double getBorrow() {
        return borrow;
    }

    public int getPeriod() {
        return period;
    }

    public double getPaymentPerMonth() {
        return paymentPerMonth;
    }

    public String getReportSentence(){
        DecimalFormat doubleDecimalFormat = new DecimalFormat("#.##");
        return String.format(REPORT_SENTENCE, customerName, doubleDecimalFormat.format(borrow), period, doubleDecimalFormat.format(paymentPerMonth));
    }

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
        return "MortageReport{" +
                "customerName='" + customerName + '\'' +
                ", borrow=" + borrow +
                ", period=" + period +
                ", paymentPerMonth=" + paymentPerMonth +
                '}';
    }
}
