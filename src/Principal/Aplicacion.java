package Principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.InvalidPositionException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Aplicacion {

	private JFrame frmTreeGenerator;
	private JButton btnGradoArbol;
	private Logica p;
	private JButton btnAgregarNodo;
	private JButton btnEliminarNodo;
	private JButton btnObtenerGrados;
	private JButton btnCamino;
	private JButton btnEliminarGrado;
	private JLabel lblMostrarRec;
	private JLabel lblOperaciones;
	private JTextArea textAreaOperaciones;
	private JTextArea textAreaRecorridos;
	private JLabel lblTodasOp;
	private JToggleButton tglbtnPost;
	private JToggleButton tglbtnPorNivel;
	private JToggleButton tglbtnPre;
	private JScrollPane spOperaciones;
	private JTextPane textPaneArbol;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion window = new Aplicacion();
					window.frmTreeGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTreeGenerator = new JFrame();
		frmTreeGenerator.setTitle("Tree generator");
		frmTreeGenerator.setBounds(100, 100, 807, 416);
		frmTreeGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnCrearArbol = new JButton("Crear \u00E1rbol...");
		btnCrearArbol.setToolTipText("Crea un \u00E1rbol a partir de un r\u00F3tulo ingresado");
		btnCrearArbol.setMnemonic('c');
		btnCrearArbol.setBounds(10, 33, 144, 29);
		btnCrearArbol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCrearArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						String ch = JOptionPane.showInputDialog("Ingrese un caracter");
						Character c = ch.charAt(0);
						p = new Logica();
						p.crearArbol(c);
						
						StyledDocument doc = textPaneArbol.getStyledDocument();
						SimpleAttributeSet center = new SimpleAttributeSet();
						StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
						doc.setParagraphAttributes(0, doc.getLength(), center, false);
						textPaneArbol.setText(p.mostrarRotuloPorNivel());
						
						btnAgregarNodo.setEnabled(true);
						btnObtenerGrados.setEnabled(true);
						btnGradoArbol.setEnabled(true);
						btnCamino.setEnabled(true);
						btnEliminarNodo.setEnabled(true);
						btnEliminarGrado.setEnabled(true);
						
						mostrarRec();
						reestablecerOpSalida();
						
					}catch(StringIndexOutOfBoundsException e1) {
						JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un caracter");
						}
					}
				}

			
		);
		frmTreeGenerator.getContentPane().setLayout(null);
		frmTreeGenerator.getContentPane().add(btnCrearArbol);
		
		btnAgregarNodo = new JButton("Agregar nodo...");
		btnAgregarNodo.setMnemonic('a');
		btnAgregarNodo.setBounds(10, 73, 144, 29);
		btnAgregarNodo.setEnabled(false);
		btnAgregarNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ch = JOptionPane.showInputDialog("Ingrese el padre del rótulo que desea agregar");
					Character c = ch.charAt(0);
					String ch2 = JOptionPane.showInputDialog("Ingrese el rótulo que desea agregar");
					Character c2 = ch2.charAt(0);
					p.agregarNodo(c, c2);
					if(p.obtenerCamino(c2) == "")
						JOptionPane.showMessageDialog(frmTreeGenerator,"El primer rótulo ingresado no existe en el árbol");
					else {
						btnEliminarNodo.setEnabled(true);
						btnEliminarGrado.setEnabled(true);
					}
					textPaneArbol.setText(p.mostrarRotuloPorNivel());
					
					
					mostrarRec();
					reestablecerOpSalida();
					
				}catch(StringIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un caracter");
				
				}
		}
			

			
		});
		btnAgregarNodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgregarNodo.setToolTipText("Agrega un nodo al \u00E1rbol");
		frmTreeGenerator.getContentPane().add(btnAgregarNodo);
		
		btnEliminarNodo = new JButton("Eliminar nodo...");
		btnEliminarNodo.setToolTipText("Elimina un nodo a trav\u00E9s de un r\u00F3tulo ingresado");
		btnEliminarNodo.setMnemonic('e');
		btnEliminarNodo.setBounds(10, 113, 144, 29);
		btnEliminarNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s = JOptionPane.showInputDialog("Ingrese el rótulo que desea eliminar");
					Character c = s.charAt(0);
					String rec = p.mostrarRotuloPreOrden();
					
					if(rec.charAt(3) == c) {
						if(p.obtenerCamino(c).charAt(6) == '0') 
							p.eliminarNodo(c);
						else if(p.obtenerCamino(c).charAt(6) == '1') 
								p.eliminarNodo(c);
							else
								JOptionPane.showMessageDialog(frmTreeGenerator,"No se puede eliminar la raíz si tiene más de un hijo");
					}else 
					p.eliminarNodo(c);
					if(p.mostrarRotuloPreOrden().length() <= 0) {
						btnEliminarNodo.setEnabled(false);
						btnEliminarGrado.setEnabled(false);
						btnAgregarNodo.setEnabled(false);	
					}
					textPaneArbol.setText(p.mostrarRotuloPorNivel());
					mostrarRec();
					reestablecerOpSalida();
				}catch(StringIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un caracter");
				}
			}
		});
		btnEliminarNodo.setEnabled(false);
		btnEliminarNodo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTreeGenerator.getContentPane().add(btnEliminarNodo);
		
		
		
		btnEliminarGrado = new JButton("Eliminar por grado...");
		btnEliminarGrado.setMnemonic('g');
		btnEliminarGrado.setBounds(10, 153, 144, 29);
		btnEliminarGrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s = JOptionPane.showInputDialog("Ingrese el grado que desea eliminar");
					char ch = s.charAt(0);
					String num = ""+ch;
					Integer n = Integer.parseInt(num);
					p.eliminarNodosGradoK(n);
					if(p.mostrarRotuloPreOrden().length() <= 0) {
						btnEliminarNodo.setEnabled(false);
						btnEliminarGrado.setEnabled(false);
						btnAgregarNodo.setEnabled(false);
					}
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un número entero");
				}catch(StringIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un caracter");
				}
				
				textPaneArbol.setText(p.mostrarRotuloPorNivel());
				mostrarRec();
				reestablecerOpSalida();
			}
		});
		btnEliminarGrado.setEnabled(false);
		btnEliminarGrado.setToolTipText("Elimina del \u00E1rbol todas las posiciones con el grado ingresado");
		btnEliminarGrado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTreeGenerator.getContentPane().add(btnEliminarGrado);
		
		
		btnObtenerGrados = new JButton("Obtener grados");
		btnObtenerGrados.setMnemonic('o');
		btnObtenerGrados.setBounds(10, 230, 144, 29);
		btnObtenerGrados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null) {
					textAreaOperaciones.setText(p.obtenerGrados());
					lblOperaciones.setText("Grados del árbol:");
				}
				mostrarRec();
			}
		});
		btnObtenerGrados.setEnabled(false);
		btnObtenerGrados.setToolTipText("Muestra todos los grados y r\u00F3tulos del \u00E1rbol");
		btnObtenerGrados.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTreeGenerator.getContentPane().add(btnObtenerGrados);
		
		btnGradoArbol = new JButton("Grado del \u00E1rbol");
		btnGradoArbol.setMnemonic('t');
		btnGradoArbol.setBounds(10, 270, 144, 29);
		btnGradoArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null) {
					textAreaOperaciones.setText(p.obtenerGradoArbol());
					lblOperaciones.setText("Grado del árbol:");
				}
				mostrarRec();
			}
		});
		btnGradoArbol.setEnabled(false);
		btnGradoArbol.setToolTipText("Muestra el mayor grado del \u00E1rbol");
		btnGradoArbol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTreeGenerator.getContentPane().add(btnGradoArbol);
		
		btnCamino = new JButton("Obtener camino...");
		btnCamino.setMnemonic('r');
		btnCamino.setBounds(10, 310, 144, 29);
		btnCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null) {
					try {
						String s = JOptionPane.showInputDialog("Ingrese el rótulo");
						Character c = s.charAt(0);
						String camino = p.obtenerCamino(c);
						if(camino == "")
							camino = "El rótulo ingresado no existe en el árbol";
						textAreaOperaciones.setText(camino);
						lblOperaciones.setText("Camino hasta "+c+":");
					}catch(StringIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(frmTreeGenerator,"Debe ingresar un caracter");
						}
				}
				mostrarRec();
			}
		});
		btnCamino.setEnabled(false);
		btnCamino.setToolTipText("Muestra el camino de la ra\u00EDz hasta el r\u00F3tulo ingresado");
		btnCamino.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frmTreeGenerator.getContentPane().add(btnCamino);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(187, 11, 594, 201);
		frmTreeGenerator.getContentPane().add(panel);
		panel.setLayout(null);
		
		textPaneArbol = new JTextPane();
		textPaneArbol.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textPaneArbol.setEditable(false);
		
		textPaneArbol.setBounds(10, 11, 574, 179);
		panel.add(textPaneArbol);
		
		lblTodasOp = new JLabel("Operaciones:");
		lblTodasOp.setBounds(10, 205, 113, 14);
		frmTreeGenerator.getContentPane().add(lblTodasOp);
		
		spOperaciones = new JScrollPane();
		spOperaciones.setBounds(197, 319, 573, 47);
		frmTreeGenerator.getContentPane().add(spOperaciones);
		
		textAreaOperaciones = new JTextArea();
		spOperaciones.setViewportView(textAreaOperaciones);
		textAreaOperaciones.setEditable(false);
		
		lblOperaciones = new JLabel("Realice una operación...");
		lblOperaciones.setBounds(196, 299, 160, 14);
		frmTreeGenerator.getContentPane().add(lblOperaciones);
		lblOperaciones.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 248, 573, 41);
		frmTreeGenerator.getContentPane().add(scrollPane);
		
		textAreaRecorridos = new JTextArea();
		textAreaRecorridos.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane.setViewportView(textAreaRecorridos);
		textAreaRecorridos.setEditable(false);
		
		lblMostrarRec = new JLabel("Seleccione el recorrido a mostrar:");
		lblMostrarRec.setBounds(197, 226, 160, 14);
		frmTreeGenerator.getContentPane().add(lblMostrarRec);
		lblMostrarRec.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		tglbtnPre = new JToggleButton("Pre orden");
		tglbtnPre.setMnemonic('q');
		tglbtnPre.setBounds(367, 223, 101, 19);
		frmTreeGenerator.getContentPane().add(tglbtnPre);
		tglbtnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null)
					textAreaRecorridos.setText(p.mostrarRotuloPreOrden());
				tglbtnPost.setSelected(false);
				tglbtnPorNivel.setSelected(false);
			}
		});
		tglbtnPre.setToolTipText("Muestra el \u00E1rbol usando recorrido pre orden");
		tglbtnPre.setSelected(true);
		tglbtnPre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		tglbtnPost = new JToggleButton("Post orden");
		tglbtnPost.setMnemonic('w');
		tglbtnPost.setBounds(478, 223, 101, 19);
		frmTreeGenerator.getContentPane().add(tglbtnPost);
		tglbtnPost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null)
					textAreaRecorridos.setText(p.mostrarRotuloPostOrden());
				tglbtnPre.setSelected(false);
				tglbtnPorNivel.setSelected(false);
			}
		});
		tglbtnPost.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tglbtnPost.setToolTipText("Muestra el \u00E1rbol usando recorrido post orden");
		
		tglbtnPorNivel = new JToggleButton("Por niveles");
		tglbtnPorNivel.setMnemonic('e');
		tglbtnPorNivel.setBounds(589, 223, 101, 19);
		frmTreeGenerator.getContentPane().add(tglbtnPorNivel);
		tglbtnPorNivel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(p != null)
					textAreaRecorridos.setText(mostrarRotuloPorNivelCorrido());
				tglbtnPre.setSelected(false);
				tglbtnPost.setSelected(false);
			}
		});
		tglbtnPorNivel.setToolTipText("Muestra el \u00E1rbol usando recorrido por niveles");
		tglbtnPorNivel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
	}

	protected void reestablecerOpSalida() {
		lblOperaciones.setText("Realice una operación...");
		textAreaOperaciones.setText("");
	}

	protected void mostrarRec() {
		String s = null;
		if(tglbtnPre.isSelected())
			s = p.mostrarRotuloPreOrden();
		else if(tglbtnPost.isSelected())
				s = p.mostrarRotuloPostOrden();
			else if(tglbtnPorNivel.isSelected())
					s = mostrarRotuloPorNivelCorrido();
		textAreaRecorridos.setText(s);
		
	}
	protected String mostrarRotuloPorNivelCorrido() {
		String s = "Nivel 1:";
		int cont = 2;
		String aux = p.mostrarRotuloPorNivel();
		String nivel = p.mostrarRotuloPorNivel();
		int longi = nivel.length()-1;
		nivel = "";
		for(int i = 0; i < longi; i++)
			nivel = nivel +" "+aux.charAt(i);
		for(int i = 0; i < nivel.length(); i++)
			if(nivel.charAt(i) != '\n' && nivel.charAt(i) != ' ')
				s = s+"  "+nivel.charAt(i);
			else if(nivel.charAt(i) == '\n') {
					s = s+nivel.charAt(i)+"Nivel "+cont+":";
					cont++;
			}
		return s;
	}
}