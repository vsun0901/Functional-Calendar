/*
    I view this class as a actual Appointment that wants to be made by a customer and aproved by a seller

    making : I dont really know what 1 could be used for
    but 2 grabs a areadly made objectAppointments and basily clones it while adding the customer name and couple of bools for methods in other classes

    bool: pending checks if a seller has seen it during the approval process
    bool: Aproved checks a AprovedAppointments has been aproved

    stills needs to be done: delete the object if denied by seller?(maybe)
 */



public class ApprovedAppointments extends Appointments {

    private boolean Pending;
    private boolean Approved;
    private String Customers;
// 1
    public ApprovedAppointments(String appointmentTitle, int StartTime, int endTime, int maxAttendees, String customers) {
        super(appointmentTitle, StartTime, endTime, maxAttendees);
        this.Pending = true;
        this.Customers = customers;
        this.Approved = false;
    }
    // 2
    public ApprovedAppointments(Appointments appointments,String customers) {
        super(appointments.getAppointmentTitle(), appointments.getRealStartTime(), appointments.getRealEndTime(), appointments.getMaxAttendees());
        this.Pending = true;
        this.Customers = customers;
    }

    public String getCustomers() {
        return Customers;
    }
    public boolean getPending() {
        return this.Pending;
    }
    public boolean getApproved() {
        return this.Approved;
    }

    public void setApproved(boolean approved) {
        this.Approved = approved;
    }

    public void setPending(boolean pending) {
        this.Pending = pending;
    }

    @Override
    public String toString() {
        return super.toString() + " name: " + getCustomers();
    }
    public void modify(boolean pending,boolean approved,int startTime,int endtime,int maxattendees){
        super.setEndTime(endtime);
        super.setMaxAttendees(maxattendees);
        super.setStartTime(startTime);
        this.Pending = pending;
        this.Approved = approved;

    }
    public String getInfo(){
        return ""+this.getRealStartTime()+"/"+this.getRealEndTime()+"\\"+this.getMaxAttendees()+"?"+this.getCustomers()+"}"+this.getApproved()+"{"+this.getPending();
    }
}
