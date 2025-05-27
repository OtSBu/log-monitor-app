package infrastructure;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LogFileWriter {

    /**
     * Writes the analysis results to a file.
     * @param filePath The output file path.
     * @param results A list containing warning/error messages about job durations.
     */
    public void writeReport(String filePath, List<String> results) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        } catch (Exception e) {
            System.err.println("Error when writing the report: " + e.getMessage());
        }
    }
}
