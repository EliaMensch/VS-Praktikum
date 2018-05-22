package uebung2_1;

import java.util.Calendar;

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
		this("Mustermann", Calendar.getInstance(), 12l);
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

	public byte[] toByteArray() {

		byte[] stream = new byte[Long.BYTES + 3 * Integer.BYTES + Integer.BYTES
				+ personenname.length() * Character.BYTES];

		addGanzzahlToByteStream(stream);
		addGeburtstagToStream(stream);
		addPersonennameToStream(stream);

		return stream;

	}

	private void addGanzzahlToByteStream(byte[] stream) {

		long ganzzahlHelper = ganzzahl;

		for (int i = 0; i < Long.BYTES; i++) {

			stream[i] = (byte) ((ganzzahlHelper % SIZE_OF_BYTE) - SIGN_BYTE);

			ganzzahlHelper /= SIZE_OF_BYTE;

		}

	}

	private void addGeburtstagToStream(byte[] stream) {

		int[] date = { geburtstag.get(Calendar.YEAR), geburtstag.get(Calendar.MONTH),
				geburtstag.get(Calendar.DAY_OF_MONTH) };

		for (int i = 0; i < date.length; i++) {

			for (int j = 0; j < Integer.BYTES; j++) {

				stream[Long.BYTES + i * Integer.BYTES + j] = (byte) ((date[i] % SIZE_OF_BYTE) - SIGN_BYTE);

				date[i] /= SIZE_OF_BYTE;

			}

		}

	}

	private void addPersonennameToStream(byte[] stream) {

		int length = personenname.length();

		for (int i = 0; i < Integer.BYTES; i++) {

			stream[Long.BYTES + 3 * Integer.BYTES + i] = (byte) ((length % SIZE_OF_BYTE) - SIGN_BYTE);

			length /= SIZE_OF_BYTE;

		}

		for (int i = 0; i < personenname.length(); i++) {

			char c = personenname.charAt(i);

			for (int j = 0; j < Character.BYTES; j++) {

				stream[Long.BYTES + 3 * Integer.BYTES + Integer.BYTES + i * Character.BYTES
						+ j] = (byte) ((c % SIZE_OF_BYTE) - SIGN_BYTE);

				c /= SIZE_OF_BYTE;

			}

		}

	}

	public void fromByteArray(byte[] stream) {

		setGanzzahl(fetchLongFromStream(stream, 0));
		setGeburtstag(fetchGeburtstagFromStream(stream));
		setPersonenname(fetchStringFromStream(stream, Long.BYTES + 3 * Integer.BYTES));

	}

	private Calendar fetchGeburtstagFromStream(byte[] stream) {

		Calendar gbt = Calendar.getInstance();

		int[] date = { 0, 0, 0 };

		for (int i = 0; i < date.length; i++) {

			date[i] = fetchIntegerFromStream(stream, Long.BYTES + i * Integer.BYTES);

		}

		gbt.set(date[0], date[1], date[2]);

		return gbt;

	}

	private int fetchIntegerFromStream(byte[] stream, int index) {

		int val = 0;

		for (int i = Integer.BYTES - 1; i >= 0; i--) {

			val += stream[index + i] + SIGN_BYTE;

			if (i != 0) {
				val *= SIZE_OF_BYTE;
			}

		}

		return val;

	}

	private long fetchLongFromStream(byte[] stream, int index) {

		long val = 0;

		for (int i = Long.BYTES - 1; i >= 0; i--) {

			val += stream[index + i] + SIGN_BYTE;

			if (i != 0) {
				val *= SIZE_OF_BYTE;
			}

		}

		return val;

	}

	private String fetchStringFromStream(byte[] stream, int index) {

		StringBuilder sb = new StringBuilder();
		int length = fetchIntegerFromStream(stream, index);

		for (int i = 0; i < length; i++) {

			sb.append(fetchCharFromStream(stream, index + Integer.BYTES + i * Character.BYTES));

		}

		return sb.toString();

	}

	private char fetchCharFromStream(byte[] stream, int index) {

		char c = 0;

		for (int i = Character.BYTES - 1; i >= 0; i--) {

			c += stream[index + i] + SIGN_BYTE;

			if (i != 0) {
				c *= SIZE_OF_BYTE;
			}

		}

		return c;

	}

	public String toString() {

		StringBuilder sb = new StringBuilder();

		sb.append("Personenname:\t");
		sb.append(personenname);
		sb.append("\nGeburtstag:\t");
		sb.append(geburtstag.get(Calendar.DAY_OF_MONTH));
		sb.append(".");
		sb.append(geburtstag.get(Calendar.MONTH));
		sb.append(".");
		sb.append(geburtstag.get(Calendar.YEAR));
		sb.append("\nGanzzahl:\t");
		sb.append(ganzzahl);

		return sb.toString();

	}

}
