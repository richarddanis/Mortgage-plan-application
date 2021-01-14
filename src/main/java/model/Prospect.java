package model;

import java.util.Objects;

/**
 * Class for representing a prospect.
 */
public class Prospect {

    private final String fullName;
    private final double totalLoan;
    private final double interest;
    private final int years;

    public Prospect(String fullName, double totalLoan, double interest, int years) {
        this.fullName = fullName;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public String getFullName() {
        return fullName;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public double getInterest() {
        return interest;
    }

    public int getYears() {
        return years;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prospect prospect = (Prospect) o;
        return Double.compare(prospect.totalLoan, totalLoan) == 0 && Double.compare(prospect.interest, interest) == 0 && years == prospect.years && Objects.equals(fullName, prospect.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, totalLoan, interest, years);
    }

    @Override
    public String toString() {
        return "Prospect{" +
                "fullName='" + fullName + '\'' +
                ", totalLoan=" + totalLoan +
                ", interest=" + interest +
                ", years=" + years +
                '}';
    }
}
