package domain;

import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class Job {
        private final String pid = UUID.randomUUID().toString();  ;
        private LocalTime startTime;
        private LocalTime endTime;

        /**
         * Computes the duration of the job in seconds.
         * @return Duration in seconds or 0 if start/end times are missing.
         */
        public long getDurationInSeconds() {
        return (startTime != null && endTime != null) ? Duration.between(startTime, endTime).getSeconds() : 0;
    }

}
