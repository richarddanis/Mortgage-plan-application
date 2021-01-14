package model;

import java.util.Objects;

/**
 * Class for representing an mortgage report.
 */
public class MortgageCalculation {

    private final String customerName;
    private final long borrow;
    private final int period;
    private final long paymentPerMonth;

    public MortgageCalculation(String customerName, long borrow, int period, long paymentPerMonth) {
        this.customerName = customerName;
        this.borrow = borrow;
        this.period = period;
        this.paymentPerMonth = paymentPerMonth;
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getBorrow() {
        return borrow;
    }

    public int getPeriod() {
        return period;
    }

    public long getPaymentPerMonth() {
        return paymentPerMonth;
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
