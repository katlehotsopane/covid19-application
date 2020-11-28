//Auther : katleho Tsopane
// Date written : 22\11\2020
// Java programme 
// the programme will be displaying the details for covid_note table in the database on how to go about.
public class CovidNote {

	// members variables
	private int uniqueID;
	private String customerId;
	private String temperature;
	private String contactAnyone;
	private String travel;
	private String symptoms;

	// constructor 
	CovidNote(String temp, String cont, String tra, String Symp, int uniqueID) { // parameters or arguments
		
		this.uniqueID = uniqueID; // this. call member variables
		
		this.temperature = temp;
		this.contactAnyone = cont;
		this.travel = tra;
		this.symptoms = Symp;
		
		int uniqueCustomerID = 1000 + this.uniqueID; // int uniqueCustomerID - this is a local variable
		this.customerId = Integer.toString(uniqueCustomerID);
	}

	public String getcustomer_Id() { //  a method (some action)
		return customerId;
	}

	public String getInsertQuery() {
		String query = "insert into covid_note values("
				+ this.uniqueID + ", "
				+ this.customerId + ", "
				+ this.temperature + ", "
				+ "\"" + this.contactAnyone + "\", "
				+ "\"" + this.travel + "\", "
				+ "\"" + this.symptoms + "\""
				+ ")";
		return query;

	}

	public String toString() {
		return "customerId: " + "\ntemperature: " + temperature + "\ncontact with anyone : " + contactAnyone
				+ "have travelled: " + travel + "\nhave any symptoms: " + symptoms;

	}

}
