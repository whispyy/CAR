import java.io.IOException;


public class Main {
	public static void main(String[] args) throws IOException{
		Serveur s = new Serveur();
		s.accueilServeur();
		s.initPort();
		s.lancementServeur();
	}
}
