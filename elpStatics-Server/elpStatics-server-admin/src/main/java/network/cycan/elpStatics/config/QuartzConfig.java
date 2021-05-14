package network.cycan.elpStatics.config;

import network.cycan.elpStatics.job.StartOfDayJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail jobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(StartOfDayJob.class)
                .withIdentity("start_of_day", "start_of_day")
                .storeDurably()
                .build();
        return jobDetail;
    }

    @Bean
    public Trigger trigger() {
        Trigger trigger =TriggerBuilder.newTrigger().forJob(jobDetail()).withIdentity("start_of_day", "start_of_day")
                .startNow()
                // 每天0点执行
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 20 * * ?"))
                .build();
        return trigger;
    }
}
