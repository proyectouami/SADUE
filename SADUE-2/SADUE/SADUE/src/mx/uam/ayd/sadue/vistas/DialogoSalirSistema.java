package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.fonts.CustomFont2;
import mx.uam.ayd.sadue.fonts.CustomFont3;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DialogoSalirSistema extends JDialog {

	/**
	 * Create the dialog.
	 */
	public DialogoSalirSistema() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 189);
		setTitle("Salir del Sistema");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		CustomFont cf = new CustomFont();
		CustomFont2 cf2 = new CustomFont2();
		CustomFont3 cf3 = new CustomFont3();
		
		getContentPane().setLayout(null);
		{
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btnAceptar.setFont(cf.MyFont(0, 15f));
	//		btnAceptar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			btnAceptar.setBounds(107, 93, 97, 28);
			getContentPane().add(btnAceptar);
		}
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnCancelar.setFont(cf.MyFont(0, 15f));
	//		btnCancelar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			btnCancelar.setBounds(238, 93, 101, 28);
			getContentPane().add(btnCancelar);
		}
		
		JLabel lblEstsAPunto = new JLabel("Est\u00E1s a punto de abandonar el sistema");
		lblEstsAPunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstsAPunto.setFont(cf.MyFont(0, 17f));
	//	lblEstsAPunto.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblEstsAPunto.setForeground(Color.LIGHT_GRAY);
		lblEstsAPunto.setBounds(69, 33, 306, 18);
		getContentPane().add(lblEstsAPunto);
		
		JLabel lblrealmenteDeseasSalir = new JLabel("\u00BFRealmente deseas salir?");
		lblrealmenteDeseasSalir.setHorizontalAlignment(SwingConstants.CENTER);
		lblrealmenteDeseasSalir.setFont(cf.MyFont(0, 17f));
	//	lblrealmenteDeseasSalir.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblrealmenteDeseasSalir.setForeground(Color.LIGHT_GRAY);
		lblrealmenteDeseasSalir.setBounds(102, 56, 239, 18);
		getContentPane().add(lblrealmenteDeseasSalir);
	}

}
