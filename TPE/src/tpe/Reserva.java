package tpe;

public class Reserva {

	String origen;
	String destino;
	String aerolinea;
	int reservas;
	
	public Reserva(String o,String d, String a,int r) {
		this.setDestino(d);
		this.setOrigen(o);
		this.setReservas(r);
		this.setAerolinea(a);
	}
	
	public String getAerolinea() {
		return aerolinea;
	}
	
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String nombre) {
		this.origen = nombre;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String nombre) {
		this.destino = nombre;
	}

	public int getReservas() {
		return reservas;
	}

	public void setReservas(int reservas) {
		this.reservas = reservas;
	}

	@Override
	public String toString() {
		return "1)Origen: " + origen + " 2)Destino: " + destino + " 3)Aerolinea: " + aerolinea + " 4)Reservas: "+ reservas;
	}
	
	
}
