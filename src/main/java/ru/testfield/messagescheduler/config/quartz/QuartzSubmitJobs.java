package ru.testfield.messagescheduler.config.quartz;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.Calendar;

@Configuration
public class QuartzSubmitJobs {
    private static final String CRON_EVERY_FIVE_MINUTES = "0/2 * * ? * * *";

    @Bean(name = "messagingJobBean")
    public JobDetailFactoryBean messagingJob() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setName("Member Statistics Job");
        factoryBean.setJobClass(MessagingJob.class);
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean(name = "messagingJobTriggerBean")
    public CronTriggerFactoryBean triggerMemberStats(@Qualifier("messagingJobBean") JobDetail jobDetail) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression(CRON_EVERY_FIVE_MINUTES);
        factoryBean.setStartTime(calendar.getTime());
        factoryBean.setStartDelay(0L);
        factoryBean.setName("Messaging Trigger");
        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
        return factoryBean;
    }
}
