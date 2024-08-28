CREATE TABLE `git_metrics` ( `repo_id` int NOT NULL,

`commit_count` int DEFAULT NULL,

`commit_dates` longtext,

`lines_added` int DEFAULT NULL,

`lines_removed` int DEFAULT NULL,

`max_change_sets` int DEFAULT NULL,

`avg_change_sets` double DEFAULT NULL,

`max_code_churn` int DEFAULT NULL,

`avg_code_churn` double DEFAULT NULL,

`contributors_count` int DEFAULT NULL,

`minor_contributors_count` int DEFAULT NULL,

`contributions_per_contributor` longtext,

`hunks_count` double DEFAULT NULL,

PRIMARY KEY (`repo_id`)

);