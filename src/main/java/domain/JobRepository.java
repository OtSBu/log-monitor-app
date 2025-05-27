package domain;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JobRepository {

    private final Map<String, Job> jobs = new HashMap<>();

    /**
     * Retrieves or creates a job entry based on the process ID.
     * @param pid Process ID.
     * @return The Job object associated with the PID.
     */
    public Job getOrCreateJob(String pid) {
        return jobs.computeIfAbsent(pid, k -> new Job());
    }
}
