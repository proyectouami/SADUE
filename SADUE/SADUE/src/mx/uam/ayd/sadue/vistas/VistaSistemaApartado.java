package mx.uam.ayd.sadue.vistas;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.negocio.ServicioSistemaApartado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VistaSistemaApartado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VistaSistemaApartado(final ServicioSistemaApartado servicioSA) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 260);
		setTitle("Sistema de Apartado");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		
		JButton btnApartar = new JButton("Apartar");
		btnApartar.setFont(cf.MyFont(0, 15f));
	//	btnApartar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnApartar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				servicioSA.apartar();
			}
		});
		btnApartar.setBounds(42, 83, 181, 65);
		contentPane.add(btnApartar);
		
		JButton btnApartados = new JButton("Apartados");
		btnApartados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				servicioSA.apartados();
			}
		});
		btnApartados.setFont(cf.MyFont(0, 15f));
	//	btnApartados.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnApartados.setBounds(265, 83, 181, 65);
		contentPane.add(btnApartados);
		
		JButton btnSalirDelSistema = new JButton("<html>" + "Salir del Sistema" + "<br><center>" + "de Apartado" + "</html>");
		btnSalirDelSistema.setFont(cf.MyFont(0, 15f));
	//	btnSalirDelSistema.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalirDelSistema.setBounds(488, 83, 181, 65);
		contentPane.add(btnSalirDelSistema);
		
		JLabel lblSeleccionaUnaDe = new JLabel("Selecciona una de las siguientes opciones:");
		lblSeleccionaUnaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionaUnaDe.setForeground(Color.LIGHT_GRAY);
		lblSeleccionaUnaDe.setFont(cf.MyFont(0, 17f));
	//	lblSeleccionaUnaDe.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblSeleccionaUnaDe.setBounds(42, 31, 627, 18);
		contentPane.add(lblSeleccionaUnaDe);
	}
}
