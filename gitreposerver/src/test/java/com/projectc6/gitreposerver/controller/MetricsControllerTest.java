package com.projectc6.gitreposerver.controller;

import com.projectc6.gitreposerver.GitreposerverApplication;
import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.service.DynamicTaskService;
import com.projectc6.gitreposerver.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MetricsController.class)
@SpringBootTest(classes = MetricsController.class)
@ContextConfiguration(classes = GitreposerverApplication.class)
class MetricsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MetricsService mockMetricsService;

    @MockBean
    private DynamicTaskService mockDynamicTaskService;

    @Test
    void testHomePage() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
    }

    @Test
    void testSearchRepo() throws Exception {
        // Setup

        // Configure MetricsService.fetchDBInfo(...).
        final GitMetrics gitMetrics = new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0);
        when(mockMetricsService.fetchDBInfo(0)).thenReturn(gitMetrics);

        when(mockMetricsService.createRepoID("repoLink")).thenReturn(0);
        when(mockMetricsService.callFlaskServer("repoLink")).thenReturn("result");
        when(mockMetricsService.createRepoInDB()).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/searchInformation")
                .param("repoLink", "repoLink")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockDynamicTaskService).stopCron();
        verify(mockDynamicTaskService).startCron("repoLink");
        verify(mockMetricsService).createRepoInDB();
    }

    @Test
    void testSearchRepo_MetricsServiceCreateRepoInDBThrowsIOException() throws Exception {
        // Setup

        // Configure MetricsService.fetchDBInfo(...).
        final GitMetrics gitMetrics = new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0);
        when(mockMetricsService.fetchDBInfo(0)).thenReturn(gitMetrics);

        when(mockMetricsService.createRepoID("repoLink")).thenReturn(0);
        when(mockMetricsService.callFlaskServer("repoLink")).thenReturn("result");
        when(mockMetricsService.createRepoInDB()).thenThrow(IOException.class);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/searchInformation")
                .param("repoLink", "repoLink")
                .accept(MediaType.TEXT_HTML))
                .andReturn().getResponse();

        // Verify the results
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals("expectedResponse", response.getContentAsString());
        verify(mockDynamicTaskService).stopCron();
        verify(mockDynamicTaskService).startCron("repoLink");
    }
}
