package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.negocio.ServicioListaEscuelas;
import javax.swing.UIManager;

public class DialogoListaEscuelas extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JList jListEscuelas = null;
	private JButton jButtonCerrar = null;
	private ServicioListaEscuelas servicioLE;
	private Escuela[] escuelas;
	private DefaultListModel model = new DefaultListModel();
	private JScrollPane jScrollPane1;
	private JButton btnEliminar;
	private CustomFont cf = new CustomFont();
	private JLabel lblListaDeEscuelas;

	/**
	 * Create the dialog.
	 */
	public DialogoListaEscuelas(final ServicioListaEscuelas servicioLE) {
		// TODO Auto-generated constructor stub
		initialize();
		escuelas = servicioLE.buscarEscuela();
		for(Escuela u:escuelas) {
			model.addElement(u);
		}

		jListEscuelas.setModel(model);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(720, 480);
		this.setTitle("Lista de Escuelas");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setBackground(Color.DARK_GRAY);
			jContentPane.setLayout(null);
			jContentPane.add(getJListUsuarios(), null);
			jContentPane.add(getJButtonCerrar(), null);
			jContentPane.add(getLblListaDeEscuelas());
			
			jScrollPane1 = new JScrollPane(jListEscuelas);
			jScrollPane1.setBounds(27, 53, 660, 330);
			jContentPane.add(jScrollPane1);
			jContentPane.add(getBtnEliminar());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jListAutores
	 *
	 * @return javax.swing.JList
	 */
	private JList getJListUsuarios() {
		if (jListEscuelas == null) {
			jListEscuelas = new JList();
			jListEscuelas.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
			jListEscuelas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListEscuelas.setBackground(SystemColor.inactiveCaption);
			jListEscuelas.setFont(cf.MyFont(0, 15f));
			jListEscuelas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(jListEscuelas.getSelectionModel() == null)
						btnEliminar.setEnabled(false);
					else
						btnEliminar.setEnabled(true);
					if(jListEscuelas.isSelectionEmpty()){
						btnEliminar.setEnabled(false);
					}
				}
			});
			jListEscuelas.setBounds(new Rectangle(6, 6, 654, 324));
		}
		return jListEscuelas;
	}

	/**
	 * This method initializes jButtonAceptar
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getJButtonCerrar() {
		if (jButtonCerrar == null) {
			jButtonCerrar = new JButton();
			jButtonCerrar.setBounds(new Rectangle(549, 395, 138, 28));
			jButtonCerrar.setFont(cf.MyFont(0, 15f));
			jButtonCerrar.setText("Cerrar");
			jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return jButtonCerrar;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String esc;
					esc = String.valueOf(jListEscuelas.getSelectedValue());
					servicioLE = new ServicioListaEscuelas(null);
					System.out.println(esc);
					servicioLE.eliminarEscuela(esc);
					model.removeElement(jListEscuelas.getSelectedValue());
					btnEliminar.setEnabled(false);
				}
			});
			btnEliminar.setFont(cf.MyFont(0, 15f));
			btnEliminar.setBounds(399, 395, 138, 28);
		}
		return btnEliminar;
	}
	private JLabel getLblListaDeEscuelas() {
		if (lblListaDeEscuelas == null) {
			lblListaDeEscuelas = new JLabel("Lista de escuelas dadas de alta en la base de datos, puedes eliminar escuelas");
			lblListaDeEscuelas.setFont(cf.MyFont(0, 17f));
			lblListaDeEscuelas.setForeground(Color.LIGHT_GRAY);
			lblListaDeEscuelas.setBounds(27, 25, 660, 18);
		}
		return lblListaDeEscuelas;
	}
}
