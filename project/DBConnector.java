import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnector
{

	private Statement st;
	private Connection con;
	
	public DBConnector()
	{
		try
		{
			String driverName = "com.mysql.jdbc.Driver";
			String DBName = "corpus";
			String dbURL = "jdbc:mysql://localhost:3307/" + DBName;
			Class.forName(driverName);
			this.con = DriverManager.getConnection(dbURL, "root", "root");
			 System.out.println("DB Connection.");
			 
			 st = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println("Mysql Server Not Connection.");
			
		}
	}
	public void insertQuery(String text, String userCountry)
	{
		text = text.replaceAll("'", "''");
		String insertQuery = "insert into incorrectcorpus(Corpus, Country) values('"+ text +"', '" + userCountry+ "');";
				
		try {
			st.execute(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ExitDB()
	{
		try {
			st.close();
			con.close();
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
}
