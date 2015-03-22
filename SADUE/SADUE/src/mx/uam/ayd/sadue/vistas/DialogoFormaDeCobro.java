package mx.uam.ayd.sadue.vistas;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class DialogoFormaDeCobro extends JDialog {
	/**
	 * 
	 */
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblIngreseLaCantidad;
	private JButton btnAceptar;
	private VistaNuevaVenta vnv;
	/**
	 * Aqui se crea la vista para generar una forma de pago.
	 */
	public DialogoFormaDeCobro(ConexionDB cone) {
		conexion=cone;
		setBounds(100, 100, 520, 254);
		setModal(true);
		setTitle("Forma de Pago");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		
		JLabel lblEfectivo = new JLabel("Efectivo: ");
		lblEfectivo.setForeground(Color.LIGHT_GRAY);
		lblEfectivo.setFont(cf.MyFont(0, 17f));
		lblEfectivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEfectivo.setBounds(79, 65, 117, 38);
		contentPane.add(lblEfectivo);
		
		JLabel lblElectronico = new JLabel("Electronico: ");
		lblElectronico.setForeground(Color.LIGHT_GRAY);
		lblElectronico.setFont(cf.MyFont(0, 17f));
		lblElectronico.setHorizontalAlignment(SwingConstants.RIGHT);
		lblElectronico.setBounds(69, 106, 127, 38);
		contentPane.add(lblElectronico);
		
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
			}
		});
		textField.setBounds(206, 69, 166, 30);
		textField.setFont(cf.MyFont(0, 17f));
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(206, 110, 166, 30);
		textField_1.setFont(cf.MyFont(0, 17f));
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		vnv = new VistaNuevaVenta(null,conexion);
		if(vnv.valorCB() == 1){
			textField_1.setText(String.valueOf(vnv.totalApagar()));
		}
		else if(vnv.valorCB() == 0){
			textField.setText(String.valueOf(vnv.totalApagar()));
		}
		//textField_1.setText(vnv.valor());
		
		lblIngreseLaCantidad = new JLabel("Ingrese la cantidad a Pagar: ");
		lblIngreseLaCantidad.setForeground(Color.LIGHT_GRAY);
		lblIngreseLaCantidad.setFont(cf.MyFont(0, 17f));
		lblIngreseLaCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLaCantidad.setBounds(41, 36, 431, 18);
		contentPane.add(lblIngreseLaCantidad);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog (null, "Venta Exitosa");
				dispose();
			}
		});
		btnAceptar.setBounds(188, 165, 138, 30);
		btnAceptar.setFont(cf.MyFont(0, 17f));
		contentPane.add(btnAceptar);
	}
}