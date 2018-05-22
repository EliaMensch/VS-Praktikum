package uebung2_1;

import java.util.Calendar;

public class Main21 {

	public static void main(String[] args) {

		Person p1 = new Person("Paul", Calendar.getInstance(), 1237628451l);
		Person p2 = new Person();

		Byte[] barr = p1.toByteArray();

		p2.fromByteArray(barr);
		System.out.println(p2.getGanzzahl());
		
		System.out.println("test2");
		System.out.println("test3");
		System.out.println("test7");
		System.out.println("GIT ist super!!");

	}

}
