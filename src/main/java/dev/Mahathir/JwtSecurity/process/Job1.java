package dev.Mahathir.JwtSecurity.process;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class Job1 implements Job {

    @Bean
    public JobDetailFactoryBean job1JobDetail() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(Job1.class);
        factory.setDurability(true);
        return factory;
    }

    @Bean
    public SimpleTriggerFactoryBean job1Trigger(JobDetail job1JobDetail) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        factory.setJobDetail(job1JobDetail);
        factory.setRepeatInterval(10000); // Repeat every 10 seconds
        factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factory;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        System.out.println("Job executed at: " + formattedTime);
        System.out.println("world ");
    }
}
