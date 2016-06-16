import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.Timer;


public class Servidor {
	
	private ArrayList<Socket> lista;
	
	public Servidor(int puerto) {
		
		lista = new ArrayList<Socket>();
		
		try {
			ServerSocket servidor = new ServerSocket(puerto);
			
			System.out.println("Servidor en Línea...");
			
			//acepta 4 usuarios (en un futuro)
			for (int i = 1; i <= 1 ; i++) {
				Socket cliente = servidor.accept();
				lista.add(cliente);
				System.out.println("hay " + lista.size() + " usuarios conectados");
				new ServidorHilo(cliente, lista).start();
			}
			
			Partida partida = new Partida();
			Mapa mapa = Mapa.getIntance();
			partida.cargarMapa(mapa);
			mapa.setTimerJuego(new Timer(1000,mapa));
			mapa.agregarUsuario(lista.get(0));
			
			
			//se conectaron los 4 clientes (ahora solo 1)
			//partida en juego
			boolean inGame = true;
			while(inGame){
				
			}
			
			
			servidor.close();
			System.out.println("El Servidor se ha cerrado");
						
		} catch (Exception e) {
			System.err.println("Ocurrió un problema con el Servidor");
		}
	}

	public static void main(String[] args) {
		new Servidor(10000);
	}
}
