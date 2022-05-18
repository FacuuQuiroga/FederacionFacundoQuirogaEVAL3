package entidades;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

public class Responsable {
	private long id;
	private String telefonoProf;
	private LocalTime horarioini;
	private LocalTime horariofin;
	DatosPersona persona;
	private java.time.LocalTime horarioIni;
	private java.time.LocalTime horarioFin;

	public Responsable() {
	}

	public Responsable(long id, String tel, LocalTime hi, LocalTime hf, DatosPersona dp) {
		this.id = id;
		this.telefonoProf = tel;
		this.horariofin = hf;
		this.horarioini = hi;
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
	
	public java.time.LocalTime getHorarioIni() {
		return horarioIni;
	}

	public void setHorarioIni(java.time.LocalTime horarioIni) {
		this.horarioIni = horarioIni;
	}

	public java.time.LocalTime getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(java.time.LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}

	/// Examen 10 Ejercicio 2
	public static Responsable nuevoResponsable() {
		Responsable ret = null;
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		long id = 0;
		String tfnProf = "";
		java.time.LocalTime horaini;
		java.time.LocalTime horafin;
		do {
			System.out.println("Introduzca el telefono profesional del nuevo responsable (9 o 10 dígitos):");
			tfnProf = in.nextLine();
			valido = Validaciones.validarTelefonoProf(tfnProf);
			if (!valido) {
				System.out.println(
						"El valor introducido para el telefono profesional no es correcto (debe ser de 9 o 10 dígitos solamente):");
				continue;
			} else
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca la hora de inicio de la franja de atención para el nuevo responsable:");
			horaini = Utilidades.leerHora();
			System.out.println("Introduzca la hora de fin de la franja de atención para el nuevo responsable:");
			horafin = Utilidades.leerHora();
			valido = Validaciones.validarRangoHorario(horaini, horafin);
			if (!valido) {
				System.out.println("El valor introducido para la franja horaria de atención no es correcta.");
				continue;
			} else
				valido = true;
		} while (!valido);
		System.out.println("Introduzca ahora los datos personales del nuevo representante:");
		DatosPersona dp = DatosPersona.nuevaPersona();
		ret = new Responsable(id, tfnProf, horaini, horafin, dp);
		return ret;
	}

	/**
	 * Ejercicio 4 examen 10
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
