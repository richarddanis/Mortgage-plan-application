package report;

import model.MortgageCalculation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

/**
 * Unit test for {@link ReportFacade}.
 */
public class ReportFacadeTest {

    private ReportFacade facade;

    private ReportWriter mockWriter;
    private ReportGenerator mockReportGenerator;

    @BeforeEach
    public void init(){
        mockWriter = Mockito.mock(ReportWriter.class);
        mockReportGenerator = Mockito.mock(ReportGenerator.class);
        facade = new ReportFacade(mockWriter, mockReportGenerator);
    }

    @Test
    public void testShouldCallExpectedMethods(){
        List<MortgageCalculation> input = Collections.singletonList(Mockito.mock(MortgageCalculation.class));
        List<Report> reports = Collections.singletonList(Mockito.mock(Report.class));

        Mockito.when(mockReportGenerator.generate(Mockito.anyListOf(MortgageCalculation.class)))
                .thenReturn(reports);

        facade.generateReport(input);

        Mockito.verify(mockReportGenerator, Mockito.only()).generate(input);
        Mockito.verify(mockWriter, Mockito.only()).writeOutFile(reports);
    }
}
