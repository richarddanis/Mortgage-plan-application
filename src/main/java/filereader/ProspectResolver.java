package filereader;

import model.Prospect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> split = new ArrayList<>();

        if (line.startsWith(QUOTE)) {
            String fullNameBetweenQuetes = line.split(QUOTE)[1];
            String buildRegexp = QUOTE + fullNameBetweenQuetes + QUOTE + COMMA;
            line = line.replaceAll(buildRegexp, EMPTY_STRING);
            split.add(fullNameBetweenQuetes);
        }

        split.addAll(Arrays.stream(line.split(COMMA)).collect(Collectors.toList()));
        return buildProspect(split);
    }

    private Prospect buildProspect(List<String> split) {
        String fullName = split.get(0);
        double totalLoan = Double.parseDouble(split.get(1));
        double interest = Double.parseDouble(split.get(2));
        int years = Integer.parseInt(split.get(3));

        return new Prospect(fullName, totalLoan, interest, years);
    }
}
