import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
	public int open(String password,Connection conn) throws Exception {
		String sql="insert card(number,money,createtime,modifytime,password) values(?,?,NOW(),NOW(),?);";
		PreparedStatement pre=null;
		try {
			pre=conn.prepareStatement(sql);
			pre.setInt(1, Util.createNumber());
			pre.setInt(2, 0);
			pre.setString(3,password);
			conn.commit(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
		return pre.executeUpdate();
	}
	
	public int modify(int number,double money,Connection conn) {
		PreparedStatement pre=null;
		try {
			String sql="update card set money=?,modifytime=NOW() where number=?;";
			pre=conn.prepareStatement(sql);
			pre.setDouble(1,money);
			pre.setInt(2,number);
			return pre.executeUpdate();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}
	
	public Card getBankCar(int number,Connection conn) {
		PreparedStatement pre=null;
		try {
			String sql="select * from card where number=? for update;";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, number);
			ResultSet result=pre.executeQuery();
			while(result.next()) {
				Card a=new Card();
				a.setId(result.getInt("id"));
				a.setNumber(result.getInt("number"));
				a.setMoney(result.getDouble("money"));
				a.setCreatetime(result.getDate("createtime"));
				a.setModifytime(result.getDate("modifytime"));
				a.setPassword(result.getString("password"));
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}
	
}
