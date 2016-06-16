import javax.swing.JFrame;

public class Partida extends JFrame {
	
	public Partida(){
		setVisible(true);
		setSize(300, 300);
	}
	
	public void cargarMapa(Mapa mapa){
		add(mapa);
	}
	
}
