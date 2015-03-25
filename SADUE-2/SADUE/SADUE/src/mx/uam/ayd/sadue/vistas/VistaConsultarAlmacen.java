package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.negocio.ServicioCambioPrecio;
import mx.uam.ayd.sadue.negocio.ServicioConsultarAlmacen;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class VistaConsultarAlmacen extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private Producto[] productos;
	private JButton btnSalir;
	private JLabel lblNewLabel;
	private JLabel lblAgregarMasProducto;
	private JButton btnAceptar;
	private JButton btnCambioPrecio;
	static Producto[] uniformeSeleccionado = null;
	private JTextField textFieldCantidad;



	/**
	 * Create the frame.
	 */
	public VistaConsultarAlmacen(final ServicioConsultarAlmacen sCA) {
		setBounds(100, 100, 790, 480);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		setTitle("Consulta de Almacen");
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		CustomFont cf = new CustomFont();
		
		table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(table.getSelectionModel().isSelectionEmpty() || table.getRowCount() == 0){
        			textFieldCantidad.setEnabled(false);
        			btnAceptar.setEnabled(false);
        			btnCambioPrecio.setEnabled(false);
        		}
        		else{
        		//	btnAceptar.setEnabled(true);
        			textFieldCantidad.setEnabled(true);
        			btnCambioPrecio.setEnabled(true);
        		}
        	}
        });
        String[] columnNames = {"Id", "Uniforme", "Escuela", "Talla", "Precio", "Cantidad"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        productos = sCA.listaUniformes();
		String[] fila = new String[6];
        
        for (int i = 0; i < productos.length; i++) {
        	fila[0] = String.valueOf(productos[i].dameIdProducto());
        	fila[1] = String.valueOf(productos[i].dameNombreProducto());
        	fila[2] = String.valueOf(productos[i].dameEscuela());
        	fila[3] = String.valueOf(productos[i].dameTalla());
        	fila[4] = String.valueOf(productos[i].damePrecio());
        	fila[5] = String.valueOf(productos[i].dameExistencia());
        	model.addRow(fila);
        }
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(cf.MyFont(0, 15f));
        
      	JScrollPane jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(49, 52, 676, 325);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        
        btnSalir = new JButton("Salir");
        btnSalir.setFont(cf.MyFont(0, 15f));
        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnSalir.setBounds(587, 389, 138, 28);
        contentPane.add(btnSalir);
        
        lblNewLabel = new JLabel("Lista de productos en existencia: ");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        lblNewLabel.setFont(cf.MyFont(0, 17f));
        lblNewLabel.setBounds(49, 13, 615, 35);
        contentPane.add(lblNewLabel);
        
        lblAgregarMasProducto = new JLabel("Agregar existencias: ");
        lblAgregarMasProducto.setHorizontalAlignment(SwingConstants.TRAILING);
        lblAgregarMasProducto.setForeground(Color.LIGHT_GRAY);
        lblAgregarMasProducto.setFont(cf.MyFont(0, 17f));
        lblAgregarMasProducto.setBounds(34, 389, 165, 27);
        contentPane.add(lblAgregarMasProducto);
                
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(cf.MyFont(0, 15f));
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				int cantidad = Integer.parseInt(textFieldCantidad.getText());
        		if(cantidad >= 0){
					String prod = "";
					uniformeSeleccionado = new Producto[1];
					for(int i=0; i < 6; i++){
						prod += table.getValueAt(table.getSelectedRow(), i) + ",";
					}
				//	prod += cantidad;
					System.out.println(prod);
					sCA.actualizarProducto(prod,cantidad);
					uniformeSeleccionado[0] = sCA.listaTemporalUniformes(prod,cantidad);
					System.out.println(uniformeSeleccionado[0]);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Debes especificar una cantidad", "Error",JOptionPane.ERROR_MESSAGE);
				}
        	}
        });
        btnAceptar.setBounds(437, 389, 138, 28);
        contentPane.add(btnAceptar);
        
        textFieldCantidad = new JTextField();
        textFieldCantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				if(((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)){
					e.consume();
				}
				if(textFieldCantidad.getText().length() >= 10){
					e.consume();
				}
			}
        	@Override
        	public void keyReleased(KeyEvent arg0) {
        		if(textFieldCantidad.getText().length() < 1)
					btnAceptar.setEnabled(false);
				else
					btnAceptar.setEnabled(true);
        	}
		});
        textFieldCantidad.setFont(cf.MyFont(0, 17f));
		textFieldCantidad.setBounds(211, 388, 63, 30);
        contentPane.add(textFieldCantidad);
        textFieldCantidad.setColumns(10);
        
        btnCambioPrecio = new JButton("Cambiar Precio");
        btnCambioPrecio.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        		String uniforme = String.valueOf(table.getValueAt(table.getSelectedRow(), 1).toString());
        		String escuela = String.valueOf(table.getValueAt(table.getSelectedRow(), 2).toString());
        		String talla = String.valueOf(table.getValueAt(table.getSelectedRow(), 3).toString());
        		int cantidad = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
        		sCA.cambiarPrecio(id, uniforme, escuela, talla, cantidad);
        		dispose();
        	}
        });
        btnCambioPrecio.setFont(cf.MyFont(0, 15f));
        btnCambioPrecio.setEnabled(false);
        btnCambioPrecio.setBounds(286, 389, 138, 28);
        contentPane.add(btnCambioPrecio);
	}
}
