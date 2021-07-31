package io;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import dames.*;


public class GUI extends JFrame implements ActionListener {
	
	private static final Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	
	private Taulell t;
	
    private ImageIcon fitxaNegra = createImageIcon("negra2.jpeg");
    private ImageIcon fitxaBlanca = createImageIcon("blanca.jpeg");
    private ImageIcon casellaBuida = createImageIcon("buida.jpeg");
    

		

	//CONSTRUCTOR
	public GUI(Taulell t) {
		
		this.t = t;
		this.setLayout(new GridLayout(8,8));	

		//to close the window the user must use the "close" button in the bottom right corner.
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		fitxes();
		this.pack();
		this.setVisible(true);
	}
	
	private void fitxes() {
		JButton b;
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				
				if (i+j%2 == 0) {
					b = new JButton(" ", fitxaNegra);
				}
				else b = new JButton(fitxaBlanca);
				


				this.add(b);
				}
			}
	}
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	//methods for CREATING COMPONENTS -------------------------------------------------------------------
	
	//creates and returns a new JTextArea of size (h, v) and sets it editable based on the parameter "editable".
	private JTextArea createTextArea(int h, int v, boolean editable) {
		JTextArea textArea = new JTextArea(h, v);
		textArea.setFont(f);
		textArea.setEditable(editable);
		return textArea;
	}
		
	/*creates and returns a new JCheckBox with label s and sets whether 
	  it is initially selected based on the parameter "initiallySelected". */
	private JCheckBox createCheckBox(String s, boolean initiallySelected) {
		JCheckBox checkBox = new JCheckBox(s, initiallySelected);
		checkBox.setFont(f);
		return checkBox;
	}
	
	/*creates and returns a new JButton with label s and adds an actionListener to it. */
	private JButton createButton(String s) {
		JButton button = new JButton(s);
		button.setSize(new Dimension(50, 50));
		button.setFont(f);
		button.addActionListener(this);
		return button;
	}
	
	/*creates and returns a new JLabel with label s. */
	private JLabel createLabel (String s) {
		JLabel label= new JLabel(s);
		label.setFont(f);
		label.setOpaque(true);
		return label;
	}
	
	//creates and returns a new JTextField of size "size" and sets it editable based on the parameter "editable".
	private JTextField createTextField(int size, boolean editable) {
		JTextField textField = new JTextField(size);
		textField.setFont(f);
		textField.setEditable(editable);
		return textField;
	}
	
	private static ImageIcon createImageIcon(String path) {
	    return new ImageIcon(path);
	}
	
	//-----------------------------------------------------------------------------------------------------
	
}
			
