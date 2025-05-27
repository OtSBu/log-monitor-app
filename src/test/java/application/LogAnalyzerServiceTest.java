package application;

import domain.Job;
import domain.JobRepository;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LogAnalyzerServiceTest {

    @Test
    public void testWarningThreshold() {
        JobRepository repository = new JobRepository();
        Job job = repository.getOrCreateJob("1001");
        job.setStartTime(java.time.LocalTime.of(10, 0, 0));
        job.setEndTime(java.time.LocalTime.of(10, 6, 0)); // 6 min

        LogAnalyzerService service = new LogAnalyzerService();
        List<String> results = service.analyze(repository);

        assertTrue("The job should generate a warning.", results.get(0).contains("WARNING"));
    }

    @Test
    public void testErrorThreshold() {
        JobRepository repository = new JobRepository();
        Job job = repository.getOrCreateJob("1002");
        job.setStartTime(java.time.LocalTime.of(10, 0, 0));
        job.setEndTime(java.time.LocalTime.of(10, 11, 0)); // 11 min

        LogAnalyzerService service = new LogAnalyzerService();
        List<String> results = service.analyze(repository);

        assertTrue("JThe job should generate an error.", results.get(0).contains("ERROR"));
    }
}
