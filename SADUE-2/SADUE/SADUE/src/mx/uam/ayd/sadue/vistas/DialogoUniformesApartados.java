package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoApartado;
import mx.uam.ayd.sadue.negocio.ServicioUniformesApartados;
import mx.uam.ayd.sadue.negocio.ServicioSeleccionUniformesApartar;

public class DialogoUniformesApartados extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane jScrollPane1;
//	private DefaultListModel model = new DefaultListModel();
	private DefaultTableModel model = new DefaultTableModel();
	private ProductoApartado[] productos;
	private JTable table;
	private ServicioUniformesApartados servA;

	/**
	 * Create the frame.
	 */
	public DialogoUniformesApartados(final ServicioUniformesApartados servicio) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Uniformes Apartados");
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
				dispose();
			}
		});
		btnCerrar.setBounds(526, 389, 138, 28);
		contentPane.add(btnCerrar);
                	
        JLabel lblSelecciona = new JLabel("Lista de uniformes apartados:");
        lblSelecciona.setForeground(Color.LIGHT_GRAY);
        lblSelecciona.setFont(cf.MyFont(0, 17f));
   //   lblSelecciona.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
        lblSelecciona.setBounds(49, 27, 615, 18);
        contentPane.add(lblSelecciona);
        
    	
        table = new JTable();
        table.setRowSelectionAllowed(false);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent arg0) {
        		
        	}
        });
        String[] columnNames = {"Id", "Uniforme", "Escuela", "Talla", "Precio", "Cantidad"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        productos = servicio.listaUniformes();
		String[] fila = new String[6];
        
        for (int i = 0; i < productos.length; i++) {
        	fila[0] = String.valueOf(productos[i].dameIdProducto());
        	fila[1] = String.valueOf(productos[i].dameNombreProducto());
        	fila[2] = String.valueOf(productos[i].dameEscuela());
        	fila[3] = String.valueOf(productos[i].dameTalla());
        	fila[4] = String.valueOf(productos[i].damePrecio());
        	fila[5] = String.valueOf(productos[i].dameCantidad());
        	model.addRow(fila);
        }
        table.setModel(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(cf.MyFont(0, 15f));
        
      	jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(49, 52, 615, 325);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        /*
		for(Producto p:productos) {
			model.addElement(p);
		}
        */
        
	}

}
