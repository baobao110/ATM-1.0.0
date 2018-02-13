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
				System.out.println("��ָ�� �쳣");
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
				System.out.println("�����ɹ�");
			}
			else {
				System.out.println("����ʧ��");
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
			System.out.println("��ָ�� �쳣");
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
			System.out.println("�˻������벻��ȷ");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("�˻������벻��ȷ");
			return;
		}
		a.setMoney(a.getMoney()+money);
		int i=car.modify(number, a.getMoney(),conn);
		if(i==1) {
			System.out.println("��Ǯ�ɹ�");
		}
		else {
			System.out.println("��Ǯʧ��");
		}
		Account b=new Account();
		b.setNumber(number);
		b.setMoney(money);
		b.setType("1");
		b.setDescription("��Ǯ");
		int j=0;
		try {
			j = account.add(b,conn);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(j==1) {
			System.out.println("��ˮ�˵������ɹ�");
		}
		else {
			System.out.println("��ˮ�˵�����ʧ��");
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
			System.out.println("��ָ�� �쳣");
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
			System.out.println("�˻������벻��ȷ");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("�˻������벻��ȷ");
			return;
		}
		if(a.getMoney()<money) {
			System.out.println("����");
			return;
		}
		a.setMoney(a.getMoney()-money);
		int i=car.modify(number, a.getMoney(),conn);
		if(i==1) {
			System.out.println("ȡǮ�ɹ�");
		}
		else {
			System.out.println("ȡǮʧ��");
		}
		Account b=new Account();
		b.setNumber(number);
		b.setMoney(money);
		b.setType("2");
		b.setDescription("ȡǮ");
		int j=0;
		try {
			j = account.add(b,conn);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(j==1) {
			System.out.println("��ˮ�˵������ɹ�");
		}
		else {
			System.out.println("��ˮ�˵�����ʧ��");
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
			System.out.println("��ָ�� �쳣");
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
			System.out.println("�˻������벻��ȷ");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("�˻������벻��ȷ");
			return;
		}
		try {
		if(a.getMoney()<money) {
			System.out.println("����");
			return;
		}
		a.setMoney(a.getMoney()-money);
		i=car.modify(OutNumber, a.getMoney(),conn);
		if(i==1) {
			System.out.println("ת�˳ɹ�");
		}
		else {
			System.out.println("ת��ʧ��");
		}
		b=new Account();
		b.setNumber(OutNumber);
		b.setMoney(money);
		b.setType("3");
		b.setDescription("ת��");
		j=account.add(b,conn);
		if(j==1) {
			System.out.println("��ˮ�˵������ɹ�");
		}
		else {
			System.out.println("��ˮ�˵�����ʧ��");
			return;
		}
		a=car.getBankCar(InNumber,conn);
		if(null==a) {
			System.out.println("�˻�����ȷ");
			return;
		}
		a.setMoney(a.getMoney()+money);
		i=car.modify(InNumber, a.getMoney(),conn);
		if(i==1) {
			System.out.println("��Ǯ�ɹ�");
		}
		else {
			System.out.println("��Ǯʧ��");
			return;
		}
		b=new Account();
		b.setNumber(InNumber);
		b.setMoney(money);
		b.setType("4");
		b.setDescription("ת��");
		j=account.add(b,conn);
		if(j==1) {
			System.out.println("��ˮ�˵������ɹ�");
		}
		else {
			System.out.println("��ˮ�˵�����ʧ��");
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
			System.out.println("��ָ�� �쳣");
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
			System.out.println("�˻������벻��ȷ");
			return;
		}
		if(!a.getPassword().equals(password)) {
			System.out.println("�˻������벻��ȷ");
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
