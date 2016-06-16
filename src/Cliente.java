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
	
	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	private void jugar() throws IOException {
		DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
		salida.writeUTF("jugar");
		salida.flush();
	}
	
	public static void main(String[] args) {
		
		try {
			new Cliente(10000,"127.0.0.1").jugar();
		} catch (Exception e) {
			System.err.println("Se cerro la conexión");
		}
	}

}
