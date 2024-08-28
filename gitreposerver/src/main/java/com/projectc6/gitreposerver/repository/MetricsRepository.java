package com.projectc6.gitreposerver.repository;

import com.projectc6.gitreposerver.model.GitMetrics;
import com.projectc6.gitreposerver.util.GitMetricsRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.projectc6.gitreposerver.util.Queries.*;

@Repository
@Lazy
public class MetricsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<GitMetrics> getMetrics() throws DataAccessException {
        return jdbcTemplate.query(RETRIEVEALLFROMDB.toString(), new GitMetricsRowMapper());
    }

    public List<GitMetrics> getRepoByID(int repoID) throws DataAccessException {
        return jdbcTemplate.query(QUERYREPOBYID.toString(), new GitMetricsRowMapper(), repoID);
    }

    public int saveRepo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(CSVFILEADDRESS.toString()));
        String line;
        List<GitMetrics> gitMetricsList = new ArrayList<>();
        bufferedReader.readLine(); // Skip header
        bufferedReader.readLine(); // Skip blank line - possible fix to not need this???
        while ((line = bufferedReader.readLine()) != null) {
            GitMetrics gitMetrics = new GitMetrics();
            String data[] = line.split(",");
            if(data[0].isEmpty()){
                break;
            }
            gitMetrics.setRepoID(Integer.parseInt(data[0]));
            gitMetrics.setCommitCount(Integer.parseInt(data[1]));
            gitMetrics.setCommitDates(data[2]);
            gitMetrics.setLinesAdded(Integer.parseInt(data[3]));
            gitMetrics.setLinesRemoved(Integer.parseInt(data[4]));
            gitMetrics.setMaxChangeSets(Integer.parseInt(data[5]));
            gitMetrics.setAvgChangeSets(Double.parseDouble(data[6]));
            gitMetrics.setMaxCodeChurn(Integer.parseInt(data[7]));
            gitMetrics.setAvgCodeChurn(Double.parseDouble(data[8]));
            gitMetrics.setContributorsCount(Integer.parseInt(data[9]));
            gitMetrics.setMinorContributorsCount(Integer.parseInt(data[10]));
            gitMetrics.setContributionsPerContributor(data[11]);
            gitMetrics.setHunksCount(Double.parseDouble(data[12]));
            gitMetricsList.add(gitMetrics);
        }

        return jdbcTemplate.update(INSERTORUPDATEREPOTODB.toString(),gitMetricsList.get(0).getRepoID(),gitMetricsList.get(0).getCommitCount(),gitMetricsList.get(0).getCommitDates()
                ,gitMetricsList.get(0).getLinesAdded(),gitMetricsList.get(0).getLinesRemoved()
        ,gitMetricsList.get(0).getMaxChangeSets(),gitMetricsList.get(0).getAvgChangeSets(),gitMetricsList.get(0).getMaxCodeChurn()
                ,gitMetricsList.get(0).getAvgCodeChurn(),gitMetricsList.get(0).getContributorsCount(),
                gitMetricsList.get(0).getMinorContributorsCount(),gitMetricsList.get(0).getContributionsPerContributor(),gitMetricsList.get(0).getHunksCount());

    }

}
