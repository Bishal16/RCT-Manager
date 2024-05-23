package dev.Mahathir.JwtSecurity.process;

import dev.Mahathir.JwtSecurity.config.JwtService;
import dev.Mahathir.JwtSecurity.entity.TokenBlacklist;
import dev.Mahathir.JwtSecurity.repo.TokenBlackListRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@Component
public class Job3 implements Job {

    private final TokenBlackListRepo tokenBlackListRepo;

    @Autowired
    public Job3(TokenBlackListRepo tokenBlackListRepo) {
        this.tokenBlackListRepo = tokenBlackListRepo;
    }

    public Job3() {
        this.tokenBlackListRepo = null;
    }


    @Bean
    public JobDetailFactoryBean job3JobDetail() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(Job3.class);
        factory.setDurability(true);
        return factory;
    }

    @Bean
    public SimpleTriggerFactoryBean job3Trigger(JobDetail job3JobDetail) {
        SimpleTriggerFactoryBean factory = new SimpleTriggerFactoryBean();
        factory.setJobDetail(job3JobDetail);
        factory.setRepeatInterval(10000); // Repeat every 10 seconds
        factory.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        return factory;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<TokenBlacklist> blacklistedTokens = tokenBlackListRepo.findAll();

        for (TokenBlacklist tokenEntity : blacklistedTokens) {
            String token = tokenEntity.getToken();
            if (JwtService.isTokenExpired(token)) {
                tokenBlackListRepo.delete(tokenEntity);
            }
        }
    }
}
