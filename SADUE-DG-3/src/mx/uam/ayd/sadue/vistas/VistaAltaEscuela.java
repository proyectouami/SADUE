package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.negocio.ServicioAltaEscuela;

public class VistaAltaEscuela extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFEscuela;

	/**
	 * Create the dialog.
	 */
	public VistaAltaEscuela(final ServicioAltaEscuela servicioAE) {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1000, 520);
		setTitle("Alta de Usuarios");
		setModal(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		getContentPane().setLayout(null);
		
		CustomFont cf = new CustomFont();
		
		final JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!servicioAE.buscarEscuela(textFEscuela.getText())){
					servicioAE.agregarEscuela(0, textFEscuela.getText());
					System.out.println(textFEscuela.getText());
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Ya existe esta escuela", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setFont(cf.MyFont(0, 15f));
	//	btnIngresar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnAceptar.setBounds(306, 348, 181, 28);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(cf.MyFont(0, 15f));
	//	btnIngresar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnCancelar.setBounds(541, 348, 181, 28);
		getContentPane().add(btnCancelar);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Escuela:");
		lblNombreDeUsuario.setForeground(Color.LIGHT_GRAY);
		lblNombreDeUsuario.setFont(cf.MyFont(0, 19f));
		lblNombreDeUsuario.setBounds(306, 250, 158, 20);
		getContentPane().add(lblNombreDeUsuario);
		
		textFEscuela = new JTextField();
		textFEscuela.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(textFEscuela.getText().length() < 3){
					btnAceptar.setEnabled(false);
				}
				else {
					btnAceptar.setEnabled(true);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFEscuela.getText().length() >= 60){
					e.consume();
				}
			}
		});
		textFEscuela.setBounds(476, 242, 246, 35);
		textFEscuela.setFont(cf.MyFont(0, 17f));
		getContentPane().add(textFEscuela);
		textFEscuela.setColumns(10);
		
		JLabel lblAltaDeUsuarios = new JLabel("Alta de Escuelas");
		lblAltaDeUsuarios.setForeground(Color.WHITE);
		lblAltaDeUsuarios.setFont(cf.MyFont(0, 30f));
		lblAltaDeUsuarios.setBounds(398, 110, 204, 31);
		getContentPane().add(lblAltaDeUsuarios);
		
		JLabel lblCompletaLosCampos = new JLabel("Escribe el nombre de una escuela");
		lblCompletaLosCampos.setForeground(Color.LIGHT_GRAY);
		lblCompletaLosCampos.setFont(cf.MyFont(0, 19f));
		lblCompletaLosCampos.setBounds(368, 153, 264, 20);
		getContentPane().add(lblCompletaLosCampos);
		
	}
}
