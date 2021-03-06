package mx.uam.ayd.sadue.vistas;

import javax.smartcardio.Card;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.negocio.ServicioApartar;
import mx.uam.ayd.sadue.negocio.ServicioSeleccionUniformesApartar;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class VistaApartar extends JDialog {

	/**
	 * 
	 */
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ServicioSeleccionUniformesApartar ssua = new ServicioSeleccionUniformesApartar(null,conexion);
	private VistaSeleccionUniformesApartar vsua;
	private ServicioApartar servicioApr;
	private JTextField textFNombre;
	private JTextField textFApellidos;
	private JTextField textFTelefono;
	private JTextField textFTarjeta;
	private JTextField textFEmail;
	private JTextField textFDireccion;
	private JScrollPane jScrollPane1;
	private JButton btnAgregarUniformes;
	private JButton btnCancelar;
	private JButton btnApartar;
	private JButton btnCancelacin;
	private DefaultTableModel model = new DefaultTableModel();
	private JLabel lblListaDeUniformes;
	private static JTable table;
	private Producto[] productos;
	private JLabel lblTotalAPagar; //muestra el texto total a pagar
	private JLabel label;         //se muestra el total a pagar 
	private double totalPagar = 0; //se guarda total a pagar cantidad numerica
	double[] total;
	private String fecha;
	static String nombre;
	static double totPag;
	static int apartId;
	//descuento	
	private int aux=-1;
	private double granTotalDesc=-1;   //almacena cantidad con su descuento
	private JLabel lblporcdesc;     //etiqueta descuento%:-
	private JTextField jTxtdescuento;   //capturan descuento-
	private JLabel lblapdesc;           //etiqueta aplicar descuento-
	private JCheckBox JchkBdescuento;    //checkbox aplicar descuento-
	private JLabel lblTotPagCDesc;  // muestra et Total a Pagar C/desc: 
	private JLabel lblGrantotcDesc;   //muestra gran total
    
	/**
	 * Create the frame.
	 */
	public VistaApartar(final ServicioApartar servicioAp,ConexionDB cone) {
		conexion=cone;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);//mod
		setModal(true);
		setTitle("Apartar");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		final Calendar c = new GregorianCalendar();
        final ServicioApartar servap = new ServicioApartar(null,conexion);
		
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
		lblNombre.setBounds(33, 56, 83, 18);
		contentPane.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setForeground(Color.LIGHT_GRAY);
		lblApellidos.setFont(cf.MyFont(0, 17f));
		lblApellidos.setBounds(33, 86, 83, 18);
		contentPane.add(lblApellidos);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelfono.setForeground(Color.LIGHT_GRAY);
		lblTelfono.setFont(cf.MyFont(0, 17f));
		lblTelfono.setBounds(308, 86, 120, 18);
		contentPane.add(lblTelfono);
		
		JLabel lblNoDeTarjeta = new JLabel("No. de Tarjeta:");
		lblNoDeTarjeta.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoDeTarjeta.setForeground(Color.LIGHT_GRAY);
		lblNoDeTarjeta.setFont(cf.MyFont(0, 17f));
		lblNoDeTarjeta.setBounds(308, 116, 120, 18);
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
		textFNombre.setBounds(128, 50, 166, 30);
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
		textFApellidos.setBounds(128, 80, 166, 30);
		contentPane.add(textFApellidos);
		textFApellidos.setColumns(10);
		
		textFTelefono = new JTextField();
		textFTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(textFTelefono.getText().length() >= 10){
					e.consume();
				}
			}
		});
		textFTelefono.setFont(cf.MyFont(0, 17f));
		textFTelefono.setBounds(440, 80, 152, 30);
		contentPane.add(textFTelefono);
		textFTelefono.setColumns(10);
		
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
		textFTarjeta.setBounds(440, 110, 152, 30);
		contentPane.add(textFTarjeta);
		textFTarjeta.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(cf.MyFont(0, 17f));
		lblEmail.setBounds(33, 116, 83, 18);
		contentPane.add(lblEmail);
		
		textFEmail = new JTextField();
		textFEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter<'a' || caracter>'z') && (caracter<'A' || caracter>'Z')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != '.')
						&& (caracter != '@') && (caracter<'0' || caracter>'9') && (caracter != '-') && (caracter != '_')){
					e.consume();
				}
				if(textFEmail.getText().length() >= 40){
					e.consume();
				}
				if (caracter == '@' && textFEmail.getText().contains("@")) {
					e.consume();
				}
			}
		});
		textFEmail.setFont(cf.MyFont(0, 17f));
		textFEmail.setBounds(128, 110, 166, 30);
		contentPane.add(textFEmail);
		textFEmail.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setHorizontalAlignment(SwingConstants.TRAILING);
		lblDireccin.setFont(cf.MyFont(0, 17f));
		lblDireccin.setForeground(Color.LIGHT_GRAY);
		lblDireccin.setBounds(308, 56, 120, 18);
		contentPane.add(lblDireccin);
		
		textFDireccion = new JTextField();
		textFDireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter<'a' || caracter>'z') && (caracter<'A' || caracter>'Z')) && (caracter != KeyEvent.VK_BACK_SPACE) && (caracter != ' ')
						&& (caracter<'0' || caracter>'9')){
					e.consume();
				}
				if(textFDireccion.getText().length() >= 60){
					e.consume();
				}
			}
		});
		textFDireccion.setFont(cf.MyFont(0, 17f));
		textFDireccion.setBounds(440, 50, 224, 30);
		contentPane.add(textFDireccion);
		textFDireccion.setColumns(10);
		
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
        		//limpia descuento y desmarca el check de descuento
    			JchkBdescuento.setSelected(false);
    			jTxtdescuento.setText("");
    			
        		
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
            	        
            	        //aqui se guarda el totalapagar numerico
            	        totalPagar = servicioAp.calculaPagoTotal(total);
            			label.setText("$ " + totalPagar);
            			//poner lo mismo en costo Descuento
            			granTotalDesc=totalPagar;
            			lblGrantotcDesc.setText("$"+" "+granTotalDesc);
            			
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
        btnCancelar.setBounds(525, 529, 138, 28);
        contentPane.add(btnCancelar);
        
        btnApartar = new JButton("Apartar");
        btnApartar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		if(textFNombre.getText().length() == 0 || textFApellidos.getText().length()== 0
						|| textFDireccion.getText().length() == 0 || textFEmail.getText().length() == 0 || textFTarjeta.getText().length()== 0
						|| textFTelefono.getText().length() == 0 || table.getRowCount() == 0){
					JOptionPane.showMessageDialog (null, "Es necesario llenar todos los campos");
				}
				else{
					//Se captura la fecha
					String dia = Integer.toString(c.get(Calendar.DATE));
					String mes = Integer.toString(c.get(Calendar.MONTH));
					String annio = Integer.toString(c.get(Calendar.YEAR));
					fecha = dia + "-" + mes + "-" + annio;
					System.out.println("Fecha de Apartado: " + fecha);
					String apart = "";
					
					//Se capturan los datos del cliente en una cadena
					apart = "0" + "," + textFNombre.getText() + "," + textFApellidos.getText() + "," + textFDireccion.getText() + "," + textFEmail.getText() + ","
							+ textFTelefono.getText() + "," + textFTarjeta.getText() + "," + fecha.toString() + "," + totalPagar + ",";
					System.out.println(apart);
					nombre = textFNombre.getText() + " " + textFApellidos.getText();
					//totPag = totalPagar;   //esta no va se cambia por la de abajo
					totPag = granTotalDesc;
					
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
					//Se hace el apartado
					servicioApr = new ServicioApartar(null,conexion);
					servicioApr.realizarApartado(0,apart,prods);
					apartId = servicioApr.dameIdApartado(textFNombre.getText(), Long.parseLong(textFTarjeta.getText()), totalPagar);
					dialogoApartadoRealizado();
					dispose();
				}
        	}
        });
        btnApartar.setFont(cf.MyFont(0, 15f));
        btnApartar.setBounds(375, 529, 138, 28);
        contentPane.add(btnApartar);
        
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
        		totalPagar = servicioAp.calculaPagoTotal(total);
    			label.setText("$ " + totalPagar);
        		btnCancelacin.setEnabled(false);
        	}
        });
        btnCancelacin.setFont(cf.MyFont(0, 15f));
        btnCancelacin.setBounds(526, 175, 138, 28);
        contentPane.add(btnCancelacin);
        
        
        //validar jtexfield descuento
        jTxtdescuento = new JTextField();
        jTxtdescuento.addKeyListener(new KeyAdapter() {
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
      //txt descuento
        jTxtdescuento.setFont(cf.MyFont(0, 17f));
        jTxtdescuento.setBounds(274, 434, 41, 25);
        contentPane.add(jTxtdescuento);
        jTxtdescuento.setColumns(10);
        
        
        
	    //accion check
        JchkBdescuento = new JCheckBox();   
        JchkBdescuento.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {

				 if(!JchkBdescuento.isSelected()){
					 if(jTxtdescuento.getText().equals("") || jTxtdescuento.getText().equals("0")){
						 granTotalDesc=totalPagar;
						 lblGrantotcDesc.setText("$"+" "+granTotalDesc);
					 }
					 else{				 
						 aux=(int) Integer.parseInt(jTxtdescuento.getText());			 
						 if(aux >100 ){
							 JOptionPane.showMessageDialog (null, "Escriba un porcentaje de descuento valido");
							 //JchkBdescuento.setSelected(false);			 
						 }
						 else{
							 granTotalDesc=servap.realizaDescuentoApartado(jTxtdescuento.getText(),totalPagar);
							 lblGrantotcDesc.setText("$"+" "+granTotalDesc);
						 }
					 }
					 
				  }
		        	
			}
		});
          
        //checkbox descuento
        
        JchkBdescuento.setBackground(Color.BLACK);
        JchkBdescuento.setForeground(Color.BLACK);
		JchkBdescuento.setFont(cf.MyFont(0, 17f));
        JchkBdescuento.setBounds(211, 469, 24, 27);
        contentPane.add(JchkBdescuento); 
        
        //lblporcdescuento
        lblporcdesc = new JLabel("Porcentaje de Descuento%:");
        lblporcdesc.setHorizontalAlignment(SwingConstants.TRAILING);
        lblporcdesc.setForeground(Color.LIGHT_GRAY);
        lblporcdesc.setFont(cf.MyFont(0, 17f));
        lblporcdesc.setBounds(49, 440, 212, 20);
        contentPane.add(lblporcdesc);
        
        //etiqueta aplicar descuento para checkbox
        lblapdesc = new JLabel("Aplicar Descuento");
        lblapdesc.setHorizontalAlignment(SwingConstants.TRAILING);
        lblapdesc.setForeground(Color.LIGHT_GRAY);
        lblapdesc.setFont(cf.MyFont(0, 17f));
        lblapdesc.setBounds(49, 475, 146, 19);
        contentPane.add(lblapdesc);
        
        //label total con descuento
        lblTotPagCDesc = new JLabel("Total a Pagar C/desc : ");
        //lblTotPagCDesc.setForeground(Color.WHITE);
        lblTotPagCDesc.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTotPagCDesc.setForeground(Color.LIGHT_GRAY);
        lblTotPagCDesc.setFont(cf.MyFont(0, 17f));
        lblTotPagCDesc.setBounds(49, 510, 175, 20);
        contentPane.add(lblTotPagCDesc);
        
        //gran total con desc
        lblGrantotcDesc = new JLabel("$");
        lblGrantotcDesc.setForeground(Color.WHITE);
        lblGrantotcDesc.setFont(cf.MyFont(0, 19f));
        lblGrantotcDesc.setForeground(Color.WHITE);
        lblGrantotcDesc.setBounds(230, 509, 120, 20);
        contentPane.add(lblGrantotcDesc);
		
	}
	
	public String dameNombreDialogo(){
		return nombre;
	}
	
	public int dameApartadoId(){
		return apartId;
	}
	
	public double dameTotal(){
		return totPag;
	}
	
	public void dialogoApartadoRealizado(){
		DialogoApartadoRealizado dialogo = new DialogoApartadoRealizado(conexion);
		dialogo.setVisible(true);
	}
}

