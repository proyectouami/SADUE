package mx.uam.ayd.sadue.vistas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import mx.uam.ayd.sadue.Aplicacion;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.fonts.CustomFont2;
import mx.uam.ayd.sadue.fonts.CustomFont3;

import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VistaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private VistaIngresarSistema vis = new VistaIngresarSistema(null);
	private String usuario = "";
	private int tipo = 1;
	
	private JButton btnNuevaVenta;
	private JButton btnSistemaApartado;
	private JButton btnReportePeriodo;
	private JButton btnAgregarUniformes;
	private JButton btnQuitarUniformes;
	private JButton btnConsultarInventario;
	
	private JButton btnAcercaDeSadue;
	private JButton btnSalirDelSistema;
	
	private JButton btnAltaDeUsuarios;
	private JButton btnListaDeUsuarios;
	
	private JButton btnAltaDeEscuelas;
	private JButton btnListaDeEscuelas;
	
	private JLabel lblbienvenidoASadue;
	private JLabel lblquQuieresHacer;
	private JLabel lblUsuario;

	/**
	 * Create the frame.
	 */
	public VistaPrincipal(final Aplicacion app) {
		super();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1024, 600);
		setTitle("SADUE v0.1");
		setLocationRelativeTo(null);
		setResizable(false);
	//	app.ingresarSistema();
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		CustomFont cf = new CustomFont();
		CustomFont2 cf2 = new CustomFont2();
		CustomFont3 cf3 = new CustomFont3();
		
		btnSistemaApartado = new JButton("Sistema de Apartado");
		btnSistemaApartado.setFont(cf.MyFont(0, 20f));
	//	btnSistemaApartado.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		btnSistemaApartado.setBounds(376, 198, 256, 88);
		btnSistemaApartado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.sistemaApartado();
			}
		});
		contentPane.setLayout(null);
		
		lblbienvenidoASadue = new JLabel("\u00A1Bienvenido a SADUE!");
		lblbienvenidoASadue.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenidoASadue.setForeground(Color.WHITE);
		lblbienvenidoASadue.setBounds(234, 52, 550, 61);
		lblbienvenidoASadue.setFont(cf3.MyFont(0, 50f));
	//	lblbienvenidoASadue.setFont(new Font("Goudy Old Style", Font.BOLD, 50));
		contentPane.add(lblbienvenidoASadue);
		
		lblquQuieresHacer = new JLabel("\u00BFQu\u00E9 quieres hacer?");
		lblquQuieresHacer.setHorizontalAlignment(SwingConstants.CENTER);
		lblquQuieresHacer.setForeground(Color.LIGHT_GRAY);
		lblquQuieresHacer.setBounds(369, 117, 280, 37);
		lblquQuieresHacer.setFont(cf2.MyFont(0, 30f));
	//	lblquQuieresHacer.setFont(new Font("Goudy Old Style", Font.PLAIN, 30));
		contentPane.add(lblquQuieresHacer);
		
		btnNuevaVenta = new JButton("Nueva Venta");
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.nuevaVenta();
			}
		});
		btnNuevaVenta.setBounds(60, 200, 256, 88);
		btnNuevaVenta.setFont(cf.MyFont(0, 20f));
	//	btnNuevaVenta.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		buttonGroup.add(btnNuevaVenta);
		contentPane.add(btnNuevaVenta);
		buttonGroup.add(btnSistemaApartado);
		contentPane.add(btnSistemaApartado);
		
		btnReportePeriodo = new JButton("<html>" + "Reporte de Ventas" + "<br><center>");
		btnReportePeriodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				app.reporteVentas();
				
			}
		});
		
		
		
		btnReportePeriodo.setBounds(692, 198, 256, 88);
		btnReportePeriodo.setFont(cf.MyFont(0, 20f));
	//	btnReportePeriodo.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		buttonGroup.add(btnReportePeriodo);
		contentPane.add(btnReportePeriodo);
		
		btnAgregarUniformes = new JButton("Agregar Uniformes");
		btnAgregarUniformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.agregarUniforme();
			}
		});
		btnAgregarUniformes.setBounds(60, 338, 256, 88);
		btnAgregarUniformes.setFont(cf.MyFont(0, 20f));
	//	btnAgregarUniformes.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		buttonGroup.add(btnAgregarUniformes);
		contentPane.add(btnAgregarUniformes);		
		
		btnQuitarUniformes = new JButton("Quitar Uniformes");
		btnQuitarUniformes.setBounds(376, 338, 256, 88);
		btnQuitarUniformes.setFont(cf.MyFont(0, 20f));
	//	btnQuitarUniformes.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		btnQuitarUniformes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.quitarUniforme();
			}
		});
		buttonGroup.add(btnQuitarUniformes);
		contentPane.add(btnQuitarUniformes);
		
		btnConsultarInventario = new JButton("Consultar Inventario");
		btnConsultarInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.consultarInventario();
			}
		});
		btnConsultarInventario.setBounds(692, 338, 256, 88);
		btnConsultarInventario.setFont(cf.MyFont(0, 20f));
	//	btnConsultarInventario.setFont(new Font("Myriad Pro", Font.PLAIN, 20));
		buttonGroup.add(btnConsultarInventario);
		contentPane.add(btnConsultarInventario);
		
		btnAcercaDeSadue = new JButton("Acerca de SADUE");
		btnAcercaDeSadue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.acercaDe();
			}
		});
		btnAcercaDeSadue.setFont(cf.MyFont(0, 15f));
	//	btnAcercaDeSadue.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnAcercaDeSadue.setBounds(767, 464, 181, 28);
		contentPane.add(btnAcercaDeSadue);
		
		lblUsuario = new JLabel("Hola, Usuario");
		lblUsuario.setForeground(Color.ORANGE);
		lblUsuario.setBounds(24, 20, 181, 20);
		lblUsuario.setFont(cf3.MyFont(0, 19f));
	//	usuario = servicioAU.buscarUsuario();
		this.usuario = vis.getUsr();
		lblUsuario.setText("hola, " + usuario);
		lblUsuario.setVisible(true);
		contentPane.add(lblUsuario);
		
		btnAltaDeUsuarios = new JButton("Alta de Usuarios");
		btnAltaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.altaUsuario();
			}
		});
		btnAltaDeUsuarios.setBounds(60, 464, 187, 28);
		btnAltaDeUsuarios.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnAltaDeUsuarios);
		
		btnListaDeUsuarios = new JButton("Lista de Usuarios");
		btnListaDeUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.dialogoUsuarios();
			}
		});
		btnListaDeUsuarios.setBounds(60, 502, 187, 28);
		btnListaDeUsuarios.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnListaDeUsuarios);
		
		btnAltaDeEscuelas = new JButton("Alta de Escuelas");
		btnAltaDeEscuelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.altaEscuela();
			}
		});
		btnAltaDeEscuelas.setBounds(415, 463, 187, 28);
		btnAltaDeEscuelas.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnAltaDeEscuelas);
		
		btnListaDeEscuelas = new JButton("Lista de Escuelas");
		btnListaDeEscuelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				app.dialogoEscuelas();
			}
		});
		btnListaDeEscuelas.setBounds(415, 502, 187, 28);
		btnListaDeEscuelas.setFont(cf.MyFont(0, 15f));
		contentPane.add(btnListaDeEscuelas);
		
		this.tipo = vis.getTipo();
		if(tipo == 0){
			btnAltaDeUsuarios.setVisible(true);
			btnListaDeUsuarios.setVisible(true);
			btnAltaDeEscuelas.setVisible(true);
			btnListaDeEscuelas.setVisible(true);
			btnAgregarUniformes.setEnabled(true);
			btnQuitarUniformes.setEnabled(true);
		}
		else{
			btnAltaDeUsuarios.setVisible(false);
			btnListaDeUsuarios.setVisible(false);
			btnAltaDeEscuelas.setVisible(false);
			btnListaDeEscuelas.setVisible(false);
			btnAgregarUniformes.setEnabled(false);
			btnQuitarUniformes.setEnabled(false);
		}
		
		btnSalirDelSistema = new JButton("Salir del Sistema");
		//	btnSalirDelSistema.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			btnSalirDelSistema.setBounds(767, 502, 181, 28);
			btnSalirDelSistema.setFont(cf.MyFont(0, 15f));
			btnSalirDelSistema.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					deshabilitarTodo();
					app.cerrarSesion();
					dispose();
				}
			});
			contentPane.add(btnSalirDelSistema);
	}
	
	private void deshabilitarTodo(){
		btnAgregarUniformes.setEnabled(false);
		btnNuevaVenta.setEnabled(false);
		btnReportePeriodo.setEnabled(false);
		btnSistemaApartado.setEnabled(false);
		btnSalirDelSistema.setEnabled(false);
		btnAcercaDeSadue.setEnabled(false);
		btnConsultarInventario.setEnabled(false);
		btnQuitarUniformes.setEnabled(false);
		btnAltaDeUsuarios.setEnabled(false);
		btnAltaDeUsuarios.setVisible(false);
		btnListaDeUsuarios.setEnabled(false);
		btnListaDeUsuarios.setVisible(false);
		btnAltaDeEscuelas.setEnabled(false);
		btnAltaDeEscuelas.setVisible(false);
		lblbienvenidoASadue.setEnabled(false);
		lblUsuario.setEnabled(false);
		lblUsuario.setVisible(false);
		lblquQuieresHacer.setEnabled(false);
	}
	
}
