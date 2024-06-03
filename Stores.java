
import com.sun.jdi.InvalidTypeException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
    has a name, a list of possible Appointments, a list of Appointments that have been requested by a customer (CustomerAppointments). conneted to one seller

    important methods
    getAprovedAppointments returns a list of Appointments that have been approved by a seller. NOTE: the return looks weird in a System print out Need to fix
    clearPend: makes every AprovedAppointments pending bool conneted to CustomerAppointments false
    getPendingAppointments: returns a list of still pending Appointments
    getApointments: returns String of ALL appointments in a order list starting at zero

    modifyApointments,addApointment,addAprovedAppointments do what they say

 */


public class Stores {
    private String name;
    private ArrayList<ApprovedAppointments> CustomerAppointments =new ArrayList<>();
    private ArrayList<Appointments> appointments;

    public Stores(ArrayList<Appointments>appointments,String name) throws IOException {
    this.name=name;
    this.appointments=appointments;
    }

    public ArrayList<ApprovedAppointments> getAprovedAppointments(){
      ArrayList<ApprovedAppointments> j = new ArrayList<ApprovedAppointments>();
      for(int i=0;i<this.CustomerAppointments.size();i++){
          if(this.CustomerAppointments.get(i).getApproved()){
              j.add(this.CustomerAppointments.get(i));
          }
      }
      return j;
    }
    public void clearPend(){
        try {
            for (int i = 0; i < this.getPendingAppointments().size() + 1; i++) {

                this.getPendingAppointments().get(0).setPending(false);
            }
        }catch (IndexOutOfBoundsException e){
            return;
        }
    }

    public ArrayList<ApprovedAppointments> getCustomerAppointments() {
        return CustomerAppointments;
    }

    public ArrayList<ApprovedAppointments> getPendingAppointments() {
        ArrayList<ApprovedAppointments> j = new ArrayList<ApprovedAppointments>();
        for(int i=0;i<this.CustomerAppointments.size();i++){
            if(this.CustomerAppointments.get(i).getPending()){
                j.add(this.CustomerAppointments.get(i));
            }
        }
        return j;
    }
    public ArrayList<Appointments> GetAppointments() {
        return appointments;
    }
    public String getAppointments() {
        String appointment = "";
        for(int i = 0; i < appointments.size(); i++) {
            appointment = appointment + i+ ": " + appointments.get(i).toString() + "\n";
        }

        return appointment;
    }
    public void remove(int where){
        this.appointments.remove(where);
    }
    public String getName() {
        return name;
    }

    public String modifyAppointments(int newStart, int newEnd, int newMax, int where){
    // "where" is the number pointing the Appointments that is going to be change in appointments list

        try {
            appointments.get(where).setEndTime(newEnd);
            appointments.get(where).setStartTime(newStart);
            appointments.get(where).setMaxAttendees(newMax);
            appointments.get(where).setTimeModify();
            return "success";
        }catch (NumberFormatException e){
            return "Failure";
        }
    }

    public void addAppointment(Appointments appointments){
        this.appointments.add(appointments);
    }
    public void addApprovedAppointments(ApprovedAppointments approvedAppointments) {
        this.CustomerAppointments.add(approvedAppointments);
    }

    public void printAppointments() throws IOException {
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("AppointmentList.txt")));
        for (ApprovedAppointments str : CustomerAppointments) {
            writer.write(str + System.lineSeparator());
        }
    }

}
