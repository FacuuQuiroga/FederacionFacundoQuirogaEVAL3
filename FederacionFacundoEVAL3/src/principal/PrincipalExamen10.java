package principal;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import entidades.Patrocinador;
import entidades.Responsable;
import utils.Datos;

public class PrincipalExamen10 {

	public static void main(String[] args) {

		/**
		 * Ejercicio 8 apartado b examen 10
		 * 
		 * @author Facu
		 */
		// creamos una coleccion del tipo Linkedlist y el objeto del tipo Patrocinador
		List<Patrocinador> patrocinadores = new LinkedList<Patrocinador>();

		Responsable r1 = new Responsable(1, "902422202", LocalTime.of(00, 00), LocalTime.of(23, 59),
				Datos.buscarPersonaPorId(1011));
		Patrocinador p1 = new Patrocinador(1, "ALSA", 500.00, "www.alsa.es", r1);
		patrocinadores.add(p1);

		Responsable r2 = new Responsable(2, "985181105", LocalTime.of(9, 00), LocalTime.of(18, 00),
				Datos.buscarPersonaPorId(1012));
		Patrocinador p2 = new Patrocinador(2, "Ayto.Gijon", 250.00, "www.gijon.es", r2);
		patrocinadores.add(p2);

		Responsable r3 = new Responsable(3, "985103000", LocalTime.of(8, 30), LocalTime.of(20, 00),
				Datos.buscarPersonaPorId(1012));
		Patrocinador p3 = new Patrocinador(3, "Universidad de Oviedo", 350.00, "www.uniovi.es", r3);
		patrocinadores.add(p3);

		Responsable r4 = new Responsable(4, "985185503", LocalTime.of(8, 30), LocalTime.of(18, 00),
				Datos.buscarPersonaPorId(1012));
		Patrocinador p4 = new Patrocinador(4, "CIFP la Laboral", 255.99, "www.cifplalaboral.es", r3);
		patrocinadores.add(p4);

		/**
		 * Ejercicio 9 apartado b examen 10 Desde el programa principal de la clase
		 * PrincipalExam10.java implementar la funcionalidad para recorrer la colección
		 * del ejercicio 8 e insertar esos datos sobre las tablas correspondientes, a
		 * través de métodos insertarConID(T) y insertarSinID (T), correspondientes a la
		 * implementación de la interfaz operacionesCRUD<Patrocinador>.
		 * 
		 * @author Facu
		 */

	}
}
