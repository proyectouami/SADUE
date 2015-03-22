package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.negocio.ServicioSeleccionUniformesApartar;

public class VistaSeleccionUniformesApartar extends JDialog{
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane jScrollPane1;
//	private DefaultListModel model = new DefaultListModel();
	private DefaultTableModel model = new DefaultTableModel();
	private Producto[] productos;
	static Producto[] uniformeSeleccionado = null;
	static int tam = 1;
	private Escuela[] escuelas;
	private DefaultComboBoxModel modelCB = new DefaultComboBoxModel();
	private JComboBox<String> comboBox;
	private JComboBox<Integer> comboBoxCantidad;
	private JLabel lblSeleccionaUnaEscuela;
	private JTable table;
	private ServicioSeleccionUniformesApartar servA = new ServicioSeleccionUniformesApartar(null,conexion);
	
	private JButton btnAceptar;

	/**
	 * Create the frame.
	 */
	public VistaSeleccionUniformesApartar(final ServicioSeleccionUniformesApartar servicio,ConexionDB cone) {
		conexion=cone;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Seleccionar Uniformes");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CustomFont cf = new CustomFont();
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(cf.MyFont(0, 15f));
	//	btnCancelar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				uniformeSeleccionado = new Producto[0];
				uniformeSeleccionado = null;
				dispose();
			}
		});
		btnCerrar.setBounds(526, 389, 138, 28);
		contentPane.add(btnCerrar);
		
   /*     
        listaUniformes = new JList();
        listaUniformes.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(listaUniformes.getSelectionModel() == null)
					btnQuitarUniforme.setEnabled(false);
				else
					btnQuitarUniforme.setEnabled(true);
				if(listaUniformes.isSelectionEmpty()){
					btnQuitarUniforme.setEnabled(false);
				}
        	}
        });
        listaUniformes.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
        listaUniformes.setBackground(SystemColor.inactiveCaption);
        listaUniformes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        productos = servicio.listaUniformes();
		for(Producto p:productos) {
			model.addElement(p);
		}

		listaUniformes.setModel(model);
*/
    //    listaUniformes.setModel(model);
       // listaUniformes.setFont(cf.MyFont(0, 15f));
        
        lblSeleccionaUnaEscuela = new JLabel("Selecciona una escuela");
        lblSeleccionaUnaEscuela.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionaUnaEscuela.setForeground(Color.LIGHT_GRAY);
        lblSeleccionaUnaEscuela.setBounds(49, 26, 615, 16);
        lblSeleccionaUnaEscuela.setFont(cf.MyFont(0, 17f));
        contentPane.add(lblSeleccionaUnaEscuela);
        
        comboBox = new JComboBox(modelCB);
        escuelas = servA.buscarEscuela();
		for(Escuela u:escuelas) {
			modelCB.addElement(u.getNombreEscuela());
			System.out.println(u.getNombreEscuela());
		}

		comboBox.setModel(modelCB);
        comboBox.setBounds(49, 46, 615, 26);
        comboBox.setToolTipText("Selecciona una escuela");
        comboBox.setFont(cf.MyFont(0, 15f));
  //      comboBox.addItem("Selecciona una escuela");
        contentPane.add(comboBox);
        
        String[] datos = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        comboBoxCantidad = new JComboBox(datos);
        comboBoxCantidad.setBounds(132, 389, 69, 26);
        comboBoxCantidad.setFont(cf.MyFont(0, 15f));
        comboBoxCantidad.setEnabled(false);
        contentPane.add(comboBoxCantidad);
        	
        JLabel lblSelecciona = new JLabel("Lista de uniformes:");
        lblSelecciona.setForeground(Color.LIGHT_GRAY);
        lblSelecciona.setFont(cf.MyFont(0, 17f));
   //   lblSelecciona.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
        lblSelecciona.setBounds(49, 101, 615, 18);
        contentPane.add(lblSelecciona);
        
    	/*public boolean isCellEditable(int row, int col){
    		if(col == 6)
    			return true;
    		else
    			return false;
    	}
    	};*/
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(table.getSelectedRow() >= 0){
        			comboBoxCantidad.setEnabled(true);
        			btnAceptar.setEnabled(true);
        		}
        		else{
        			btnAceptar.setEnabled(false);
        			comboBoxCantidad.setEnabled(false);
        		}
        	}
        });
        String[] columnNames = {"Id", "Uniforme", "Escuela", "Talla", "Precio", "Existencia"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(cf.MyFont(0, 15f));
        
        JButton btnBuscarUniformes = new JButton("Buscar Uniformes");
        btnBuscarUniformes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		model.setRowCount(0);
        		productos = servicio.listaUniformes(String.valueOf(comboBox.getSelectedItem()));
        		String[] fila = new String[6];
                
                for (int i = 0; i < productos.length; i++) {
                	fila[0] = String.valueOf(productos[i].dameIdProducto());
                	fila[1] = String.valueOf(productos[i].dameNombreProducto());
                	fila[4] = String.valueOf(productos[i].damePrecio());
                	fila[3] = String.valueOf(productos[i].dameTalla());
                	fila[2] = String.valueOf(productos[i].dameEscuela());
                	fila[5] = String.valueOf(productos[i].dameExistencia());
                	model.addRow(fila);
                }
               	table.setModel(model);
        	}
        });
        btnBuscarUniformes.setBounds(502, 82, 162, 28);
        btnBuscarUniformes.setFont(cf.MyFont(0, 15f));
        contentPane.add(btnBuscarUniformes);
        
        btnAceptar = new JButton("Aceptar");
		btnAceptar.setEnabled(false);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() < 0)
					JOptionPane.showMessageDialog(null, "Debes seleccionar algún uniforme", "Error",JOptionPane.ERROR_MESSAGE);
				else{
					if(comboBoxCantidad.getSelectedIndex() != 0){
						if((Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString()) >= comboBoxCantidad.getSelectedIndex())){
						String prod = "";
						int cantidad = comboBoxCantidad.getSelectedIndex();
						uniformeSeleccionado = new Producto[1];
						for(int i=0; i < 6; i++){
							prod += table.getValueAt(table.getSelectedRow(), i) + ",";
						}
					//	prod += cantidad;
						System.out.println(prod);
						servicio.listaTemporalUniformes(prod,cantidad);
						uniformeSeleccionado[0] = servicio.listaTemporalUniformes(prod,cantidad);
						System.out.println(uniformeSeleccionado[0]);
						dispose();
						}
						else
							JOptionPane.showMessageDialog(null, "No hay suficientes productos para realizar\neste apartado", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						JOptionPane.showMessageDialog(null, "Debes especificar una cantidad", "Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAceptar.setFont(cf.MyFont(0, 15f));
	//	btnQuitarUniforme.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnAceptar.setBounds(376, 389, 138, 28);
		contentPane.add(btnAceptar);
        
      	jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(49, 122, 615, 255);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setHorizontalAlignment(SwingConstants.TRAILING);
        lblCantidad.setForeground(Color.LIGHT_GRAY);
        lblCantidad.setBounds(21, 394, 99, 18);
        lblCantidad.setFont(cf.MyFont(0, 17f));
        contentPane.add(lblCantidad);
        /*
		for(Producto p:productos) {
			model.addElement(p);
		}
        */
        
	}
	
	public Producto[] uniformesSeleccionados(){
		return uniformeSeleccionado;
	}
	
	public int tamArreglo(){
		return tam;
	}
	
}
