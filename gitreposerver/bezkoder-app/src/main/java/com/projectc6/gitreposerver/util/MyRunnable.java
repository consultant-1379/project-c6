package com.projectc6.gitreposerver.util;

import com.projectc6.gitreposerver.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@EnableAsync
public class MyRunnable implements Runnable {
    @Autowired
    private MetricsService metricsService = (MetricsService) GetBeanUtil.getBean("metricsService");

    private Logger logger;
    private String repo;

    @Override
    public void run() {
        logger.log(Level.FINE, "DynamicTask query the git repo link at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        metricsService.callFlaskServer(repo);

        try {
            metricsService.createRepoInDB();

            logger.log(Level.FINE, "IN RUNNABLE THREAD: " + metricsService.fetchDBInfo(metricsService.createRepoID(repo)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyRunnable setParam(String repoLink) {
        repo = repoLink;

        return this;
    }
}
