package filereader;

import model.Prospect;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Unit test for {@link ProspectReader}.
 */
public class ProspectReaderTest {

    private ProspectReader reader;
    private ProspectResolver mockResolver;

    private Writer writer;
    private File file;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void init() throws IOException {
        mockResolver = Mockito.mock(ProspectResolver.class);
        reader = new ProspectReader(mockResolver);
        file = folder.newFile("unit-test.txt");
        writer = new FileWriter(file);
    }

    @Test
    public void testShouldReadAndMapGivenFileToProspectObject() throws IOException {
        Mockito.when(mockResolver.mapFrom(Mockito.anyString())).thenReturn(new Prospect("test", 2,3.0,4));
        writeStringToFile("first line \n test,2,3,4");

        List<Prospect> expected = Collections.singletonList(new Prospect("test", 2, 3.0, 4));

        List<Prospect> actual = reader.fromFile(file.getAbsolutePath());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(mockResolver, Mockito.only()).mapFrom(Mockito.anyString());
    }

    @Test
    public void testShouldSkipTheFirstLineOfFile() throws IOException {
        writeStringToFile("first line");

        List<Prospect> actual = reader.fromFile(file.getAbsolutePath());

        Assertions.assertEquals(Collections.emptyList(), actual);
        Mockito.verify(mockResolver, Mockito.never()).mapFrom(Mockito.anyString());
    }

    @Test
    public void testShouldFilterTheEmptyObject() throws IOException {
        Mockito.when(mockResolver.mapFrom(Mockito.anyString())).thenReturn(new Prospect("test", 2,3.0,4)).thenReturn(null);
        writeStringToFile("first line \n test,2,3,4 \n test,2,3,4");

        List<Prospect> expected = Collections.singletonList(new Prospect("test", 2, 3.0, 4));

        List<Prospect> actual = reader.fromFile(file.getAbsolutePath());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(mockResolver, Mockito.times(2)).mapFrom(Mockito.anyString());
    }

    @Test
    public void testShouldCatchAndContinueTheReadWhenResolverThrownAnException() throws IOException {
        Mockito.when(mockResolver.mapFrom(Mockito.anyString())).thenThrow(new RuntimeException()).thenReturn(new Prospect("test-2", 6,5.0,4));
        writeStringToFile("first line \n test,2,3,4 \n test-2,6,5,4");
        List<Prospect> expected = Collections.singletonList(new Prospect("test-2", 6.0, 5.0, 4));


        List<Prospect> actual = reader.fromFile(file.getAbsolutePath());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(mockResolver, Mockito.times(2)).mapFrom(Mockito.anyString());
    }

    private void writeStringToFile(String line) throws IOException {
        writer.write(line);
        writer.flush();
        writer.close();
    }
}
