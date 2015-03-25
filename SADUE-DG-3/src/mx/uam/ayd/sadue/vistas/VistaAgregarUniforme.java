package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import mx.uam.ayd.sadue.Aplicacion;
import mx.uam.ayd.sadue.negocio.ServicioAgregarUniforme;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Escuela;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

 public class VistaAgregarUniforme extends JDialog {
	/**
	 * 
	 */
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPrecio;
	private JComboBox comboBoxTalla;
	private JComboBox comboBoxEscuelas;
	private DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
	private JComboBox comboBoxUniforme;
	private JTextField textFieldCantidad;
	//private ServicioAgregarUniforme servicioAU;

	public VistaAgregarUniforme(final ServicioAgregarUniforme servicioAgU, ConexionDB cone) {
		conexion=cone;
		setTitle("Agregar Uniforme");
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(null);
		setModal(true);
		setResizable(false);
		
		CustomFont cf = new CustomFont();
		
		JLabel lblUniforme = new JLabel("Uniforme:");
		lblUniforme.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUniforme.setForeground(Color.WHITE);
		lblUniforme.setFont(cf.MyFont(0, 17f));
		lblUniforme.setBounds(30, 48, 103, 18);
		getContentPane().add(lblUniforme);
		
		JLabel lblTalla = new JLabel("Talla: ");
		lblTalla.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTalla.setForeground(Color.WHITE);
		lblTalla.setFont(cf.MyFont(0, 17f));
		lblTalla.setBounds(353, 48, 79, 18);
		getContentPane().add(lblTalla);
		
		comboBoxTalla = new JComboBox();
		comboBoxTalla.setModel(new DefaultComboBoxModel(new String[] {"4", "6", "8", "10", "12", "14", "16", "18", "CH", "M", "G", "EG"}));
		comboBoxTalla.setBounds(444, 42, 196, 30);
		comboBoxTalla.setFont(cf.MyFont(0, 17f));
		getContentPane().add(comboBoxTalla);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setForeground(Color.WHITE);
		lblPrecio.setFont(cf.MyFont(0, 17f));
		lblPrecio.setBounds(184, 147, 103, 18);
		getContentPane().add(lblPrecio);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') ||	(caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.')){
					e.consume();
				}
				if (caracter == '.' && textFieldPrecio.getText().contains(".")) {
					e.consume();
				}
				if(textFieldPrecio.getText().length() >= 10){
					e.consume();
				}
			}
		});
		textFieldPrecio.setBounds(299, 141, 103, 30);
		textFieldPrecio.setFont(cf.MyFont(0, 17f));
		getContentPane().add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 304);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(502, 200, 138, 28);
		btnCancelar.setFont(cf.MyFont(0, 15f));
		getContentPane().add(btnCancelar);
		
		JLabel lblEscuela = new JLabel("Escuela: ");
		lblEscuela.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEscuela.setForeground(Color.WHITE);
		lblEscuela.setFont(cf.MyFont(0, 17f));
		lblEscuela.setBounds(30, 97, 103, 18);
		getContentPane().add(lblEscuela);
		
		comboBoxEscuelas = new JComboBox();
		agregarElementos();
		comboBoxEscuelas.setFont(cf.MyFont(0, 17f));
		comboBoxEscuelas.setBounds(145, 91, 495, 30);
		getContentPane().add(comboBoxEscuelas);
		
		comboBoxUniforme = new JComboBox();
		comboBoxUniforme.setModel(new DefaultComboBoxModel(new String[] {"Pants", "Playera", "Sudadera", "Short",
				"Camisa", "Pantalon", "Sueter", "Blusa", "Jumper", "Calcetas"}));
		//Claves para tipo de uniforme:
		//{"0 Pants", "1 Playera", "2 Sudadera", "3 Short", "4 Camisa", "5 Pantalon", "6 Sueter", "7 Blusa", "8 Jumper", "9 Calcetas"}
		comboBoxUniforme.setFont(cf.MyFont(0, 17f));
		comboBoxUniforme.setBounds(145, 42, 196, 30);
		getContentPane().add(comboBoxUniforme);
		
		JLabel lblCantidad = new JLabel("Cantidad: ");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantidad.setForeground(Color.WHITE);
		lblCantidad.setFont(cf.MyFont(0, 17f));
		lblCantidad.setBounds(414, 147, 111, 18);
		getContentPane().add(lblCantidad);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') ||	(caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(textFieldCantidad.getText().length() >= 10){
					e.consume();
				}
			}
		});
		textFieldCantidad.setBounds(537, 141, 103, 30);
		textFieldCantidad.setFont(cf.MyFont(0, 17f));
		getContentPane().add(textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		final JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*try{
					String nombre = textFieldNombreAutor.getText();
					String apellido = textFieldApellidoAutor.getText();
					int anioNacimiento = Integer.parseInt(textFieldAnioNacimiento.getText());
					
					control.agregaAutor(nombre, apellido, anioNacimiento);
					JOptionPane.showMessageDialog (null, "Se agreg— el Autor: " + nombre + " " + apellido);
					setVisible(false);
				}catch(Exception e){
					JOptionPane.showMessageDialog (null, "-Es necesario llenar todos los campos\n-No poner letras en campos de numeros");
				}*/
				if(comboBoxEscuelas.getSelectedIndex() == -1){
					JOptionPane.showMessageDialog (null, "No hay escuelas en la base de datos\nNo puedes continuar");
				}else{
					if(comboBoxUniforme.getSelectedItem().equals(null) || textFieldPrecio.getText().length()== 0
							|| textFieldCantidad.getText().length() == 0 || comboBoxEscuelas.getSelectedItem().equals(null)
							|| comboBoxTalla.getSelectedItem().equals(null)){
						JOptionPane.showMessageDialog (null, "Es necesario llenar todos los campos");
					}
					else{
						if(!servicioAgU.buscarProducto(comboBoxUniforme.getSelectedIndex(), String.valueOf(comboBoxEscuelas.getSelectedItem()),String.valueOf(comboBoxTalla.getSelectedItem()))){
							System.out.println(comboBoxUniforme.getSelectedItem() + ", " + comboBoxEscuelas.getSelectedItem() + ", " + comboBoxTalla.getSelectedItem()
									+ ", $" + textFieldPrecio.getText() + ", " + textFieldCantidad.getText());
							int uniforme = (comboBoxUniforme.getSelectedIndex());
							int existencia = Integer.parseInt(textFieldCantidad.getText());
							double prec = Double.parseDouble(textFieldPrecio.getText());
							String talla = (String)(comboBoxTalla.getSelectedItem());
							String escuela = servicioAgU.buscarUnaEscuela(comboBoxEscuelas.getSelectedIndex()+1);
							servicioAgU.agregarUniforme(uniforme, prec, talla, escuela, existencia);
							dialogoUniformeAgregado();
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Ya existe este uniforme", "Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				
			}
		});
		btnAceptar.setBounds(352, 200, 138, 28);
		btnAceptar.setFont(cf.MyFont(0, 15f));
		getContentPane().add(btnAceptar);
		
		setLocationRelativeTo(null);
		
		
		
	}
	private void agregarElementos() {
		Escuela[] escuelas;
		ServicioAgregarUniforme servicio = new ServicioAgregarUniforme(null,conexion);

		escuelas = servicio.buscarEscuela();
		for(Escuela u:escuelas) {
			cbModel.addElement(u.getNombreEscuela());
		}
		comboBoxEscuelas.setModel(cbModel);
		// TODO Auto-generated method stub
	}
	
	public void dialogoUniformeAgregado(){
		DialogoUniformeAgregado dialogo = new DialogoUniformeAgregado();
		dialogo.setVisible(true);
	}
}
