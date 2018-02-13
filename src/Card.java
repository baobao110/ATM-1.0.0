
import java.util.Date;

public class Card {
	private int id;
	private int number;
	private double money;
	private Date createtime;
	private Date modifytime;
	private String password;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public Date getModifytime() {
		return modifytime;
	}
	
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", money=" + money + ", createtime=" + createtime
				+ ", modifytime=" + modifytime + ", password=" + password + "]";
	}
	

}
