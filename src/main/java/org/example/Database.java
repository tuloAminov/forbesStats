package org.example;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {

    private static Connection connection;
    private static Statement statement;
    private static Map<String, Double> netWorth;

    public static void init(ArrayList<Person> people) throws SQLException {

        connect();

        createTablePerson();

        for (int i = 0; i < people.size(); i++) {
            putDataIntoPerson(people.get(i));
        }

        getNetWorthByCountry();
        EventQueue.invokeLater(() -> {
            Graphics g = new Graphics(netWorth);
            g.setVisible(true);
        });

        System.out.println("Самый молодой миллиардер из Франции, капитал которого превышает 10 млрд.:\n" + youngestBillionaireFromFrance() + "\n" + "\n");
        System.out.println("Бизнесмен из США, имеющий самый большой капитал в сфере Energy:\n" + richestAmericanInTheEnergyIndustry());

        disconnect();
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/forbes.db");
        statement = connection.createStatement();
    }

    private static void disconnect() throws SQLException {
        statement.close();
        connection.close();
    }

    private static void createTablePerson() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS Person");
        statement.execute("CREATE TABLE IF NOT EXISTS Person (" +
                "fullName VARCHAR, " +
                "rank INTEGER, " +
                "netWorth FLOAT, " +
                "age INTEGER, " +
                "country VARCHAR, " +
                "source VARCHAR, " +
                "industry VARCHAR);"
        );
    }

    private static void putDataIntoPerson(Person person) throws SQLException {
        String query = String.format(
                "INSERT INTO Person VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                person.getFullName(),
                person.getRank(),
                person.getNetWorth(),
                person.getAge(),
                person.getCountry(),
                person.getSource(),
                person.getIndustry()
        );

        statement.executeUpdate(query);
    }

    private static void getNetWorthByCountry() throws SQLException {
        netWorth = new HashMap<>();
        String sql =
                "SELECT country, netWorth " +
                        "FROM Person " +
                        "GROUP BY country;";

        ResultSet res = statement.executeQuery(sql);
        while(res.next()) {
            netWorth.put(res.getString("country"), Double.parseDouble(res.getString("netWorth")));
        }
    }

    private static String youngestBillionaireFromFrance() throws SQLException {
        return statement.executeQuery(
                "SELECT fullName, min(age) " +
                        "FROM person " +
                        "WHERE country='France' AND netWorth>=10 ;"
        ).getString("fullName");
    }

    private static String richestAmericanInTheEnergyIndustry() throws SQLException {
        return statement.executeQuery(
                "SELECT fullName, max(netWorth) " +
                        "FROM person " +
                        "WHERE country='United States' AND industry='Energy';"
        ).getString("fullName");
    }

}
