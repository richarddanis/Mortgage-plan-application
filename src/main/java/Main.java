import report.ReportFacade;

import java.util.Collections;

/**
 * Entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
       new ReportFacade().generateReport(Collections.emptyList());
    }
}
