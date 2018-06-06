package uebung3;

public class Main3 {

	public static void main(String[] args) {
		
		Server server = new Server();
		
		Client client = new Client();
		
		System.out.println(client.getPerson().toString());
		
		System.out.println();
		
		byte funcID = 2;
		
		client.getFromServer(server.getFromClient(client.toDo(funcID)));
		
		System.out.println(client.getPerson().toString());

	}

}
