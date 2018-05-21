package uebung2_1;

import java.util.ArrayList;
import java.util.Calendar;

public class Person {

	private String personenname;
	private Calendar geburtstag;
	private long ganzzahl;

	int sizeofByte = 0x256;

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

	public long getGanzzahl() {
		return ganzzahl;
	}

	public void setGanzzahl(long ganzzahl) {
		this.ganzzahl = ganzzahl;
	}

	public Byte[] toByteArray() {

		ArrayList<Byte> barr = new ArrayList<Byte>();

		long ganzzahlHelper = ganzzahl;

		System.out.println(ganzzahl);

		for (int i = 0; i < 8; i++) {

			barr.add((byte) (ganzzahlHelper % sizeofByte));
			ganzzahlHelper /= sizeofByte;

		}

		Byte[] b = new Byte[barr.size()];
		for (int i = 0; i < b.length; i++) {

			b[i] = barr.get(i);

		}

		return b;
	}

	public void fromByteArray(Byte[] barr) {

		for (int i = 0; i < barr.length; i++) {

			System.out.println(barr[i]);

		}

		for (int i = barr.length - 1; i >= 0; i--) {

			ganzzahl += barr[i];
			ganzzahl *= sizeofByte;

		}

	}

}
