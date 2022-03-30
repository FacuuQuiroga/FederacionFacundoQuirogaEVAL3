package entidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import utils.Datos;
import utils.Utilidades;
import validaciones.Validaciones;
import entidades.ComparadorDocumentacion;

public class DatosPersona implements Comparable<DatosPersona> {
	private long id;
	private String nombre;
	private String telefono;
	private LocalDate fechaNac;

	private Documentacion nifnie; // Examen 2 Ejercicio 3.2

	public DatosPersona(long id, String nombre, String telefono, LocalDate fechaNac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
	}

	// Examen 2 Ejercicio 3.2
	public DatosPersona(long id, String nombre, String telefono, LocalDate fechaNac, Documentacion nifnie) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.nifnie = nifnie;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public Documentacion getNifnie() {
		return nifnie;
	}

	public void setNifnie(Documentacion nifnie) {
		this.nifnie = nifnie;
	}

	@Override
	public String toString() {
		return nombre + " NIF/NIE: " + nifnie.mostrar() + " Tfn:" + telefono + " ("
				+ fechaNac.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ")";
	}

	// Examen 2 Ejercicio 3.3
	// Examen 5 Ejercicio 3
	public static DatosPersona nuevaPersona() {
		DatosPersona ret = null;
		Scanner in;
		long id = -1;
		String nombre = "";
		String tfn = "";
		boolean valido = false;
		do {
			System.out.println("Introduzca el id de la nueva persona:");
			in = new Scanner(System.in);
			id = in.nextInt();
			valido = Validaciones.validarId(id);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el identificador.");
			else
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el nombre de la nueva persona:");
			in = new Scanner(System.in);
			nombre = in.nextLine();
			valido = Validaciones.validarNombre(nombre);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el nombre no es válido. ");
		} while (!valido);
		do {
			System.out.println("Introduzca el teléfono de la nueva persona:");
			in = new Scanner(System.in);
			tfn = in.nextLine();
			valido = Validaciones.validarTelefono(tfn);
			if (!valido)
				System.out.println("ERROR: El valor introducido para el teléfono no es válido. ");
		} while (!valido);
		System.out.println("Introduzca la fecha de nacimiento de la nueva persona");
		LocalDate fecha = Utilidades.leerFecha();
		System.out.println("¿Va a introducir un NIF? (pulse 'S' par SÍ o 'N' para NIE)");
		boolean esnif = Utilidades.leerBoolean();
		Documentacion doc;
		valido = false;
		do {
			if (esnif)
				doc = NIF.nuevoNIF();
			else
				doc = NIE.nuevoNIE();
			valido = doc.validar();
			if (!valido)
				System.out.println("ERROR: El documento introducido no es válido.");
		} while (!valido);
		ret = new DatosPersona(id, nombre, tfn, fecha, doc);
		return ret;
	}

	/**
	 * Ejercicio 1 apartado A examen 9
	 * 
	 * @return orden: idPersona | nombre | telefono | fechaNac(dd/MM/YYYY) | NIFNIE
	 * @author Facu
	 */
	public String data() {
		String ret = "";
		ret = this.id + "|" + this.nombre + "|" + this.telefono + "|" + this.fechaNac + "|" + this.nifnie.mostrar();
		return ret;
	}

	/**
	 * Ejercicio 1 apartado C examen 9
	 * 
	 * @author Facu
	 */
	public static void exportarAtletasTxt() {
		System.out.println("Guardando datos en altetas_alfabetico.txt...");

		File fOut = new File("atletas_alfabetico.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(fOut);
			bw = new BufferedWriter(fw);

			LinkedList<DatosPersona> dp = new LinkedList<DatosPersona>();
			for (int i = 0; i < Datos.ATLETAS.length; i++) {
				Atleta a = null;
				DatosPersona dp1 = null;
				a = Datos.ATLETAS[i];
				dp1 = a.getPersona();
				dp.add(dp1);
				Collections.sort(dp, new ComparadorAlfabetico());
			}
			Iterator<DatosPersona> it = dp.iterator();

			while (it.hasNext()) {
				DatosPersona per =it.next();
				//imprime bien todos los datos de atleta pero no los guarda en el fichero txt
				System.out.println(per.data());
				bw.write(per.data() + "\n");

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ejercicio 2 apartado A examen 9
	 * @author Facu
	 */
	@Override
	public int compareTo(DatosPersona o) {
		ComparadorDocumentacion cd = null;
		
		if (fechaNac == o.fechaNac)
			int coso = cd.compare(nifnie, o.nifnie);
			return coso;
		else if (fechaNac.compareTo(o.fechaNac)) 
			return 1;
		else
			return -1;
	}

}
