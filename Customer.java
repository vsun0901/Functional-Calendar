
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Stores> allStores;

    public Customer(String email, String password,ArrayList<Stores> all) {
        super(email, password, role.CUSTOMER);
        this.allStores=all;
    }

    public ArrayList<Stores> getAllStores() {
        return allStores;
    }

    public String AHHHH(){
        String re="";
        for(int i=0;i<this.allStores.size();i++){
            for(int j=0;j<this.allStores.get(i).getCustomerAppointments().size();j++){
                if (this.getEmail()==this.allStores.get(i).getCustomerAppointments().get(j).getCustomers()) {
                    re = re + this.allStores.get(i).getCustomerAppointments().get(j).toString();
                }
            }
        }
        return re;
    }


    public void viewCalendar() {
        /* Customers can view any of the created calendars for a store */
        // there is no method right now to get the calendar for a person.
        // assuming there is such a method, I will get the customer's calendar and print it.

        // Calendar c = getMyCalendar(); OR Calendar c = getMyCalendar(this.name);
        // c.printCalendar();

    }

    public boolean makeAppointment(String appointmentTitle, int StartTime,int endTime,int maxAttendees) {
        /* Customers can make or cancel appointment requests. */

        // an appointment has to be associated with a customer adn calendar,
        // however that has nto been implemented yet.
        // Appointments(String appointmentTitle, int StartTime,int endTime,int maxAttendees);

        Appointments a = new Appointments(appointmentTitle, StartTime, endTime, maxAttendees);

        // there should be a method to add this appointment to a calendar.
        // Calendar c = getMyCalendar(); OR Calendar c = getMyCalendar(this.name);
        // c.addAppointment(a);

        //return true if success, false if failed.

        return true; //placeholder
    }

    public boolean cancelAppointment(Appointments a) {
        // assuming calendar class can return appointments that have been created

        // Calendar c = getMyCalendar(); OR Calendar c = getMyCalendar(this.name);

        // I will find the appointment in the given calendar either with the appointment,
        // or criteria to search the appointment (title, description,etc.):
        // Appointments toCancel = c.findAppointment(a);

        // assuming there is a cancel method in the calendar, I will cancel it
        // c.removeAppointment(toCancel);

        // true if success, false if failed
        // return (c.removeAppointment(toCancel));

        return true; //placeholder
    }

    public void viewApprovedAppointments() {
        /* Customers can view a list of their currently approved appointments. */

        // I should get the list of approved appointments from the calendar, so assuming
        // Calendar c = getMyCalendar(); OR Calendar c = getMyCalendar(this.name);

        // ArrayList<ApprovedAppointments> appointmentList = c.getApprovedAppointments();
        // for (ApprovedAppointments aa : appointmentList) {
            // System.out.println(aa.toString());
    }

    public boolean rescheduleAppointments(Appointments a, String newAppointmentTitle, int newStartTime,
                                          int newEndTime, int newMaxAttendees) {
        boolean cancelSuccess = this.cancelAppointment(a);
        boolean newApptSuccess = false;
        if (cancelSuccess) {
            newApptSuccess = this.makeAppointment(newAppointmentTitle, newStartTime, newEndTime, newMaxAttendees);
        }
        if (newApptSuccess) {
            return true;
        } else {
            return false;
        }
    }

    public boolean exportApprovedAppointmentFile(String outFile) {
        /* Customers can export a file with all of their approved appointments. */

        // I should get the list of approved appointments from the calendar, so assuming
        // Calendar c = getMyCalendar(); OR Calendar c = getMyCalendar(this.name);

//        ArrayList<AprovedAppointments> appointmentList = c.getApprovedAppointments();
//
//        try {
//            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outFile)));
//            for (AprovedAppointments aa : appointmentList) {
//                pw.write(aa.toString());
//            }
//            pw.flush();
//            pw.close();
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
        return true; //placeholder
    }
}
