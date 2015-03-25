package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import mx.uam.ayd.sadue.Aplicacion;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.fonts.CustomFont2;
import mx.uam.ayd.sadue.fonts.CustomFont3;
import mx.uam.ayd.sadue.negocio.ServicioIngresarSistema;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class VistaIngresarSistema extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFUsuario;
	private JPasswordField jpassField;
	private JCheckBox chckbxVerContrasea;
	private JTextField textFPasswd;
	static String usr;
	static int tipo;

	/**
	 * Create the frame.
	 * 
	 * Metodo que inicializa todos los elementos de la vista Ingresar al Sistema
	 * @return null
	 * 
	 */
	public VistaIngresarSistema(final ServicioIngresarSistema servicioIS) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setResizable(false);
		setLocationRelativeTo(null);
	//	setModal(true);
		final Aplicacion app = new Aplicacion();
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		CustomFont2 cf2 = new CustomFont2();
		CustomFont3 cf3 = new CustomFont3();
		
		JLabel lblbienvenidoASadue = new JLabel("\u00A1Bienvenido a SADUE!");
		lblbienvenidoASadue.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenidoASadue.setForeground(Color.WHITE);
		lblbienvenidoASadue.setBounds(240, 52, 537, 61);
		lblbienvenidoASadue.setFont(cf3.MyFont(0, 50f));
	//	lblbienvenidoASadue.setFont(new Font("Goudy Old Style", Font.BOLD, 50));
		contentPane.add(lblbienvenidoASadue);
		
		JLabel lblquQuieresHacer = new JLabel("Ingresa tu nombre de usuario y contrase\u00F1a");
		lblquQuieresHacer.setHorizontalAlignment(SwingConstants.CENTER);
		lblquQuieresHacer.setForeground(Color.LIGHT_GRAY);
		lblquQuieresHacer.setBounds(240, 117, 537, 35);
		lblquQuieresHacer.setFont(cf2.MyFont(0, 29f));
	//	lblquQuieresHacer.setFont(new Font("Goudy Old Style", Font.PLAIN, 29));
		contentPane.add(lblquQuieresHacer);
		
		//Se muestra la contrasenia cuando CheckBox esta activado y se oculta cuando esta deseleccionada
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
		chckbxVerContrasea.setBounds(426, 330, 162, 25);
		contentPane.add(chckbxVerContrasea);
		
		JButton btnSalirDelSistema = new JButton("Salir");
		btnSalirDelSistema.setFont(cf.MyFont(0, 15f));
	//	btnSalirDelSistema.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnSalirDelSistema.setBounds(560, 406, 181, 28);
		btnSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.salir();
			}
		});
		contentPane.add(btnSalirDelSistema);
		
		//se inicializa el boton Ingresar
		final JButton btnIngresar = new JButton("Ingresar");
		//btnIngresar.setEnabled(false);
		btnIngresar.setFont(cf.MyFont(0, 15f));
	//	btnIngresar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnIngresar.setBounds(267, 406, 181, 28);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servicioIS.buscarUsuario(textFUsuario.getText(), String.valueOf(jpassField.getPassword()))){
					usr = textFUsuario.getText();
					tipo = servicioIS.buscarTipoUsuario(textFUsuario.getText(), String.valueOf(jpassField.getPassword()));
					app.principal();
					dispose();
				}
				else{
					app.dialogoNoAcceso();
				}
			}
		});
		contentPane.add(btnIngresar);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(cf.MyFont(0, 19f));
	//	lblUsuario.setFont(new Font("Myriad Pro", Font.PLAIN, 19));
		lblUsuario.setBounds(289, 226, 127, 20);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(cf.MyFont(0, 19f));
	//	lblContrasea.setFont(new Font("Myriad Pro", Font.PLAIN, 19));
		lblContrasea.setBounds(289, 294, 127, 20);
		contentPane.add(lblContrasea);
		
		textFUsuario = new JTextField();
		textFUsuario.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(textFUsuario.getText().length() < 3){
					btnIngresar.setEnabled(false);
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
		textFUsuario.setFont(cf.MyFont(0, 17f));
	//	textFUsuario.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		textFUsuario.setBounds(426, 220, 246, 35);
		contentPane.add(textFUsuario);
		textFUsuario.setColumns(10);
		
		//Se captura la contrasenia
		jpassField = new JPasswordField(10);
		jpassField.setText("Password");
		jpassField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				if(jpassField.getPassword().length < 5){
					btnIngresar.setEnabled(false);
				}
				else {
					btnIngresar.setEnabled(true);
				}
				if(jpassField.getPassword().length > 0)
					chckbxVerContrasea.setEnabled(true);
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
		jpassField.setBounds(426, 288, 246, 35);
		contentPane.add(jpassField);
		jpassField.setColumns(10);
		
		/*JLabel lblAltaDeUsuarios = new JLabel("Alta de Usuarios");
		lblAltaDeUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAltaDeUsuarios.setForeground(Color.ORANGE);
		lblAltaDeUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				app.altaUsuario();
			}
		});
		lblAltaDeUsuarios.setFont(cf.MyFont(0, 17f));
		lblAltaDeUsuarios.setBounds(39, 499, 144, 18);
		contentPane.add(lblAltaDeUsuarios);*/
		
		//Se valida que el password no contenga mas de 15 caracteres
		textFPasswd = new JTextField();
		textFPasswd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFPasswd.getText().length() >= 15){
					e.consume();				}
			}
		});
		textFPasswd.setEnabled(false);
		textFPasswd.setFont(cf.MyFont(0, 17f));
		textFPasswd.setBounds(426, 288, 246, 35);
		contentPane.add(textFPasswd);
		textFPasswd.setColumns(10);
		
		//etiqueta para cambiar la contrase–a
		JLabel lblOlvTuContrasenia = new JLabel("\u00BFOlvidaste tu contrase\u00F1a?");
		lblOlvTuContrasenia.setForeground(new Color(0, 204, 255));
		lblOlvTuContrasenia.setBounds(426, 511, 246, 25);
		lblOlvTuContrasenia.setFont(cf.MyFont(0, 17f));
		//Evento que se lanza al presionar la etiqueta
		lblOlvTuContrasenia.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				app.cambiarContrasenia();//hace un llamado un metodo de la clase Aplicacion
			}
		});
		contentPane.add(lblOlvTuContrasenia);
		
		
	}
	
	public String getUsr(){
		return usr;
	}
	
	public int getTipo(){
		return tipo;
	}
}
