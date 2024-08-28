import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProcessCSV {
    //    public void process() throws SQLException, ClassNotFoundException {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            String csvFilePath = "./python-api/metrics.csv";

            int batchSize = 20;

            Connection connection = null;

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gitrepoanalysis?characterEncoding=utf8", "root", "");
//                connection.setAutoCommit(false);

                String sql = ("INSERT INTO git_metrics(commit_count, commit_dates, lines_added, lines_removed, max_change_sets, avg_change_sets, max_code_churn, " +
                        "avgCodeChurn, contributorsCount, minorContributorsCount, contributionsPerContributor, hunks_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
                String line;

                int count = 0;

                bufferedReader.readLine(); // Skip header
                bufferedReader.readLine(); // Skip blank line - possible fix to not need this???
                while ((line = bufferedReader.readLine()) != null) {
                    String data[] = line.split(",");
                    StringBuilder stringBuilder = new StringBuilder();

                    String commitCount = data[0];
                    System.out.println(data[0]);

                    String commitDates = data[1];
                    System.out.println(data[1]);

                    String linesAdded = data[2];

                    String linesRemoved = data[3];

                    String maxChangeSets = data[4];

                    String avgChangeSets = data[5];

                    String maxCodeChurn = data[6];

                    String avgCodeChurn = data[7];

                    String contributorsCount = data[8];

                    String minorContributorsCount = data[9];

                    String contributionsPerContributor = data[10];

                    String hunksCount = data[11];
                    System.out.println(data[11]);
                    
                    preparedStatement.setInt(1, Integer.parseInt(commitCount));
                    preparedStatement.setString(2, commitDates);
                    preparedStatement.setInt(3, Integer.parseInt(linesAdded));
                    preparedStatement.setInt(4, Integer.parseInt(linesRemoved));
                    preparedStatement.setInt(5, Integer.parseInt(maxChangeSets));
                    preparedStatement.setDouble(6, Double.parseDouble(avgChangeSets));
                    preparedStatement.setInt(7, Integer.parseInt(maxCodeChurn));
                    preparedStatement.setDouble(8, Double.parseDouble(avgCodeChurn));
                    preparedStatement.setInt(9, Integer.parseInt(contributorsCount));
                    preparedStatement.setInt(10, Integer.parseInt(minorContributorsCount));
                    preparedStatement.setString(11, contributionsPerContributor);
                    preparedStatement.setDouble(12, Double.parseDouble(hunksCount));

                    preparedStatement.addBatch();

                    if (count % batchSize == 0) {
                        preparedStatement.executeBatch();
                    }
                }

                bufferedReader.close();

                preparedStatement.executeBatch();

                connection.commit();
                connection.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
//            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("Repo metrics added to database");

            return;
        }
    }
}
