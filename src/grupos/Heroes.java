package grupos;

// TODO: Auto-generated Javadoc
/**
 * The Class Heroes.
 */
public class Heroes extends Personajes { // 1. Eliminamos "implements DadosTirados"

	protected DadosTirados tirada;

	/**
	 * Instantiates a new heroes.En esta clase se establecen los requisitos de tiradas para Elfos y Hobbits, además las clases Humano, Elfo y Hobbit
	 * heredarán de esta.
	 *
	 * @param nombre the nombre
	 * @param puntosVida the puntos vida
	 * @param armadura the armadura
	 */
	public Heroes(String nombre, int puntosVida, int armadura) {
		super(nombre, puntosVida, armadura);
		
		// 3. Inicializamos la lógica de los dados usando una Lambda en el constructor
		this.tirada = () -> {
			int dado_uno = (int) (Math.random() * 101);
			int dado_dos = (int) (Math.random() * 101);
			return Math.max(dado_uno, dado_dos); // Math.max simplifica tu if/else original
		};
	}

	/**
	 * Resultado dados. Método que tirará dos dados de 0 a 100, devolviento únicamente el de mayor resultado.
	 * * @return the int
	 */
	public final int resultadoDados() {
		// 4. Mantenemos el método para no romper la clase Guerra, 
		// pero ahora delega la acción al atributo 'tirada'
		return this.tirada.resultadoDados();
	}
	
	/**
	 * Rabia. Método que se dará a los elfos un +10 en tirada de dados cuando lucha contra Orcos
	 *
	 * @return the int
	 */
	public final int rabia() {
		// Usamos el resultado de la tirada interna y sumamos 10
		return this.tirada.resultadoDados() + 10;
	}
	
	/**
	 * Miedo. Método que se dará a los Hobbits en tiradas -5 cuando lucha contra Trasgos.
	 *
	 * @return the int
	 */
	public final int miedo() {
		// Usamos el resultado de la tirada interna y restamos 5
		return this.tirada.resultadoDados() - 5;
	}
}
