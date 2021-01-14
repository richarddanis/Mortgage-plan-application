package filereader;

import model.Prospect;

public class ProspectResolver {

    private static final String QUOTE = "\"";
    private static final String COMMA = ",";
    private static final String EMPTY_STRING = "";

    public ProspectResolver() {
    }

    /**
     * Mapping the given line to {@link Prospect}.
     * Exception is thrown if the line contains unexpected character or token.
     *
     * @param line line which is contains prospect fields
     * @return generated prospect
     */
    public Prospect mapFrom(String line) {
        LinePart nameSection = resolveFullName(line);
        LinePart remainLineBeforeLoan = resolveNumericValue(nameSection.getRemainLine());
        LinePart remainLineBeforeInterest = resolveNumericValue(remainLineBeforeLoan.getRemainLine());
        LinePart remainLifeBeforeYears = resolveNumericValue(remainLineBeforeInterest.getRemainLine());

        String fullName = nameSection.getResult();
        double totalLoan = Double.parseDouble(remainLineBeforeLoan.getResult());
        double interest = Double.parseDouble(remainLineBeforeInterest.getResult());
        int years = Integer.parseInt(remainLifeBeforeYears.getResult());

        return new Prospect(fullName, totalLoan, interest, years);
    }

    private LinePart resolveFullName(String line) {
        return line.startsWith(QUOTE) ? resolveQuotesName(line) : resolveName(line);
    }

    private LinePart resolveQuotesName(String line){
        String fullNameBetweenQuetes = line.split(QUOTE)[1];
        String buildRegexp = QUOTE + fullNameBetweenQuetes + QUOTE + COMMA;
        String remainLine = line.replaceAll(buildRegexp, EMPTY_STRING);
        return new LinePart(remainLine, fullNameBetweenQuetes);
    }

    private LinePart resolveName(String line){
        return new LinePart(line.substring(line.indexOf(COMMA) + 1), line.substring(0, line.indexOf(COMMA)));
    }

    private LinePart resolveNumericValue(String line){
        return line.contains(COMMA) ? new LinePart(line.substring(line.indexOf(COMMA) + 1), line.substring(0, line.indexOf(COMMA))) : new LinePart(EMPTY_STRING, line.substring(line.indexOf(COMMA) + 1));
    }

    /**
     * Inner class which is store the remain line and the resolved character(s).
     */
    static class LinePart {
        private final String remainLine;
        private final String result;

        public LinePart(String remainLine, String result) {
            this.remainLine = remainLine;
            this.result = result;
        }

        public String getRemainLine() {
            return remainLine;
        }

        public String getResult() {
            return result;
        }
    }
}
