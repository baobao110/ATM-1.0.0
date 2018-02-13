import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {
	public int add(Account a,Connection conn) throws Exception {
		PreparedStatement pre=null;
		if(null==conn) {
			System.out.println("空指针异常");
			return 0;
		}
		try {
			String sql="insert account(number,money,type,createtime,description) values(?,?,?,NOW(),?);";
			pre=conn.prepareStatement(sql);
			pre.setInt(1, a.getNumber());
			pre.setDouble(2, a.getMoney());
			pre.setString(3,a.getType());
			pre.setString(4, a.getDescription());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback();
		}
			return pre.executeUpdate();
	}
	
	public ArrayList<Account> list(int number){
		ResultSet result=null;
		PreparedStatement pre=null;
		ArrayList<Account> account=new ArrayList<Account>();
		Connection conn=DATA.getConnection();
		if(null==conn) {
			System.out.println("空指针异常");
			return null;
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String sql="select * from account where number=? for update;";
			pre=conn.prepareStatement(sql);
			pre.setInt(1,number);
			result=pre.executeQuery();
			while(result.next()) {
				Account a=new Account();
				a.setId(result.getInt("id"));
				a.setNumber(result.getInt("number"));
				a.setMoney(result.getDouble("money"));
				a.setType(result.getString("type"));
				a.setCreatetime(result.getDate("createtime"));
				a.setDescription(result.getString("description"));
				account.add(a);
				conn.commit();
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
		return account;
	}
	
}
