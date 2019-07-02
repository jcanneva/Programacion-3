package entidad;

/**
 * La clase Timer representa un temporizador que puede ser utilizado para controlar
 * el tiempo de ejecución de un proceso.
 * @author programacionIII
 *
 */

public class Timer {

	private double startTime;

	/**
	 * Constructor de la clase.
	 */
	public Timer() {
		startTime = 0;
	}

	/**
	 * Inicia el temporizador.
	 */
	public void start() {
		startTime = System.nanoTime();
	}

	/**
	 * Detiene el temporizador
	 * @return el tiempo, en milisegundos, transcurrido entre que se inicio y
	 * se detuvo el temporizador.
	 */
	public double stop() {
		double stopTime = System.nanoTime();
		return (stopTime - startTime) / 1000000.0;
	}
	
}
