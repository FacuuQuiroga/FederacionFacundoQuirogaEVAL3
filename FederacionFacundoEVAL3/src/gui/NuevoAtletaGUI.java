package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.*;
import dao.AtletaDAO;
import dao.DatosPersonaDAO;
import dao.EquipoDAO;
import entidades.Equipo;
import utils.ConexBD;
import validaciones.Validaciones;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class NuevoAtletaGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_Nombre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_NifNie;
	private JTextField textField_Tel;
	private JTextField textField_Altura;
	private JTextField textField_Peso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuevoAtletaGUI frame = new NuevoAtletaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NuevoAtletaGUI() {
		setTitle("Nuevo Atleta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Labels texto
		JLabel lblNombre = new JLabel("Nombre *:");
		lblNombre.setBounds(54, 50, 58, 14);
		contentPane.add(lblNombre);

		JLabel lblTel = new JLabel("Telefono *:");
		lblTel.setBounds(54, 154, 58, 14);
		contentPane.add(lblTel);

		JLabel lblDoc = new JLabel("Documentacion *:");
		lblDoc.setBounds(54, 79, 99, 14);
		contentPane.add(lblDoc);

		JLabel lblFNac = new JLabel("Fecha de Nacimiento *:");
		lblFNac.setBounds(311, 151, 124, 20);
		contentPane.add(lblFNac);

		JLabel lblAltura = new JLabel("Altura *:");
		lblAltura.setBounds(66, 262, 46, 14);
		contentPane.add(lblAltura);

		JLabel lblPeso = new JLabel("Peso *:");
		lblPeso.setBounds(66, 293, 46, 14);
		contentPane.add(lblPeso);

		// Spinners
		JSpinner spinner_FechaNac = new JSpinner();
		spinner_FechaNac.setModel(
				new SpinnerDateModel(new Date(1652220000000L), null, new Date(1652220000000L), Calendar.WEEK_OF_YEAR));
		spinner_FechaNac.setBounds(428, 151, 144, 20);
		contentPane.add(spinner_FechaNac);

		// button group doc
		JRadioButton rdbtnNif = new JRadioButton("NIF");
		buttonGroup.add(rdbtnNif);
		rdbtnNif.setBounds(159, 75, 54, 23);
		contentPane.add(rdbtnNif);

		JRadioButton rdbtnNie = new JRadioButton("NIE");
		buttonGroup.add(rdbtnNie);
		rdbtnNie.setBounds(222, 75, 48, 23);
		contentPane.add(rdbtnNie);

		// textFields
		textField_Nombre = new JTextField();
		textField_Nombre.setBounds(149, 47, 195, 20);
		contentPane.add(textField_Nombre);
		textField_Nombre.setColumns(10);

		textField_NifNie = new JTextField();
		textField_NifNie.setColumns(10);
		textField_NifNie.setBounds(266, 105, 195, 20);
		contentPane.add(textField_NifNie);

		textField_Altura = new JTextField();
		textField_Altura.setBounds(148, 259, 86, 20);
		contentPane.add(textField_Altura);
		textField_Altura.setColumns(10);

		textField_Peso = new JTextField();
		textField_Peso.setColumns(10);
		textField_Peso.setBounds(148, 290, 86, 20);
		contentPane.add(textField_Peso);

		textField_Tel = new JTextField();
		textField_Tel.setColumns(10);
		textField_Tel.setBounds(122, 151, 166, 20);
		contentPane.add(textField_Tel);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Atleta nuevo = new Atleta();
				DatosPersona nuevoDp = new DatosPersona();

				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";

				// tomamos los datos para DatosPersona nuevoDp
				String nombre = textField_Nombre.getText();
				valido = Validaciones.validarNombre(nombre);
				if (!valido) {
					errores += "El nombre del atleta no es valido. \n";
					lblNombre.setForeground(Color.RED);
				} else {
					nuevoDp.setNombre(nombre);
					valido = false;
				}

				String telefono = textField_Tel.getText();
				valido = Validaciones.validarTelefono(telefono);
				if (!valido) {
					errores += "El Telefono del atleta no es valido. \n";
					lblTel.setForeground(Color.RED);
				} else {
					nuevoDp.setTelefono(telefono);
					valido = false;
				}

				Date fechaFea = (java.util.Date) spinner_FechaNac.getValue();
				LocalDate fechaLinda = LocalDate.of(fechaFea.getYear() + 1900, fechaFea.getMonth() + 1,
						fechaFea.getDate());
				valido = Validaciones.validarFecha(fechaLinda);
				if (!valido) {
					errores += "La fecha del atleta no es valida. \n";
					lblFNac.setForeground(Color.RED);
				} else {
					nuevoDp.setFechaNac(fechaLinda);
					valido = false;
				}

				Documentacion docs;
				valido = !(!rdbtnNif.isSelected() && !rdbtnNie.isSelected())
						|| (rdbtnNif.isSelected() && rdbtnNie.isSelected());
				if (!valido) {
					errores += "Se debe seleccionar un tipo de Documentacion\n";
					lblDoc.setForeground(Color.RED);
				} else {
					String doc = textField_NifNie.getText();
					boolean docValido = false;
					if (!rdbtnNif.isSelected()) {
						docs = new NIF(doc);
						docValido = Validaciones.validarNIF(doc);
					} else {
						docs = new NIE(doc);
						docValido = Validaciones.validarNIE(doc);
					}
					if (!docValido) {
						errores += "el numero de Documentacion no es valido \n";
						lblDoc.setForeground(Color.RED);
					}
					nuevoDp.setNifnie(docs);
					valido = false;
				}

				// tomamos los datos para DatosPersona para atleta
				nuevo.setPersona(nuevoDp);

				String altura = textField_Altura.getText();
				Float aFloat = Float.parseFloat(altura);
				valido = Validaciones.validarAltura(aFloat);
				if (!valido) {
					errores += "La altura del atleta no es valida. \n";
					lblAltura.setForeground(Color.RED);
				} else {
					nuevo.setAltura(aFloat);
					valido = false;
				}

				String peso = textField_Peso.getText();
				Float aPeso = Float.parseFloat(peso);
				valido = Validaciones.validarPeso(aPeso);
				if (!valido) {
					errores += "El peso del atleta no es valida. \n";
					lblAltura.setForeground(Color.RED);
				} else {
					nuevo.setPeso(aPeso);
					valido = false;
				}

				// seleccion del equipo

				// Aviso de errores
				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos de Nueva Prueba NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// insertamos los datos de Datospersona y Atleta a la BD
				DatosPersonaDAO dpDao = new DatosPersonaDAO(ConexBD.establecerConexion());
				long idDpNuevo = dpDao.insertarSinID(nuevoDp);
				if (idDpNuevo <= 0) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al insertar la Nueva Persona en la BD";
					msj = "Hubo un error y NO se ha insertado la nueva Persona en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					nuevoDp.setId(idDpNuevo);
					titulo = "Nueva Persona insertada en la BD";
					msj = "Se ha insertado correctamente a la nueva persona:\n" + nuevoDp.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
				}
				
				AtletaDAO aDao = new AtletaDAO(ConexBD.establecerConexion());
				long idANuevo = aDao.insertarSinID(nuevo);
				if (idANuevo <= 0) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al insertar al nuevo Alteta en la BD";
					msj = "Hubo un error y NO se ha insertado al nuevo Altleta en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					nuevo.setId(idANuevo);
					titulo = "Nuevo atleta insertada en la BD";
					msj = "Se ha insertado correctamente al nuevo Atelta:\n" + nuevoDp.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});
		btnAceptar.setBounds(399, 391, 89, 23);
		contentPane.add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(498, 391, 89, 23);
		contentPane.add(btnCancelar);

		JTextPane txtpnDatosPersonales = new JTextPane();
		txtpnDatosPersonales.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosPersonales.setText("Datos Personales");
		txtpnDatosPersonales.setBounds(24, 11, 136, 25);
		contentPane.add(txtpnDatosPersonales);

		JTextPane txtpnSeleccioneLaOpcion = new JTextPane();
		txtpnSeleccioneLaOpcion.setText("Seleccione la opcion e introduzca el valor");
		txtpnSeleccioneLaOpcion.setBounds(54, 104, 202, 23);
		contentPane.add(txtpnSeleccioneLaOpcion);

		JTextPane txtpnDatosFisicos = new JTextPane();
		txtpnDatosFisicos.setText("Datos Fisicos");
		txtpnDatosFisicos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnDatosFisicos.setBounds(24, 223, 136, 25);
		contentPane.add(txtpnDatosFisicos);

		JTextPane txtpnMenFormato = new JTextPane();
		txtpnMenFormato.setText("m. (en formato xx.xx)");
		txtpnMenFormato.setBounds(259, 256, 114, 23);
		contentPane.add(txtpnMenFormato);

		JTextPane txtpnKgenFormato = new JTextPane();
		txtpnKgenFormato.setText("Kg. (en formato xx.xx)");
		txtpnKgenFormato.setBounds(259, 290, 130, 23);
		contentPane.add(txtpnKgenFormato);

		JTextPane txtpnEquipo = new JTextPane();
		txtpnEquipo.setText("Equipo:");
		txtpnEquipo.setBounds(41, 347, 48, 20);
		contentPane.add(txtpnEquipo);

		// Combobox de equipos
		DefaultComboBoxModel<Equipo> modeloEquipos = new DefaultComboBoxModel<Equipo>();
		JComboBox<Equipo> comboBoxEquipos = new JComboBox<Equipo>(modeloEquipos);
		EquipoDAO equipoDAO = new EquipoDAO(ConexBD.getCon());
		ArrayList<Equipo> listaEquipos = (ArrayList<Equipo>) equipoDAO.buscarTodos();
		for (Equipo pat : listaEquipos)
			comboBoxEquipos.addItem(pat);

		/// Strings para el combobox
		String[] equipoStr = new String[listaEquipos.size()];
		for (int i = 0; i < listaEquipos.size(); i++) {
			equipoStr[i] = listaEquipos.get(i).toString();
		}
		comboBoxEquipos.setModel(new DefaultComboBoxModel(equipoStr));

		comboBoxEquipos.setBounds(99, 345, 465, 22);
		contentPane.add(comboBoxEquipos);

	}
}
