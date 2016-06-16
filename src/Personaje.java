import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Personaje {
	private int x = 0;
	private int y = 0;
	private int dx = 0;
	private int dy = 0;
	private Socket socketCliente;

	public Personaje(Socket socket, int x, int y) {
		this.x = x;
		this.y = y;
		this.socketCliente = socket;
	}
	
	public Socket getSocketCliente() {
		return socketCliente;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void mover() {
		x += dx;
		y += dy;
	}

	public void keyPressed(KeyEvent e) throws IOException {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			moverDerecha();
			/*dx = 1;
			dy = 0;*/
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
			dy = 0;
		}

		if (key == KeyEvent.VK_UP) {
			
			
			dy = -1;
			dx = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
			dx = 0;
		}
	}

	private void moverDerecha() throws IOException {
		DataOutputStream salida = new DataOutputStream(this.socketCliente.getOutputStream());
		salida.writeUTF("D");
		salida.flush();
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
	
	public void paint(Graphics2D g) {
		g.fillOval(x, y, 30, 30);
	}
}