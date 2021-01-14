package calculator;

import model.MortgageCalculation;
import model.Prospect;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MortgageCalculator {

    private static final int MONTH_IN_YEAR = 12;
    private static final int PERCENTAGE = 100;

    public MortgageCalculator() {
    }

    /**
     * Calculate the mortgage.
     *
     * @param prospectList list of read prospects
     * @return list of mortgage object
     */
    public List<MortgageCalculation> calculate(List<Prospect> prospectList) {
        return prospectList.stream()
                .map(this::calculateMortgage)
                .collect(Collectors.toList());
    }

    private MortgageCalculation calculateMortgage(Prospect prospect) {
        double monthlyRate = calculateMonthlyRate(prospect.getInterest());
        double paymentPerMonth = (prospect.getTotalLoan() * (monthlyRate * interestPower(monthlyRate, numberOfMonths(prospect.getYears()))))
                / (interestPower(monthlyRate, numberOfMonths(prospect.getYears())) - 1);
        return new MortgageCalculation(prospect.getFullName(), prospect.getTotalLoan(), prospect.getYears(), paymentPerMonth);
    }

    private int numberOfMonths(int years) {
        return years * MONTH_IN_YEAR;
    }

    private double interestPower(double interest, int numberOfMonths){
        double result = interest + 1;
        double multiplier = result;
        result *= IntStream.range(0, numberOfMonths - 1).mapToDouble(i -> multiplier).reduce(1, (a, b) -> a * b);
        return result;
    }

    private double calculateMonthlyRate(double interestRate) {
        return interestRate / PERCENTAGE / MONTH_IN_YEAR;
    }
}
