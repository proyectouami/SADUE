package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import mx.uam.ayd.sadue.fonts.CustomFont;

public class DialogoUniformeQuitado extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public DialogoUniformeQuitado() {
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
		
		JLabel lblEstsAPunto = new JLabel("¡El uniforme se ha quitado exitosamente!");
		lblEstsAPunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstsAPunto.setFont(cf.MyFont(0, 17f));
	//	lblEstsAPunto.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblEstsAPunto.setForeground(Color.LIGHT_GRAY);
		lblEstsAPunto.setBounds(66, 30, 312, 18);
		getContentPane().add(lblEstsAPunto);
	}

}
