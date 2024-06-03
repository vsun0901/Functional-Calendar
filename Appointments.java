// for the System time
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/*
    this class I image to be the times that be scheduled at a store

    note: get"blank"Time returns the in a 24 hour clock string ex: 23:45, 1:42.
          setTimeModify auto grabs the system clock and date

    */
public class Appointments {
  private String appointmentTitle;  
  private int StartTime;
  private int endTime;
  private int maxAttendees;
  private String timeModify;


    public Appointments(String appointmentTitle, int StartTime,int endTime,int maxAttendees) {
      this.appointmentTitle = appointmentTitle;
      this.StartTime = StartTime;
      this.endTime = endTime;
      this.maxAttendees = maxAttendees;
      // put timeModify into years/months/day hours/minutes
      String time = LocalTime.now()+"";
      time = LocalDate.now() + " " + time.substring(0,5);
      this.timeModify = time;
  }

    public String getEndTime() {
      String time = "" + this.endTime;
      // puts time into ##:## or #:## format
        if (time.length() == 3) {
            return time.substring(0,1) + ":" + time.substring(1);
        } else if(time.length() == 4) {
            return time.substring(0,2) + ":" + time.substring(2);
        } else {
            return "";
        }
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public String getStartTime() {
        String time = "" + this.StartTime;
        if (time.length() == 3) {
            return time.substring(0,1) + ":" + time.substring(1);
        } else if(time.length() == 4) {
            return time.substring(0,2) + ":" + time.substring(2);
        } else {
            return "";
        }
    }

    public String getTimeModify() {
        return timeModify;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public void setStartTime(int startTime) {
        StartTime = startTime;
    }

    public void setTimeModify() {
        String time = LocalTime.now() + "";
        time=LocalDate.now() + " " + time.substring(0,5);
        this.timeModify = time;
    }
    public String getAppointmentTitle() {
        return appointmentTitle;
    }
    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }
    public String toString() {
      return getStartTime() + " to " + getEndTime() + ", Max attendees " + getMaxAttendees() + ", time modified " + getTimeModify();
    }
    public int getRealStartTime() {
      return this.StartTime;
    }
    public int getRealEndTime() {
      return this.endTime;
    }
    public ArrayList<Appointments> appointmentList() {
        ArrayList<Appointments> appointments = new ArrayList<>();
        Appointments obj = new Appointments(appointmentTitle, StartTime, endTime, maxAttendees);
        appointments.add(obj);

        return appointments;
    }
    public void mod(String appointmentTitle, int endTime, int startTime, int maxAttendees){
        this.appointmentTitle=appointmentTitle;
        this.endTime=endTime;
        this.StartTime=StartTime;
        this.maxAttendees=maxAttendees;
    }
}
