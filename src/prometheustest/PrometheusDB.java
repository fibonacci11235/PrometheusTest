/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prometheustest;

/**
 *
 * @author josiah
 */
import java.sql.*;

public class PrometheusDB {
    
    public static Connection connection;
    public static Statement statement;
    public static void loadDB()
            throws SQLException, ClassNotFoundException, Exception {
// Load the JDBC driver
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        System.out.println("Driver loaded");
// Establish a connection
        connection = DriverManager.getConnection("jdbc:derby://localhost:1527/PrometheusDB", "student", "1234");
        System.out.println("Database connected");
        
        try {

			CSVLoader loader = new CSVLoader(connection);
                        ResultSet resultSet=sqlStatement("Select * from student.course");
			if(!resultSet.next() ){
			loader.loadCSV("C:\\Users\\josiah\\Documents\\NetBeansProjects\\PrometheusTest\\src\\prometheustest\\courseData.csv", "COURSE", false);
                        };
		} catch (Exception e) {
			e.printStackTrace();
		}
     

    }
    
    public static ResultSet sqlStatement(String sqlStatement) throws SQLException{
        
        // Create a statement
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sqlStatement);
        return result;
    }
}
