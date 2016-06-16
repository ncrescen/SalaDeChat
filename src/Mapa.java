import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Mapa extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Timer timerJuego;
	private static int DELAY_JUEGO = 5;
	private Personaje personajeDeUnCliente;
	
	public Mapa(ArrayList<Socket> listaClientes){
		inicializarPersonajes(listaClientes);
		inicializarComponentes();
	}
	
	private void inicializarPersonajes(ArrayList<Socket> listaClientes) {
		Socket socket = listaClientes.get(0);
		personajeDeUnCliente = new Personaje(socket, 10, 10);	
	}

	private void inicializarComponentes(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		timerJuego = new Timer(DELAY_JUEGO, this);
        timerJuego.start(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		personajeDeUnCliente.mover();
		repaint();
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarElementos(g);
    }

    private void dibujarElementos(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        personajeDeUnCliente.paint(g2d);   
    }

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			try {
				personajeDeUnCliente.keyPressed(e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			personajeDeUnCliente.keyReleased(e);
		}
	}
}