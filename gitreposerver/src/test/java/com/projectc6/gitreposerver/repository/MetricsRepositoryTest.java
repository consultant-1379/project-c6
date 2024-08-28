package com.projectc6.gitreposerver.repository;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.util.GitMetricsRowMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MetricsRepositoryTest {

    private MetricsRepository metricsRepositoryUnderTest;

    @BeforeEach
    void setUp() {
        metricsRepositoryUnderTest = new MetricsRepository();
        metricsRepositoryUnderTest.jdbcTemplate = mock(JdbcTemplate.class);
    }

    @Test
    void testGetMetrics() {
        // Setup

        // Configure JdbcTemplate.query(...).
        final List<GitMetrics> gitMetrics = List.of(new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0));
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class))).thenReturn(gitMetrics);

        // Run the test
        final List<GitMetrics> result = metricsRepositoryUnderTest.getMetrics();

        // Verify the results
    }

    @Test
    void testGetMetrics_JdbcTemplateReturnsNoItems() {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<GitMetrics> result = metricsRepositoryUnderTest.getMetrics();

        // Verify the results
    }

    @Test
    void testGetMetrics_JdbcTemplateThrowsDataAccessException() {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class))).thenThrow(DataAccessException.class);

        // Run the test
        assertThrows(DataAccessException.class, () -> metricsRepositoryUnderTest.getMetrics());
    }

    @Test
    void testGetRepoByID() {
        // Setup

        // Configure JdbcTemplate.query(...).
        final List<GitMetrics> gitMetrics = List.of(new GitMetrics(0, 0, "commitDates", 0, 0, 0, 0.0, 0, 0.0, 0, 0, "contributionsPerContributor", 0.0));
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class), any(Object.class))).thenReturn(gitMetrics);

        // Run the test
        final List<GitMetrics> result = metricsRepositoryUnderTest.getRepoByID(0);

        // Verify the results
    }

    @Test
    void testGetRepoByID_JdbcTemplateReturnsNoItems() {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class), any(Object.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<GitMetrics> result = metricsRepositoryUnderTest.getRepoByID(0);

        // Verify the results
    }

    @Test
    void testGetRepoByID_JdbcTemplateThrowsDataAccessException() {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.query(eq("sql"), any(GitMetricsRowMapper.class), any(Object.class))).thenThrow(DataAccessException.class);

        // Run the test
        assertThrows(DataAccessException.class, () -> metricsRepositoryUnderTest.getRepoByID(0));
    }

    @Test
    void testSaveRepo() throws Exception {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.update("sql", "args")).thenReturn(0);

        // Run the test
        final int result = metricsRepositoryUnderTest.saveRepo();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    void testSaveRepo_JdbcTemplateThrowsDataAccessException() throws Exception {
        // Setup
        when(metricsRepositoryUnderTest.jdbcTemplate.update("sql", "args")).thenThrow(DataAccessException.class);

        // Run the test
        final int result = metricsRepositoryUnderTest.saveRepo();

        // Verify the results
        assertEquals(0, result);
    }
}
