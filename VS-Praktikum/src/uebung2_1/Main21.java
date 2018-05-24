package uebung2_1;

import java.util.Calendar;
import java.util.TimeZone;

public class Main21 {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.set(1978, 10, 25);
		TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
		cal.setTimeZone(tz);

		Person p1 = new Person(
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.",
				cal, 1237628451l);
		Person p2 = new Person();

		System.out.println(p1.toString());
		System.out.println();
		System.out.println(p2.toString());

		byte[] barr = p1.toByteArray();
		
		Person p3 = Person.fromByteArray(barr);
		
		
		System.out.println();
		System.out.println("Serialisierte Person");
	
		System.out.println();
		System.out.println(p3.toString());

	}

}
