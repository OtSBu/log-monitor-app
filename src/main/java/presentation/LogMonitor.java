package presentation;

import application.LogAnalyzerService;
import domain.JobRepository;
import infrastructure.LogFileReader;
import infrastructure.LogFileWriter;

public class LogMonitor {

    private static final String LOG_FILE = "logs.log";
    private static final String REPORT_FILE = "log_report.txt";

    public static void main(String[] args) {

        // Repository to store job processing times
        JobRepository repository = new JobRepository();

        // Initialize components
        LogFileReader fileReader = new LogFileReader();
        LogAnalyzerService analyzerService = new LogAnalyzerService();
        LogFileWriter fileWriter = new LogFileWriter();

        //  1. Read log file and extract job data
        fileReader.readLogFile(LOG_FILE, repository);

        // 2. Analyze job durations and identify delays
        var results = analyzerService.analyze(repository);

        // 3. Write results to a report file
        System.out.println("Writing in log_report.txt with " + results.size() + " lines.");

        fileWriter.writeReport(REPORT_FILE, results);

        System.out.println("Analysis complete! Results saved to '" + REPORT_FILE + "'.");
    }
}
