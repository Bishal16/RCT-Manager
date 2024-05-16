package dev.Mahathir.JwtSecurity.process;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.List;

@Configuration
public class ProcessRunner {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setAutoStartup(false);
        return schedulerFactoryBean;
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        return schedulerFactoryBean().getScheduler();
    }

    public void scheduleJob(Scheduler scheduler, List<JobDetail> jobDetails, List<Trigger> triggers) throws SchedulerException{
        for (int i = 0; i < jobDetails.size(); i++) {
            JobDetail jobDetail = jobDetails.get(i);
            Trigger trigger = triggers.get(i);
            scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();
    }

}
