package infrastracture;

import domain.Job;
import domain.JobRepository;
import infrastructure.LogFileReader;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.junit.Assert.assertEquals;

public class LogFileReaderTest {

    @Test
    public void testLogParsing() throws IOException {
        // Create a temporary test log file with correct format
        String testFile = "test_logs.log";
        Files.write(Paths.get(testFile),
                "10:00:00,2001,START\n10:10:00,2001,END\n".getBytes(),
                StandardOpenOption.CREATE);

        // Initialize repository and log reader
        JobRepository repository = new JobRepository();
        LogFileReader reader = new LogFileReader();
        reader.readLogFile(testFile, repository);

        // Retrieve the job and verify duration
        Job job = repository.getOrCreateJob("2001");

        // Debugging output: Ensure timestamps are correctly set
        System.out.println("START Time: " + job.getStartTime());
        System.out.println("END Time: " + job.getEndTime());

        long duration = job.getDurationInSeconds();
        System.out.println("Computed Job Duration: " + duration);

        // Check if the duration matches the expected value
        assertEquals("Job duration should be 600 seconds.",600, duration);

        // Cleanup: Delete the test file after execution
        Files.deleteIfExists(Paths.get(testFile));
    }
}