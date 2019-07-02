package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entidad.Aeropuerto;
import entidad.Grafo;
import entidad.Ruta;
import entidad.Reserva;
import tpe.Sistema;

public class CSVReader {

	public void cargarDatos(Sistema s, Grafo grafo) {
		String csvAeropuerto = "C:/Users/jcann/TPE/src/Dataset/Mini/Aeropuertos.csv";
		String csvRutas = "C:/Users/jcann/TPE/src/Dataset/Mini/Rutas.csv";
		String csvReservas = "C:/Users/jcann/TPE/src/Dataset/Mini/Reservas.csv";
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

		// reservas
		try (BufferedReader br = new BufferedReader(new FileReader(csvReservas))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				int cant = Integer.parseInt(items[3]);
				Reserva r = new Reserva(items[0], items[1], items[2], cant);
				s.addReserva(r);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
