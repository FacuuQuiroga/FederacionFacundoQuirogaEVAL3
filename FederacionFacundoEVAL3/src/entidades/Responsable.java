package entidades;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Responsable {
	private long id;
	private String telefonoProf;
	private LocalTime horarioini;
	private LocalTime horariofin;
	DatosPersona persona;

	public Responsable() {
	}
	
	public Responsable(long id, String tel,LocalTime hi,LocalTime hf,DatosPersona dp) {
		this.id = id;
		this.telefonoProf = tel;
		this.horariofin=hf;
		this.horarioini= hi;
		this.persona = dp; 
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefonoProf() {
		return telefonoProf;
	}

	public void setTelefonoProf(String telefonoProf) {
		this.telefonoProf = telefonoProf;
	}

	public LocalTime getHorarioini() {
		return horarioini;
	}

	public void setHorarioini(LocalTime horarioini) {
		this.horarioini = horarioini;
	}

	public LocalTime getHorariofin() {
		return horariofin;
	}

	public void setHorariofin(LocalTime horariofin) {
		this.horariofin = horariofin;
	}
	
	
	
	public DatosPersona getPersona() {
		return persona;
	}

	public void setPersona(DatosPersona persona) {
		this.persona = persona;
	}

	/**
	 * Ejercicio 4  examen 10
	 * 
	 * @return orden: idResponsable | idPersona | telefonoProf | horaIni(HH:mm) |
	 *         horaFin(HH:mm)
	 * 
	 * @author Facu
	 */
	public String data() {
		String ret = "";
		ret = "" + this.id + "|" + persona.getId() + "|" + this.telefonoProf + "|"
				+ this.horarioini.format(DateTimeFormatter.ofPattern("HH:mm")) + "|"
				+ this.horariofin.format(DateTimeFormatter.ofPattern("HH:mm"));
		return ret;
	}
}
