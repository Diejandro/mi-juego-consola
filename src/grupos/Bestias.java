package grupos;

// TODO: Auto-generated Javadoc
/**
 * The Class Bestias.
 */
public class Bestias extends Personajes{
	
	/** The tirada. */
	protected DadosTirados tirada;
	
	/**
	 * Instantiates a new bestias.
	 *
	 * @param nombre the nombre
	 * @param puntosVida the puntos vida
	 * @param armadura the armadura
	 */
	public Bestias (String nombre, int puntosVida, int armadura) {
		super(nombre, puntosVida, armadura);
		
		this.tirada = () -> (int) (Math.random() * 91);
	}
	
	/**
	 * Resultado dados.
	 *
	 * @return the int
	 */
	public final int resultadoDados() {
		return this.tirada.resultadoDados();
	}

	/**
	 * Fuerza.
	 *
	 * @return the int
	 */
	public final int fuerza() {
		 int resultado = tirada.resultadoDados();
		 
		 return (int) (resultado * 0.1);
		 
	}
}