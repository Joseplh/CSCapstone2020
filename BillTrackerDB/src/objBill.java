public class objBill {
    private String doc;		// Document name for this legislative body
    private String intro;	// Primary Introducer is the source of who proposed the bill
    private String stat;	// This is the status of the current bill
    private String desc;	// general string description of the bill
    private int ID;			// Unique document ID
    private boolean isEnd;	// boolean value to mark the end of the linked list
    private objBill next;	// Next bill in the list
 
    /** Constructor **/
    public objBill() {//default constructor for initial linked list and end of list.
    	this.doc = "";
    	this.intro = "";
    	this.stat = "";
    	this.desc = "";
    	this.ID = -1;
    	this.isEnd = true;
    	this.next = null;
    }
    public objBill(String doc, String intro, String stat, String desc, int ID, objBill next) {
    	this.doc = doc;
    	this.intro = intro;
    	this.stat = stat;
    	this.desc = desc;
    	this.ID = ID;
    	this.isEnd = false;
    	this.next = next;
    }
    public String getDoc() {
    	return doc;
    }
    public String getIntro() {
    	return intro;
    }
    public String getStat() {
    	return stat;
    }
    public String getDesc() {
    	return desc;
    }
    public int getID() {
    	return ID;
    }
    public boolean getIsEnd() {
    	return isEnd;
    }
    public objBill getNext() {
    	return next;
    }
}
