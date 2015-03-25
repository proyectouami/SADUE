package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mx.uam.ayd.sadue.fonts.CustomFont;

public class DialogoApartadoFinalizado extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public DialogoApartadoFinalizado() {
		setTitle("Apartado Finalizado");
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 450, 151);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
			btnAceptar.setBounds(173, 67, 97, 28);
			getContentPane().add(btnAceptar);
		}
		
		JLabel lblCompraFin = new JLabel("\u00A1El apartado se ha completado!");
		lblCompraFin.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompraFin.setFont(cf.MyFont(0, 17f));
	//	lblEstsAPunto.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblCompraFin.setForeground(Color.LIGHT_GRAY);
		lblCompraFin.setBounds(54, 30, 335, 18);
		getContentPane().add(lblCompraFin);
	}

}
