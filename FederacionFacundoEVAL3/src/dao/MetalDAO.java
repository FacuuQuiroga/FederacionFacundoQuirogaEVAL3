package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import entidades.Metal;
import utils.ConexBD;

public class MetalDAO implements operacionesCRUD<Metal> {

	@Override
	public boolean insertarConID(Metal m) {
		boolean ret = false;
		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr = "insert into managers(idmetal,idtipometal,pureza,es_oro,es_plata,es_bronce) values (?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);

			pstmt.setLong(1, m.);
			pstmt.setLong(2, m.);
			pstmt.setLong(3, (long) m.getPureza());
			pstmt.setString(4, m.);
			pstmt.setString(5, m.);
			pstmt.setString(6, m.);

			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}
		return ret;		return false;
	}

	@Override
	public long insertarSinID(Metal elemento) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Metal buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Metal> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(Metal elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Metal elemento) {
		// TODO Auto-generated method stub
		return false;
	}

}
