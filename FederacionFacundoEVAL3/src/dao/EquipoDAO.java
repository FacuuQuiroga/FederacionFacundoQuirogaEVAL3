package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Equipo;
import entidades.Lugar;
import entidades.Patrocinador;
import utils.ConexBD;

public class EquipoDAO implements operacionesCRUD<Equipo> {
	Connection conex;

	public EquipoDAO(Connection co) {
		if (this.conex == null)
			this.conex = co;
	}

	@Override
	public boolean insertarConID(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Equipo elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Equipo buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Equipo> buscarTodos() {
		Collection<Equipo> equipos = new ArrayList<Equipo>();
		Connection conex = ConexBD.establecerConexion();
		String consultaSelect = "select * FROM equipos";
		try {
			Statement pstmt = conex.createStatement();
			ResultSet result = pstmt.executeQuery(consultaSelect);
			while (result.next()) {
				long idEquipo = result.getLong("id");
				String nombre = result.getString("nombre");
				long idManager = result.getLong("idmanager");
				int anio = result.getInt("anioinscripcion");
				
				Equipo e = new Equipo();
				e.setId(idEquipo);
				e.setAnioinscripcion(anio);
				e.setNombre(nombre);
			
				ManagerDAO mDAO = new ManagerDAO(conex);
				e.setManager(mDAO.buscarPorID(idManager));
				equipos.add(e);
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return equipos;
	}

	@Override
	public boolean modificar(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Equipo elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
