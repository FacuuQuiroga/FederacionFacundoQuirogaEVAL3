package entidades;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Collection;
import java.util.Scanner;

import dao.operacionesCRUD;
import utils.ConexBD;
import utils.Utilidades;
import validaciones.Validaciones;

public class Patrocinador implements operacionesCRUD<Patrocinador>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -343032129428521823L;
	private long id;
	private String nombre;
	private String web;
	private double dotacion;
	Responsable responsable;

	public Patrocinador() {
	}

	public Patrocinador(long id, String nombre, double dotacion, String web, Responsable r) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.web = web;
		this.dotacion = dotacion;
		this.responsable = r;
	}

	public Patrocinador(long id, String nombre, String web, double dotacion, Responsable r) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.web = web;
		this.dotacion = dotacion;
		this.responsable = r;
	}

	public Patrocinador(Patrocinador p) {
		super();
		this.id = p.id;
		this.nombre = p.nombre;
		this.web = p.web;
		this.dotacion = p.dotacion;
		this.responsable = p.responsable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public double getDotacion() {
		return dotacion;
	}

	public void setDotacion(double dotacion) {
		this.dotacion = dotacion;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	/// Examen 10 ejercicio 2
	public static Patrocinador nuevoPatrocinador() {
		Patrocinador ret = null;
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		long id = 0;
		String nombre = "";
		String web = "";
		double dotacion = 0.0;

		do {
			System.out.println(
					"Introduzca el nombre del nuevo patrocinador (entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
			nombre = in.nextLine();
			valido = Validaciones.validarNombrePatrocinador(nombre);
			if (!valido) {
				System.out.println(
						"El valor introducido para el nombre del patrocinador (debe ser  entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
				continue;
			} else
				valido = true;
		} while (!valido);
		valido = false;

		System.out.println("¿Desea introducir la url de la web del nuevo patrocinador?");
		boolean confirmacion = Utilidades.leerBoolean();
		if (confirmacion) {
			do {
				System.out.println(
						"Introduzca la URL de la web del nuevo patrocinador (entre 3 y 150 caracteres alfabéticos o numéricos solamente):");
				web = in.nextLine();
				valido = Validaciones.validarWebPatrocinador(web);
				if (!valido) {
					System.out.println("El valor introducido para la web del patrocinador es inválido.");
					continue;
				} else
					valido = true;
			} while (!valido);
		}
		valido = false;
		do {
			System.out.println("Introduzca la dotacion en euros del nuevo patrocinador:");
			dotacion = Utilidades.leerDouble();
			valido = Validaciones.validarDotacion(dotacion);
			if (!valido) {
				System.out.println("El valor introducido para la dotacion no es correcta (debe ser mayor que 100):");
				continue;
			} else
				valido = true;
		} while (!valido);

		System.out.println("Introduzca los datos del responsable del nuevo patrocinador:");
		Responsable responsable = Responsable.nuevoResponsable();
		ret = new Patrocinador(id, nombre, web, dotacion, responsable);
		return ret;
	}

	/**
	 * Ejercicio 8 apartado A examen 10
	 * 
	 * @return orden: idPatrocinador | idResponsable | nombre | dotacion | web
	 * 
	 * @author Facu
	 */
	public String data() {
		String ret = "";
		ret = "" + this.id + "|" + responsable.getId() + "|" + this.nombre + "|" + this.dotacion + "|" + this.web;
		return ret;
	}

	/// Examen 10 ejercicio 3C
	/***
	 * Funcion que devuelve una cadena de caracteres con los datos básicos de un
	 * patrocinador: idPatrocinador + nombre + web (si la hay).
	 * 
	 * @return la cadena formateada
	 */
	public String mostrarBasico() {
		String ret = "";
		ret += this.id + ". " + this.nombre + (!this.web.equals("") ? " " + web : " ");
		return ret;
	}

	/// Examen 10 ejercicio 3C
	/**
	 * Funcion que devuelve una cadena de caracteres con los datos de un
	 * patrocinador al completo: idPatrocinador + nombre + web (si la hay) +
	 * dotación en euros (xx.xx euros) + los datos del responsable
	 */
	public String mostrarCompleto() {
		String ret = mostrarBasico();
		ret += Utilidades.mostrarDouble2Decimales(dotacion) + "euros aportados\t";
		ret += "Responsable: " + this.responsable.toString();
		return ret;
	}

	/**
	 * Ejercicio 9 apartado b examen 10
	 * 
	 * @author Facu
	 */
	@Override
	public boolean insertarConID(Patrocinador p) {
		boolean ret = false;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 = "insert into responsable(id, telefonoprof, horarioini, horariofin,persona) values (?,?,?,?,?)";
		String consultaInsertStr2 = "insert into patrocinador(id, nombre, web, dotacion, responsable) values (?,?,?,?,?)";
		try {
			// insertamos el representante
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setLong(1, p.responsable.getId());
			pstmt.setString(2, p.responsable.getTelefonoProf());
			Time horainiSQL = java.sql.Time.valueOf(p.responsable.getHorarioini());
			pstmt.setTime(3, horainiSQL);
			Time horafinSQL = java.sql.Time.valueOf(p.responsable.getHorariofin());
			pstmt.setTime(4, horafinSQL);
			pstmt.setLong(5, p.responsable.persona.getId());

			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				// insertamos el patrocinador
				PreparedStatement pstmt2 = conex.prepareStatement(consultaInsertStr2);
				pstmt2.setLong(1, p.getId());
				pstmt2.setString(2, p.getNombre());
				pstmt2.setString(3, p.getNombre());
				pstmt2.setDouble(4, p.getDotacion());
				pstmt2.setLong(5, p.responsable.getId());
				int resultadoInsercion2 = pstmt2.executeUpdate();
				if (resultadoInsercion2 == 1) {
					System.out.println("Se ha insertado correctamente el nuevo Patrocinador.");
					ret = true;
				}
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

		return ret;
	}

	@Override
	public long insertarSinID(Patrocinador p) {
		long ret = -1;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 = "insert into responsable(telefonoprof, horarioini, horariofin,persona) values (?,?,?,?)";
		String consultaInsertStr2 = "insert into patrocinador( nombre, web, dotacion, responsable) values (?,?,?,?)";
		try {
			// insertamos el representante
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setString(1, p.responsable.getTelefonoProf());
			Time horainiSQL = java.sql.Time.valueOf(p.responsable.getHorarioini());
			pstmt.setTime(2, horainiSQL);
			Time horafinSQL = java.sql.Time.valueOf(p.responsable.getHorariofin());
			pstmt.setTime(3, horafinSQL);
			pstmt.setLong(4, p.responsable.persona.getId());

			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				// consultamos el id que nos autocalculo para el responsable
				String consultaSelect = "SELECT id FROM responsables WHERE (telefonoprof=? AND persona=?  )";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setString(1, p.responsable.getTelefonoProf());
				pstmt2.setLong(2, p.responsable.persona.getId());
				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long idPerPatr = result.getLong("id");
					if (idPerPatr != -1) {
						// insertamos el patrocinador
						PreparedStatement pstmt3 = conex.prepareStatement(consultaInsertStr2);
						pstmt2.setString(1, p.getNombre());
						pstmt2.setString(2, p.getNombre());
						pstmt2.setDouble(3, p.getDotacion());
						pstmt2.setLong(4, idPerPatr);

						int resultadoInsercion2 = pstmt3.executeUpdate();
						if (resultadoInsercion2 == 1) {
							System.out.println("Se ha insertado correctamente el nuevo Patrocinador.");
							ret = p.getId();
						}
					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return ret;
		}
		return ret;
	}

	/**
	 * Ejercicio 10 examen 10
	 * 
	 * @author Facu
	 */
	@Override
	public Patrocinador buscarPorID(long id) {
		return null;
	}

	@Override
	public Collection<Patrocinador> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Patrocinador elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Patrocinador elemento) {
		// TODO Auto-generated method stub
		return false;
	}
}
