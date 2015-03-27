package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.negocio.ServicioNuevaVenta;
import mx.uam.ayd.sadue.negocio.ServicioSeleccionUniformesApartar;

import javax.swing.JCheckBox;

public class VistaNuevaVenta extends JDialog {

	/**
	 * 
	 */
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServicioSeleccionUniformesApartar ssua = new ServicioSeleccionUniformesApartar(null,conexion);
	private VistaSeleccionUniformesApartar vsua;
	private ServicioNuevaVenta servicioNuVe;
	private JTextField textFNombre;
	private JTextField textFApellidos;
	private JTextField textFTarjeta;
	private JScrollPane jScrollPane1;
	private JButton btnAgregarUniformes;
	private JButton btnCancelar;
	private JButton btnTerminarVenta;
	private JButton btnCancelacin;
	private DefaultTableModel model = new DefaultTableModel();
	private JLabel lblListaDeUniformes;
	private static JTable table;
	private Producto[] productos;
	private JLabel lblTotalAPagar;
	private JLabel label;
	private double totalPagar = 0;
	private int aux=0;
	private double totalDescPagar=-1;
	double[] total;
	private String fecha;
	static String nombre;
	static double totPag;
	static int apartId;
	private JCheckBox chckbxEfectivo, chckbxElectronico;
	private JTextField jTxtdescuento;
	private JLabel lblTotPagCDesc;
	private JLabel lblporcdesc;
	/**
	 * Create the frame.
	 */
	public VistaNuevaVenta(final ServicioNuevaVenta servicioNV,ConexionDB cone) {
		conexion=cone;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 570);
		setModal(true);
		setTitle("Nueva Venta");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		final Calendar c = new GregorianCalendar();
		final ServicioNuevaVenta snv = new ServicioNuevaVenta();
		
		JSeparator separator = new JSeparator();
		separator.setBounds(33, 161, 647, 2);
		contentPane.add(separator);
		
		JLabel lblDatosDelCliente = new JLabel("Datos del Cliente:");
		lblDatosDelCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatosDelCliente.setForeground(Color.WHITE);
		lblDatosDelCliente.setFont(cf.MyFont(0, 19f));
	//	lblDatosDelCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatosDelCliente.setBounds(262, 18, 190, 20);
		contentPane.add(lblDatosDelCliente);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setForeground(Color.LIGHT_GRAY);
		lblNombre.setFont(cf.MyFont(0, 17f));
		lblNombre.setBounds(147, 50, 115, 18);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setForeground(Color.LIGHT_GRAY);
		lblApellidos.setFont(cf.MyFont(0, 17f));
		lblApellidos.setBounds(147, 80, 115, 18);
		contentPane.add(lblApellidos);
		
		JLabel lblNoDeTarjeta = new JLabel("No. de Tarjeta:");
		lblNoDeTarjeta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoDeTarjeta.setForeground(Color.LIGHT_GRAY);
		lblNoDeTarjeta.setFont(cf.MyFont(0, 17f));
		lblNoDeTarjeta.setBounds(147, 110, 115, 18);
		contentPane.add(lblNoDeTarjeta);
		
		textFNombre = new JTextField();
		textFNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter<'a' || caracter>'z') && (caracter<'A' || caracter>'Z')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != ' ')){
					e.consume();
				}
				if(textFNombre.getText().length() >= 40){
					e.consume();
				}
			}
		});
		textFNombre.setFont(cf.MyFont(0, 17f));
		textFNombre.setBounds(274, 50, 239, 30);
		contentPane.add(textFNombre);
		textFNombre.setColumns(10);
		
		textFApellidos = new JTextField();
		textFApellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter<'a' || caracter>'z') && (caracter<'A' || caracter>'Z')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != ' ')){
					e.consume();
				}
				if(textFApellidos.getText().length() >= 40){
					e.consume();
				}
			}
		});
		textFApellidos.setFont(cf.MyFont(0, 17f));
		textFApellidos.setBounds(274, 80, 239, 30);
		contentPane.add(textFApellidos);
		textFApellidos.setColumns(10);
		
		textFTarjeta = new JTextField();
		textFTarjeta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(textFTarjeta.getText().length() >= 10){
					e.consume();
				}
			}
		});
		textFTarjeta.setFont(cf.MyFont(0, 17f));
		textFTarjeta.setBounds(274, 110, 239, 30);
		contentPane.add(textFTarjeta);
		textFTarjeta.setColumns(10);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectionModel().isSelectionEmpty())
					btnCancelacin.setEnabled(false);
				else
					btnCancelacin.setEnabled(true);
			}
		});
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(cf.MyFont(0, 15f));
		String[] columnNames = {"Id", "Uniforme", "Escuela", "Talla", "Precio", "Cantidad", "Total"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
		table.setModel(model);
        jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(49, 212, 615, 177);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        
        label = new JLabel("$ " + totalPagar);
        label.setForeground(Color.WHITE);
        label.setBounds(175, 407, 119, 20);
        label.setFont(cf.MyFont(0, 19f));
        contentPane.add(label);
        
        btnAgregarUniformes = new JButton("Agregar Uniforme");
        btnAgregarUniformes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ssua.inicia();
        		chckbxEfectivo.setSelected(false);
                chckbxElectronico.setSelected(false);        		
        		String[] fila = new String[7];
        		vsua = new VistaSeleccionUniformesApartar(null,conexion);
        		try{
        			
                    //Intentanmos validar si el objeto productos equivale a vacio.  
        			productos = vsua.uniformesSeleccionados();
            		if(productos.length > 0){
            			
            			for (int i = 0; i < productos.length; i++) {
            				fila[0] = String.valueOf(productos[i].dameIdProducto());
            				fila[1] = String.valueOf(productos[i].dameNombreProducto());
            				fila[2] = String.valueOf(productos[i].dameEscuela());
            				fila[3] = String.valueOf(productos[i].dameTalla());
            				fila[4] = String.valueOf(productos[i].damePrecio());
            				fila[5] = String.valueOf(productos[i].dameExistencia());
            				fila[6] = String.valueOf(productos[i].damePrecio()*productos[i].dameExistencia());
            				model.addRow(fila);
            			}
            			table.setModel(model);
            			total = new double[table.getRowCount()];
            	        for(int i=0; i < table.getRowCount(); i++){
            	        	total[i] = Double.parseDouble(table.getValueAt(i, 6).toString());
            	        }
            	        
            	        totalPagar = ServicioNuevaVenta.calculaPagoTotal(total);
            			label.setText("$ " + totalPagar);
            		}
            		else{
            			fila[0] = "";
        				fila[1] = "";
        				fila[2] = "";
        				fila[3] = "";
        				fila[4] = "";
        				fila[5] = "";
        				fila[6] = "";
        				model.addRow(fila);
            			table.setModel(model);
            		}
                }catch(NullPointerException ex){           
                    System.out.println("Se lanza una excepcion "+ex);
                }
        	}

        });
        btnAgregarUniformes.setFont(cf.MyFont(0, 15f));
        btnAgregarUniformes.setBounds(353, 175, 160, 28);
        contentPane.add(btnAgregarUniformes);
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		dispose();
        	}
        });
        btnCancelar.setFont(cf.MyFont(0, 15f));
        btnCancelar.setBounds(526, 491, 138, 28);
        contentPane.add(btnCancelar);
        
        btnTerminarVenta = new JButton("Terminar Venta");
        btnTerminarVenta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(textFNombre.getText().length() == 0 || textFApellidos.getText().length()== 0
						 || textFTarjeta.getText().length()== 0|| table.getRowCount() == 0 || (!chckbxEfectivo.isSelected()&& !chckbxElectronico.isSelected())){
					JOptionPane.showMessageDialog (null, "Es necesario llenar todos los campos");
				}
				else{
					//Se captura la fecha
					String dia = Integer.toString(c.get(Calendar.DATE));
					String mes = Integer.toString(c.get(Calendar.MONTH));
					String annio = Integer.toString(c.get(Calendar.YEAR));
					int chec=0;
					fecha = dia + "-" + mes + "-" + annio;
					System.out.println("Fecha de Venta: " + fecha);
					String apart = "";
					
					if(chckbxEfectivo.isSelected())
						chec = 1;
					
					
					//Datos del cliente
					apart = textFNombre.getText() + "," + textFApellidos.getText() + "," + fecha.toString() + "," + chec + "," + totalPagar + ",";
					System.out.println(apart);
					nombre = textFNombre.getText() + " " + textFApellidos.getText();
					totPag = totalDescPagar;
					
					//Se capturan los productos apartados en un arreglo de cadenas
					String prod = "";
					String[] prods = new String[table.getRowCount()];
					for(int j=0; j < table.getRowCount(); j++){
						for(int i=0; i < 7; i++)
							prod += table.getValueAt(j, i) + ",";
						System.out.println(prod);
						prods[j] = prod;
						prod = "";
					}
					//Se hace la venta
					servicioNuVe = new ServicioNuevaVenta(null,conexion);
					servicioNuVe.agregarVenta(0,apart,prods);
					DialogoFormaDeCobro dFP = new DialogoFormaDeCobro(conexion);
					dFP.setVisible(true);
					dispose();
				}
        	}
        });
        btnTerminarVenta.setFont(cf.MyFont(0, 15f));
        btnTerminarVenta.setBounds(375, 491, 138, 28);
        contentPane.add(btnTerminarVenta);
        
        lblListaDeUniformes = new JLabel("Lista de uniformes:");
        lblListaDeUniformes.setForeground(Color.LIGHT_GRAY);
        lblListaDeUniformes.setBounds(49, 188, 163, 18);
        lblListaDeUniformes.setFont(cf.MyFont(0, 17f));
        contentPane.add(lblListaDeUniformes);
        
        lblTotalAPagar = new JLabel("Total a pagar:");
        lblTotalAPagar.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTotalAPagar.setForeground(Color.LIGHT_GRAY);
        lblTotalAPagar.setBounds(49, 407, 114, 18);
        lblTotalAPagar.setFont(cf.MyFont(0, 17f));
        contentPane.add(lblTotalAPagar);
        
        btnCancelacin = new JButton("Cancelaci\u00F3n");
        btnCancelacin.setEnabled(false);
        btnCancelacin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		model.removeRow(table.getSelectedRow());
        		total = new double[table.getRowCount()];
    	        for(int i=0; i < table.getRowCount(); i++){
    	        	total[i] = Double.parseDouble(table.getValueAt(i, 6).toString());
    	        }
        		totalPagar = ServicioNuevaVenta.calculaPagoTotal(total);
        		totPag = totalPagar;
    			label.setText("$ " + totalPagar);
        		btnCancelacin.setEnabled(false);
        	}
        });
        btnCancelacin.setFont(cf.MyFont(0, 15f));
        btnCancelacin.setBounds(526, 175, 138, 28);
        contentPane.add(btnCancelacin);
		
		//descuento
		  jTxtdescuento = new JTextField();
        //validar txt de descuento
        jTxtdescuento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(jTxtdescuento.getText().length() >= 3){
					e.consume();
				}
			}
		});
		
        jTxtdescuento.setBounds(185, 440, 37, 28);
        jTxtdescuento.setFont(cf.MyFont(0, 17f));
        contentPane.add(jTxtdescuento);
        jTxtdescuento.setColumns(10);
        
        JLabel lblTotalcdescuento = new JLabel("Total con descuento:");
        lblTotalcdescuento.setForeground(Color.LIGHT_GRAY);
        lblTotalcdescuento.setFont(cf.MyFont(0, 17f));
        lblTotalcdescuento.setBounds(68, 491, 160, 30);
        contentPane.add(lblTotalcdescuento);
        
        lblTotPagCDesc = new JLabel("$ 0"); //concatenar
        lblTotPagCDesc.setForeground(Color.WHITE);
        lblTotPagCDesc.setBounds(229, 495, 128, 21);
        lblTotPagCDesc.setFont(cf.MyFont(0, 19f));
        contentPane.add(lblTotPagCDesc);
		
        lblporcdesc = new JLabel("Descuento % :");
        lblporcdesc.setForeground(Color.LIGHT_GRAY);
        lblporcdesc.setFont(cf.MyFont(0, 17f));
        lblporcdesc.setBounds(69, 448, 103, 20);
        contentPane.add(lblporcdesc);
		
		
        
        chckbxEfectivo = new JCheckBox("Efectivo");
        chckbxEfectivo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {

		        
				if(chckbxElectronico.isSelected() == true)
		        	chckbxEfectivo.setEnabled(false);
		        else{
		        	chckbxEfectivo.setEnabled(true);
		        	
		        }	
				 if(jTxtdescuento.getText().equals("") || jTxtdescuento.getText().equals("0")){
					 totalDescPagar=totalPagar;
					 lblTotPagCDesc.setText("$"+" "+totalDescPagar);
				 }
				 else{
					 //valido que sea menor o igual que 100 y no negativo
					 
					 aux=(int) Integer.parseInt(jTxtdescuento.getText());
					 
					 if(aux >100 ){
						 JOptionPane.showMessageDialog (null, "Escriba un porcentaje de descuento valido");
					 }
					 else{
						 totalDescPagar=snv.realizaDescNVenta(jTxtdescuento.getText(),totalPagar);
						 lblTotPagCDesc.setText("$"+" "+totalDescPagar);
					 }
				 }
				 
				 
		        
		      }
		});
        chckbxEfectivo.setForeground(Color.LIGHT_GRAY);
		chckbxEfectivo.setFont(cf.MyFont(0, 17f));
        chckbxEfectivo.setBounds(537, 79, 115, 18);
        contentPane.add(chckbxEfectivo);
        
        chckbxElectronico = new JCheckBox("Electronico");
        chckbxElectronico.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {

				 if(chckbxEfectivo.isSelected() == true)
		        	chckbxElectronico.setEnabled(false);
		        else
		        	chckbxElectronico.setEnabled(true);
		        
		        if(jTxtdescuento.getText().equals("") || jTxtdescuento.getText().equals("0")){
					 totalDescPagar=totalPagar;
					 lblTotPagCDesc.setText("$"+" "+totalDescPagar);
				 }
				 else{
					 
					 aux=(int) Integer.parseInt(jTxtdescuento.getText());
					 
					 if(aux >100 ){
						 JOptionPane.showMessageDialog (null, "Escriba un porcentaje de descuento valido");
					 }
					 else{
						 totalDescPagar=snv.realizaDescNVenta(jTxtdescuento.getText(),totalPagar);
						 lblTotPagCDesc.setText("$"+" "+totalDescPagar);
					 }
					 
				 }
				
		        
			}
		});
        chckbxElectronico.setForeground(Color.LIGHT_GRAY);
		chckbxElectronico.setFont(cf.MyFont(0, 17f));
        chckbxElectronico.setBounds(537, 109, 115, 18);
        contentPane.add(chckbxElectronico);
        
        JLabel lblTipoDePago = new JLabel("Tipo de pago");
        lblTipoDePago.setForeground(Color.LIGHT_GRAY);
		lblTipoDePago.setFont(cf.MyFont(0, 17f));
        lblTipoDePago.setBounds(537, 50, 115, 16);
        contentPane.add(lblTipoDePago);
		
	}
	public String valor(){
		return label.getText();
	}
	public double totalApagar(){
		return totPag;
	}
	public int valorCB(){
		if(chckbxEfectivo.isSelected() == true){
        	chckbxElectronico.setEnabled(false);
        	return 1;
        	}
		else{
			chckbxEfectivo.setEnabled(false);
			return 0;
		}
		
	}

}