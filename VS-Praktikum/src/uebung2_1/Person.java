package uebung2_1;

import java.util.Calendar;
import java.util.TimeZone;

public class Person {

	private String personenname;
	private Calendar geburtstag;
	private long ganzzahl;

	private final int SIGN_BYTE = 128;
	private final int SIZE_OF_BYTE = 256;


	public Person(String personenname, Calendar geburtstag, long ganzzahl) {

		setPersonenname(personenname);
		setGeburtstag(geburtstag);
		setGanzzahl(ganzzahl);
	

	}

	public Person() {
		this("Mustermann", Calendar.getInstance(), 42l);
	}

	public String getPersonenname() {
		return personenname;
	}

	public void setPersonenname(String personenname) {
		this.personenname = personenname;
	}

	public Calendar getGeburtstag() {
		return geburtstag;
	}

	public void setGeburtstag(Calendar geburtstag) {
		this.geburtstag = geburtstag;
	}

	public void setGeburtstag(int year, int month, int day) {
		this.geburtstag.set(year, month, day);
	}

	public long getGanzzahl() {
		return ganzzahl;
	}

	public void setGanzzahl(long ganzzahl) {
		this.ganzzahl = ganzzahl;
	}

	private int getStreamLength() {

		// L�nge der Ganzzahl
		int length = Long.BYTES;

		// L�nge des Geburtstages
		length += Integer.BYTES * 3;
		length += Integer.BYTES + geburtstag.getTimeZone().getID().length() * Character.BYTES;

		// L�nge des Personennamens
		length += Integer.BYTES + personenname.length() * Character.BYTES;

		return length;

	}

	public byte[] toByteArray() {

		byte[] stream = new byte[getStreamLength()];

				Pointer index = new Pointer(0);

		addLongToStream(stream, ganzzahl, index);
		addCalendarToStream(stream, geburtstag, index);
		addStringToStream(stream, personenname, index);

		

		return stream;

	}

	private void addLongToStream(byte[] stream, long val, Pointer index) {

		for (int i = 0; i < Long.BYTES; i++) {

			stream[index.getIndex() + i] = (byte) ((val % SIZE_OF_BYTE) - SIGN_BYTE);

			val /= SIZE_OF_BYTE;

		}

		index.setIndex(index.getIndex()+ Long.BYTES); 

	}

	private static void addIntegerToStream(byte[] stream, int val, Pointer index) {

		for (int i = 0; i < Integer.BYTES; i++) {

			stream[index.getIndex() + i] = (byte) ((val % SIZE_OF_BYTE) - SIGN_BYTE);

			val /= SIZE_OF_BYTE;

		}

		index.setIndex(index.getIndex()+ Integer.BYTES); 

	}

	private static void addCharacterToStream(byte[] stream, char c, Pointer index) {

		for (int i = 0; i < Character.BYTES; i++) {

			stream[index.getIndex()+ i] = (byte) ((c % SIZE_OF_BYTE) - SIGN_BYTE);
			

			c /= SIZE_OF_BYTE;

		}
//w00t
		index.setIndex(index.getIndex()+ Character.BYTES); 

	}

	private void addStringToStream(byte[] stream, String s) {

		addIntegerToStream(stream, s.length());

		for (int i = 0; i < s.length(); i++) {

			addCharacterToStream(stream, s.charAt(i));

		}

	}

	private void addCalendarToStream(byte[] stream, Calendar cal) {

		addIntegerToStream(stream, cal.get(Calendar.YEAR));
		addIntegerToStream(stream, cal.get(Calendar.MONTH));
		addIntegerToStream(stream, cal.get(Calendar.DAY_OF_MONTH));

		addStringToStream(stream, cal.getTimeZone().getID());

	}

	public Person fromByteArray(byte[] stream) {
		Person x = new Person();
		//this.index = 0;

		long gz = fetchLongFromStream(stream);
		setGeburtstag(fetchCalendarFromStream(stream));
		setPersonenname(fetchStringFromStream(stream));

		this.index = 0;
return 0;
	}

	private Calendar fetchCalendarFromStream(byte[] stream) {

		Calendar gbt = Calendar.getInstance();

		int[] date = { 0, 0, 0 };

		for (int i = 0; i < date.length; i++) {

			date[i] = fetchIntegerFromStream(stream);
		
		}

		gbt.set(date[0], date[1], date[2]);

		gbt.setTimeZone(TimeZone.getTimeZone(fetchStringFromStream(stream)));

		return gbt;

	}

	private int fetchIntegerFromStream(byte[] stream) {

		int val = 0;

		for (int i = Integer.BYTES - 1; i >= 0; i--) {

			val += stream[this.index + i] + SIGN_BYTE;

			if (i != 0) {
				val *= SIZE_OF_BYTE;
			}

		}

		this.index += Integer.BYTES;

		return val;

	}

	private long fetchLongFromStream(byte[] stream) {

		long val = 0;

		for (int i = Long.BYTES - 1; i >= 0; i--) {

			val += stream[this.index + i] + SIGN_BYTE;

			if (i != 0) {
				val *= SIZE_OF_BYTE;
			}

		}

		this.index += Long.BYTES;

		return val;

	}

	private String fetchStringFromStream(byte[] stream) {

		StringBuilder sb = new StringBuilder();
		int length = fetchIntegerFromStream(stream);

		for (int i = 0; i < length; i++) {

			sb.append(fetchCharFromStream(stream));

		}

		return sb.toString();

	}

	private char fetchCharFromStream(byte[] stream) {

		char c = 0;

		for (int i = Character.BYTES - 1; i >= 0; i--) {

			c += stream[this.index + i] + SIGN_BYTE;

			if (i != 0) {
				c *= SIZE_OF_BYTE;
			}

		}

		this.index += Character.BYTES;

		return c;

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Personenname:\t");
		sb.append(personenname);
		sb.append("\nGeburtstag:\t");
		sb.append(geburtstag.get(Calendar.DAY_OF_MONTH));
		sb.append(".");
		sb.append(String.format("%02d", geburtstag.get(Calendar.MONTH) + 1));
		sb.append(".");
		sb.append(geburtstag.get(Calendar.YEAR));
		sb.append("\t");
		sb.append(geburtstag.getTimeZone().getID());
		sb.append("\nGanzzahl:\t");
		sb.append(ganzzahl);

		return sb.toString();

	}

}
