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

    public List<MortgageCalculation> calculate(List<Prospect> prospectList) {
        return prospectList.stream()
                .map(this::calculateMortgage)
                .collect(Collectors.toList());
    }

    private MortgageCalculation calculateMortgage(Prospect prospect) {
        double monthlyRate = calculateMonthlyRate(prospect.getInterest());
        double result = (prospect.getTotalLoan() * (monthlyRate * interestPower(monthlyRate,prospect.getYears() * MONTH_IN_YEAR)))
                / (interestPower(monthlyRate, prospect.getYears() * MONTH_IN_YEAR) - 1);
        return new MortgageCalculation(prospect.getFullName(), prospect.getTotalLoan(), prospect.getYears(), result);
    }

    private double interestPower(double interest, int numberOfMonths){
        double result = interest + 1;
        double multiplier = result;
        result *= IntStream.range(0, numberOfMonths - 1).mapToDouble(i -> multiplier).reduce(1, (a, b) -> a * b);
        return result;
    }

    private double calculateMonthlyRate(double rate) {
        return rate / PERCENTAGE / MONTH_IN_YEAR;
    }
}
