package infrastructure;

import domain.Job;
import domain.JobRepository;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class LogFileReader {
    /**
     * Reads the log file and extracts job processing details.
     *
     * @param filePath   Path to the log file.
     * @param repository Object to store job details.
     */
    public void readLogFile(String filePath, JobRepository repository) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read: " + line);
                // Split the log entry into its components (timestamp, process ID, status)
                String[] parts = line.trim().split("\\s*,\\s*");
                System.out.println("Split result: " + Arrays.toString(parts)); // Debugging

                if (parts.length != 3) {
                    System.err.println("Invalid line format: " + line);
                    continue;
                }
                LocalTime timestamp = LocalTime.parse(parts[0], DateTimeFormatter.ofPattern("HH:mm:ss"));
                String pid = parts[1];
                String status = parts[2];

                Job job = repository.getOrCreateJob(pid);
                if ("START".equals(status)) {
                    job.setStartTime(timestamp);
                } else if ("END".equals(status)) {
                    job.setEndTime(timestamp);
                }
            }
            System.out.println("Finished reading log file.");
        } catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
