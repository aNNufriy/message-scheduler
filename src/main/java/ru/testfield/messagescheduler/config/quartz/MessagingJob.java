package ru.testfield.messagescheduler.config.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class MessagingJob implements Job {
        @Override
        public void execute(JobExecutionContext context) {
//            System.out.println("hello, world");
        }
}
