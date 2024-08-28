package com.projectc6.gitreposerver.service;

import com.projectc6.gitreposerver.util.DynamicConfiguration;
import com.projectc6.gitreposerver.util.MyRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DynamicTaskService {
    @Autowired
    private DynamicConfiguration dynamicConfiguration;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;
    private Logger logger;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        return new ThreadPoolTaskScheduler();
    }

    public void startCron(String repoLink){
        future = threadPoolTaskScheduler.schedule(new MyRunnable().setParam(repoLink), triggerContext ->
                new CronTrigger(dynamicConfiguration.getTimeInterval()).nextExecutionTime(triggerContext));

        logger.log(Level.FINE, "DynamicTask.startTimeExecutor");
    }


    public void stopCron(){
        if(future!=null){
            future.cancel(true);
        }

        logger.log(Level.FINE,"DynamicTask.stopTimeExecutor");
    }
}
