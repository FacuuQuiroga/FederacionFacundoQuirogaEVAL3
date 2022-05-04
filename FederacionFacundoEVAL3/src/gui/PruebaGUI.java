package gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.PatrocinadorDAO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import entidades.Lugar;
import entidades.Patrocinador;
import entidades.Prueba;
import utils.ConexBD;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PruebaGUI extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PruebaGUI frame = new PruebaGUI();
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
	public PruebaGUI() {
		setTitle("Nueva Prueba");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(335, 236, 89, 23);
		getContentPane().add(btnNewButton_1);

		JTextPane txtpnIdDeLa = new JTextPane();
		txtpnIdDeLa.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnIdDeLa.setEnabled(false);
		txtpnIdDeLa.setEditable(false);
		txtpnIdDeLa.setText("Id de la Prueba");
		txtpnIdDeLa.setBounds(31, 32, 115, 20);
		getContentPane().add(txtpnIdDeLa);

		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setText("Nombre");
		txtpnNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnNombre.setEnabled(false);
		txtpnNombre.setEditable(false);
		txtpnNombre.setBounds(31, 63, 115, 20);
		getContentPane().add(txtpnNombre);

		JTextPane txtpnFechaDeCelebracion = new JTextPane();
		txtpnFechaDeCelebracion.setText("Fecha de celebracion");
		txtpnFechaDeCelebracion.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnFechaDeCelebracion.setEnabled(false);
		txtpnFechaDeCelebracion.setEditable(false);
		txtpnFechaDeCelebracion.setBounds(31, 94, 142, 20);
		getContentPane().add(txtpnFechaDeCelebracion);

		JTextPane txtpnLugar = new JTextPane();
		txtpnLugar.setText("Lugar");
		txtpnLugar.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnLugar.setEnabled(false);
		txtpnLugar.setEditable(false);
		txtpnLugar.setBounds(31, 125, 115, 20);
		getContentPane().add(txtpnLugar);

		JTextPane txtpnIndividual = new JTextPane();
		txtpnIndividual.setText("Tipo de prueba");
		txtpnIndividual.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnIndividual.setEnabled(false);
		txtpnIndividual.setEditable(false);
		txtpnIndividual.setBounds(31, 156, 115, 20);
		getContentPane().add(txtpnIndividual);

		JTextPane txtpnPatrocinador = new JTextPane();
		txtpnPatrocinador.setText("Patrocinador");
		txtpnPatrocinador.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnPatrocinador.setEnabled(false);
		txtpnPatrocinador.setEditable(false);
		txtpnPatrocinador.setBounds(31, 187, 115, 20);
		getContentPane().add(txtpnPatrocinador);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(192, 63, 163, 20);
		getContentPane().add(textField);

		JTextPane txtpnIdDeLa_1 = new JTextPane();
		// obtener el ultimo id desde la base de datos y ponerlo en una variable y
		// remplazarlo en elsiguiente campo
		txtpnIdDeLa_1.setText("13211232311232");
		txtpnIdDeLa_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnIdDeLa_1.setEnabled(false);
		txtpnIdDeLa_1.setEditable(false);
		txtpnIdDeLa_1.setBounds(192, 32, 163, 20);
		getContentPane().add(txtpnIdDeLa_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(192, 94, 163, 20);
		getContentPane().add(textField_1);

		//// botonGroup del tipo de prueba
		ButtonGroup grupoTipo = new ButtonGroup();

		JCheckBox chckbxNewCheckBox = new JCheckBox("Individual");
		chckbxNewCheckBox.setBounds(192, 156, 97, 23);
		getContentPane().add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Equipos");
		chckbxNewCheckBox_1.setBounds(291, 156, 97, 23);
		getContentPane().add(chckbxNewCheckBox_1);

		grupoTipo.add(chckbxNewCheckBox_1);
		grupoTipo.add(chckbxNewCheckBox);
		////

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(Lugar.values()));
		comboBox.setBounds(192, 125, 163, 22);
		getContentPane().add(comboBox);

		////
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(192, 187, 163, 22);
		getContentPane().add(comboBox_1);

		ArrayList<Patrocinador> p = new ArrayList();
		Connection c = ConexBD.establecerConexion();
		PatrocinadorDAO pDAO = new PatrocinadorDAO(c);

		p = (ArrayList<Patrocinador>) pDAO.buscarTodos();
		comboBox.setModel((ComboBoxModel) p);

		//// ActionListener del boton Aceptar
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long id = 0;// hay que obtener el ultimo id desde la DB
				String nombre = textField.getText();
				LocalDate fecha = LocalDate.parse(textField_1.getText());
				Lugar lugar = (Lugar) comboBox.getSelectedItem();
				boolean individual = false;
				Patrocinador patro = (Patrocinador) comboBox_1.getSelectedItem();

				Prueba prueba = new Prueba(id, nombre, fecha, lugar, individual, patro);
			}
		});
		btnNewButton.setBounds(231, 236, 89, 23);
		getContentPane().add(btnNewButton);

	}
}
