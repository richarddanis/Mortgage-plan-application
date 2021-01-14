package filereader;

import model.Prospect;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Specific file reader for {@link Prospect}.
 */
public class ProspectReader {

   private static final int FIRST_LINE = 1;
   private final ProspectResolver prospectResolver;

   public ProspectReader(ProspectResolver prospectResolver) {
      this.prospectResolver = prospectResolver;
   }

   /**
    * Read a file line by line and resolved in based on {@link ProspectResolver} strategy.
    * If the file contains an unexpected line the reader logs it and continues the process.
    * The first line is skipped.
    *
    * @param path file resource path
    * @return list of valid prospect
    */
   public List<Prospect> fromFile(String path) {
      List<Prospect> prospectList = new ArrayList<>();

      try (Stream<String> stream = Files.lines(Paths.get(path))) {
         prospectList = stream.skip(FIRST_LINE)
                 .map(this::generateProspect)
                 .filter(Objects::nonNull)
                 .collect(Collectors.toList());
      } catch (IOException e) {
         System.out.printf("Unexpected exception while read a file, filepath: %s, exception: %s%n", path, e);
      }

      return prospectList;
   }

   private Prospect generateProspect(String lines) {
      Prospect prospect = null;
      try{
         prospect = prospectResolver.mapFrom(lines);
      } catch (Exception e) {
         System.out.printf("Cannot resolve the given line: %s ,exception: %s%n", lines, e);
      }
      return prospect;
   }
}
