package filereader;

import model.Prospect;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit test for {@link ProspectResolver}.
 */
public class ProspectResolverTest {

    private ProspectResolver resolver;

    @Before
    public void init() {
        resolver = new ProspectResolver();
    }

    @Test
    public void testShouldResolveLineWhenContainsCommaSeparatedCharacters(){
        String input = "FullName,1000,1,1";
        Prospect expected = new Prospect("FullName", 1000,1,1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShouldResolveLineWhenContainsQuoteSeparatedName(){
        String input = "\"Full,Name\",1000,1,1";
        Prospect expected = new Prospect("Full,Name", 1000,1,1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShouldResolveLineWhenLastCharacterContainsComma(){
        String input = "FullName,1000,1,1,";
        Prospect expected = new Prospect("FullName", 1000,1,1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShouldResolveLineWhenLineHasExtendedFields(){
        String input = "FullName,1000,1,1,extendField";
        Prospect expected = new Prospect("FullName", 1000, 1, 1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShouldResolveLineWhenLoanIsFloatNumber(){
        String input = "FullName, 1000.5,1,1";
        Prospect expected = new Prospect("FullName", 1000.5, 1, 1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShouldResolveLineWhenInterestIsFloatNumber(){
        String input = "fullName, 1000,1.5,1";
        Prospect expected = new Prospect("fullName", 1000, 1.5, 1);

        Prospect actual = resolver.mapFrom(input);

        Assertions.assertEquals(expected, actual);
    }

   @Test(expected = NumberFormatException.class)
    public void testShouldThrowExceptionWhenLineContainsUnexpectedToken(){
        String input = ",fullName, 1000,1.5,1";

        resolver.mapFrom(input);
    }

    @Test(expected = NumberFormatException.class)
    public void testShouldThrowExceptionWhenNumericFieldsContainsUnexpectedToken(){
        String input = ",fullName, 100d0,1d.5,1a";

        resolver.mapFrom(input);
    }
}
