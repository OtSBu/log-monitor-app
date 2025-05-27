package application;

import domain.Job;
import domain.JobRepository;

import java.util.ArrayList;
import java.util.List;

public class LogAnalyzerService {

    private static final int WARNING_THRESHOLD = 5 * 60;
    private static final int ERROR_THRESHOLD = 10 * 60;

    /**
     * Analyzes job durations and generates warnings/errors if needed.
     * @param repository The repository containing all job data.
     * @return A list of warning/error messages based on job execution times.
     */
    public List<String> analyze(JobRepository repository) {
        List<String> results = new ArrayList<>();

        for (Job job : repository.getJobs().values()) {
            long duration = job.getDurationInSeconds();

            // Identification of jobs exceeding defined thresholds
            if (duration > ERROR_THRESHOLD) {
                results.add("ERROR: Job " + job.getPid() + " took " + duration + " seconds.");
            } else if (duration > WARNING_THRESHOLD) {
                results.add("WARNING: Job " + job.getPid() + " took " + duration + " seconds.");
            }
        }
        return results;
    }
}
