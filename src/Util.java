import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Util {
	public static int createNumber() {
		String str="";
		Random a=new Random();
		str+=(a.nextInt(9)+1);
		for(int i=0;i<3;i++) {
			Random b=new Random();
			str+=b.nextInt(10);
		}
		return Integer.parseInt(str);
	}
}
