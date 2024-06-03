import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calendar {
    private String calendarTitle;
    private String calendarDescription;

    public Calendar(String calendarTitle, String calendarDescription) {
        this.calendarTitle = calendarTitle;
        this.calendarDescription = calendarDescription;

    }
    public String getCalendarTitle() {
        return calendarTitle;
    }
    public String getCalendarDescription() {
        return calendarDescription;
    }
    public void setCalendarTitle(String calendarTitle) {
        this.calendarTitle = calendarTitle;
    }
    public void setCalendarDescription(String calendarDescription) {
        this.calendarDescription = calendarDescription;
    }
    public String printCalendar() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("AppointmentList.txt"));
        String str;
        StringBuilder appointmentPrint = new StringBuilder();
        while ((str = reader.readLine()) != null) {
            appointmentPrint.append(str);
        }
        return this.calendarTitle + "\n" + this.calendarDescription + "\n" + appointmentPrint;
    }
}