package entidad;

public class Reserva {

	private String origen;
	private String destino;
	private String aerolinea;
	private int reservas;
	private static final String ORIGEN="1)Origen: ";
	private static final String DESTINO=" 2)Destino: ";
	private static final String AEROLINEA=" 3)Aerolinea: ";
	private static final String RESERVAS=" 4)Reservas: ";
	
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
	
	public String imprimir() {
		return ORIGEN+origen+DESTINO+destino+AEROLINEA+aerolinea+RESERVAS+reservas;
	}
	
	
}
