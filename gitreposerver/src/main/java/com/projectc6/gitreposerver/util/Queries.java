package com.projectc6.gitreposerver.util;

public enum Queries {
    INSERTORUPDATEREPOTODB{
        @Override
        public String toString(){
            return "REPLACE INTO git_metrics(repo_id, commit_count, commit_dates, lines_added, lines_removed, max_change_sets, avg_change_sets, max_code_churn, " +
                    "avg_code_churn, contributors_count, minor_contributors_count, contributions_per_contributor, hunks_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }
    },

    RETRIEVEALLFROMDB{
        @Override
        public String toString() {
            return "SELECT * FROM git_metrics";
        }
    },

    QUERYREPOBYID{
        @Override
        public String toString() {
            return "SELECT * FROM git_metrics WHERE repo_id = ?";
        }
    },

    CSVFILEADDRESS{
        @Override
        public String toString() {
            return "./python_api/metrics.csv";
        }
    }

}
