package mx.uam.ayd.sadue.vistas;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.negocio.ServicioCambioPrecio;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaCambioPrecio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFPrecio;
	private JButton btnAceptar;

	/**
	 * Create the frame.
	 */
	public VistaCambioPrecio(final ServicioCambioPrecio scp) {
		setBounds(100, 100, 509, 199);
		setTitle("Cambiar Precio");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		CustomFont cf = new CustomFont();
		contentPane.setLayout(null);
		
		JLabel lblIngresa = new JLabel("Ingresa el nuevo precio");
		lblIngresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngresa.setForeground(Color.LIGHT_GRAY);
		lblIngresa.setFont(cf.MyFont(0, 17f));
	//	lblSeleccionaUnaDe.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblIngresa.setBounds(102, 21, 299, 39);
		contentPane.add(lblIngresa);
		
		txtFPrecio = new JTextField();
		txtFPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if((caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.')
						&& (caracter<'0' || caracter>'9')){
					e.consume();
				}
				if(txtFPrecio.getText().length() >= 7){
					e.consume();
				}
				if (caracter == '.' && txtFPrecio.getText().contains(".")) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(txtFPrecio.getText().length() < 1)
					btnAceptar.setEnabled(false);
				else
					btnAceptar.setEnabled(true);
			}
		});
		txtFPrecio.setBounds(256, 64, 122, 28);
		contentPane.add(txtFPrecio);
		txtFPrecio.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double precio = Double.parseDouble(txtFPrecio.getText());
				scp.cambiaPrecio(precio);
				dialogoApartadoRealizado();
				dispose();
			}
		});
		btnAceptar.setBounds(102, 111, 138, 28);
		btnAceptar.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(263, 111, 138, 28);
		btnCancelar.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnCancelar);
		
		JLabel lblPrecio = new JLabel("Nuevo precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setForeground(Color.LIGHT_GRAY);
		lblPrecio.setFont(cf.MyFont(0, 17f));
		lblPrecio.setBounds(102, 70, 142, 18);
		contentPane.add(lblPrecio);
	}
	
	public void dialogoApartadoRealizado(){
		DialogoCambioPrecio dialogo = new DialogoCambioPrecio();
		dialogo.setVisible(true);
	}
}
