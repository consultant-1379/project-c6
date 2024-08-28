package com.projectc6.gitreposerver.util;

import com.projectc6.gitreposerver.model.GitMetrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GitMetricsRowMapperTest {

    private GitMetricsRowMapper gitMetricsRowMapperUnderTest;

    @BeforeEach
    void setUp() {
        gitMetricsRowMapperUnderTest = new GitMetricsRowMapper();
    }

    @Test
    void testMapRow() throws Exception {
        // Setup
        final ResultSet resultSet = null;

        // Run the test
        final GitMetrics result = gitMetricsRowMapperUnderTest.mapRow(resultSet, 0);

        // Verify the results
    }

    @Test
    void testMapRow_ThrowsSQLException() {
        // Setup
        final ResultSet resultSet = null;

        // Run the test
        assertThrows(SQLException.class, () -> gitMetricsRowMapperUnderTest.mapRow(resultSet, 0));
    }

    @Test
    void testGetContributors() {
        // Setup
        final Map<String, String> expectedResult = Map.ofEntries(Map.entry("value", "value"));

        // Run the test
        final Map<String, String> result = gitMetricsRowMapperUnderTest.getContributors("data");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetDates() {
        // Setup

        // Run the test
        final List<String> result = gitMetricsRowMapperUnderTest.getDates("data");

        // Verify the results
        assertEquals(List.of("value"), result);
    }
}
