package fi.iot.iiframework.source;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ReadJob implements Job {

    public ReadJob() {
        // Instances of Job must have a public no-argument constructor.  
    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        JobDataMap data = context.getMergedJobDataMap();
        System.out.println("Task was run.");
    }
}