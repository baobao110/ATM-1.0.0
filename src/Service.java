import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
	private static final CardDAO car=new CardDAO();
	private static final AccountDAO account=new AccountDAO();
	
	public void open(String password) {
		Card a=null;;
		int number=0;
		int i=0;
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=DATA.getConnection();
			if(null==conn) {
				System.out.println("空指针 异常");
				return;
			}
		conn.setAutoCommit(false);
			do {
			number=Util.createNumber();
			a=car.getBankCar(number,conn);
			}while(a!=null);
			try {
				i=car.open(password,conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(pre.executeUpdate()==1) {
				System.out.println("开户成功");
			}
			else {
				System.out.println("开户失败");
			}
			conn.commit();
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
		DATA.remove(conn);
	}
	
	public void save(int number,double money,String password) {
		Connection conn=DATA.getConnection();
		if(null==conn) {
			System.out.println("空指针 异常");
			return;
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Card a=car.getBankCar(number,conn);
		if(null==a) {
			System.out.println("账户或密码不正确");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("账户或密码不正确");
			return;
		}
		a.setMoney(a.getMoney()+money);
		int i=car.modify(number, a.getMoney(),conn);
		if(i==1) {
			System.out.println("存钱成功");
		}
		else {
			System.out.println("存钱失败");
		}
		Account b=new Account();
		b.setNumber(number);
		b.setMoney(money);
		b.setType("1");
		b.setDescription("存钱");
		int j=0;
		try {
			j = account.add(b,conn);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(j==1) {
			System.out.println("流水账单创建成功");
		}
		else {
			System.out.println("流水账单创建失败");
		}
		try {
			conn.commit();
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
		DATA.remove(conn);
	}
	
	public void draw(int number,double money,String password) {
		Connection conn=DATA.getConnection();
		if(null==conn) {
			System.out.println("空指针 异常");
			return;
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Card a=car.getBankCar(number,conn);
		if(null==a) {
			System.out.println("账户或密码不正确");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("账户或密码不正确");
			return;
		}
		if(a.getMoney()<money) {
			System.out.println("余额不足");
			return;
		}
		a.setMoney(a.getMoney()-money);
		int i=car.modify(number, a.getMoney(),conn);
		if(i==1) {
			System.out.println("取钱成功");
		}
		else {
			System.out.println("取钱失败");
		}
		Account b=new Account();
		b.setNumber(number);
		b.setMoney(money);
		b.setType("2");
		b.setDescription("取钱");
		int j=0;
		try {
			j = account.add(b,conn);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(j==1) {
			System.out.println("流水账单创建成功");
		}
		else {
			System.out.println("流水账单创建失败");
		}
		try {
			conn.commit();
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
		DATA.remove(conn);
	}
	
	public void transfer(double money,int OutNumber,int InNumber,String password) {
		int i=0;
		int j=0;
		Card a=null;
		Account b=null;
		Connection conn=DATA.getConnection();
		if(null==conn) {
			System.out.println("空指针 异常");
			return;
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		a=car.getBankCar(OutNumber,conn);
		if(null==a) {
			System.out.println("账户或密码不正确");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("账户或密码不正确");
			return;
		}
		try {
		if(a.getMoney()<money) {
			System.out.println("余额不足");
			return;
		}
		a.setMoney(a.getMoney()-money);
		i=car.modify(OutNumber, a.getMoney(),conn);
		if(i==1) {
			System.out.println("转账成功");
		}
		else {
			System.out.println("转账失败");
		}
		b=new Account();
		b.setNumber(OutNumber);
		b.setMoney(money);
		b.setType("3");
		b.setDescription("转出");
		j=account.add(b,conn);
		if(j==1) {
			System.out.println("流水账单创建成功");
		}
		else {
			System.out.println("流水账单创建失败");
			return;
		}
		a=car.getBankCar(InNumber,conn);
		if(null==a) {
			System.out.println("账户不正确");
			return;
		}
		a.setMoney(a.getMoney()+money);
		i=car.modify(InNumber, a.getMoney(),conn);
		if(i==1) {
			System.out.println("存钱成功");
		}
		else {
			System.out.println("存钱失败");
			return;
		}
		b=new Account();
		b.setNumber(InNumber);
		b.setMoney(money);
		b.setType("4");
		b.setDescription("转入");
		j=account.add(b,conn);
		if(j==1) {
			System.out.println("流水账单创建成功");
		}
		else {
			System.out.println("流水账单创建失败");
			return;
		}
		
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		DATA.remove(conn);
	}
	
	public void List(int number,String password) {
		Connection conn=DATA.getConnection();
		if(null==conn) {
			System.out.println("空指针 异常");
			return;
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Card a=car.getBankCar(number,conn);
		if(null==a) {
			System.out.println("账户或密码不正确");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("账户或密码不正确");
			return;
		}
		ArrayList<Account> b=account.list(number);
		for(Account i:b) {
			System.out.println(i);
		}
		try {
			conn.commit();
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
	}
}
