//Auther : katleho Wilson Tsopane
// Date written : 23\11\2020
// Java programme 
// main class for both classes for customer and covid_note

import java.sql.*;
import java.util.Scanner;

public class Main {
	// collecting the information of the customer
	public static customer getCustomerinfor(int nrRows) {
		System.out.print("Enter your first name");
		Scanner scan = new Scanner(System.in);
		String name = scan.next();
		System.out.print("please enter your lastname");
		scan = new Scanner(System.in);
		String surname = scan.next();
		System.out.print("please enter yourcontact number as an individual");
		scan = new Scanner(System.in);
		String contact_number = scan.next();
		System.out.print("please enter your address");
		scan = new Scanner(System.in);
		String address = scan.nextLine();

		return new customer(name, surname, contact_number, address, nrRows);
	}

	// collecting the information of covid class.
	public static CovidNote getCovidnoteinfo(int uniqueID, String cus_Id) {
		
		// for variables I have used camelcase .
		
		System.out.print("Enter your temperature");
		Scanner scan = new Scanner(System.in);
		String temperature = scan.next(); // getting this value from input
		System.out.print("please enter if you have onced been in contact with anyone with covid");
		scan = new Scanner(System.in);
		String contactWithAnyone = scan.next(); // reading data from the user 
		System.out.print("please enter if you have travelled during thi pandemic");
		scan = new Scanner(System.in);
		String travel = scan.next(); // reading data from the user
		System.out.print("please enter if you any symptoms at the moment");
		scan = new Scanner(System.in);
		String symptoms = scan.nextLine(); // reading data from the user

		CovidNote object = new CovidNote(temperature, contactWithAnyone, travel, symptoms, uniqueID); //  creating a new object being  a constructor
		
		return object; // this one returns an object that was created from the constructor
								
	}

	// created the main method because that is where execution start.
	public static void main(String[] args) {

		// Connect to the library_db database, via the jdbc:mysql: channel
		// on localhost (this PC)

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/consumers?useSSL=false",
					"root", "tsops1997%");
			// Created a direct line to the database for running  queries
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			int rowsAffected;
			{
				// executeQuery: runs a SELECT statement and returns the results

				resultSet = statement.executeQuery("SELECT * FROM Customers");//
				int nrRows1 = 0;
				resultSet = statement
						.executeQuery("SELECT customerId, firstname, lastname,contactnumbers,Address FROM Customers");
				while (resultSet.next()) {
					System.out.println(resultSet.getInt("customerId") + ", " + resultSet.getString("firstname") + ", "
							+ resultSet.getString("lastname") + ", " + resultSet.getString("contactnumbers")
							+ resultSet.getString("address"));
					nrRows1++;

				}
				System.out.println(nrRows1);
				customer cus = getCustomerinfor(nrRows1); // fetching the customer information by rows.
				System.out.println(cus.toDatabase());
				rowsAffected = statement.executeUpdate(cus.toDatabase());

				// Covid_note
				resultSet = statement.executeQuery("SELECT * FROM covid_note");// selecting data that is being store in
																				// the database.
				nrRows1 = 0; // 0 for it to automatically get it itself nRows.

				resultSet = statement.executeQuery(
						"select transaction_number, customerId, Temparature, In_contact_with_anyone_with_covid, have_travelled,have_any_symptoms from covid_note"
						);
				// READ from results set
				while (resultSet.next()) {
					int customerId = resultSet.getInt("customerId");
					System.out.println("customer id is: " + customerId); // assingning
					double Temparature = resultSet.getDouble("Temparature");
					System.out.println("Temparature is: " + Temparature);
					nrRows1++;
				} // closed the loop

				String cus_Id = cus.getUser_Id();
				
				
				//  incrementing  number +1
				//generating database Id
				
				resultSet = statement.executeQuery("select count(*) as total from covid_note");// it returns the number of records
				int recordsCount = 0;
				while (resultSet.next()) { // the number of records in the dbs.
					recordsCount = resultSet.getInt("total");
				}
				
				int uniqueCovidNoteID = recordsCount + 1;
				

				CovidNote cov = getCovidnoteinfo(uniqueCovidNoteID, cus_Id);
				String insertQuery = cov.getInsertQuery();
				
				System.out.println(insertQuery);
				
				int insertCount = statement.executeUpdate(insertQuery);
				
				if (insertCount == 1) {
					System.out.println("INSERT WAS SUCCESSFUL");
				} else {
					System.out.println("INSERT WAS NOT SUCCESSFUL");
				}
				
				resultSet = statement.executeQuery(
						"select transaction_number, customerId, Temparature, In_contact_with_anyone_with_covid, have_travelled,have_any_symptoms from covid_note"
						);
				// READ from results set
				while (resultSet.next()) {
					int customerId = resultSet.getInt("customerId");
					System.out.println("customer id is: " + customerId); // assingning
					
					double temparature = resultSet.getDouble("Temparature");
					System.out.println("Temparature is: " + temparature);
					
				}
				
			}
		} catch (SQLException e) {
			//  catch the SQLException -
			
			e.printStackTrace();

		}
	}

}
