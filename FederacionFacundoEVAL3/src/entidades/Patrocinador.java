package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import utils.ConexBD;

public class Patrocinador implements operacionesCRUD<Patrocinador> {
	private long id;
	private String nombre;
	private String web;
	private double dotacion;
	Responsable responsable;

	public Patrocinador() {
	}

	public Patrocinador(int idPatr, String nombre, double d, String web, Responsable resp) {
		this.id = idPatr;
		this.nombre = nombre;
		this.dotacion = d;
		this.web = web;
		this.responsable = resp;
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
	 * Ejercicio 10  examen 10
	 * 
	 * @author Facu
	 */
	@Override
	public long buscarPorID(Patrocinador p) {
		return id;
	
	}
}
