# CS-Project-4
Repository for Project 4
1. Instructions on how to run and compile our project:
  Our project will run by running the Main2.java class.
in order to run:  it needs a text called storage, lines 342 and 368
lines 313 314 315 319 402 404 needs a path to folder where the classes are kept ex: C:\Users\micha\IdeaProjects\untitled25\src\scr\ to your path to the folder
if it is the first ever time the program is running type NEW in the storage.txt

3. A list of who submitted which parts of the assignment on Brightspace and Vocareum.
  Vallerie - Submitted Report on Brightspace
  michael carrillo - Submitted Vocareum workspace
4. A detailed description of each class.
   The User class contains the variables String email, String password, and then an enum to represent if the user is a Seller or a Customer. Within this class, the method writeVault creates a PrintWriter to write the user’s account details (email and password) to a text file called “userVault.txt”, and the method readVault creates a BufferedReader to access and read the file. These methods are used in the rest of the methods to determine if the user already has an account, by reading through the text file in the findUser to see if there is a match, and then adding the user’s email and password to the file if it cannot be found in the method writeAUserVault. The class also has the methods editUser and updateVault to allow users to change their login details.
  The Customer class allows the user to modify their appointments. The makeAppointment class allows the user to create an appointment for a store and write it to their calendar, and the cancelAppointment class deletes the appointment.
The Seller class allows the user to access all their stores and the current appointments associated with them. The method ApproveCurrentPendingAppointments uses an ArrayList of Stores to see all pending appointments for their stores and allows them to approve it, setting the corresponding boolean to true to signify that it was approved. The method filePrintin then prints the seller’s stores and the current list of appointments associated with them.
The Stores class handles customer appointments made for specific stores. The method getApprovedAppointments gets the appointments the seller approved of to add it to an arraylist of the customer’s appointments so that it can be printed in their calendar. The method getPendingAppointments does the same for the pending appointments the customer made.
The Appointment class is where the various appointments’ titles, start times, end times, and max attendees are set, and also where the modification time stamp is created.
The ApprovedAppointments class extends the Appointments class and is where the appointments boolean values are assigned, signifying if the appointment was approved or still pending.
The Calendar class reads from the text file list of appointments made in the Stores class and returns all appointments found underneath the calendar's title and description.
