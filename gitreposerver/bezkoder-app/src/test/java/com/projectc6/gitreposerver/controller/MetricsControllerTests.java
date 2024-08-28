package com.projectc6.gitreposerver.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.springframework.ui.Model;

class MetricsControllerTests {
    private MetricsController metricsController = new MetricsController();
    private Model model;
    private String expected = "index";
    private String repoLink = "";
}
