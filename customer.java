//Auther : katleho Tsopane
// Date written : 22\11\2020
// Java programme 
// the programme will be displaying the details for customer table in the database on how to go about.
public class customer {

			private String user_Id;
			private String name;
			private String surname;
			private String TelNum;
			private String Adress;
			
			//constructor
			customer(String n, String sn, String t, String a, int nrRows){
			name = n;
			surname = sn;
			TelNum = t;
			Adress = a;
			generateId(nrRows);
			}
			
			public String getUser_Id() {
				return user_Id;
			}

			public String getName() {
				return name;
			}

			public String getSurname() {
				return surname;
			}

			//Auto generate Id
			public void generateId( int nrRows){
			if(nrRows < 10){
				user_Id = "100" + Integer.toString(nrRows);
			} else if( nrRows < 100){
				user_Id = "10" + Integer.toString(nrRows);
			} else if( nrRows < 100){
				user_Id = "1" + Integer.toString(nrRows);
			}else{
				user_Id = Integer.toString(nrRows);
			}
			}
			public String toDatabase(){
				return "insert into Customers values("+
						user_Id + ",'"+name +"','"+ surname+ "','"+TelNum +"','" + Adress + "')";
			}
			public String	toString(){
				return "Customer ID: "+ user_Id +
						"\nCustomer Name: "+ name +
						"\nCustomer Surname: "+ surname +
						"Customer Contacts numbers: "+ TelNum +
						"\nAddress: "+ Adress;
					
				}
			//	genNum();
				
			

	}


