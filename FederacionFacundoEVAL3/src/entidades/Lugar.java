package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import dao.operacionesCRUD;
import utils.ConexBD;

//Examen 1 Ejercicio 1
public enum Lugar implements operacionesCRUD<Lugar> {
	GIJONMESTAS(1, "Las Mestas", "Gijon", true), GIJONCENTRO(2, "Centro ciudad", "Gijon", true),
	OVIEDOSANFRANCISCO(3, "Parque San Francisco", "Oviedo", true), AVILESPUERTO(4, "Puerto", "Aviles", true),
	AVILESPABELLON(5, "Pabellon deportivo Aviles", "Aviles", false), OVIEDOCENTRO(6, "Centro ciudad", "Oviedo", true);

	private int id;
	private String nombre;
	private String ubicacion;
	private boolean airelibre;

	private Lugar(int id, String nombre, String ubicacion, boolean airelibre) {
		this.id = id;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.airelibre = airelibre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public boolean isAirelibre() {
		return airelibre;
	}

	/**
	 * Ejercicio10 examen 10
	 * 
	 * @author Facu
	 */

	@Override
	public boolean insertarConID(Lugar elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Lugar elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Lugar buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Lugar> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Lugar elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Lugar elemento) {
		// TODO Auto-generated method stub
		return false;
	}
}
