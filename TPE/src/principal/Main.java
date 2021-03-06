package principal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

import entidad.Grafo;
import entidad.Aeropuerto;
import entidad.Ruta;
import entidad.Reserva;
import entidad.Timer;
import data.CSVReader;
import data.CSVWritter;

public class Main {

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		Sistema sistema = new Sistema(grafo);
		CSVReader reader = new CSVReader();
		CSVWritter writter = new CSVWritter();
		reader.cargarDatos(sistema, grafo);
		actualizarRutasConReservas(grafo);
		menu(sistema, grafo, writter);
	}

	public static void menu(Sistema sistema, Grafo grafo, CSVWritter writter) {
		boolean seguir = true;
		Timer t = new Timer();
		double timer;
		while (seguir) {
			listarOpciones();
			String op = getString();
			switch (op) {
			case "a":
				// 1
				LinkedList<Aeropuerto> a = new LinkedList<Aeropuerto>(grafo.getAeropuertos());
				ListIterator<Aeropuerto> itr = a.listIterator();
				System.out.println();
				System.out.println("Aeropuertos:");
				while (itr.hasNext()) {
					Aeropuerto aer = itr.next();
					System.out.println(aer.imprimir());
				}
				writter.guardarSalidaA(a);
				System.out.println();
				break;
			case "b":
				// 2
				LinkedList<Reserva> reservas = new LinkedList<Reserva>(sistema.getReservas());
				ListIterator<Reserva> itr2 = reservas.listIterator();
				System.out.println();
				System.out.println("Reservas:");
				while (itr2.hasNext()) {
					Reserva rev = itr2.next();
					System.out.println(rev.imprimir());
				}
				writter.guardarSalidaB(reservas);
				System.out.println();
				break;
			case "c":
				// 3
				System.out.println();
				System.out.println("Ingrese aeropuerto origen:");
				String origen = getString();
				System.out.println("Ingrese aeropuerto destino:");
				String destino = getString();
				System.out.println("Ingrese aerolínea deseada");
				String aerolinea = getString();
				t.start();
				Ruta r = grafo.getRuta(origen, destino, aerolinea);
				timer = t.stop();
				System.out.println("Tiempo de ejecucion " + timer);
				if (r != null) {
					int pasaj = r.getAerolineas().get(aerolinea);
					double dist = r.getDistancia();
					System.out.println("Existe el vuelo");
					System.out.println("Distancia: " + dist);
					System.out.println("Pasajes disponibles: " + pasaj);
					writter.guardarSalidaC(r, aerolinea, pasaj, dist);
				} else {
					System.out.println("No existe el vuelo");
				}
				System.out.println();
				break;
			case "d":
				// 4
				System.out.println();
				System.out.println("Ingrese aeropuerto origen:");
				String origen2 = getString();
				System.out.println("Ingrese aeropuerto destino:");
				String destino2 = getString();
				System.out.println("Ingrese aerolínea no deseada");
				String aerolinea2 = getString();
				t.start();
				LinkedList<LinkedList<Ruta>> servicio2 = sistema.servicio2(origen2, destino2, aerolinea2);
				timer = t.stop();
				System.out.println("Tiempo de ejecucion " + timer);
				ListIterator<LinkedList<Ruta>> itr1 = servicio2.listIterator();
				System.out.println("Rutas:  De " + origen2 + " a " + destino2);
				System.out.println();
				if (itr1.hasNext()) {
					while (itr1.hasNext()) {
						LinkedList<Ruta> tmp = itr1.next();
						ListIterator<Ruta> itr3 = tmp.listIterator();
						int escalas = -1;
						double km = 0;
						while (itr3.hasNext()) {
							LinkedList<String> aerolineas = new LinkedList<String>();
							Ruta ruta = itr3.next();
							escalas++;
							km += ruta.getDistancia();
							java.util.Iterator<String> it = ruta.getAerolineas().keySet().iterator();
							while (it.hasNext()) {
								String aux = it.next();
								if (!aux.equals(aerolinea2))
									aerolineas.add(aux);
							}
							System.out.print(ruta + " " + " " + aerolineas + " ");
						}
						System.out.println();
						System.out.println("1)Escalas: " + escalas + "   2)Distancia: " + km);
						System.out.println();
					}
					writter.guardarSalidaD(servicio2, origen2, destino2, aerolinea2);
					sistema.clearServicio2();
				} else
					System.out.println("No existen vuelos");
				System.out.println();
				break;
			case "e":
				// 5
				System.out.println();
				System.out.println("Ingrese pais: ");
				String pais1 = getString();
				System.out.println("Ingrese pais: ");
				String pais2 = getString();
				t.start();
				LinkedList<Ruta> salida = sistema.servicio3(pais1, pais2);
				timer = t.stop();
				System.out.println("Tiempo de ejecucion " + timer);
				if (!salida.isEmpty()) {
					ListIterator<Ruta> it = salida.listIterator();
					while (it.hasNext()) {
						System.out.println(it.next());
					}
					writter.guardarSalidaE(salida, pais1, pais2);
				} else
					System.out.println("No existe");
				System.out.println();
				break;
			case "f":
				System.out.println("Ingrese aeropuerto origen: ");
				String o = getString();
				t.start();
				LinkedList<Ruta> tmp = sistema.backtracking(o);
				timer = t.stop();
				System.out.println("Tiempo de ejecucion " + timer);
				if (tmp != null) {
					ListIterator<Ruta> itr3 = tmp.listIterator();
					double km = 0;
					System.out.println("Mejor solucion: ");
					while (itr3.hasNext()) {
						Ruta aux = itr3.next();
						if (km == 0) {
							System.out.print(aux.toString() + " -- ");
							km += aux.getDistancia();
						} else {
							System.out.print(aux.getDestino().getNombre() + " -- ");
							km += aux.getDistancia();
						}
					}
					System.out.println();
					System.out.println(km);
					System.out.println();
					sistema.clearBack();
				} else
					System.out.println("No hay solucion");
				break;
			case "g":
				System.out.println("Ingrese aeropuerto origen: ");
				String aer = getString();
				t.start();
				LinkedList<Ruta> greedy = sistema.greedy(aer);
				timer = t.stop();
				System.out.println("Tiempo de ejecucion " + timer);
				if (greedy != null) {
					ListIterator<Ruta> it4 = greedy.listIterator();
					double dist = 0;
					while (it4.hasNext()) {
						Ruta rtmp = it4.next();
						dist += rtmp.getDistancia();
						System.out.print(rtmp.toString() + " -- ");
					}
					System.out.println();
					System.out.println("Distancia: " + " " + dist);
					System.out.println();
					sistema.clearGreedy();
				}
				break;
			default:
				seguir = false;
				;
			}
		}
	}

	public static void actualizarRutasConReservas(Grafo grafo) {
		LinkedList<Ruta> rutas = grafo.getRutas();
		ListIterator<Ruta> iterador = rutas.listIterator();
		while (iterador.hasNext()) {
			iterador.next().actualizar();
		}
	}

	public static void listarOpciones() {
		System.out.println("Ingrese una opcion: ");
		System.out.println("a. Listar aeropuertos");
		System.out.println("b. Listar reservas");
		System.out.println("c. Verificar vuelo directo");
		System.out.println("d. Obtener vuelos sin aerolinea");
		System.out.println("e. Vuelos disponibles");
		System.out.println("f. Backtracking");
		System.out.println("g. Greedy");
		System.out.println(" . Exit");
	}

	public static String getString() {
		BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
		String opcion;
		try {
			opcion = new String(entrada.readLine());
		} catch (Exception e) {
			opcion = "";
		}
		return opcion;
	}
}