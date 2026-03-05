package juego;

import java.util.ArrayList;
import java.util.List;

import grupos.Bestias;
import grupos.Heroes;
import personajes.Elfo;
import personajes.Hobbit;
import personajes.Humano;
import personajes.Orco;
import personajes.Trasgo;

// TODO: Auto-generated Javadoc
/**
 * The Class Aplicacion.
 */
public class Aplicacion {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Se crea dos listas con los dos bandos que se enfretarán en la batalla.
		List<Heroes> luchadoresHeroes = new ArrayList<>();
		List<Bestias> luchadoresBestias = new ArrayList<>();

		//Se procede a agregar los integrantes de cada bando.
		luchadoresHeroes.add(new Elfo("legolas", 150, 30));
		luchadoresHeroes.add(new Humano("Aragorn", 150, 50));
		luchadoresHeroes.add(new Humano("Boromir", 100,60));
		luchadoresHeroes.add(new Humano("Gandalf", 300, 30));
		luchadoresHeroes.add(new Hobbit("Frodo", 20, 10));

		luchadoresBestias.add(new Orco("Lurtz", 200, 60));
		luchadoresBestias.add(new Orco("Shagrat", 220, 50));
		luchadoresBestias.add(new Trasgo("Uglúk", 120, 30));
		luchadoresBestias.add(new Trasgo("Mauhúr", 100, 30));
		
		//Método estático que recibe los bandos y ejecuta la batalla.
		Guerra.batalla(luchadoresHeroes, luchadoresBestias);

	}
}
