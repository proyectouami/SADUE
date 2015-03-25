package mx.uam.ayd.sadue.vistas;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.negocio.ServicioQuitarUniforme;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

public class VistaQuitarUniforme extends JDialog {

	/**
	 * 
	 */
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane jScrollPane1;
//	private DefaultListModel model = new DefaultListModel();
	private DefaultTableModel model = new DefaultTableModel();
	private ServicioQuitarUniforme servicioQU;
	private Producto[] productos;
	private Escuela[] escuelas;
	private DefaultComboBoxModel modelCB = new DefaultComboBoxModel();
	private JComboBox comboBox;
	private JLabel lblSeleccionaUnaEscuela;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VistaQuitarUniforme(final ServicioQuitarUniforme servicio,ConexionDB cone) {
		conexion=cone;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Quitar Uniformes");
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
		
		JButton btnCancelar = new JButton("Cerrar");
		btnCancelar.setFont(cf.MyFont(0, 15f));
	//	btnCancelar.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(526, 389, 138, 28);
		contentPane.add(btnCancelar);
		
		final JButton btnQuitarUniforme = new JButton("Quitar Uniforme");
		btnQuitarUniforme.setEnabled(false);
		btnQuitarUniforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prod = "";
				for(int i=0; i < 6; i++){
					prod += table.getValueAt(table.getSelectedRow(), i) + ",";
				}
				servicioQU = new ServicioQuitarUniforme(null,conexion);
				System.out.println(prod);
				servicioQU.quitarProducto(prod);
				model.removeRow(table.getSelectedRow());
				btnQuitarUniforme.setEnabled(false);
				dialogoUniformeQuitado();
				dispose();
			}
		});
		btnQuitarUniforme.setFont(cf.MyFont(0, 15f));
	//	btnQuitarUniforme.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnQuitarUniforme.setBounds(376, 389, 138, 28);
		contentPane.add(btnQuitarUniforme);
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
        
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		if(table.getSelectionModel().isSelectionEmpty())
					btnQuitarUniforme.setEnabled(false);
				else
					btnQuitarUniforme.setEnabled(true);
        	}
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(cf.MyFont(0, 15f));
        
        JLabel lblSelecciona = new JLabel("Lista de uniformes:");
        lblSelecciona.setForeground(Color.LIGHT_GRAY);
        lblSelecciona.setFont(cf.MyFont(0, 17f));
   //   lblSelecciona.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
        lblSelecciona.setBounds(49, 101, 615, 18);
        contentPane.add(lblSelecciona);
        
        comboBox = new JComboBox(modelCB);
        escuelas = servicio.buscarEscuela();
		for(Escuela u:escuelas) {
			modelCB.addElement(u.getNombreEscuela());
		}

		comboBox.setModel(modelCB);
        comboBox.setBounds(49, 46, 615, 26);
        comboBox.setToolTipText("Selecciona una escuela");
        comboBox.setFont(cf.MyFont(0, 15f));
  //      comboBox.addItem("Selecciona una escuela");
        contentPane.add(comboBox);
        
        lblSeleccionaUnaEscuela = new JLabel("Selecciona una escuela");
        lblSeleccionaUnaEscuela.setHorizontalAlignment(SwingConstants.CENTER);
        lblSeleccionaUnaEscuela.setForeground(Color.LIGHT_GRAY);
        lblSeleccionaUnaEscuela.setBounds(49, 26, 615, 16);
        lblSeleccionaUnaEscuela.setFont(cf.MyFont(0, 17f));
        contentPane.add(lblSeleccionaUnaEscuela);
        
      //productos = servicio.listaUniformes();
        String[] columnNames = {"Id", "Uniforme", "Precio", "Talla", "Escuela", "Existencia"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(model);        
        
        JButton btnBuscarUniformes = new JButton("Buscar Uniformes");
        btnBuscarUniformes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		model.setRowCount(0);
        		productos = servicio.listaUniformes(String.valueOf(comboBox.getSelectedItem()));
        		String[] fila = new String[6];
                
                for (int i = 0; i < productos.length; i++) {
                	fila[0] = String.valueOf(productos[i].dameIdProducto());
                	fila[1] = String.valueOf(productos[i].dameNombreProducto());
                	fila[2] = String.valueOf(productos[i].damePrecio());
                	fila[3] = String.valueOf(productos[i].dameTalla());
                	fila[4] = String.valueOf(productos[i].dameEscuela());
                	fila[5] = String.valueOf(productos[i].dameExistencia());
                	model.addRow(fila);
                }

               	table.setModel(model);
        	}
        });
        btnBuscarUniformes.setBounds(502, 82, 162, 28);
        btnBuscarUniformes.setFont(cf.MyFont(0, 15f));
        contentPane.add(btnBuscarUniformes);
        
      	jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(49, 122, 615, 255);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        /*
		for(Producto p:productos) {
			model.addElement(p);
		}
        */
        
	}
	
	public void dialogoUniformeQuitado(){
		DialogoUniformeQuitado dialogo = new DialogoUniformeQuitado();
		dialogo.setVisible(true);
	}
}
