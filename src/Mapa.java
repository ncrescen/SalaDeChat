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

	private static final Mapa instanciaMapa = new Mapa();
	private Timer timerJuego;
	private int TIMER_JUEGO;
	private Personaje personajeDeUnCliente;
	
	private Mapa(){

	}
	
	public static Mapa getIntance(){
		return instanciaMapa;
	}
	
	public Timer getTimerJuego() {
		return timerJuego;
	}

	public void setTimerJuego(Timer timerJuego) {
		this.timerJuego = timerJuego;
	}

	public int getTIMER_JUEGO() {
		return TIMER_JUEGO;
	}

	public void setTIMER_JUEGO(int tIMER_JUEGO) {
		TIMER_JUEGO = tIMER_JUEGO;
	}
	
	public Personaje getPersonaje(Personaje personajeJugador) {
		Personaje personaje = personajeJugador;
		//esto seria una lista de personajes que recorro hasta que encuentro el buscado
		if(personaje.getSocketCliente().equals(personajeJugador.getSocketCliente()))
			personaje = personajeDeUnCliente;
		return personaje;
	}

	public void setPersonaje(Personaje personajeDeUnCliente) {
		this.personajeDeUnCliente = personajeDeUnCliente;
	}

	public void agregarUsuario(Socket cliente){
		this.personajeDeUnCliente = new Personaje(cliente, 10, 10);
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