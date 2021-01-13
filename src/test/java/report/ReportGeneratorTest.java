package report;

import model.MortgageCalculation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * Unit test for {@link ReportGenerator}.
 */
public class ReportGeneratorTest {

    private final ReportGenerator reportGenerator = new ReportGenerator();

    @Test
    public void testShouldGenerateReport(){
        final Report expect = new Report("Test User wants to borrow 3 € for a period of 3 years and pay 3 € each month", "Test User");

        List<Report> actual = reportGenerator.generate(Collections.singletonList(new MortgageCalculation("Test User", 3, 3, 3)));

        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Test User wants to borrow 3 € for a period of 3 years and pay 3 € each month", actual.get(0).getReport());
        Assertions.assertEquals("Test User-report", actual.get(0).getFileName());
        Assertions.assertEquals(expect, actual.get(0));
    }
}
