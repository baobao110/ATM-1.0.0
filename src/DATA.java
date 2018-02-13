
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class DATA {
	private static  LinkedList<Connection> conn=new LinkedList<Connection>();
	
	static {
		try {
			Properties a=new Properties();
			a.load(DATA.class.getResourceAsStream("/1.properties"));
			Class.forName(a.getProperty("Driver"));
			for(int i=0;i<100;i++) {
				conn.add(DriverManager.getConnection(a.getProperty("url"),a.getProperty("user"),a.getProperty("password")));
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn.pollFirst();
	}
	
	public static void remove(Connection e) {
		if(null==e) {
			return;
		}
		conn.addLast(e);
	}
	
}