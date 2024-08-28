package com.projectc6.gitreposerver.util;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.service.MetricsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyRunnableTest {

    @Mock
    private MetricsService mockMetricsService;

    @InjectMocks
    private MyRunnable myRunnableUnderTest;

    @Test
    void testRun() throws Exception {
        // Setup
        when(mockMetricsService.callFlaskServer("repoLink")).thenReturn("result");
        when(mockMetricsService.createRepoInDB()).thenReturn(0);

        // Configure MetricsService.fetchDBInfo(...).
        final GitMetrics gitMetrics = new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0);
        when(mockMetricsService.fetchDBInfo(0)).thenReturn(gitMetrics);

        when(mockMetricsService.createRepoID("repoLink")).thenReturn(0);

        // Run the test
        myRunnableUnderTest.run();

        // Verify the results
        verify(mockMetricsService).callFlaskServer("repoLink");
        verify(mockMetricsService).createRepoInDB();
    }

    @Test
    void testRun_MetricsServiceCreateRepoInDBThrowsIOException() throws Exception {
        // Setup
        when(mockMetricsService.callFlaskServer("repoLink")).thenReturn("result");
        when(mockMetricsService.createRepoInDB()).thenThrow(IOException.class);

        // Run the test
        myRunnableUnderTest.run();

        // Verify the results
        verify(mockMetricsService).callFlaskServer("repoLink");
    }

    @Test
    void testSetParam() {
        // Setup

        // Run the test
        final MyRunnable result = myRunnableUnderTest.setParam("repoLink");

        // Verify the results
    }
}
