import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServidorHilo extends Thread {
	
	private Socket cliente; //hilo del cliente que estoy creando
	private ArrayList<Socket> lista; //lista de hilos que me va a servir para informar a los demas sockets conectados
	
	public ServidorHilo(Socket cliente, ArrayList<Socket> lista) {
		super();
		this.cliente = cliente;
		this.lista = lista;
	}
	
	public void run() {
		
		String texto = "";
		try {
			texto = new DataInputStream(cliente.getInputStream()).readUTF();
			boolean escuchando = true;
			
			while(escuchando) {
				System.out.println(texto);
				
				if(texto.equals("D")){
					DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
					salida.writeUTF("Lo recibi y te respondo OK");
					salida.flush();
				}
				//System.out.println(texto);
				
				/*for(Socket indice : lista) {
					if(indice != cliente)
						new DataOutputStream(indice.getOutputStream()).writeUTF(texto);
				}*/
				//System.out.println("ESTOY EN EL SERVER: '" + texto + "'");
				texto = new DataInputStream(cliente.getInputStream()).readUTF();
			}	
			
			System.out.println("El cliente se ha desconectado estoy en el hilo servidor");
		} catch (Exception e) {
			System.out.println("El cliente se ha desconectado estoy en el hilo servidor en la excepcion");
		}
	}
}
