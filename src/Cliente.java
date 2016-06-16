import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	
	private Socket cliente;
	//private Personaje personaje;
	
	public Cliente(int puerto, String ip) {
		try {
			cliente = new Socket(ip, puerto);
			new ClienteHilo(cliente).start();			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//this.personaje = new Personaje(cliente, 10,10);
	}

	public static void main(String[] args) {
		try {
			new Cliente(10000, "localhost").jugar();
		} catch (Exception e) {
			System.err.println("Se cerro la conexión");
		}
	}

	private void jugar() throws IOException {
		DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
		salida.writeUTF("jugar");
		salida.flush();
	} 

}
