/*Linked list for bill database in memory*/
public class billList {
	private objBill node;
	public void addBill(){
		node = new objBill();
	}
	public String billDoc(){
		return node.getDoc();
	}
	public String billIntro(){
		return node.getIntro();
	}
	public String billStat(){
		return node.getStat();
	}
	public String billDesc(){
		return node.getDesc();
	}
	public int billID(){
		return node.getID();
	}
}
