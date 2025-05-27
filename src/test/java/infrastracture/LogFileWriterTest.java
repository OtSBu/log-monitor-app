package infrastracture;

import infrastructure.LogFileWriter;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LogFileWriterTest {

    @Test
    public void testReportWriting() throws IOException {
        String testFile = "test_report.txt";
        LogFileWriter writer = new LogFileWriter();

        List<String> data = List.of("ERROR: The job 3001 took 700 seconds.");
        writer.writeReport(testFile, data);

        List<String> lines = Files.readAllLines(Paths.get(testFile));

        assertEquals("The report should contain the proper message.", data.get(0), lines.get(0));

        Files.deleteIfExists(Paths.get(testFile));
    }

}
