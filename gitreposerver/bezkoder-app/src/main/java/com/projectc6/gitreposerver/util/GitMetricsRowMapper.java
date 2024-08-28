package com.projectc6.gitreposerver.util;

import com.projectc6.gitreposerver.model.GitMetrics;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class GitMetricsRowMapper implements RowMapper<GitMetrics> {
    @Override
    public GitMetrics mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

        GitMetrics gitMetrics = new GitMetrics(resultSet.getInt("repo_id"), resultSet.getInt("commit_count"), resultSet.getString("commit_dates"),
                resultSet.getInt("lines_added"), resultSet.getInt("lines_removed"), resultSet.getInt("max_change_sets"),
                resultSet.getDouble("avg_change_sets"), resultSet.getInt("max_code_churn"), resultSet.getDouble("avg_change_sets"),resultSet.getInt("contributors_count"),
                resultSet.getInt("minor_contributors_count"), resultSet.getString("contributions_per_contributor"), resultSet.getDouble("hunks_count"));
        gitMetrics.setCommitDateList(getDates(resultSet.getString("commit_dates")));
        gitMetrics.setContributionsList(getContributors(resultSet.getString("contributions_per_contributor")));

        return gitMetrics;
    }

    public Map<String,String> getContributors(String data){
        Map<String,String> hashmap;
        hashmap = new HashMap<>();
        String[] ans =data.split("\\$");

        ArrayList<String> k = new ArrayList<>();

        for(String c : ans){
            k.add(c);
        }

        var start = 0;

        while(start<k.size()){
            String[] sub = k.get(start).split(":");

            hashmap.put(sub[0],sub[1]);

            start++;
        }

        return hashmap;
    }


    private List<String> getDates(String data){
        return Arrays.asList(data.split("\\$"));
    }
}
