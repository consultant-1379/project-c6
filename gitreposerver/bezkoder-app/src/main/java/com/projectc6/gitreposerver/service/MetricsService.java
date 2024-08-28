package com.projectc6.gitreposerver.service;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class MetricsService {
    private String baseAddr = "http://localhost:5000/?repo=";
    private String postAddr = "&repo_id=";

    @Autowired
    private MetricsRepository metricsRepository;

    private Logger logger;

    public GitMetrics fetchDBInfo(int repoID) {
        // Display repo ID
        List<GitMetrics> gitMetricsList = metricsRepository.getRepoByID(repoID);

        if (gitMetricsList.size() == 0) {
            return null;
        }

        return gitMetricsList.get(0);
    }


    public int createRepoInDB() throws IOException {
        return metricsRepository.saveRepo();
    }

    // Connect to Flask server
    public String callFlaskServer(String repoLink) {
        var restTemplate = new RestTemplate();
        int repoID = createRepoID(repoLink);

        String result = restTemplate.getForObject(baseAddr + repoLink + postAddr + repoID, String.class);

        logger.log(Level.FINE, result);

        return result;
    }

    public int createRepoID(String repoLink) {
        return repoLink.hashCode() & 0x7ffffff;
    }
}
