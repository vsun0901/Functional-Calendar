import java.util.*;

public class TestCases {

	public static void main(String[] args) {

		testUserCreation();
		testUserDuplicationError();
		testUserDuplicationSuccess();
		// testMakeCustomerAppointment();
		// testCancelCustomerAppointment();
		testCalendar();
		testAppointments();
		testApprovedAppointments();
		testModifyAppointments();

	}

	public static void testUserCreation() {

		try {
			// Creates new User for test
			User newUser = new User("john.doe@example.com", "password123", User.role.CUSTOMER);

			String actualResult;

			if (newUser.findUser()) {
				actualResult = "User created successfully!";
			} else {
				actualResult = "User already exists!";
			}

			String expectedResult = "User created successfully!";

			// checks if the actual matches the expected
			if (actualResult.equalsIgnoreCase(expectedResult)) {
				System.out.println("User Creation Success");
			} else {
				System.out.println("User Creation Failed");
			}

			// Prints error if strings do not match
			assertEquals("User creation failed", expectedResult, actualResult);

		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testUserDuplicationError() {

		try {
			User newUser = new User("jane.doe@example.com", "password123", User.role.CUSTOMER);

			User duplicateUser = new User("jane.doe@example.com", "password123", User.role.SELLER);

			String expectedResult2 = "User already exists!";

			String actualResult2;

			// Runs findUser() method from User class
			if (duplicateUser.findUser()) {
				actualResult2 = "User already exists!";
			} else {
				actualResult2 = "User created successfully!";
			}

			// checks if the actual matches the expected
			if (actualResult2.equalsIgnoreCase(expectedResult2)) {
				System.out.println("User already exists!");
			} else {
				System.out.println("User Duplication Check Failed");
			}

			// Prints error if strings do not match
			assertEquals("Duplicate user failed", expectedResult2, actualResult2);

		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testUserDuplicationSuccess() {

		try {
			User newUser = new User("john.doe@example.com", "password123", User.role.CUSTOMER);

			User duplicateUser = new User("john.doe@example.com", "password456", User.role.SELLER);

			String expectedResult2 = "User created successfully!";

			String actualResult2;

			// Runs findUser() method from User class
			if (!duplicateUser.findUser()) {
				actualResult2 = "User already exists!";
			} else {
				actualResult2 = "User created successfully!";
			}

			// checks if the actual matches the expected
			if (actualResult2.equalsIgnoreCase(expectedResult2)) {
				System.out.println("User Duplication Check Success! User has been created!");
			} else {
				System.out.println("User Duplication Check Failed");
			}

			// Prints error if strings do not match
			assertEquals("Duplicate user failed", expectedResult2, actualResult2);

		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testMakeCustomerAppointment() {

		try {
			// Creates new Customer
			Customer customer = new Customer("john.doe@example.com", "password123");

			// Calls the method being tested with valid input
			boolean resultValid = customer.makeAppointment("Meeting", 900, 1000, 10);

			// Sets the expected result for valid input
			boolean expectedResultValid = true;

			// Assert the result for valid input
			assertEquals("Make appointment test failed for valid input", expectedResultValid, resultValid);
			System.out.println("Customer Appointment Creation Success for valid input");

			// Calls the method being tested with invalid input
			boolean resultInvalid = customer.makeAppointment("Invalid Meeting", 1100, 1000, -5);

			// Sets the expected result for invalid input
			boolean expectedResultInvalid = false;

			// Assert the result for invalid input
			assertEquals("Make appointment test failed for invalid input", expectedResultInvalid, resultInvalid);
			System.out.println("Customer Appointment Creation Failed for invalid input, as expected");
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testToStringStores() {
		try {
			// Creates a seller with two stores and appointments
			ArrayList<Stores> storesList = new ArrayList<>();
			Stores store1 = new Stores(new ArrayList<>(), "Store1");
			Stores store2 = new Stores(new ArrayList<>(), "Store2");
			storesList.add(store1);
			storesList.add(store2);
			Seller seller = new Seller("seller@example.com", "password", User.role.SELLER, storesList);

			// Calls the method being tested
			String actualResult = seller.toStringStores();

			// Expected result
			String expectedResult = "Store1\n" + "\n" + "Store2\n" + "\n";

			// Assert the result
			assertEquals("ToStringStores Test Failed", expectedResult, actualResult);
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testApproveCurrentPendingAppointments() {
		try {
			// Creates a seller with a store and a pending appointment
			ArrayList<Stores> storesList = new ArrayList<>();
			Stores store = new Stores(new ArrayList<>(), "Store1");
			ArrayList<Appointments> appointmentsList = new ArrayList<>();
			appointmentsList.add(new Appointments("Meeting", 10, 12, 5));

			storesList.add(store);
			Seller seller = new Seller("seller@example.com", "password", User.role.SELLER, storesList);

			// Calls the method being tested
			seller.ApproveCurrentPendingAppointments();

			// Assert the result
			assertEquals("ApproveCurrentPendingAppointments Test Failed", true,
					store.getApprovedAppointments().get(0).getApproved());
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testNewStore() {
		try {
			// Creates a seller with one store
			ArrayList<Stores> storesList = new ArrayList<>();
			Stores store1 = new Stores(new ArrayList<>(), "Store1");
			storesList.add(store1);
			Seller seller = new Seller("seller@example.com", "password", User.role.SELLER, storesList);

			// Adds a new store
			Stores store2 = new Stores(new ArrayList<>(), "Store2");
			seller.newStore(store2);

			// Calls the method being tested
			ArrayList<Stores> actualResult = seller.getStores();

			// Expected result
			ArrayList<Stores> expectedResult = new ArrayList<>();
			expectedResult.add(store1);
			expectedResult.add(store2);

			// Assert the result
			assertEquals("NewStore Test Failed", expectedResult, actualResult);
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testCalendar() {
		try {
			// Creates calendar for testing
			Calendar myCalendar = new Calendar("My Calendar", "Important events");

			// Call the method being tested
			String actualResult = myCalendar.printCalendar();

			// Expected result
			String expectedResult = myCalendar.getCalendarTitle() + "\n" + myCalendar.getCalendarDescription() + "\n ";

			// Assert the result
			assertEquals("Calendar creation test failed", expectedResult, actualResult);
			System.out.println("Calendar Creation Success!");
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testAppointments() {

		try {
			// Creates appointment for testing
			Appointments appointment = new Appointments("Meeting", 10, 12, 5);

			// Call the method being tested
			String actualResult = appointment.toString();

			// Expected result
			String expectedResult = appointment.getStartTime() + " to " + appointment.getEndTime() + ", Max attendees "
					+ appointment.getMaxAttendees() + ", time modified " + appointment.getTimeModify();

			// Assert the result
			assertEquals("Appointment creation test failed", expectedResult, actualResult);
			System.out.println("Appointment Creation Success!");
		} catch (Exception e) {
			System.out.println("Test Case Failed: " + e.getMessage());
		}
	}

	public static void testApprovedAppointments() {
		try {
			// Creates approved appointment for testing
			ApprovedAppointments approvedAppointment = new ApprovedAppointments("Meeting", 10, 12, 5, "John Doe");

			// Call the method being tested
			String actualResult = approvedAppointment.toString();

			// Expected result
			String expectedResult = approvedAppointment.getStartTime() + " to " + approvedAppointment.getEndTime()
					+ ", Max attendees " + approvedAppointment.getMaxAttendees() + ", time modified "
					+ approvedAppointment.getTimeModify() + " name: " + approvedAppointment.getCustomers();

			// Assert the result
			assertEquals("Approved Appointments Test Failed", expectedResult, actualResult);
			System.out.println("Approved Appointment Success!");
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
		}
	}

	public static void testModifyAppointments() {
		try {
			// Creates approved appointment for testing
			ApprovedAppointments approvedAppointment = new ApprovedAppointments("Meeting", 10, 12, 5, "John Doe");

			// Call the method being tested (modify)
			approvedAppointment.modify(true, true, 13, 15, 8);

			// Expected results after modification
			String expectedTitle = "Meeting";
			int expectedStartTime = 13;
			int expectedEndTime = 15;
			int expectedMaxAttendees = 8;
			String expectedCustomers = "John Doe";

			// Assert the modified properties
			assertEquals("Modified Title Test Failed", expectedTitle, approvedAppointment.getAppointmentTitle());
			assertEquals("Modified StartTime Test Failed", expectedStartTime, approvedAppointment.getRealStartTime());
			assertEquals("Modified EndTime Test Failed", expectedEndTime, approvedAppointment.getRealEndTime());
			assertEquals("Modified MaxAttendees Test Failed", expectedMaxAttendees,
					approvedAppointment.getMaxAttendees());
			assertEquals("Modified Customers Test Failed", expectedCustomers, approvedAppointment.getCustomers());

			// Assert the pending and approved status
			assertEquals("Modified Pending Status Test Failed", true, approvedAppointment.getPending());
			assertEquals("Modified Approved Status Test Failed", true, approvedAppointment.getApproved());

			System.out.println("Modify Appointments Success!");
		} catch (Exception e) {
			System.out.println("Test case failed: " + e.getMessage());
		}
	}

	public static void assertEquals(String message, Object expected, Object actual) {
		if (!expected.equals(actual)) {
			throw new AssertionError(message + " - Expected: <" + expected + ">, Actual: <" + actual + ">");
		}
	}

}
