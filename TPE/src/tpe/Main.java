package tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
	
	public static void CSVReader(Grafo grafo) {
		String csvAeropuerto = "C:/Users/jcann/TPE/src/tpe/Dataset/Aeropuertos.csv";
		String csvRutas = "C:/Users/jcann/TPE/src/tpe/Dataset/Rutas.csv";
		String csvReservas = "C:/Users/jcann/TPE/src/tpe/Dataset/Reservas.csv";
		String line = "";
		String cvsSplitBy = ";";

		// aeropuertos
		try (BufferedReader br = new BufferedReader(new FileReader(csvAeropuerto))) {
			
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				Aeropuerto p = new Aeropuerto(items[0], items[2], items[1]);
				grafo.addAeropuerto(p);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		// rutas
		try (BufferedReader br = new BufferedReader(new FileReader(csvRutas))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				Aeropuerto origen = grafo.getAeropuerto(items[0]);
				Aeropuerto destino = grafo.getAeropuerto(items[1]);
				double distancia = Double.parseDouble(items[2]);
				boolean cabotaje;
				if (items[3].contains("1")) {
					cabotaje = true;
					;
				} else
					cabotaje = false;
				Ruta r = new Ruta(origen, destino, distancia, cabotaje);
				String linea = items[4];
				String split = "\\{|,|-|}|/";
				String[] items2 = linea.split(split);
				int i = 1;
				while (i < items2.length - 1) {
					String aerolinea = items2[i];
					Integer cant = Integer.parseInt(items2[i + 1]);
					r.addAerolinea(aerolinea, cant);
					i = i + 2;
				}
				grafo.addRuta(r);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//reservas
		try (BufferedReader br = new BufferedReader(new FileReader(csvReservas))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				int cant = Integer.parseInt(items[3]);
				Reserva r = new Reserva(items[0],items[1],items[2],cant);
				grafo.addReserva(r);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		Grafo grafo = new Grafo();
		CSVReader(grafo);
		
		//1
//		LinkedList<Aeropuerto> a = new LinkedList<Aeropuerto>(grafo.getAeropuertos());
//		ListIterator<Aeropuerto> itr = a.listIterator();
//		System.out.println("Aeropuertos:");
//		while (itr.hasNext())
//			System.out.println(itr.next().toString());
		
		//2
//		LinkedList<Reserva> reservas = new LinkedList<Reserva>(grafo.getReservas());
//		ListIterator<Reserva> itr2 = reservas.listIterator();
//		System.out.println("Reservas:");
//		while (itr2.hasNext())
//			System.out.println(itr2.next().toString());
		
		//3
		String origen="Jorge Chavez";
		String destino="Armando Tola";
//		String aerolinea="LATAM";
//		Ruta r = grafo.getRuta(origen, destino, aerolinea);
//		if(r!=null) {
//			System.out.println("existe");
//			System.out.println(origen);
//			System.out.println(destino);
//			System.out.println(aerolinea);
//			System.out.println(r.getDistancia());
//			System.out.println(r.getPasajes(aerolinea));
//		}else {
//			System.out.println("no existe");	
//		}
		
		//4
		Aeropuerto or = grafo.getAeropuerto(origen);
		Aeropuerto dest = grafo.getAeropuerto(destino);
		LinkedList<Ruta> rutas = grafo.DFS_recorrer(or, dest);
		ListIterator<Ruta> itr1 = rutas.listIterator();
		System.out.println("Rutas:");
		while (itr1.hasNext())
			System.out.println(itr1.next().toString());
	}
}