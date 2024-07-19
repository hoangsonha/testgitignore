package com.hsh.demo.demoquazt;

import com.hsh.demo.demoquazt.job.BootJob;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.quartz.*;

@SpringBootApplication
public class DemoQuaztApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoQuaztApplication.class, args);


        SchedulerFactory sf = new StdSchedulerFactory();

        try {
            Scheduler scheduler = sf.getScheduler();

            JobDetail jobDetail = JobBuilder.newJob().ofType(BootJob.class).withIdentity("RUN QUARTZ", "JOB GROUP").withDescription("Description job...").storeDurably(true).build();
            String time = "* * * ? * *"; // 10s
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("RUN QUARTZ", "JOB GROUP").startNow().withSchedule(CronScheduleBuilder.cronSchedule(time)).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
