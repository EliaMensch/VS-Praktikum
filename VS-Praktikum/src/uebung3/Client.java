package uebung3;

import java.util.Calendar;

import uebung2_1.Person;

public class Client {

	private Person person;

	public Client(String personenname, Calendar geburtstag, long ganzzahl) {

		setPerson(new Person(personenname, geburtstag, ganzzahl));

	}

	public Client() {

		setPerson(new Person());

	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public byte[] toDo(byte funcID) {

		byte[] serializedPerson = person.toByteArray();

		byte[] outputStream = new byte[serializedPerson.length + 1];

		outputStream[0] = funcID;

		for (int i = 1; i < outputStream.length; i++) {

			outputStream[i] = serializedPerson[i - 1];

		}

		return outputStream;

	}

	public void getFromServer(byte[] inputStream) {

		person = Person.fromByteArray(inputStream);

	}

}
