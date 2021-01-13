package report;

import java.util.Objects;

/**
 * Class for representing a report.
 */
public class Report {

    private static final String FILE_NAME_PLACE_HOLDER = "-report";

    private final String report;
    private final String fileWithFormat;

    public Report(String report, String fileName) {
        this.report = report;
        this.fileWithFormat =  fileName + FILE_NAME_PLACE_HOLDER;
    }

    public String getReport() {
        return report;
    }

    public String getFileName() {
        return fileWithFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report1 = (Report) o;
        return Objects.equals(report, report1.report) && Objects.equals(fileWithFormat, report1.fileWithFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(report, fileWithFormat);
    }

    @Override
    public String toString() {
        return "Report{" +
                "report='" + report + '\'' +
                ", fileWithFormat='" + fileWithFormat + '\'' +
                '}';
    }
}
