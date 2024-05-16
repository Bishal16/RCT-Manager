package dev.Mahathir.JwtSecurity;

import dev.Mahathir.JwtSecurity.process.ProcessRunner;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//
@SuppressWarnings("CallToPrintStackTrace")
@SpringBootApplication
@EnableDiscoveryClient
public class RtcMain {
	private final ProcessRunner processRunner;
	private final Scheduler scheduler;

	@Autowired
	public RtcMain(ProcessRunner processRunner,
				   Scheduler scheduler) {
		this.processRunner = processRunner;
		this.scheduler = scheduler;
	}


	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(RtcMain.class, args);
		RtcMain app = applicationContext.getBean(RtcMain.class);
		app.scheduleProcess(applicationContext, app);
	}


	private void scheduleProcess(ApplicationContext applicationContext, RtcMain app){
		try (InputStream inputStream = app.getClass().getClassLoader().getResourceAsStream("job.txt")) {
            assert inputStream != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String jobName;
                List<JobDetail> jobDetailList = new ArrayList<>();
                List<Trigger> triggerList = new ArrayList<>();
                while ((jobName = br.readLine()) != null) {
                    jobDetailList.add((JobDetail) applicationContext.getBean(jobName + "JobDetail"));
                    triggerList.add((Trigger) applicationContext.getBean(jobName + "Trigger"));
                    //System.out.println(jobName);
                }
                try {
                    processRunner.scheduleJob(scheduler, jobDetailList, triggerList);
                } catch (SchedulerException e) {
                    throw new RuntimeException(e);
                }

            }
        } catch (IOException e) {
			e.printStackTrace();
		}
	}

}
