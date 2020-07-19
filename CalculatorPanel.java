package A3;

/**
 * The class <b>CalculatorPanel</b> launches the Calculator
 *
 * @author Feng-Weihan, University of Ottawa
 */
import java.awt.*;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * <b>the purpose </b> of this class Creates the user interface of  Calculator. 
 * this calculator has 20 keys , 10 of which are number keys,
 * 6 of which are operator keys(+,-,*,/,^,%),the keys left are the decimal key,
 * clear key, back 1 bit key(del), clear key and equal key(=) respectively.  
 * The calculate support the double operation. 
 */
public class CalculatorPanel extends JFrame{
	// store number keys
	JButton [] numberKeys ;
	//the penal contains the number 
	JPanel numberPanel ;
	// show penal
	JPanel showPanel;
	//set text show penal
    static JTextField text;
    //the object of function
    Function fun = new Function();
    
    /*
     * This is constructor. Default paramiters ;
     * the width and height are 300 an 500 respectively. 
     * */
	public CalculatorPanel() {
		//set title
        this.setTitle("My Calculator");
        //set the width and height
        this.setSize(300, 500);
        //set layout of the calculate panel
        this.setLayout(new GridLayout(2,1,10,10));
        //show penal on screen
        setShowPanel();
        //add the penal layouted to JFrame locate at NORTH
        this.add(showPanel,BorderLayout.NORTH);
        //set number keys
        setNumberKeys();
        //locate the number at the SOUTH of penal.
        this.add(numberPanel,BorderLayout.SOUTH);

        
        this.setVisible(true);
	}
	//return final result
	public double finalR() {
		return fun.finalResult();
	}
	/*no paramiter
	 * show panel
	 * return nothing
	 * */
	private void setShowPanel() {
        showPanel = new JPanel();
        text = new  JTextField() ;

        showPanel.setPreferredSize(new Dimension(10, 300));
        showPanel.setLayout(new GridLayout(1,1));
        showPanel.setBackground(Color.white);
        text.setBounds(showPanel.getBounds());
        showPanel.add(text);         		
	}
	//show the text on textField
	//first clear field and then show the string; 
	public static final void setText(String str) {
		text.setText("");
		text.setText(str);
	}  
	// clear textField
	public static final void clearText(String str) {
		text.setText("");
	}
	//set number keys and other keys for name
	private void setNumberKeys() {
		numberKeys = new JButton[20];
		int k=9;
        for(int i=0;i<16;i++) {
        	if (i==3) { numberKeys[i]=new JButton("+");continue;};
        	if (i==7) {numberKeys[i]=new JButton("-");continue;};
        	if (i==11) {numberKeys[i]=new JButton("*");continue;};
           	if (i==12) {numberKeys[i]=new JButton(".");continue;};  
           	if (i==14) {numberKeys[i]=new JButton("C");continue;};
        	if (i==15) {numberKeys[i]=new JButton("/");continue;};
        	numberKeys[i]=new JButton(""+(k--));
        } 
    	
        numberPanel = new JPanel();    	
		numberPanel.setBackground(Color.cyan);
		numberPanel.setPreferredSize(new Dimension(250, 300));
		numberPanel.setLayout(new GridLayout(5,4,10,10));
		
	    numberKeys[16] = new JButton("="); 
	    numberKeys[17] = new JButton("del"); 	    
	    numberKeys[18] = new JButton("%"); 
	    numberKeys[19] = new JButton("^"); 
   // add the ActionListener and responded by the object of class function
	    for(int i=0;i<20;i++) {
     		numberPanel.add(numberKeys[i]);
           	numberKeys[i].setSize(30,30);
           	numberKeys[i].addActionListener(fun);
        }  	    
	}
}
