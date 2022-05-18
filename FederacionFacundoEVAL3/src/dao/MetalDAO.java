package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import entidades.Metal;
import entidades.Oro;
import entidades.Plata;
import entidades.Bronce;
import entidades.Equipo;
import utils.ConexBD;

public class MetalDAO implements operacionesCRUD<Metal> {

	Connection conex;

	public MetalDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}

	@Override
	public boolean insertarConID(Metal elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long insertarSinID(Metal m) {
		long ret = -1;
		String consultaInsertStr1 = "insert into metales(pureza, asignada, idoro, idplata, idbronce ) values (?,?,?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			pstmt.setFloat(1, m.getPureza());
			pstmt.setBoolean(2, m.isAsignada());
			if (m.getClass().equals(Oro.class)) {
				m = (Oro) m;
				pstmt.setLong(3, ((Oro) m).getId());
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else if (m.getClass().equals(Plata.class)) {
				m = (Plata) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setLong(4, ((Plata) m).getId());
				pstmt.setNull(5, java.sql.Types.INTEGER);
			} else {
				m = (Bronce) m;
				pstmt.setNull(3, java.sql.Types.INTEGER);
				pstmt.setNull(4, java.sql.Types.INTEGER);
				pstmt.setLong(5, ((Bronce) m).getId());
			}

			int resultadoInsercion = pstmt.executeUpdate();
			if (resultadoInsercion == 1) {
				String consultaSelect = "SELECT id FROM metales WHERE (pureza=? AND ";
				PreparedStatement pstmt2 = conex.prepareStatement(consultaSelect);
				pstmt2.setFloat(1, m.getPureza());
				if (m.getClass().equals(Oro.class)) {
					consultaSelect += " idoro=?)";
					pstmt2.setLong(2, ((Oro) m).getId());
				} else if (m.getClass().equals(Plata.class)) {
					consultaSelect += " idplata=?)";
					pstmt2.setLong(2, ((Plata) m).getId());
				} else {
					consultaSelect += " idbronce=?)";
					pstmt2.setLong(2, ((Bronce) m).getId());
				}

				ResultSet result = pstmt2.executeQuery();
				while (result.next()) {
					long idmetal = result.getLong("id");
					if (idmetal != -1)
						ret = idmetal;
				}
				result.close();
				pstmt2.close();
			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
			return -1;
		}

		return ret;
	}

	@Override
	public Metal buscarPorID(long id) {
		return null;
	}

	@Override
	public Collection<Metal> buscarTodos() {
		Metal m = null;

		Collection<Metal> metales = new ArrayList<Metal>();
		Connection conex = ConexBD.establecerConexion();
		String consultaSelect = "select * FROM metales";
		try {
			Statement pstmt = conex.createStatement();
			ResultSet result = pstmt.executeQuery(consultaSelect);
			while (result.next()) {
				//pureza, asignada, idoro, idplata, idbronce
				
				long idOro = result.getLong("idoro");
				long idPlata = result.getLong("idplata");
				long idBronce = result.getLong("idbronce");

				Float pureza = result.getFloat("pureza");
				boolean asignada = result.getBoolean("asignada");
				
				if(idOro != 0) {
					Oro o = new Oro(idOro, pureza);
					o.setAsignada(asignada);
					metales.add(o);
				}else if (idPlata !=0) {
					Plata p = new Plata(idPlata, pureza);
					p.setAsignada(asignada);
					metales.add(p);
				}else {
					Bronce b = new Bronce(idBronce, pureza);
					b.setAsignada(asignada);
					metales.add(b);
				}

			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return metales;		
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
