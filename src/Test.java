import java.sql.SQLException;

public class Test {
	private static final Service a=new Service();
	
	public static void main(String[] args) {
		for(int i=0;i<200;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					transfer(1,6018,4133,"123456");
					transfer(1,4133,6018,"111111");
					//save(6018,20,"123456");
				}
			}).start();
		}
		
		/*for(int i=0;i<200;i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					transfer(1,4133,6018,"111111");
					//save(4133,20,"111111");
				}
			}).start();
		}*/
	}
	
	public static void open(String password) {
		String check="[0-9a-zA-Z] {6,12}";
		if(!password.matches(check)) {
			System.out.println("�����ʽ����ȷΪ���ֻ���ĸ,���볤��6-12λ");
			return;
		}
		a.open(password);
	}
	
	public static void save(int number,double money,String password) {
		a.save(number, money, password);
	}
	
	public static void draw(int number,double money,String password) {
		a.draw(number, money, password);
	}
	
	public static void transfer(double money,int OutNumber,int InNumber,String password) {
		a.transfer(money, OutNumber, InNumber, password);;
	}
	
	public static void List(int number,String password) {
		a.List(number, password);
	}
}
