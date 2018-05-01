import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.SocketException;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.*;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.*;

public class MainTest {

	public static void main(String[] args) {
		Calendar calendar = new Calendar();
		calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		calendar.getProperties().add(Version.VERSION_2_0);
		calendar.getProperties().add(CalScale.GREGORIAN);
		
		java.util.Calendar calendarJU = java.util.Calendar.getInstance();
		calendarJU.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		calendarJU.set(java.util.Calendar.DAY_OF_MONTH, 25);

		// initialise as an all-day event..
		VEvent christmas = new VEvent(new Date(calendarJU.getTime()), "Christmas Day");

		// Generate a UID for the event..
		UidGenerator ug;
		try {
			ug = new UidGenerator("1");
			christmas.getProperties().add(ug.generateUid());
			calendar.getComponents().add(christmas);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileOutputStream fout;
		try {
			fout = new FileOutputStream("mycalendar.ics");
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(calendar, fout);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

}
