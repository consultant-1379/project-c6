package com.projectc6.gitreposerver.model;

import java.util.List;
import java.util.Map;

public class GitMetrics {
    private int repoID;
    private int commitCount;
    private String commitDates;
    private int linesAdded;
    private int linesRemoved;
    private int maxChangeSets;
    private double avgChangeSets;
    private int maxCodeChurn;
    private double avgCodeChurn;
    private int contributorsCount;
    private int minorContributorsCount;
    private String contributionsPerContributor;
    private double hunksCount;

    private List<String> commitDateList;
    private Map<String,String> contributionsList;

    public GitMetrics(int repoID, int commitCount, String commitDates, int linesAdded,
                      int linesRemoved, int maxChangeSets, double avgChangeSets, int maxCodeChurn,
                      double avgCodeChurn, int contributorsCount, int minorContributorsCount, String contributionsPerContributor, double hunksCount) {
        this.repoID = repoID;
        this.commitCount = commitCount;
        this.commitDates = commitDates;
        this.linesAdded = linesAdded;
        this.linesRemoved = linesRemoved;
        this.maxChangeSets = maxChangeSets;
        this.avgChangeSets = avgChangeSets;
        this.maxCodeChurn = maxCodeChurn;
        this.avgCodeChurn = avgCodeChurn;
        this.contributorsCount = contributorsCount;
        this.minorContributorsCount = minorContributorsCount;
        this.contributionsPerContributor = contributionsPerContributor;
        this.hunksCount = hunksCount;
    }

    public GitMetrics() {
    }

    public int getRepoID() {
        return repoID;
    }

    public void setRepoID(int repoID) {
        this.repoID = repoID;
    }

    public int getCommitCount() {
        return commitCount;
    }

    public void setCommitCount(int commitCount) {
        this.commitCount = commitCount;
    }

    public String getCommitDates() {
        return commitDates;
    }

    public void setCommitDates(String commitDates) {
        this.commitDates = commitDates;
    }

    public String getContributionsPerContributor() {
        return contributionsPerContributor;
    }

    public void setContributionsPerContributor(String contributionsPerContributor) {
        this.contributionsPerContributor = contributionsPerContributor;
    }

    public int getLinesAdded() {
        return linesAdded;
    }

    public void setLinesAdded(int linesAdded) {
        this.linesAdded = linesAdded;
    }

    public int getLinesRemoved() {
        return linesRemoved;
    }

    public void setLinesRemoved(int linesRemoved) {
        this.linesRemoved = linesRemoved;
    }

    public int getMaxChangeSets() {
        return maxChangeSets;
    }

    public void setMaxChangeSets(int maxChangeSets) {
        this.maxChangeSets = maxChangeSets;
    }

    public double getAvgChangeSets() {
        return avgChangeSets;
    }

    public void setAvgChangeSets(double avgChangeSets) {
        this.avgChangeSets = avgChangeSets;
    }

    public int getMaxCodeChurn() {
        return maxCodeChurn;
    }

    public void setMaxCodeChurn(int maxCodeChurn) {
        this.maxCodeChurn = maxCodeChurn;
    }

    public double getAvgCodeChurn() {
        return avgCodeChurn;
    }

    public void setAvgCodeChurn(double avgCodeChurn) {
        this.avgCodeChurn = avgCodeChurn;
    }

    public int getContributorsCount() {
        return contributorsCount;
    }

    public void setContributorsCount(int contributorsCount) {
        this.contributorsCount = contributorsCount;
    }

    public int getMinorContributorsCount() {
        return minorContributorsCount;
    }

    public void setMinorContributorsCount(int minorContributorsCount) {
        this.minorContributorsCount = minorContributorsCount;
    }

    public double getHunksCount() {
        return hunksCount;
    }

    public void setHunksCount(double hunksCount) {
        this.hunksCount = hunksCount;
    }

    public List<String> getCommitDateList() {
        return commitDateList;
    }

    public void setCommitDateList(List<String> commitDateList) {
        this.commitDateList = commitDateList;
    }

    public Map<String, String> getContributionsList() {
        return contributionsList;
    }

    public void setContributionsList(Map<String, String> contributionsList) {
        this.contributionsList = contributionsList;
    }

    @Override
    public String toString() {
        return "GitMetrics{" +
                "repoID=" + repoID +
                ", commitCount=" + commitCount +
                ", commitDates='" + commitDates + '\'' +
                ", linesAdded=" + linesAdded +
                ", linesRemoved=" + linesRemoved +
                ", maxChangeSets=" + maxChangeSets +
                ", avgChangeSets=" + avgChangeSets +
                ", maxCodeChurn=" + maxCodeChurn +
                ", avgCodeChurn=" + avgCodeChurn +
                ", contributorsCount=" + contributorsCount +
                ", minorContributorsCount=" + minorContributorsCount +
                ", contributionsPerContributor='" + contributionsPerContributor + '\'' +
                ", hunksCount=" + hunksCount +
                ", commitDateList=" + commitDateList +
                ", contributionsList=" + contributionsList +
                '}';
    }
}
