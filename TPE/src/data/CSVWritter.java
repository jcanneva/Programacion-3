package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

import entidad.Aeropuerto;
import entidad.Reserva;
import entidad.Ruta;

public class CSVWritter {

	public void guardarSalidaA(LinkedList<Aeropuerto> a) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/Users/jcann/TPE/src/Dataget/salidaA.csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			ListIterator<Aeropuerto> itr = a.listIterator();
			while (itr.hasNext()) {  
				bw.write(itr.next().imprimir());
				bw.newLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public void guardarSalidaB(LinkedList<Reserva> r) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/Users/jcann/TPE/src/Dataget/salidaB.csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			ListIterator<Reserva> itr = r.listIterator();
				while (itr.hasNext()) {
					bw.write(itr.next().imprimir());
					bw.newLine();
				}
			bw.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public void guardarSalidaC(Ruta r, String a, int p, double d) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/Users/jcann/TPE/src/Dataget/salidaC.csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(r.toString());
			bw.newLine();
			bw.write(a);
			bw.newLine();
			bw.write("Distancia: " + d);
			bw.newLine();
			bw.write("Pasajes disponibles: " + p);
			bw.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public void guardarSalidaD(LinkedList<LinkedList<Ruta>> servicio2, String o, String d, String a) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/Users/jcann/TPE/src/Dataget/salidaD.csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			ListIterator<LinkedList<Ruta>> itr = servicio2.listIterator();
			int contador=0;
			bw.write("De " + o + " a " + d);
			bw.newLine();
			bw.write(" Sin arolinea:"+a);
			bw.newLine();
			while (itr.hasNext()) {
				LinkedList<Ruta> r = itr.next();
				ListIterator<Ruta> it = r.listIterator();
				contador++;
				bw.write(contador+") ");
				bw.newLine();
				int escalas=-1; double km=0;
				while (it.hasNext()) {
					Ruta ru= it.next();
					bw.write(ru.toString());
					bw.newLine();
					escalas++;
					km=ru.getDistancia();
				}
				bw.write("Escalas: " + escalas + " Distancia:" + km);
				bw.newLine();
			}
			bw.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}

	public void guardarSalidaE(LinkedList<Ruta> r, String p1, String p2) {
		BufferedWriter bw = null;
		try {
			File file = new File("C:/Users/jcann/TPE/src/Dataget/salidaE.csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(p1 + " " + p2);
			bw.newLine();
			bw.write(r.toString());
			bw.newLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
		}
	}
}
