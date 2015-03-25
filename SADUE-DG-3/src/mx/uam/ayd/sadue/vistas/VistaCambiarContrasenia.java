package mx.uam.ayd.sadue.vistas;

import mx.uam.ayd.sadue.negocio.ServicioCambiarContrasenia;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mx.uam.ayd.sadue.fonts.CustomFont3;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class VistaCambiarContrasenia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JPasswordField passwdFieldNuevContrasenia;
	private JPasswordField passwdFieldConfContrasenia;
	private JLabel lblCambiarContrasea;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblUsuario;
	private JLabel lblNuevaContrasea;
	private JLabel lblConfirmarContrasea;

	/**
	 * Se crea la vista cambiar Contrase–a y todos sus elementos
	 * @author josealbertorojas.
	 */
	public VistaCambiarContrasenia(final ServicioCambiarContrasenia sCambContrasenia) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 520);
		setTitle("Cambiar Contrase–a");
		setLocationRelativeTo(null);
		setResizable(false);
		
		//creamos un panel para el fondo
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//fuente
		CustomFont3 cf3 = new CustomFont3();
		
		//Etiqueta que muestra el texto cambiar Contrase–a
		lblCambiarContrasea = new JLabel("Cambiar Contrase\u00F1a");
		lblCambiarContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambiarContrasea.setBounds(267, 28, 482, 61);
		lblCambiarContrasea.setForeground(Color.WHITE);
		lblCambiarContrasea.setFont(cf3.MyFont(0, 50f));
		getContentPane().add(lblCambiarContrasea);
		
		//Se crea el boton aceptar para confirmar cambio de contrase–a
		btnAceptar = new JButton("Aceptar");
		//se crea el evento para el boton
		btnAceptar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//Valida que el usuario ingresado este registrado en el sistema
				if(sCambContrasenia.validaExistencia(textFieldUsuario.getText())){
					String x = "";
					//Ciclo que convierte el pasword en una cadena
					for (int i = 0; i < passwdFieldNuevContrasenia.getPassword().length; i++) {
						x += passwdFieldNuevContrasenia.getPassword()[i];
					}
					//verifica si se cambia la contrase–a
					sCambContrasenia.cambiaContrasenia(textFieldUsuario.getText(),x);
					JOptionPane.showMessageDialog(null,"Cambio de Contras–a exitoso");
					dispose();
				}
				else
					//si el usuario no esta en el sistema manda un mensaje
					JOptionPane.showMessageDialog(null,"El usuario"+ textFieldUsuario.getText() +"no existe");
			}
		});
		btnAceptar.setEnabled(false);
		btnAceptar.setBounds(267, 356, 181, 28);
		getContentPane().add(btnAceptar);
		
		//se crea el boton cancelar para cancelar el cambio de contrase–a
		btnCancelar = new JButton("Cancelar");
		//se crea el evento para este boton
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//cierra la vista cambiar contrase–a
				dispose();
			}
		});
		btnCancelar.setBounds(560, 356, 181, 28);
		getContentPane().add(btnCancelar);
		
		//caja de texto para ingresar el usuario
		textFieldUsuario = new JTextField();
		//Evento para la caja de texto
		textFieldUsuario.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				//verifica que el nombre de usuario sea mayor a 2 caracteres 
				if(textFieldUsuario.getText().length() < 3){
					passwdFieldNuevContrasenia.setText("");
					passwdFieldNuevContrasenia.setEnabled(false);
				}
				else{
					passwdFieldNuevContrasenia.setText("");
					passwdFieldNuevContrasenia.setEnabled(true);
				}
			}
		});
		textFieldUsuario.setBounds(469, 138, 216, 28);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		//etiqueta para indicar que corresponde la caja de texto
		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setForeground(Color.LIGHT_GRAY);
		lblUsuario.setFont(cf3.MyFont(0, 19f));
		lblUsuario.setBounds(267, 138, 181, 28);
		contentPane.add(lblUsuario);
		
		//se crea una caja de texto con atributos de password
		passwdFieldNuevContrasenia = new JPasswordField();
		//evento para la caja de texto del pasword
		passwdFieldNuevContrasenia.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				//Por regla de negocio, verifica que la contrase–a contenga un num. mayor de 4 caracteres
				if(passwdFieldNuevContrasenia.getPassword().length < 5){
					passwdFieldConfContrasenia.setText("");
					passwdFieldConfContrasenia.setEnabled(false);
				}
				else{
					passwdFieldConfContrasenia.setText("");
					passwdFieldConfContrasenia.setEnabled(true);
				}
			}
			public void keyTyped(KeyEvent e) {
				//por reglas de negocio, la contrase–a no puede tener mas de 15 caracteres
				if(passwdFieldNuevContrasenia.getPassword().length >= 15){
					e.consume();
				}
			}
		});
		passwdFieldNuevContrasenia.setBounds(469, 190, 216, 28);
		passwdFieldNuevContrasenia.setEnabled(false);
		contentPane.add(passwdFieldNuevContrasenia);
		passwdFieldNuevContrasenia.setColumns(10);
		
		//etiqueta para indicar que es nueva contrase–a
		lblNuevaContrasea = new JLabel("Nueva Contrase\u00F1a: ");
		lblNuevaContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNuevaContrasea.setFont(cf3.MyFont(0, 19f));
		lblNuevaContrasea.setForeground(Color.LIGHT_GRAY);
		lblNuevaContrasea.setBounds(267, 190, 181, 28);
		contentPane.add(lblNuevaContrasea);
		
		//caja de texto para confirmar la nueva contrase–a.
		passwdFieldConfContrasenia = new JPasswordField();
		passwdFieldConfContrasenia.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e){
				String x = null,y = null ;
				//convierte la nueva contrase–a a cadena
				for (int i = 0; i < passwdFieldNuevContrasenia.getPassword().length; i++) {
					x += passwdFieldNuevContrasenia.getPassword()[i];
				}
				//convierte la confirmacion de contrase–a a cadena
				for (int j = 0; j < passwdFieldConfContrasenia.getPassword().length; j++) {
					y += passwdFieldConfContrasenia.getPassword()[j];
				}
				//verifica si son iguales.
				if(x.equals(y)){
						btnAceptar.setEnabled(true);
				}
				else
					btnAceptar.setEnabled(false);
			}
		});
		passwdFieldConfContrasenia.setEnabled(false);
		passwdFieldConfContrasenia.setBounds(469, 243, 216, 28);
		contentPane.add(passwdFieldConfContrasenia);
		passwdFieldConfContrasenia.setColumns(10);
		
		//etiqueta para indicar que es la confirmacion de contrase–a.
		lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a: ");
		lblConfirmarContrasea.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmarContrasea.setFont(cf3.MyFont(0, 19f));
		lblConfirmarContrasea.setForeground(Color.LIGHT_GRAY);
		lblConfirmarContrasea.setBounds(255, 243, 193, 28);
		contentPane.add(lblConfirmarContrasea);
			
	}
}
