import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Partida extends JFrame {

	private static final long serialVersionUID = 1L;

	//le paso al juego los sockets de mis clientes
	public Partida(ArrayList<Socket> listaClientes){
		add(new Mapa(listaClientes));
		setVisible(true);
		setSize(300, 300);
	}
	
	/*public static void main(String[] args) {
		new Partida();
	}*/
}
