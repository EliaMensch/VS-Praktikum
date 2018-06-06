package uebung3;

import java.util.Calendar;

import uebung2_1.Person;

public class Server {
	
	public byte[] getFromClient(byte[] inputStream) {
		
		byte[] stream = new byte[inputStream.length - 1];
		
		for(int i = 1; i < inputStream.length; i++) {
			
			stream[i - 1] = inputStream[i];
			
		}
		
		Person p = Person.fromByteArray(stream);
		
		switch(inputStream[0]) {
		
		case 1:
			return func_1(p);
			
		case 2:
			return func_2(p);
		
		default:
			return p.toByteArray();
		
		}
		
	}
	
	private byte[] func_1(Person p) {
		
		p.setGanzzahl(p.getGanzzahl() + 42);
		
		Calendar cal = p.getGeburtstag();
		cal.set(cal.get(Calendar.YEAR) + 5, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		p.setGeburtstag(cal);
		
		p.setPersonenname("Dr. " + p.getPersonenname());
		
		return p.toByteArray();
		
	}
	
	private byte[] func_2(Person p) {
		
		p.setGanzzahl(9001);
		
		Calendar cal = p.getGeburtstag();
		cal.set(cal.get(Calendar.YEAR) + 10, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
		p.setGeburtstag(cal);
		
		p.setPersonenname("Prof. Dr. " + p.getPersonenname());
		
		return p.toByteArray();
		
	}

}
