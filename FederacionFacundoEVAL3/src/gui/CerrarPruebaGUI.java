package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import dao.AtletaDAO;
import dao.MetalDAO;
import dao.PatrocinadorDAO;
import dao.PruebaDAO;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import entidades.Atleta;
import entidades.Bronce;
import entidades.Lugar;
import entidades.Metal;
import entidades.Oro;
import entidades.Patrocinador;
import entidades.Plata;
import entidades.Prueba;
import entidades.Resultado;
import utils.ConexBD;
import validaciones.Validaciones;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JCheckBox;

public class CerrarPruebaGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private final ButtonGroup buttonGroupTipo = new ButtonGroup();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int idprueba = 1;
					CerrarPruebaGUI frame = new CerrarPruebaGUI(idprueba);
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
	public CerrarPruebaGUI(int idprueba) {
		PruebaDAO pDAO = new PruebaDAO(ConexBD.getCon());
		Prueba prueba = pDAO.buscarPorID(idprueba);
		if (prueba != null) {
			setTitle("Cerrar Prueba" + idprueba);
		} else
			setTitle("Cerrar Prueba INDETERMINADA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 905, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Datos de la prueba", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 21, 424, 199);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblIdPrueba = new JLabel("IdPrueba:");
		lblIdPrueba.setBounds(22, 21, 77, 14);
		panel_1.add(lblIdPrueba);

		textField = new JTextField("" + prueba.getId());
		textField.setBounds(86, 14, 86, 20);
		panel_1.add(textField);
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 48, 77, 14);
		panel_1.add(lblNombre);

		textFieldNombre = new JTextField(prueba.getNombre());
		textFieldNombre.setBounds(86, 41, 261, 20);
		panel_1.add(textFieldNombre);
		textFieldNombre.setEnabled(false);
		textFieldNombre.setEditable(false);
		textFieldNombre.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(22, 73, 46, 14);
		panel_1.add(lblFecha);

		JLabel lblLugar = new JLabel("Lugar:");
		lblLugar.setBounds(200, 73, 46, 14);
		panel_1.add(lblLugar);

		JComboBox comboBoxLugar = new JComboBox();
		comboBoxLugar.setBounds(245, 69, 169, 22);
		panel_1.add(comboBoxLugar);
		comboBoxLugar.setEnabled(false);
		comboBoxLugar.setModel(new DefaultComboBoxModel(Lugar.values()));

		JPanel panel = new JPanel();
		panel.setBounds(22, 98, 210, 52);
		panel_1.add(panel);
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Tipo de Prueba:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JRadioButton rbIndividual = new JRadioButton("Individual");

		rbIndividual.setEnabled(false);
		buttonGroupTipo.add(rbIndividual);
		panel.add(rbIndividual);

		JRadioButton rbEquipos = new JRadioButton("Por Equipos");
		rbEquipos.setEnabled(false);
		buttonGroupTipo.add(rbEquipos);
		panel.add(rbEquipos);
		if (prueba.isIndividual())
			rbIndividual.setSelected(true);
		else
			rbEquipos.setSelected(true);

		JLabel lblPatrocinador = new JLabel("Patrocinador:");
		lblPatrocinador.setBounds(22, 161, 87, 14);
		panel_1.add(lblPatrocinador);

		JLabel lblprimerpuesto = new JLabel("Primer puesto *:");
		lblprimerpuesto.setBounds(444, 21, 109, 14);
		contentPane.add(lblprimerpuesto);

		JLabel lblsegundopuesto = new JLabel("Segundo puesto *:");
		lblsegundopuesto.setBounds(444, 151, 109, 14);
		contentPane.add(lblsegundopuesto);

		JLabel lbltercerpuesto = new JLabel("Tercer puesto *:");
		lbltercerpuesto.setBounds(444, 283, 109, 14);
		contentPane.add(lbltercerpuesto);

		DefaultComboBoxModel<Atleta> atletasModel = new DefaultComboBoxModel<Atleta>();
		AtletaDAO aDAO = new AtletaDAO(ConexBD.getCon());
		ArrayList<Atleta> atletassList = (ArrayList<Atleta>) aDAO.buscarTodos();
		/// Pero el modelo de comboBox básico requiere Strings
		String[] atletasStr = new String[atletassList.size()];
		for (int i = 0; i < atletassList.size(); i++)
			atletasStr[i] = atletassList.get(i).getPersona().data();

		JComboBox<Atleta> comboBoxPuesto1 = new JComboBox<Atleta>();
		comboBoxPuesto1.setModel(new DefaultComboBoxModel(atletasStr));
		lblprimerpuesto.setLabelFor(comboBoxPuesto1);
		comboBoxPuesto1.setBounds(544, 17, 334, 22);
		contentPane.add(comboBoxPuesto1);

		JComboBox<Atleta> comboBoxPuesto2 = new JComboBox<Atleta>();
		comboBoxPuesto2.setModel(new DefaultComboBoxModel(atletasStr));
		lblsegundopuesto.setLabelFor(comboBoxPuesto2);
		comboBoxPuesto2.setBounds(544, 147, 334, 22);
		contentPane.add(comboBoxPuesto2);

		JComboBox<Atleta> comboBoxPuesto3 = new JComboBox<Atleta>();
		comboBoxPuesto3.setModel(new DefaultComboBoxModel(atletasStr));
		lbltercerpuesto.setLabelFor(comboBoxPuesto3);
		comboBoxPuesto3.setBounds(544, 279, 334, 22);
		contentPane.add(comboBoxPuesto3);

		LocalDate hoyMas1MesLD = LocalDate.now().plusMonths(1);
		java.util.Date hoyMas1Mes = new Date(hoyMas1MesLD.getYear() - 1900, hoyMas1MesLD.getMonthValue() - 1,
				hoyMas1MesLD.getDayOfMonth());

		/// Las siguientes lineas seria lo deseable: trabajar diectamente con objetos en
		/// el model del comboBox
		DefaultComboBoxModel<Patrocinador> patrocinadoresModel = new DefaultComboBoxModel<Patrocinador>();
		JComboBox<Patrocinador> comboBoxPatrocinador = new JComboBox<Patrocinador>(patrocinadoresModel);
		PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
		ArrayList<Patrocinador> patrocinadoresList = (ArrayList<Patrocinador>) patDAO.buscarTodos();
		for (Patrocinador pat : patrocinadoresList)
			comboBoxPatrocinador.addItem(pat);

		/// Pero el modelo de comboBox básico requiere Strings
		String[] patrocinadoresStr = new String[patrocinadoresList.size()];
		for (int i = 0; i < patrocinadoresList.size(); i++)
			patrocinadoresStr[i] = patrocinadoresList.get(i).mostrarBasico();
		comboBoxPatrocinador.setModel(new DefaultComboBoxModel(patrocinadoresStr));
		comboBoxPatrocinador.setBounds(96, 157, 261, 22);
		panel_1.add(comboBoxPatrocinador);
		comboBoxPatrocinador.setEnabled(false);

		JSpinner spinnerFecha = new JSpinner();
		spinnerFecha.setBounds(86, 69, 86, 20);
		panel_1.add(spinnerFecha);
		spinnerFecha.setEnabled(false);
		spinnerFecha.setModel(new SpinnerDateModel(hoyMas1Mes, hoyMas1Mes, null, Calendar.DAY_OF_YEAR));

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = "Cerrar ventana";
				String msj = "¿Realmente desea cerrar la ventana?";
				int opcion = JOptionPane.showConfirmDialog(null, msj, titulo, JOptionPane.OK_CANCEL_OPTION);
				if (opcion == JOptionPane.YES_OPTION) {
					/// Aqui se redirigiría al usuario hacia la pantalla principal o se saldria
					System.exit(0); /// SALIR directamente
				}

			}
		});
		buttonCancelar.setBounds(789, 399, 89, 23);
		contentPane.add(buttonCancelar);

		JPanel panel_Tiempo_Oro = new JPanel();
		panel_Tiempo_Oro.setBounds(444, 46, 434, 44);
		contentPane.add(panel_Tiempo_Oro);
		panel_Tiempo_Oro.setLayout(null);

		JTextPane txtpnHoras = new JTextPane();
		txtpnHoras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnHoras.setEditable(false);
		txtpnHoras.setText("Horas");
		txtpnHoras.setBounds(10, 11, 51, 25);
		panel_Tiempo_Oro.add(txtpnHoras);

		JTextPane txtpnMinutos = new JTextPane();
		txtpnMinutos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnMinutos.setText("Minutos");
		txtpnMinutos.setEditable(false);
		txtpnMinutos.setBounds(104, 11, 58, 25);
		panel_Tiempo_Oro.add(txtpnMinutos);

		JTextPane txtpnSegundos = new JTextPane();
		txtpnSegundos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnSegundos.setText("Segundos");
		txtpnSegundos.setEditable(false);
		txtpnSegundos.setBounds(203, 11, 70, 25);
		panel_Tiempo_Oro.add(txtpnSegundos);

		JTextPane txtpnCentesimas = new JTextPane();
		txtpnCentesimas.setText("Cent.");
		txtpnCentesimas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnCentesimas.setEditable(false);
		txtpnCentesimas.setBounds(325, 11, 51, 25);
		panel_Tiempo_Oro.add(txtpnCentesimas);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		spinner.setBounds(57, 11, 39, 23);
		panel_Tiempo_Oro.add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spinner_1.setBounds(166, 11, 39, 23);
		panel_Tiempo_Oro.add(spinner_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spinner_2.setBounds(276, 11, 39, 23);
		panel_Tiempo_Oro.add(spinner_2);

		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_3.setBounds(385, 13, 39, 23);
		panel_Tiempo_Oro.add(spinner_3);

		JLabel lbls_definitivo = new JLabel("Establecer como DEFINITIVO? :");
		lbls_definitivo.setBounds(20, 263, 166, 14);
		contentPane.add(lbls_definitivo);

		JPanel panel_Tiempo_Plata = new JPanel();
		panel_Tiempo_Plata.setLayout(null);
		panel_Tiempo_Plata.setBounds(444, 176, 434, 44);
		contentPane.add(panel_Tiempo_Plata);

		JTextPane txtpnHoras_1 = new JTextPane();
		txtpnHoras_1.setText("Horas");
		txtpnHoras_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnHoras_1.setEditable(false);
		txtpnHoras_1.setBounds(10, 11, 51, 25);
		panel_Tiempo_Plata.add(txtpnHoras_1);

		JTextPane txtpnMinutos_1 = new JTextPane();
		txtpnMinutos_1.setText("Minutos");
		txtpnMinutos_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnMinutos_1.setEditable(false);
		txtpnMinutos_1.setBounds(104, 11, 58, 25);
		panel_Tiempo_Plata.add(txtpnMinutos_1);

		JTextPane txtpnSegundos_1 = new JTextPane();
		txtpnSegundos_1.setText("Segundos");
		txtpnSegundos_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnSegundos_1.setEditable(false);
		txtpnSegundos_1.setBounds(203, 11, 70, 25);
		panel_Tiempo_Plata.add(txtpnSegundos_1);

		JTextPane txtpnCentesimas_1 = new JTextPane();
		txtpnCentesimas_1.setText("Cent.");
		txtpnCentesimas_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnCentesimas_1.setEditable(false);
		txtpnCentesimas_1.setBounds(325, 11, 51, 25);
		panel_Tiempo_Plata.add(txtpnCentesimas_1);

		JSpinner spinner_4 = new JSpinner();
		spinner_4.setBounds(57, 11, 39, 23);
		panel_Tiempo_Plata.add(spinner_4);

		JSpinner spinner_1_1 = new JSpinner();
		spinner_1_1.setBounds(166, 11, 39, 23);
		panel_Tiempo_Plata.add(spinner_1_1);

		JSpinner spinner_2_1 = new JSpinner();
		spinner_2_1.setBounds(276, 11, 39, 23);
		panel_Tiempo_Plata.add(spinner_2_1);

		JSpinner spinner_3_1 = new JSpinner();
		spinner_3_1.setBounds(385, 13, 39, 23);
		panel_Tiempo_Plata.add(spinner_3_1);

		JPanel panel_Tiempo_Bronce = new JPanel();
		panel_Tiempo_Bronce.setLayout(null);
		panel_Tiempo_Bronce.setBounds(444, 308, 434, 44);
		contentPane.add(panel_Tiempo_Bronce);

		JTextPane txtpnHoras_2 = new JTextPane();
		txtpnHoras_2.setText("Horas");
		txtpnHoras_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnHoras_2.setEditable(false);
		txtpnHoras_2.setBounds(10, 11, 51, 25);
		panel_Tiempo_Bronce.add(txtpnHoras_2);

		JTextPane txtpnMinutos_2 = new JTextPane();
		txtpnMinutos_2.setText("Minutos");
		txtpnMinutos_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnMinutos_2.setEditable(false);
		txtpnMinutos_2.setBounds(104, 11, 58, 25);
		panel_Tiempo_Bronce.add(txtpnMinutos_2);

		JTextPane txtpnSegundos_2 = new JTextPane();
		txtpnSegundos_2.setText("Segundos");
		txtpnSegundos_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnSegundos_2.setEditable(false);
		txtpnSegundos_2.setBounds(203, 11, 70, 25);
		panel_Tiempo_Bronce.add(txtpnSegundos_2);

		JTextPane txtpnCentesimas_2 = new JTextPane();
		txtpnCentesimas_2.setText("Cent.");
		txtpnCentesimas_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnCentesimas_2.setEditable(false);
		txtpnCentesimas_2.setBounds(325, 11, 51, 25);
		panel_Tiempo_Bronce.add(txtpnCentesimas_2);

		JSpinner spinner_5 = new JSpinner();
		spinner_5.setBounds(57, 11, 39, 23);
		panel_Tiempo_Bronce.add(spinner_5);

		JSpinner spinner_1_2 = new JSpinner();
		spinner_1_2.setBounds(166, 11, 39, 23);
		panel_Tiempo_Bronce.add(spinner_1_2);

		JSpinner spinner_2_2 = new JSpinner();
		spinner_2_2.setBounds(276, 11, 39, 23);
		panel_Tiempo_Bronce.add(spinner_2_2);

		JSpinner spinner_3_2 = new JSpinner();
		spinner_3_2.setBounds(385, 13, 39, 23);
		panel_Tiempo_Bronce.add(spinner_3_2);

		JLabel lbls_Plata = new JLabel("id Plata:");
		lbls_Plata.setBounds(444, 231, 47, 14);
		contentPane.add(lbls_Plata);

		JLabel lbls_Oro = new JLabel("id Oro:");
		lbls_Oro.setBounds(444, 98, 47, 14);
		contentPane.add(lbls_Oro);

		JLabel lbls_Bronce = new JLabel("id Bronce:");
		lbls_Bronce.setBounds(444, 363, 65, 14);
		contentPane.add(lbls_Bronce);

		/// OBTENER ID DE LOS METALES NO ASIGNADOS
		JComboBox comboBox_Oro = new JComboBox();
		comboBox_Oro.setBounds(501, 94, 59, 22);
		getContentPane().add(comboBox_Oro);

		JComboBox comboBox_Plata = new JComboBox();
		comboBox_Plata.setBounds(501, 227, 59, 22);
		contentPane.add(comboBox_Plata);

		JComboBox comboBox_Bronce = new JComboBox();
		comboBox_Bronce.setBounds(501, 359, 59, 22);
		contentPane.add(comboBox_Bronce);

		ArrayList<Metal> m = new ArrayList();
		Connection met = ConexBD.establecerConexion();
		MetalDAO metDAO = new MetalDAO(met);
		m = (ArrayList<Metal>) metDAO.buscarTodos();

		for (int i = 0; i < m.size(); i++) {
			if (m.getClass().equals(Oro.class) && (!m.get(i).isAsignada())) {
				ArrayList<Metal> o = new ArrayList();
				o.add(m.get(i));
				comboBox_Oro.setModel((ComboBoxModel) o);

			} else if (m.getClass().equals(Plata.class) && (!m.get(i).isAsignada())) {
				ArrayList<Metal> p = new ArrayList();
				p.add(m.get(i));
				comboBox_Plata.setModel((ComboBoxModel) p);

			} else if (m.getClass().equals(Bronce.class) && (!m.get(i).isAsignada())) {
				ArrayList<Metal> b = new ArrayList();
				b.add(m.get(i));
				comboBox_Bronce.setModel((ComboBoxModel) b);
			}
		}

		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(190, 259, 97, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JButton buttonAceptar = new JButton("Aceptar");
		buttonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean valido = false;
				String titulo = "";
				String msj = "";
				String errores = "";
				//TODO  
				if (!chckbxNewCheckBox.isSelected()) {
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				}

				Resultado result = new Resultado();

				long idOro = comboBox_Oro.getSelectedIndex();
				float pureza = 0.0F;// TODO hay q obtener la pureza desde la db
				Oro o = new Oro(idOro, pureza);
				valido = Validaciones.validarId(idOro);
				if (!valido) {
					errores += "El Id del oro no es correcto.\n";
					lbls_Oro.setForeground(Color.RED);
				} else
					result.setPrimero(o);
				valido = false;

				long idPlata = comboBox_Oro.getSelectedIndex();
				float pureza_2 = 0.0F;// TODO hay q obtener la pureza desde la db
				Plata p = new Plata(idPlata, pureza_2);
				valido = Validaciones.validarId(idPlata);
				if (!valido) {
					errores += "El Id del Plata no es correcto.\n";
					lbls_Plata.setForeground(Color.RED);
				} else
					result.setSegundo(p);
				valido = false;

				long idBronce = comboBox_Oro.getSelectedIndex();
				float pureza_3 = 0.0F;// TODO hay q obtener la pureza desde la db
				Bronce b = new Bronce(idBronce, pureza_3);
				valido = Validaciones.validarId(idBronce);
				if (!valido) {
					errores += "El Id del Bronce no es correcto.\n";
					lbls_Bronce.setForeground(Color.RED);
				} else
					result.setTercero(b);
				valido = false;

				/// Tomar cada campo y validarlo. Si alguno no es correcto, avisar al usuario
//				String nombre = textFieldNombre.getText();
//				valido = Validaciones.validarNombrePrueba(nombre);
//				if (!valido) {
//					errores += "El nombre de la prueba no es válido (5-150 caracteres).\n";
//					lblNombre.setForeground(Color.RED);
//				} else
//					nueva.setNombre(nombre);
//				valido = false;
//
//				java.util.Date fecha = (java.util.Date) spinnerFecha.getValue();
//				valido = Validaciones.validarFechaNuevaPrueba(fecha);
//				if (!valido) {
//					errores += "La fecha de la prueba no es válido (posterior a 1 mes desde hoy).\n";
//					lblFecha.setForeground(Color.RED);
//				} else {
//					LocalDate fechaLD = LocalDate.of(fecha.getYear() + 1900, fecha.getMonth() + 1, fecha.getDate());
//					nueva.setFecha(fechaLD);
//				}
//				valido = false;
//				valido = (comboBoxLugar.getSelectedIndex() != -1);
//				if (!valido) {
//					errores += "Debe seleccionar el lugar de celebración de la nueva prueba.\n";
//					lblLugar.setForeground(Color.RED);
//				} else {
//					Lugar lugar = (Lugar) comboBoxLugar.getSelectedItem();
//					nueva.setLugar(lugar);
//				}
//				valido = false;
//				valido = !(!rbIndividual.isSelected() && !rbEquipos.isSelected())
//						|| (rbIndividual.isSelected() && rbEquipos.isSelected());
//				if (!valido) {
//					errores += "Debe seleccionar si la nueva prueba es individual O por equipos.\n";
//					rbIndividual.setForeground(Color.RED);
//					rbEquipos.setForeground(Color.RED);
//				} else {
//					nueva.setIndividual(rbIndividual.isSelected() ? true : false);
//				}
//				valido = false;
//				valido = (comboBoxPatrocinador.getSelectedIndex() != -1);
//				if (!valido) {
//					errores += "Debe seleccionar el Patrocinador de la nueva prueba.\n";
//					lblPatrocinador.setForeground(Color.RED);
//				} else {
//					PatrocinadorDAO patDAO = new PatrocinadorDAO(ConexBD.getCon());
//					String seleccionado = (String) comboBoxPatrocinador.getSelectedItem();
//					String[] aux = seleccionado.split("\\.");
//					long idPat = Long.valueOf(aux[0]);
//					Patrocinador patrocinador = patDAO.buscarPorID(idPat);
//					ConexBD.cerrarConexion();
//					if (patrocinador == null)
//						valido = false;
//					else
//						nueva.setPatrocinador(patrocinador);
//				}

				valido = errores.isEmpty();

				if (!valido) {
					titulo = "ERROR: Campos inválidos";
					msj = "ERROR: los siguientes campos NO son válidos:\n\n";
					if (!errores.isEmpty())
						msj += errores + "\n";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
					return;
				}

				/// Si todos los datos son correctos, llamar a PruebaDAO para insertar en la BD
				PruebaDAO pruebadao = new PruebaDAO(ConexBD.establecerConexion());
				boolean correcto = pruebadao.modificar(prueba);
				/// Tanto si la inserción de la nueva prueba tiene éxito como si no, avisar al
				/// usuario
				if (!correcto) {
					// hubo error al insertar en BD y no se generó la nueva prueba
					titulo = "ERROR al cerrar la Prueba en la BD";
					msj = "Hubo un error y NO se ha cerrado la prueba en la BD.";
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.ERROR_MESSAGE);
				} else {
					titulo = "Prueba " + prueba.getId() + " cerrada en la BD";
					msj = "Se ha cerrado correctamente la  prueba:\n" + prueba.toString();
					JOptionPane.showMessageDialog(null, msj, titulo, JOptionPane.INFORMATION_MESSAGE);
					/// Aqui se redirigiría al usuario hacia la pantalla principal
					/// TODO
				}
			}
		});
		buttonAceptar.setBounds(679, 399, 89, 23);
		contentPane.add(buttonAceptar);
	}
}