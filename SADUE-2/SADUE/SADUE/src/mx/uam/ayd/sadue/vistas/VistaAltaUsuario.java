package mx.uam.ayd.sadue.vistas;


import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JLabel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.negocio.ServicioAltaUsuario;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VistaAltaUsuario extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFUsuario;
	private JTextField textFPasswd;
	private JTextField textFTipoUsuario;
	private JPasswordField jpassField;
	private JCheckBox chckbxVerContrasea;

	/**
	 * Create the dialog.
	 */
	public VistaAltaUsuario(final ServicioAltaUsuario servicioAU) {
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
				if(!servicioAU.buscarUsuario(textFUsuario.getText())){
					servicioAU.agregarUsuario(textFUsuario.getText(), String.valueOf(jpassField.getPassword()), Integer.parseInt(textFTipoUsuario.getText()));
					System.out.println(textFUsuario.getText() + ", " + String.valueOf(jpassField.getPassword()) + ", " + textFTipoUsuario.getText());
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Ya existe este usuario", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAceptar.setFont(cf.MyFont(0, 15f));
	//	btnIngresar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnAceptar.setBounds(306, 376, 181, 28);
		getContentPane().add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(cf.MyFont(0, 15f));
	//	btnIngresar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnCancelar.setBounds(541, 376, 181, 28);
		getContentPane().add(btnCancelar);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setForeground(Color.LIGHT_GRAY);
		lblNombreDeUsuario.setFont(cf.MyFont(0, 19f));
		lblNombreDeUsuario.setBounds(306, 174, 158, 20);
		getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setForeground(Color.LIGHT_GRAY);
		lblContrasea.setFont(cf.MyFont(0, 19f));
		lblContrasea.setBounds(368, 230, 96, 20);
		getContentPane().add(lblContrasea);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario.setForeground(Color.LIGHT_GRAY);
		lblTipoDeUsuario.setFont(cf.MyFont(0, 19f));
		lblTipoDeUsuario.setBounds(336, 310, 128, 20);
		getContentPane().add(lblTipoDeUsuario);
		
		//Se muestra la contraseña cuando CheckBox esta activado y se oculta cuando esta deseleccionada
		chckbxVerContrasea = new JCheckBox("Ver contrase\u00F1a");
		chckbxVerContrasea.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxVerContrasea.isSelected()){
					textFPasswd.setText(String.valueOf(jpassField.getPassword()));
					jpassField.setVisible(false);
					textFPasswd.setEnabled(true);
					textFPasswd.setVisible(true);
				}
				else{
					jpassField.setText(textFPasswd.getText());
					jpassField.setVisible(true);
					textFPasswd.setVisible(false);
				}
			}
		});
		chckbxVerContrasea.setEnabled(false);
		chckbxVerContrasea.setForeground(Color.LIGHT_GRAY);
		chckbxVerContrasea.setFont(cf.MyFont(0, 15f));
		//	chckbxVerContrasea.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		chckbxVerContrasea.setBackground(Color.DARK_GRAY);
		chckbxVerContrasea.setBounds(478, 262, 119, 25);
		getContentPane().add(chckbxVerContrasea);
		
		textFUsuario = new JTextField();
		textFUsuario.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(textFUsuario.getText().length() < 3){
					btnAceptar.setEnabled(false);
					jpassField.setText("Password");
					jpassField.setEnabled(false);
					chckbxVerContrasea.setEnabled(false);
				}
				else {
					jpassField.setText("");
					jpassField.setEnabled(true);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFUsuario.getText().length() >= 20){
					e.consume();
				}
			}
		});
		textFUsuario.setBounds(476, 167, 246, 35);
		textFUsuario.setFont(cf.MyFont(0, 17f));
		getContentPane().add(textFUsuario);
		textFUsuario.setColumns(10);
		
		textFPasswd = new JTextField();
		textFPasswd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFPasswd.getText().length() >= 15){
					e.consume();
				}
			}
		});
		textFPasswd.setBounds(476, 223, 246, 35);
		textFPasswd.setFont(cf.MyFont(0, 17f));
		textFPasswd.setVisible(false);	
		getContentPane().add(textFPasswd);
		textFPasswd.setColumns(10);
		
		textFTipoUsuario = new JTextField(1);
		textFTipoUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textFTipoUsuario.getText().length() > 0){
					btnAceptar.setEnabled(true);
				}
				else{
					btnAceptar.setEnabled(false);
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') ||	(caracter > '1')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(textFTipoUsuario.getText().length() >= 1){
					e.consume();
				}
			}
		});
		textFTipoUsuario.setBounds(476, 303, 76, 35);
		textFTipoUsuario.setFont(cf.MyFont(0, 17f));
		textFTipoUsuario.setEnabled(false);
		getContentPane().add(textFTipoUsuario);
		textFTipoUsuario.setColumns(10);
		
		JLabel lblAltaDeUsuarios = new JLabel("Alta de Usuarios");
		lblAltaDeUsuarios.setForeground(Color.WHITE);
		lblAltaDeUsuarios.setFont(cf.MyFont(0, 30f));
		lblAltaDeUsuarios.setBounds(410, 58, 204, 31);
		getContentPane().add(lblAltaDeUsuarios);
		
		JLabel lblCompletaLosCampos = new JLabel("Completa los campos con los datos requeridos");
		lblCompletaLosCampos.setForeground(Color.LIGHT_GRAY);
		lblCompletaLosCampos.setFont(cf.MyFont(0, 19f));
		lblCompletaLosCampos.setBounds(325, 101, 374, 20);
		getContentPane().add(lblCompletaLosCampos);
		
		JLabel lblTipoDeUsuario_1 = new JLabel("Tipo de Usuario:");
		lblTipoDeUsuario_1.setForeground(Color.WHITE);
		lblTipoDeUsuario_1.setFont(cf.MyFont(0, 15f));
		lblTipoDeUsuario_1.setBounds(6, 458, 112, 18);
		getContentPane().add(lblTipoDeUsuario_1);
		setUndecorated(true);
		
		JLabel lblTipoDeUsuario_2 = new JLabel("0 = Administrador");
		lblTipoDeUsuario_2.setForeground(Color.WHITE);
		lblTipoDeUsuario_2.setFont(cf.MyFont(0, 15f));
		lblTipoDeUsuario_2.setBounds(6, 477, 125, 18);
		getContentPane().add(lblTipoDeUsuario_2);
		setUndecorated(true);
		
		JLabel lblTipoDeUsuario_3 = new JLabel("1 = Empleado");
		lblTipoDeUsuario_3.setForeground(Color.WHITE);
		lblTipoDeUsuario_3.setFont(cf.MyFont(0, 15f));
		lblTipoDeUsuario_3.setBounds(6, 496, 98, 18);
		getContentPane().add(lblTipoDeUsuario_3);		
		
		//Se captura la contraseña
		jpassField = new JPasswordField(10);
		jpassField.setText("Password");
		jpassField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(jpassField.getPassword().length < 5){
					btnAceptar.setEnabled(false);
					textFTipoUsuario.setEnabled(false);
				}
				if(jpassField.getPassword().length > 0){
					chckbxVerContrasea.setEnabled(true);
					textFTipoUsuario.setEnabled(true);
				}
				else
					chckbxVerContrasea.setEnabled(false);
			}
			@Override
			public void keyTyped(KeyEvent e) {
				if(jpassField.getPassword().length >= 15){
					e.consume();
				}
			}
		});
		jpassField.setEnabled(false);
		jpassField.setFont(cf.MyFont(0, 17f));
	//	jpassField.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		jpassField.setBounds(476, 223, 246, 35);
		getContentPane().add(jpassField);
		jpassField.setColumns(10);
	}
}
