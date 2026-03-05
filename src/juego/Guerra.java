package juego;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import grupos.Bestias;
import grupos.Heroes;
import personajes.Elfo;
import personajes.Hobbit;
import personajes.Orco;
import personajes.Trasgo;

// TODO: Auto-generated Javadoc
/**
 * The Class Guerra.
 */
public class Guerra {

	/**
	 * Batalla. Método principal donde se desarrolla toda la batalla.
	 *
	 * @param luchadoresHeroes  the luchadores heroes
	 * @param luchadoresBestias the luchadores bestias
	 */
	public static void batalla(List<Heroes> luchadoresHeroes, List<Bestias> luchadoresBestias) {

		/*
		 * Se crea un lanzador que se encargará de comprobar que se cumpla o no la
		 * condición. se crea un contador el cual se encargará de hacer un control de
		 * los turnos realizados.
		 */
		boolean lanzador = false; // Corregido de Boolean (objeto) a boolean (primitivo)
		int contador = 1;

		/*
		 * Se instancias dos listas Queue para gestionar las listas en caso en el que
		 * tanto en la de heroes como en la de bestias haya más que en el otro.
		 */
		Queue<Heroes> colaHeroes = new LinkedList<>();
		Queue<Bestias> colaBestias = new LinkedList<>();

		// Un bucle while donde se realizarán todas las repeticiones generales del
		// combate
		while (!lanzador) {

			// Se crean dos objetos Iterator<> los cuales recorrerán las listas de heroes y
			// luchadores
			Iterator<Heroes> iteradorHeroes = luchadoresHeroes.iterator();
			Iterator<Bestias> iteradorBestias = luchadoresBestias.iterator();
			
			// imprimimos por pantalla los turnos que van sucediendo con el contador.
			System.out.println("\nturno " + contador);

			/*
			 * Ponemos otro bucle while donde se comprobará si los integradores tiene
			 * elementos en en su interior.
			 */
			while (iteradorHeroes.hasNext() && iteradorBestias.hasNext()) {
				// instancia un objeto heroe y uno bestia con la posición de la lista para
				// realizar el combate
				Heroes heroe = iteradorHeroes.next();
				Bestias bestia = iteradorBestias.next();

				// presentación de combatientes enfrentados
				System.out.println("Lucha entre " + heroe.getNombre() + " (Vida = " + heroe.getPuntosVida()
						+ " Armadura = " + heroe.getArmadura() + ") y " + bestia.getNombre() + "(Vida = "
						+ bestia.getPuntosVida() + " Armadura = " + bestia.getArmadura() + ")");

				// Una vez presentados, se hace uso del método que calcula y modifica los datos
				// del daño.
				Guerra.calculoTotal(heroe, bestia);

				/*
				 * se comprueba de cada objeto si sus puntos de vida son igual o menos a 0. si
				 * se cumple la condición es el que personaje habrá muerto lo que imprimirá por
				 * pantalla el nombre de este y posteriormente será eliminado del iterador.
				 */
				if (bestia.getPuntosVida() <= 0) {
					System.out.println("\n**** " + bestia.getNombre() + " muere.****\n");
					iteradorBestias.remove();

				}
				if (heroe.getPuntosVida() <= 0) {
					System.out.println("\n**** " + heroe.getNombre() + " muere.****\n");
					iteradorHeroes.remove();
				}
			}

			// En este punto terminaría el turno y le sumaría en 1 el contador.
			contador++;

			/*
			 * En los dos siguientes bucles while se comprueba si ambas listas tienen la
			 * misma cantidad de elementos, de no ser así, el excedente se añade a la cola y
			 * se remueve de la lista originar para evitar de errores de capacidad.
			 */
			while (luchadoresHeroes.size() > luchadoresBestias.size()) {
				colaHeroes.add(luchadoresHeroes.remove(luchadoresHeroes.size() - 1));
			}
			while (luchadoresBestias.size() > luchadoresHeroes.size()) {
				colaBestias.add(luchadoresBestias.remove(luchadoresBestias.size() - 1));
			}

			/*
			 * Las dos siguientes condiciones se encargan de comprobar si alguna de las dos
			 * colas héroe o bestia no están vacías, si se cumple la condición se añade uno
			 * de los personajes de la cola a la lista de comparte principal.
			 */
			if (!colaHeroes.isEmpty()) {
				luchadoresHeroes.add(colaHeroes.poll());
			}
			if (!colaBestias.isEmpty()) {
				luchadoresBestias.add(colaBestias.poll());
			}

			/*
			 * Las dos siguientes condiciones comprueban de alguna de las dos listas está
			 * vacía y la que esté vacía será porque el otro equipo ha ganado, imprimiendo
			 * el resultado por consola y cambiando el valor de la condición cerrando el
			 * bucle principal.
			 */
			if (luchadoresHeroes.isEmpty()) {
				System.out.println("La Bestias ganan la batalla");
				lanzador = true;
			} else if (luchadoresBestias.isEmpty()) {
				System.out.println("Los Heroes ganan la batalla");
				lanzador = true;
			}

		}

	}

	/**
	 * Calculo total. Muestra por pantalla información de daño y modifica los
	 * valores de vida.
	 *
	 * @param heroe  the heroe
	 * @param bestia the bestia
	 */
	public static final void calculoTotal(Heroes heroe, Bestias bestia) {
		// dado se encarga de devolver el numero de dado según las condiciones del
		// personaje.
		int dadoHeroe = Guerra.totalDadoHeroe(heroe, bestia);
		int dadoBestia = Guerra.totalDadoBestia(bestia);

		// danio nos devuelve el daño que recibe el personaje teniendo en cuenta la
		// armadura.
		int danioBestia = Guerra.calculoDanio(dadoHeroe, bestia.getArmadura());
		int danioHeroe = Guerra.calculoDanio(dadoBestia, heroe.getArmadura());

		// se hacen los cálculos de vida menos el daño e imprime alguna información.
		bestia.setPuntosVida(Math.max(bestia.getPuntosVida() - danioBestia, 0));
		System.out.println(heroe.getNombre() + " saca " + dadoHeroe + " y le quita " + danioBestia);

		heroe.setPuntosVida(Math.max(heroe.getPuntosVida() - danioHeroe, 0));
		System.out.println(bestia.getNombre() + " saca " + dadoBestia + " y le quita " + danioHeroe);

	}

	/**
	 * Total dado heroe.devuelve el número de dado teniendo en cuenta las
	 * condiciones del personaje
	 *
	 * Se piden ciertas condiciones pedidas en el ejercicio, la cual si personaje es
	 * un elfo y combate contra un orco, este hace llamada al método rabia que suma
	 * en 10 el el resultado del dado mayor.
	 *
	 * Si el personaje es un Hobbit y se enfrenta a un trasgos se hace llamada al
	 * método miedo, el cual reduce en 5 puntos del resultado del dado mayor.
	 * * @param heroe  the heroe
	 * @param bestia the bestia
	 * @return the int
	 */
	public static int totalDadoHeroe(Heroes heroe, Bestias bestia) {
		// Al estar los métodos en la clase padre Heroes, se llaman directamente sin casteos.
		if (heroe instanceof Elfo && bestia instanceof Orco)
			return heroe.rabia();
		else if (heroe instanceof Hobbit && bestia instanceof Trasgo)
			return heroe.miedo();
		else
			return heroe.resultadoDados();
	}

	/**
	 * Total dado bestia. Se identifica el atacante.
	 * * Aquí se establece el requisito que se dará a los orcos, el cual nos pide que reduce
	 * un 10% de armadura a su contrincante en cada turno. Para poderlo hacer de una forma simplificada para el ejercicio
	 * equivadría a aumentar su daño en un 10% 
	 *
	 * @param bestia the bestia
	 * @return the int
	 */
	public static int totalDadoBestia(Bestias bestia) {
		// Al estar los métodos en la clase padre Bestias, se llaman directamente sin casteos.
		// NOTA: Asegúrate de que en la clase Bestias el método se llame fuerza() y no fuerta()
		if (bestia instanceof Orco)
			return bestia.fuerza(); 
		else
			return bestia.resultadoDados();
	}

	/**
	 * Calculo danio. Calculo de daór de dado y armadura donde cualquier valore negativo es 0.
	 *
	 * @param dado     the dado
	 * @param armadura the armadura
	 * @return the int
	 */
	public static int calculoDanio(int dado, int armadura) {
		return Math.max(dado - armadura, 0);
	}

}