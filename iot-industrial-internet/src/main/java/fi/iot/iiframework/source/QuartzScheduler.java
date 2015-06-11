package fi.iot.iiframework.source;

import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzScheduler {

    private static Scheduler scheduler;
    private ReadJob job;

    public QuartzScheduler() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        job = (ReadJob) newJob(ReadJob.class)
                .withIdentity("job", "group")
                .build();
    }

    public void shutDown() throws SchedulerException {
        scheduler.shutdown();
    }

    public void schedule(final int interval, final Runnable r) throws SchedulerException {
        Trigger trigger = newTrigger()
                .withIdentity("trigger", "group")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(interval)
                        .repeatForever())
                .build();
        scheduler.scheduleJob((JobDetail) job, trigger);
    }

    public void cancel() throws SchedulerException {
        scheduler.unscheduleJob(triggerKey("trigger", "group"));
    }
}
