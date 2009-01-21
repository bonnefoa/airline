import java.sql.*;

class Inspector {

    static Connection link;
    static String myURL = "jdbc:odbc:AIRLINER";

	/*	***************************	*/
	/*	CONNECTING TO THE DATA BASE	*/
	/*	***************************	*/
    
    public static void init() {
    	try {
    		DriverManager.registerDriver(new sun.jdbc.odbc.JdbcOdbcDriver());
    		link = DriverManager.getConnection(myURL);
    	} catch (SQLException e) {
    		System.out.println("Connection error: " + e.getMessage());
    	}
    }

	/*	***********************************	*/
	/*	INSPECTING THE DATA BASE STRUCTURE	*/
	/*	***********************************	*/
    
    public static void main(String args[]) throws SQLException {
        ResultSet answer;
        init();
        java.sql.DatabaseMetaData patrol = link.getMetaData();
        answer = patrol.getTables(null, null, null, null);
        while (answer.next()) {
                String tT = answer.getString("TABLE_TYPE");
                String tS = answer.getString("TABLE_SCHEM");
                String tN = answer.getString("TABLE_NAME");
                if (answer.wasNull() == false) {
                        System.out.println("TYPE : " + tT);
                        System.out.println("SCHEMA : " + tS);
                        System.out.println("NOM : " + tN);
                }
                System.out.println();
                System.out.println("===");
                System.out.println();
        }
        answer.close();
        link.close();
    }
}
