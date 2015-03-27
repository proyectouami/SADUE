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

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;

public class DialogoApartadoRealizado extends JDialog {
	ConexionDB conexion;
	private VistaApartar vap;

	/**
	 * Create the dialog.
	 */
	public DialogoApartadoRealizado(ConexionDB cone) {
		conexion=cone;
		setTitle("Apartado Realizado");
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 534, 310);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		
		CustomFont cf = new CustomFont();
		vap = new VistaApartar(null,conexion);

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
			btnAceptar.setBounds(215, 226, 97, 28);
			getContentPane().add(btnAceptar);
		}
		
		JLabel lblEstsAPunto = new JLabel("\u00A1Se realiz\u00F3 el apartado exitosamente!");
		lblEstsAPunto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstsAPunto.setFont(cf.MyFont(0, 17f));
	//	lblEstsAPunto.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblEstsAPunto.setForeground(Color.LIGHT_GRAY);
		lblEstsAPunto.setBounds(108, 30, 312, 18);
		getContentPane().add(lblEstsAPunto);
		
		JLabel lblApartadoNo = new JLabel("Apartado No.");
		lblApartadoNo.setForeground(Color.LIGHT_GRAY);
		lblApartadoNo.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApartadoNo.setBounds(66, 70, 120, 18);
		lblApartadoNo.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblApartadoNo);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setForeground(Color.LIGHT_GRAY);
		lblCliente.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCliente.setBounds(66, 97, 120, 18);
		lblCliente.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblCliente);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar:");
		lblTotalAPagar.setForeground(Color.LIGHT_GRAY);
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalAPagar.setBounds(66, 124, 120, 18);
		lblTotalAPagar.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblTotalAPagar);
		
		JLabel lblACuenta = new JLabel("30% a cuenta:");
		lblACuenta.setForeground(Color.LIGHT_GRAY);
		lblACuenta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblACuenta.setBounds(66, 151, 120, 18);
		lblACuenta.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblACuenta);
		
		JLabel lblDebe = new JLabel("Debe:");
		lblDebe.setForeground(Color.LIGHT_GRAY);
		lblDebe.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDebe.setBounds(66, 178, 120, 18);
		lblDebe.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblDebe);
		
		JLabel lblCliente_1 = new JLabel("Cliente");
		lblCliente_1.setText(vap.dameNombreDialogo());
		lblCliente_1.setForeground(Color.WHITE);
		lblCliente_1.setBounds(198, 97, 222, 18);
		lblCliente_1.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblCliente_1);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setText(String.valueOf(vap.dameApartadoId()));
		lblNumero.setForeground(Color.WHITE);
		lblNumero.setBounds(198, 70, 222, 18);
		lblNumero.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblNumero);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setText("$ " + String.valueOf(vap.dameTotal()));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setBounds(198, 124, 222, 18);
		lblTotal.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblTotal);
		
		JLabel lblAnticipo = new JLabel("Anticipo");
		double ant = vap.dameTotal()*.30;
		ant = Math.round(ant*Math.pow(10, 2))/Math.pow(10, 2);
		lblAnticipo.setText("$ " + String.valueOf(ant));
		lblAnticipo.setForeground(Color.WHITE);
		lblAnticipo.setBounds(198, 151, 222, 18);
		lblAnticipo.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblAnticipo);
		
		JLabel lblDeuda = new JLabel("Deuda");
		double deu = vap.dameTotal()*.70;
		deu = Math.round(deu*Math.pow(10, 2))/Math.pow(10, 2);
		lblDeuda.setText("$ " + String.valueOf(deu));
		lblDeuda.setForeground(Color.WHITE);
		lblDeuda.setBounds(198, 178, 222, 18);
		lblDeuda.setFont(cf.MyFont(0, 17f));
		getContentPane().add(lblDeuda);
	}

}
