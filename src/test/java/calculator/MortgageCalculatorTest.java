package calculator;

import model.MortgageCalculation;
import model.Prospect;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test for {@link MortgageCalculator}.
 */
public class MortgageCalculatorTest {

    private MortgageCalculator calculator;

    @BeforeEach
    public void init(){
        calculator = new MortgageCalculator();
    }

    @Test
    public void testShouldCalculateTheMortgage() {
        List<Prospect> prospectList = Arrays.asList(new Prospect("test", 100.5, 100.2, 3), new Prospect("test-2", 200,50, 10));
        List<MortgageCalculation> expected = Arrays.asList(new MortgageCalculation("test", 100.5, 3, 8.887108378218747), new MortgageCalculation("test-2", 200.0, 10, 8.39593952209981));

        List<MortgageCalculation> actual = calculator.calculate(prospectList);

        Assertions.assertEquals(expected, actual);
    }
}
