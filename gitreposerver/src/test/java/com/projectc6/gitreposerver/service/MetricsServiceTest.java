package com.projectc6.gitreposerver.service;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.repository.MetricsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricsServiceTest {

    @Mock
    private MetricsRepository mockMetricsRepository;

    @InjectMocks
    private MetricsService metricsServiceUnderTest;

    @Test
    void testFetchDBInfo() {
        // Setup

        // Configure MetricsRepository.getRepoByID(...).
        final List<GitMetrics> gitMetrics = List.of(new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0));
        when(mockMetricsRepository.getRepoByID(0)).thenReturn(gitMetrics);

        // Run the test
        final GitMetrics result = metricsServiceUnderTest.fetchDBInfo(0);

        // Verify the results
    }

    @Test
    void testFetchDBInfo_MetricsRepositoryReturnsNoItems() {
        // Setup
        when(mockMetricsRepository.getRepoByID(0)).thenReturn(Collections.emptyList());

        // Run the test
        final GitMetrics result = metricsServiceUnderTest.fetchDBInfo(0);

        // Verify the results
    }

    @Test
    void testFetchDBInfo_MetricsRepositoryThrowsDataAccessException() {
        // Setup
        when(mockMetricsRepository.getRepoByID(0)).thenThrow(DataAccessException.class);

        // Run the test
        final GitMetrics result = metricsServiceUnderTest.fetchDBInfo(0);

        // Verify the results
    }

    @Test
    void testCreateRepoInDB() throws Exception {
        // Setup
        when(mockMetricsRepository.saveRepo()).thenReturn(0);

        // Run the test
        final int result = metricsServiceUnderTest.createRepoInDB();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testCreateRepoInDB_MetricsRepositoryThrowsIOException() throws Exception {
        // Setup
        when(mockMetricsRepository.saveRepo()).thenThrow(IOException.class);

        // Run the test
        assertThrows(IOException.class, () -> metricsServiceUnderTest.createRepoInDB());
    }

    @Test
    void testCallFlaskServer() {
        // Setup

        // Run the test
        final String result = metricsServiceUnderTest.callFlaskServer("repoLink");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testCreateRepoID() {
        // Setup

        // Run the test
        final int result = metricsServiceUnderTest.createRepoID("repoLink");

        // Verify the results
        assertEquals(0, result);
    }
}
