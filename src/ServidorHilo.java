import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;


public class ServidorHilo extends Thread {
	
	private Socket cliente;
	private ArrayList<Socket> lista;
	
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
				
				if(texto.equals("D")){
					DataOutputStream salida = new DataOutputStream(lista.get(0).getOutputStream());
					salida.writeUTF("Lo recibi y te respondo OK");
					salida.flush();
				}
				//System.out.println(texto);
				
				/*for(Socket indice : lista) {
					if(indice != cliente)
						new DataOutputStream(indice.getOutputStream()).writeUTF(texto);
				}*/
				System.out.println("ESTOY EN EL SERVER: " + texto);
				sleep(5000);
				texto = new DataInputStream(cliente.getInputStream()).readUTF();
				
				//System.out.println("ESTOY CICLANDO");
			}
			
			System.out.println("El cliente se ha desconectado estoy en el hilo servidor");
		} catch (Exception e) {
			System.out.println("El cliente se ha desconectado estoy en el hilo servidor en la excepcion");
		}
	}
}
