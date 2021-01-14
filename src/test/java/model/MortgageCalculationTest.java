package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link MortgageCalculation}.
 */
public class MortgageCalculationTest {

    private MortgageCalculation mortgageCalculation;

    @BeforeEach
    public void init(){
        mortgageCalculation = new MortgageCalculation("test", 2.5,3,200.5);
    }

    @Test
    public void testShouldRetrieveReportSentence() {
        String expected = "test wants to borrow 2.5 € for a period of 3 years and pay 200.5 € each month";
        Assertions.assertEquals(expected, mortgageCalculation.getReportSentence());
    }

    @Test
    public void testShouldRetrieveGeneratedFileName() {
        String expected = "test-report";
        Assertions.assertEquals(expected, mortgageCalculation.getReportFileName());
    }
}
