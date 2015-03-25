package mx.uam.ayd.sadue.vistas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.negocio.ServicioApartados;
import mx.uam.ayd.sadue.negocio.ServicioUniformesApartados;

public class VistaApartados extends JDialog {
	ConexionDB conexion;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane jScrollPane1;
//	private DefaultListModel model = new DefaultListModel();
	private DefaultTableModel model = new DefaultTableModel();
	private ServicioApartados servicioApartados;
	private Apartado[] apartados;
	private JTable table;
	private JButton btnVerProductos;
	private ServicioUniformesApartados sua;

	/**
	 * Create the dialog.
	 */
	public VistaApartados(final ServicioApartados servicioAP,ConexionDB cone) {
		conexion=cone;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setTitle("Apartados");
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 480);
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
		btnCancelar.setBounds(710, 389, 138, 28);
		contentPane.add(btnCancelar);
		
		final JButton btnFinalizaApartado = new JButton("Finalizar Apartado");
		btnFinalizaApartado.setEnabled(false);
		btnFinalizaApartado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!table.getValueAt(table.getSelectedRow(), 9).equals("Pagada")){
					String apart = "";
					for(int i=0; i < 11; i++){
						apart += table.getValueAt(table.getSelectedRow(), i) + ",";
					}
					servicioApartados = new ServicioApartados(null,conexion);
					System.out.println(apart);
					servicioApartados.finalizarApartado(apart);
					btnFinalizaApartado.setEnabled(false);
					dialogoApartadoFinalizado();
				//	dispose();
				}
			}
		});
		btnFinalizaApartado.setFont(cf.MyFont(0, 15f));
	//	btnQuitarUniforme.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
		btnFinalizaApartado.setBounds(509, 389, 189, 28);
		contentPane.add(btnFinalizaApartado);
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
        		if(table.getSelectionModel().isSelectionEmpty() || table.getRowCount() == 0){
					btnFinalizaApartado.setEnabled(false);
					btnVerProductos.setEnabled(false);
        		}
				else{
					btnFinalizaApartado.setEnabled(true);
					btnVerProductos.setEnabled(true);
				}
        	}
        });
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(cf.MyFont(0, 15f));
        
        JLabel lblSelecciona = new JLabel("Lista de apartados:");
        lblSelecciona.setForeground(Color.LIGHT_GRAY);
        lblSelecciona.setFont(cf.MyFont(0, 17f));
   //   lblSelecciona.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
        lblSelecciona.setBounds(49, 29, 799, 18);
        contentPane.add(lblSelecciona);
        
      //productos = servicio.listaUniformes();
        String[] columnNames = {"No. Apartado", "Fecha", "Nombre", "Apellido", "Dirección",
        						"E-mail", "Teléfono", "No. Tarjeta", "Total", "Cuenta", "Deuda"};
        model.setColumnIdentifiers(columnNames);
        table.getTableHeader().setReorderingAllowed(false);
        model.setRowCount(0);
		apartados = servicioAP.listaApartados();
		String[] fila = new String[11];
        
        for (int i = 0; i < apartados.length; i++) {
        	fila[0] = String.valueOf(apartados[i].dameId());
        	fila[1] = String.valueOf(apartados[i].dameFecha());
        	fila[2] = String.valueOf(apartados[i].dameNombreCliente());
        	fila[3] = String.valueOf(apartados[i].dameApellidoCliente());
        	fila[4] = String.valueOf(apartados[i].dameDireccionCliente());
        	fila[5] = String.valueOf(apartados[i].dameEmailCliente());
        	fila[6] = String.valueOf(apartados[i].dameTelCliente());
        	fila[7] = String.valueOf(apartados[i].dameNoTarjetaCliente());
        	fila[8] = String.valueOf(apartados[i].damePagoTotal());
        	fila[9] = String.valueOf(apartados[i].dameCuenta());
        	fila[10] = String.valueOf(apartados[i].dameDeuda());
        	model.addRow(fila);
        }

       	table.setModel(model);        
      	jScrollPane1 = new JScrollPane(table);
        jScrollPane1.setBounds(46, 58, 802, 319);
        jScrollPane1.setViewportView(table);
        contentPane.add(jScrollPane1);
        
        btnVerProductos = new JButton("Ver Productos");
        btnVerProductos.setEnabled(false);
        btnVerProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        		servicioAP.verProductos(id);
        	}
        });
        btnVerProductos.setBounds(49, 388, 138, 28);
        btnVerProductos.setFont(cf.MyFont(0, 15f));
        contentPane.add(btnVerProductos);
        /*
		for(Producto p:productos) {
			model.addElement(p);
		}
        */
        
	}
	
	public void dialogoApartadoFinalizado(){
		DialogoApartadoFinalizado dialogo = new DialogoApartadoFinalizado();
		dialogo.setVisible(true);
	}

}
