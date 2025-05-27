package domain;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class JobTest {

    @Test
    public void testJobDurationCalculation() {
        Job job = new Job();
        job.setStartTime(LocalTime.of(10, 0, 0));
        job.setEndTime(LocalTime.of(10, 10, 0));

        assertEquals("The job duration should be 600 seconds.", 600, job.getDurationInSeconds());
    }


        @Test
    public void testJobWithoutEndTime() {
        Job job = new Job();
        job.setStartTime(LocalTime.of(10, 0, 0));

        assertEquals("The duration should be  0 if END is not setup", 0, job.getDurationInSeconds());
    }
}
