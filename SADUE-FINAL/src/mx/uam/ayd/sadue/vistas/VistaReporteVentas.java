package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoVentido;
import mx.uam.ayd.sadue.modelo.Venta;
import mx.uam.ayd.sadue.negocio.ServicioNuevaVenta;
import mx.uam.ayd.sadue.negocio.ServicioReporteVentas;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.KeyAdapter;

public class VistaReporteVentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private ProductoVentido[] productos;
	static Producto[] uniformeSeleccionado = null;
	private ServicioNuevaVenta servicioNuVe;
	
	private JPanel contentPane;
	private JTextField txtfhora;
	private JTextField txtffecha;
	private JTextField txtfnombre;
	private VistaIngresarSistema vis = new VistaIngresarSistema(null);
	private String usuario = "";
	Calendar calendario = Calendar.getInstance();
	int hora, minutos, segundos;
	
	//String fechaR;
	
	Date fecha=new Date();
	SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
	
	private double totalp = 0;
	double[] total;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaReporteVentas frame = new VistaReporteVentas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	 * Create the frame.
	 */
	
	
	public VistaReporteVentas(final ServicioReporteVentas sRV) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 489);
		//setModal(true);
		setTitle("Reporte de Rentas");
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setBackground(Color.DARK_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		CustomFont cf = new CustomFont();
		
		table = new JTable();
        String[] columnNames = {"Idproducto","Uniforme","Talla","Escuela","Cantidad","Precio","total",};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        productos = sRV.listaProductosv();
      		/*String[] fila = new String[7];
              
              for (int i = 0; i < productos.length; i++) {
            	fila[0] = String.valueOf(productos[i].dameIdVenta());
              	fila[1] = String.valueOf(productos[i].dameIdProducto());
              	fila[2] = String.valueOf(productos[i].dameNombreProducto());
              	fila[3] = String.valueOf(productos[i].damePrecio());
              	fila[4] = String.valueOf(productos[i].dameTalla());
              	fila[5] = String.valueOf(productos[i].dameEscuela());
              	fila[6] = String.valueOf(productos[i].dameCantidad());
            	
              	model.addRow(fila);
              }

        */
        table.setModel(model);
       // table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       // table.setFont(cf.MyFont(0, 15f));
        
        
		JSeparator separator = new JSeparator();
		separator.setBounds(28, 128, 647, 2);
		panel.add(separator);
		
		JLabel lbfecha = new JLabel("Fecha:");
		lbfecha.setHorizontalAlignment(SwingConstants.TRAILING);
		lbfecha.setForeground(Color.LIGHT_GRAY);
		lbfecha.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lbfecha.setBounds(365, 50, 126, 18);
		panel.add(lbfecha);
		
		JLabel lbnombreU = new JLabel("Nombre del Usuario:");
		lbnombreU.setHorizontalAlignment(SwingConstants.TRAILING);
		lbnombreU.setForeground(Color.LIGHT_GRAY);
		lbnombreU.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lbnombreU.setBounds(31, 98, 186, 18);
		panel.add(lbnombreU);
		
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);
	//	segundos = calendario.get(Calendar.SECOND);
		
		txtfhora = new JTextField(hora+":"+minutos);
		txtfhora.setEditable(false);
		txtfhora.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		txtfhora.setColumns(10);
		txtfhora.setBounds(229, 44, 114, 30);
		panel.add(txtfhora);
		
	
		txtffecha = new JTextField(formatoFecha.format(fecha));
		txtffecha.setEditable(false);
		/*txtffecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				 char c= evt.getKeyChar();
			        if((c<'0'|| c>'9') && c>'/')
			         evt.consume();
			    
			}
		});*/
		txtffecha.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		txtffecha.setColumns(10);
		txtffecha.setBounds(504, 44, 154, 30);
		panel.add(txtffecha);
		
		
		this.usuario = vis.getUsr();
		txtfnombre = new JTextField(usuario);
		txtfnombre.setEditable(false);
		txtfnombre.setText(usuario);
		txtfnombre.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		txtfnombre.setColumns(10);
		txtfnombre.setBounds(229, 86, 154, 30);
		panel.add(txtfnombre);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(43, 170, 615, 177);
		scrollPane.setViewportView(table);
		panel.add(scrollPane);
		
		final JLabel label_4 = new JLabel("$"+totalp);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Myriad Pro", Font.PLAIN, 19));
		label_4.setBounds(543, 347, 119, 20);
		panel.add(label_4);
		
		final JButton btngenerar = new JButton("Generar");
		btngenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CustomFont cf = new CustomFont();
		        productos = sRV.listaProductosv();
		      		String[] fila = new String[7];
		              
		              for (int i = 0; i < productos.length; i++) {
		              	fila[0] = String.valueOf(productos[i].dameIdProducto());
		              	fila[1] = String.valueOf(productos[i].dameNombreProducto());
		              	fila[2] = String.valueOf(productos[i].dameTalla());
		              	fila[3] = String.valueOf(productos[i].dameEscuela());
		              	fila[4] = String.valueOf(productos[i].dameCantidad());
		            	fila[5] = String.valueOf(productos[i].damePrecio());
		            	fila[6] = String.valueOf(productos[i].damePrecio()*productos[i].dameCantidad());
		            	
		              	model.addRow(fila);
		              }

		        
		        table.setModel(model);
		        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		        table.setFont(cf.MyFont(0, 15f));

		    	
    			total = new double[table.getRowCount()];
    	        for(int i=0; i < table.getRowCount(); i++){
    	        	total[i] = Double.parseDouble(table.getValueAt(i, 6).toString());
    	        }
    	        
    	        totalp = ServicioNuevaVenta.calculaPagoTotal(total);
    	        label_4.setText("$ " + totalp);
    	        btngenerar.setEnabled(false);
    	        DialogoReporteVGenerado dRV = new DialogoReporteVGenerado();
				dRV.setVisible(true);
				
				
			}
		});
		btngenerar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btngenerar.setBounds(328, 402, 160, 28);
		panel.add(btngenerar);
		
		JButton btncancelar = new JButton("Cancelar");
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btncancelar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btncancelar.setBounds(525, 402, 138, 28);
		panel.add(btncancelar);
		
		JLabel label_5 = new JLabel("Lista de uniformes:");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		label_5.setBounds(43, 147, 163, 18);
		panel.add(label_5);
		
		JLabel lblTotal = new JLabel("Venta Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotal.setForeground(Color.LIGHT_GRAY);
		lblTotal.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
		lblTotal.setBounds(417, 349, 114, 18);
		panel.add(lblTotal);
		
		JLabel lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHora.setForeground(Color.LIGHT_GRAY);
		lblHora.setFont(new Font("Dialog", Font.PLAIN, 17));
		lblHora.setBounds(152, 50, 65, 18);
		panel.add(lblHora);
	}
}
