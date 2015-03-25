package mx.uam.ayd.sadue.vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import org.w3c.dom.ls.LSLoadEvent;

import mx.uam.ayd.sadue.fonts.CustomFont;
import mx.uam.ayd.sadue.fonts.CustomFont2;
import mx.uam.ayd.sadue.fonts.CustomFont3;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoAcercaDe extends JDialog {



	/**
	 * Create the dialog.
	 */
	public DialogoAcercaDe() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setBounds(100, 100, 1000, 520);
		setLocationRelativeTo(null);
		setResizable(false);
		setModal(true);
		setTitle("Acerca de SADUE");
		setUndecorated(true);
		
		CustomFont cf = new CustomFont();
		CustomFont2 cf2 = new CustomFont2();
		CustomFont3 cf3 = new CustomFont3();
		
		getContentPane().setLayout(null);
		{
			JLabel lblSadue = new JLabel("SADUE");
			lblSadue.setHorizontalAlignment(SwingConstants.CENTER);
			lblSadue.setFont(cf3.MyFont(0, 60f));
	//		lblSadue.setFont(new Font("Goudy Old Style", Font.BOLD, 60));
			lblSadue.setForeground(Color.WHITE);
			lblSadue.setBounds(291, 38, 417, 73);
			getContentPane().add(lblSadue);
		}
		{
			JLabel lblSistemaAdministradorDe = new JLabel("Sistema Administrador de Uniformes Escolares");
			lblSistemaAdministradorDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblSistemaAdministradorDe.setFont(cf2.MyFont(0, 25f));
	//		lblSistemaAdministradorDe.setFont(new Font("Goudy Old Style", Font.PLAIN, 25));
			lblSistemaAdministradorDe.setForeground(Color.LIGHT_GRAY);
			lblSistemaAdministradorDe.setBounds(230, 108, 539, 31);
			getContentPane().add(lblSistemaAdministradorDe);
		}
		{
			JLabel lblVersinBetaV = new JLabel("Versi\u00F3n BETA v0.1");
			lblVersinBetaV.setHorizontalAlignment(SwingConstants.CENTER);
			lblVersinBetaV.setFont(cf.MyFont(0, 15f));
	//		lblVersinBetaV.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblVersinBetaV.setForeground(Color.ORANGE);
			lblVersinBetaV.setBounds(423, 145, 154, 16);
			getContentPane().add(lblVersinBetaV);
		}
		{
			JLabel lblDesarrolladoPorWalle = new JLabel("Desarrollado por WALL-E Company");
			lblDesarrolladoPorWalle.setHorizontalAlignment(SwingConstants.CENTER);
			lblDesarrolladoPorWalle.setForeground(Color.WHITE);
			lblDesarrolladoPorWalle.setFont(cf.MyFont(0, 17f));
	//		lblDesarrolladoPorWalle.setFont(new Font("Myriad Pro", Font.PLAIN, 17));
			lblDesarrolladoPorWalle.setBounds(340, 232, 319, 18);
			getContentPane().add(lblDesarrolladoPorWalle);
		}
		{
			JLabel lblFloresRamosEva = new JLabel("Flores Ramos Eva Hayde");
			lblFloresRamosEva.setForeground(Color.LIGHT_GRAY);
			lblFloresRamosEva.setFont(cf.MyFont(0, 15f));
	//		lblFloresRamosEva.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblFloresRamosEva.setBounds(265, 260, 213, 16);
			getContentPane().add(lblFloresRamosEva);
		}
		{
			JLabel lblRojasLibradoJos = new JLabel("Rojas Librado Jos\u00E9 Alberto");
			lblRojasLibradoJos.setForeground(Color.LIGHT_GRAY);
			lblRojasLibradoJos.setFont(cf.MyFont(0, 15f));
	//		lblRojasLibradoJos.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblRojasLibradoJos.setBounds(265, 285, 213, 16);
			getContentPane().add(lblRojasLibradoJos);
		}
		{
			JLabel lblValdsLpezHctor = new JLabel("Vald\u00E9s L\u00F3pez H\u00E9ctor");
			lblValdsLpezHctor.setForeground(Color.LIGHT_GRAY);
			lblValdsLpezHctor.setFont(cf.MyFont(0, 15f));
	//		lblValdsLpezHctor.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblValdsLpezHctor.setBounds(265, 310, 213, 16);
			getContentPane().add(lblValdsLpezHctor);
		}
		{
			JLabel lblAnlisisYDiseo = new JLabel("An\u00E1lisis y Dise\u00F1o de Sistemas de Computaci\u00F3n");
			lblAnlisisYDiseo.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnlisisYDiseo.setForeground(Color.WHITE);
			lblAnlisisYDiseo.setFont(cf.MyFont(0, 15f));
	//		lblAnlisisYDiseo.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblAnlisisYDiseo.setBounds(636, 480, 358, 16);
			getContentPane().add(lblAnlisisYDiseo);
		}
		{
			JLabel lblDrHumbertoCervantes = new JLabel("Dr. Humberto Cervantes Maceda");
			lblDrHumbertoCervantes.setHorizontalAlignment(SwingConstants.TRAILING);
			lblDrHumbertoCervantes.setForeground(Color.LIGHT_GRAY);
			lblDrHumbertoCervantes.setFont(cf.MyFont(0, 15f));
	//		lblDrHumbertoCervantes.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblDrHumbertoCervantes.setBounds(636, 498, 358, 16);
			getContentPane().add(lblDrHumbertoCervantes);
		}
		{
			JLabel lblUniversidadAutnomaMetropolitana = new JLabel("Universidad Aut\u00F3noma Metropolitana");
			lblUniversidadAutnomaMetropolitana.setForeground(Color.WHITE);
			lblUniversidadAutnomaMetropolitana.setFont(cf.MyFont(0, 15f));
	//		lblUniversidadAutnomaMetropolitana.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
			lblUniversidadAutnomaMetropolitana.setBounds(6, 482, 303, 16);
			getContentPane().add(lblUniversidadAutnomaMetropolitana);
		}
		{
			JLabel lblIztapalapa = new JLabel("Unidad Iztapalapa");
			lblIztapalapa.setFont(cf.MyFont(0, 13f));
	//		lblIztapalapa.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
			lblIztapalapa.setForeground(new Color(124, 252, 0));
			lblIztapalapa.setBounds(6, 500, 303, 14);
			getContentPane().add(lblIztapalapa);
		}
		{
			JLabel lblDevelopers = new JLabel("Developers");
			lblDevelopers.setForeground(Color.ORANGE);
			lblDevelopers.setFont(cf3.MyFont(0, 30f));
	//		lblDevelopers.setFont(new Font("Segoe Print", Font.PLAIN, 25));
			lblDevelopers.setBounds(598, 270, 185, 37);
			getContentPane().add(lblDevelopers);
		}
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCerrar.setBounds(431, 380, 138, 28);
		btnCerrar.setFont(cf.MyFont(0, 15f));
		getContentPane().add(btnCerrar);
	}
}
