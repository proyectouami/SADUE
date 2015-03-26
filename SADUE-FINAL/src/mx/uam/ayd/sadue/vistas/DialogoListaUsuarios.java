package mx.uam.ayd.sadue.vistas;

import java.awt.Rectangle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.modelo.Usuario;
import mx.uam.ayd.sadue.negocio.ServicioListaUsuarios;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;



public class DialogoListaUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JList jListUsuarios = null;
	private JButton jButtonCerrar = null;
	private ServicioListaUsuarios servicioLU;
	private Usuario[] usuarios;
	private DefaultListModel model = new DefaultListModel();
	private JScrollPane jScrollPane1;
	private JButton btnEliminar;
	private CustomFont cf = new CustomFont();
	private JLabel lblListaDeUsuarios;

	/**
	 * Create the dialog.
	 */
	public DialogoListaUsuarios(final ServicioListaUsuarios servicioLU) {
		// TODO Auto-generated constructor stub
		initialize();
		usuarios = servicioLU.buscarUsuario();
		for(Usuario u:usuarios) {
			model.addElement(u);
		}

		jListUsuarios.setModel(model);
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(720, 480);
		this.setTitle("Lista de Usuarios");
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
			
			jScrollPane1 = new JScrollPane(jListUsuarios);
			jScrollPane1.setBounds(27, 53, 660, 330);
			jContentPane.add(jScrollPane1);
			jContentPane.add(getBtnEliminar());
			jContentPane.add(getLblListaDeUsuarios());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jListAutores
	 *
	 * @return javax.swing.JList
	 */
	private JList getJListUsuarios() {
		if (jListUsuarios == null) {
			jListUsuarios = new JList();
			jListUsuarios.setBorder(UIManager.getBorder("List.focusCellHighlightBorder"));
			jListUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jListUsuarios.setBackground(SystemColor.inactiveCaption);
			jListUsuarios.setFont(cf.MyFont(0, 15f));
			jListUsuarios.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					if(jListUsuarios.getSelectionModel() == null)
						btnEliminar.setEnabled(false);
					else
						btnEliminar.setEnabled(true);
				}
			});
			jListUsuarios.setBounds(new Rectangle(6, 6, 654, 324));
		}
		return jListUsuarios;
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
					String usr;
					usr = String.valueOf(jListUsuarios.getSelectedValue());
					servicioLU = new ServicioListaUsuarios(null);
					System.out.println(usr);
					servicioLU.eliminarUsuario(usr);
					model.removeElement(jListUsuarios.getSelectedValue());
					btnEliminar.setEnabled(false);
				}
			});
			btnEliminar.setFont(cf.MyFont(0, 15f));
			btnEliminar.setBounds(399, 395, 138, 28);
		}
		return btnEliminar;
	}
	private JLabel getLblListaDeUsuarios() {
		if (lblListaDeUsuarios == null) {
			lblListaDeUsuarios = new JLabel("Lista de usuarios dados de alta en la base de datos, puedes eliminar usuarios");
			lblListaDeUsuarios.setFont(cf.MyFont(0, 17f));
			lblListaDeUsuarios.setForeground(Color.LIGHT_GRAY);
			lblListaDeUsuarios.setBounds(27, 32, 660, 18);
		}
		return lblListaDeUsuarios;
	}
}
