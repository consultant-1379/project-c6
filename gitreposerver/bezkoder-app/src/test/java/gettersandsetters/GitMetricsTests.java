package gettersandsetters;

import com.projectc6.gitreposerver.model.GitMetrics;

import org.junit.jupiter.api.Test;


import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GitMetricsTests {

    @Test
     void testSetter_setProperlyID(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setRepoID(1);
        assertEquals(1,gitMetrics.getRepoID());

    }

    @Test
     void testSetter_setProperlycommitCount(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setCommitCount(10);
        assertEquals(1,gitMetrics.getCommitCount());

    }

    @Test
    void testSetter_setProperlycommitDates(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setCommitDates("23/9/2021");
        assertEquals("23/9/2021",gitMetrics.getCommitDates());

    }

    @Test
    void testSetter_setProperlylcontributionsPerContributor(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setContributionsPerContributor("11");
        assertEquals("11",gitMetrics.getContributionsPerContributor());

    }



    @Test
    void testSetter_setProperlylinesAdded(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setLinesAdded(2);
        assertEquals(2,gitMetrics.getLinesAdded());

    }


    @Test
    void testSetter_setProperlylinesRemoved(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setLinesRemoved(3);
        assertEquals(3,gitMetrics.getLinesRemoved());

    }

    @Test
    void testSetter_setProperlymaxChangeSets(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setMaxChangeSets(3);
        assertEquals(3,gitMetrics.getMaxChangeSets());

    }

    @Test
    void testSetter_setProperlyavgChangeSets(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setAvgChangeSets(4.5);
        assertEquals(4.5,gitMetrics.getAvgChangeSets());

    }

    @Test
    void testSetter_setProperlymaxCodeChurn(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setMaxCodeChurn(4);
        assertEquals(4,gitMetrics.getMaxCodeChurn());

    }

    @Test
    void testSetter_setProperlyavgCodeChurn(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setAvgCodeChurn(5);
        assertEquals(5,gitMetrics.getAvgCodeChurn());

    }


    @Test
    void testSetter_setProperlycontributorsCount(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setContributorsCount(6);
        assertEquals(6,gitMetrics.getContributorsCount());

    }


    @Test
    void testSetter_setProperlyminorContributorsCount(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setMinorContributorsCount(7);
        assertEquals(7,gitMetrics.getMinorContributorsCount());

    }


    @Test
    void testSetter_setProperlyminorhunksCount(){
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setHunksCount(8);
        assertEquals(8,gitMetrics.getHunksCount());

    }


    @Test
    void testSetter_setProperlysetCommitDateList(){
        List<String> myList = Arrays.asList("23/9/2021", "24/9/2021");
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setCommitDateList(myList);
        assertEquals(myList,gitMetrics.getCommitDateList());

    }

    @Test
    void testSetter_setProperlysetcontributionsList(){
        Map<String, String> myMap = new HashMap<>();
        myMap.put("1", "Name");
        GitMetrics gitMetrics = new GitMetrics();
        gitMetrics.setContributionsList(myMap);
        assertEquals(myMap,gitMetrics.getContributionsList());

    }

    @Test
    void testSetter_setProperlytoString(){
        GitMetrics gitMetrics = new GitMetrics();
        List<String> mySecondList = new ArrayList<>();
        mySecondList.add("23/9/21");
        mySecondList.add("24/9/21");

        Map<String, String> mySecondMap = new HashMap<>();
        mySecondMap.put("2", "Second Name");
        mySecondMap.put("3", "Third Name");
        String toStringData = "GitMetrics{" +
                "repoID=" + 1 +
                ", commitCount=" + 2 +
                ", commitDates='" + "23/9/21" + '\'' +
                ", linesAdded=" + 3 +
                ", linesRemoved=" + 4 +
                ", maxChangeSets=" + 5 +
                ", avgChangeSets=" + 6 +
                ", maxCodeChurn=" + 7 +
                ", avgCodeChurn=" + 8 +
                ", contributorsCount=" + 9 +
                ", minorContributorsCount=" + 10 +
                ", contributionsPerContributor='" + "1: firstName" + '\'' +
                ", hunksCount=" + 11 +
                ", commitDateList=" + mySecondList +
                ", contributionsList=" + mySecondMap +
                '}';
        assertEquals(toStringData,gitMetrics.toString());

    }






}
