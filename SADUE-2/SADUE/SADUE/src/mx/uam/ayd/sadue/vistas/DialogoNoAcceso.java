package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import javax.swing.SwingConstants;

public class DialogoNoAcceso extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public DialogoNoAcceso() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 162);
		setTitle("Error");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		CustomFont cf = new CustomFont();
		
		getContentPane().setLayout(null);
		{
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAceptar.setFont(cf.MyFont(0, 15f));
	//		btnAceptar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			btnAceptar.setBounds(173, 84, 97, 28);
			getContentPane().add(btnAceptar);
		}
		
		JLabel lblEstsAPunto = new JLabel("No puedes acceder al sistema");
		lblEstsAPunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstsAPunto.setFont(cf.MyFont(0, 17f));
	//	lblEstsAPunto.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblEstsAPunto.setForeground(Color.LIGHT_GRAY);
		lblEstsAPunto.setBounds(85, 24, 273, 18);
		getContentPane().add(lblEstsAPunto);
		
		JLabel lblrealmenteDeseasSalir = new JLabel("Usuario / Contrase\u00F1a incorrecto");
		lblrealmenteDeseasSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblrealmenteDeseasSalir.setFont(cf.MyFont(0, 17f));
	//	lblrealmenteDeseasSalir.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblrealmenteDeseasSalir.setForeground(Color.LIGHT_GRAY);
		lblrealmenteDeseasSalir.setBounds(83, 47, 278, 18);
		getContentPane().add(lblrealmenteDeseasSalir);
	}

}
