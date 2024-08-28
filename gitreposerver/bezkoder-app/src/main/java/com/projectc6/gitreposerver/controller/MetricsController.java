package com.projectc6.gitreposerver.controller;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.service.DynamicTaskService;
import com.projectc6.gitreposerver.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
public class MetricsController {
    @Autowired
    MetricsService metricsService;

    @Autowired
    DynamicTaskService dynamicTaskService;

    private String check = "check";
    private String gitRepo = "GitRepo";
    private String dateListStr = "DateList";
    private String contributionsMap = "ContributionsMap";
    private String wrongLink = "wrongLink";
    private String indexPage = "index";


    @GetMapping(path = "/", produces = {"application/json", "application/xml"})
    public String homePage(Model model) {
        GitMetrics gitMetrics = new GitMetrics();
        List<String> dateList = new ArrayList<>();
        Map<String, String> contributionMap = new HashMap<>();

        model.addAttribute(wrongLink, false);
        model.addAttribute(check, false);
        model.addAttribute(gitRepo, gitMetrics);
        model.addAttribute(dateListStr, dateList);
        model.addAttribute(contributionsMap, contributionMap);

        return indexPage;
    }

    @GetMapping(path = "/searchInformation")
    public String searchRepo(@RequestParam String repoLink, Model model) throws SQLException, ClassNotFoundException, IOException {
        dynamicTaskService.stopCron();
        GitMetrics gitMetrics = metricsService.fetchDBInfo(metricsService.createRepoID(repoLink));

        if (gitMetrics != null) {
            model.addAttribute(check, true);
            model.addAttribute(gitRepo, gitMetrics);
            model.addAttribute(dateListStr, gitMetrics.getCommitDateList());
            model.addAttribute(contributionsMap, gitMetrics.getContributionsList());
            dynamicTaskService.startCron(repoLink);

            return indexPage;
        }

        if ((metricsService.callFlaskServer(repoLink)).equals("-1")) {
            model.addAttribute(wrongLink, true);

            return indexPage;
        }

        metricsService.createRepoInDB();
        dynamicTaskService.startCron(repoLink);

        gitMetrics = metricsService.fetchDBInfo(metricsService.createRepoID(repoLink));
        model.addAttribute(check, true);
        model.addAttribute(gitRepo, gitMetrics);
        model.addAttribute(dateListStr, gitMetrics.getCommitDateList());
        model.addAttribute(contributionsMap, gitMetrics.getContributionsList());

        return indexPage;
    }
}
