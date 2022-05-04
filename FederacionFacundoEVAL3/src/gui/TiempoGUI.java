package gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import entidades.Tiempo;

import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TiempoGUI extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TiempoGUI frame = new TiempoGUI();
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
	public TiempoGUI() {
		setTitle("Nuevo Tiempo");
		setBounds(100, 100, 427, 230);
		getContentPane().setLayout(null);

		

		JTextPane txtpnHoras = new JTextPane();
		txtpnHoras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnHoras.setEditable(false);
		txtpnHoras.setText("Horas");
		txtpnHoras.setBounds(54, 62, 51, 25);
		getContentPane().add(txtpnHoras);

		JTextPane txtpnMinutos = new JTextPane();
		txtpnMinutos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnMinutos.setText("Minutos");
		txtpnMinutos.setEditable(false);
		txtpnMinutos.setBounds(123, 62, 58, 25);
		getContentPane().add(txtpnMinutos);

		JTextPane txtpnSegundos = new JTextPane();
		txtpnSegundos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnSegundos.setText("Segundos");
		txtpnSegundos.setEditable(false);
		txtpnSegundos.setBounds(199, 62, 70, 25);
		getContentPane().add(txtpnSegundos);

		JTextPane txtpnCentesimas = new JTextPane();
		txtpnCentesimas.setText("Centesimas");
		txtpnCentesimas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnCentesimas.setEditable(false);
		txtpnCentesimas.setBounds(279, 62, 82, 25);
		getContentPane().add(txtpnCentesimas);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 24, 1));
		spinner.setBounds(54, 100, 39, 23);
		getContentPane().add(spinner);

		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spinner_1.setBounds(133, 100, 39, 23);
		getContentPane().add(spinner_1);

		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 60, 1));
		spinner_2.setBounds(219, 100, 39, 23);
		getContentPane().add(spinner_2);

		JSpinner spinner_3 = new JSpinner();
		spinner_3.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinner_3.setBounds(301, 100, 39, 23);
		getContentPane().add(spinner_3);
		
		JButton btnNewButton = new JButton("Agregar nuevo Tiempo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(123, 150, 167, 23);
		getContentPane().add(btnNewButton);

	}
}
