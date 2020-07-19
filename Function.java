package A3;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.* ;
/**
 * The class <b>Function</b> launches the Calculator
 *
 * @author Feng_Weihan, University of Ottawa
 */
public class Function implements ActionListener{
	//storing operands 
    private Stack<Double> operandStack ;
    //storing operators
    private Stack<String> operatorStack ;
    //receive all symbols input 
    private Stack<String> stack;
	private double result = 0;
	/**
	 * <b>the purpose </b> of this class implements the all funtions of  Calculator. 
	 * Support double operations. The operations contain plus , subtract,multiply ,devid
	 * ,model and power .
	 * The constructor has no parameter.  
	 * the main task instances tree stacks: 
	 * operandStack, storing operands 
	 * operatorStack storing operators
	 * stack receive all symbols input 
	 */
	public Function() {
    	operandStack = new Stack<>(); 
    	operatorStack= new Stack<>(); 
    	stack = new Stack<>(); 
    }
    /*
     * input pamameters ((double, double,String)
     * the operations add ,sub,div,mul, power and model
     * return double
     * note: % operation for integer 
     * */
    private double arith(double op1, double op2,String operator) {
    	switch (operator) {
    	case "+" : return (op1+op2);
    	case "-" : return (op1-op2);
    	case "*": return (op1*op2);
    	case "/": return (op1/op2);
    	case "%": return ((int)op1%(int)op2);
    	case "^": return  (double)Math.pow(op1,op2);
       }
    	return 0.0;
    }

    /*input(nothing);
	 *return final result; 
	*/
	public double finalResult() {return result;}
	/*input no parameter
	 *the arithmetic expression in stack.
	 *this method implements the all functions
	 *the method analysis the arithmetic expression .
	 * Analysing the operand and operators are processing
	 * simultaneously  .
	 * if reading number char, transform it into number  and 
	 * form a double number ,then push it operandStack;     
	 * the operator is encountered, push it to operatorStack
	 * or compare it with the top operator of operatorStack
	 * if priority is higher, operating and result is stacked
	 * into operandStack.
	 * */
	private void calculator() {
	   //take the length of arithmetic expression  	
	   int s = stack.size();
	   //declare  tree operands  
	   //rspectively store first operand ,second operand 
	   //and operating result.
	   double opd1,opd2,result;
	   //this opd is for forming the entire double number one by one bit.
	   double opd=0;
	   //operator symbol
	   String optr="";
	   //it is for forming operands. 
	   boolean firstNum = false;
	   //it is for forming the decimal parts.
	   double decimal =1 ;
	 //it is for forming the decimal parts.
	   boolean decimalPart = false;
	   // analusing the arithmetic expression one by one bit
	   for(int i=0;i<s;i++) {
		   switch (stack.get(i)) {
		   //add and sub have same priority level;
		   //push opd formed into operandStack; firstNumber =0
		   //finished the combining of a operand.
		     case "-":
		     case "+": if (firstNum) { operandStack.push(opd);opd=0; firstNum = false;decimalPart = false;}
		   //when operatorStack.isEmpty() is false,take second oprand and then
		     // take first perand and the computer ,result pushed into operandStack.
		               while(!operatorStack.isEmpty()) { 
		            	  if(!operandStack.isEmpty())  {
		            		  opd2=(double)operandStack.pop();
		            	  }else {
		            		  showInfo("Expression Error!"); 
		            		  return;}
		            	  if(!operandStack.isEmpty())  {
		            		  opd1=(double)operandStack.pop();
		            	  }else {showInfo("Expression Error!"); 
		            	      return;}		            	  
	
		            	  optr= operatorStack.pop();
		            	  operandStack.push(arith(opd1,opd2,optr));
		                   }
		               operatorStack.push(stack.get(i));
		               break;
		   //"*","/" and "%"  have same priority level;
		     case "*":
		     case "/":
		     case "%": 
				   //push opd formed into operandStack; firstNumber =0
				   //finished the combining of a operand.
		    	 if (firstNum) { operandStack.push(opd);opd=0; firstNum = false;decimalPart = false;}
		    	  //when operatorStack.isEmpty() is false,take second oprand and then
		    	  // take first perand and the computer ,result pushed into operandStack.		    	               
                      while (!(operatorStack.isEmpty())&&("*/%^".contains(operatorStack.peek()))) { 
           	             if(!operandStack.isEmpty())  {opd2=(double)operandStack.pop();
           	             }else {showInfo("Expression Error!"); return;}
           	             if(!operandStack.isEmpty())  {opd1=(double)operandStack.pop();
           	             }else {showInfo("Expression Error!"); return;}		            	  
                       
           	             optr= operatorStack.pop();
           	             //process the case of devid as zero 
           	             if (("/%".contains(optr))&&(opd2==0)) {
           	            	  String st = toString(stack)+" 0 as divisor !";
          		        	  CalculatorPanel.setText(st);
          		   	          operandStack.clear();
          			          operatorStack.clear(); 
          			          stack.clear();
          			          return;
           	             }
           	             operandStack.push(arith(opd1,opd2,optr));
                        }
                      
                      operatorStack.push(stack.get(i));
                      break;
           		   //"^"  has the highest priority level;
		     case "^":
				   //push opd formed into operandStack; firstNumber =0
				   //finished the combining of a operand.
		    	 if (firstNum) { operandStack.push(opd);opd=0; firstNum = false;decimalPart = false;}
			    	  //when operatorStack.isEmpty() is false,take second oprand and then
			    	  // take first perand and the computer ,result pushed into operandStack.		    	               	    	         
                     while (!(operatorStack.isEmpty())&&("^".equals(operatorStack.peek()))) { 
       	                 if(!operandStack.isEmpty())  {opd2=(double)operandStack.pop();
       	                 }else {showInfo("Expression Error!"); return;}
       	                 if(!operandStack.isEmpty())  {opd1=(double)operandStack.pop();
       	                 }else {showInfo("Expression Error!"); return;}		            	  

       	                 optr= operatorStack.pop();
       	                 operandStack.push(arith(opd1,opd2,optr));
                        }
                     operatorStack.push(stack.get(i));
                     break;	
                     //if encounted ".", decimalPart=true. 
                     //next combine the decimal parts . 
                     //first number *0.1 ,add the original integer part.
                     //second number * 0.01 ....   ;    decimal=0.1 ,
		     case ".": if (!decimalPart) {decimalPart=true; decimal=0.1 ;break;
		                   }else {showInfo("double '.'");break;}
		     default:
		    	     if (firstNum) {
		    	       	if (!decimalPart) 
		    	            opd = opd*10+Integer.parseInt(stack.get(i));
		    	       	else {
		    	       		opd = opd+Integer.parseInt(stack.get(i))*decimal;
		    	       		decimal=decimal*0.1;
		    	       	}
		    	     }else {
			    	   opd = Integer.parseInt(stack.get(i));
			    	   firstNum = true;
		    	     }  
		   }
          } 
	     if (firstNum) {operandStack.push(opd);opd=0;firstNum = false;}
		 while (!operatorStack.isEmpty()) {
                if(!operandStack.isEmpty())  {opd2=(double)operandStack.pop();
                }else {showInfo("Expression Error!"); return;}
                if(!operandStack.isEmpty())  {opd1=(double)operandStack.pop();
                }else {showInfo("Expression Error!"); return;}		            	  

                optr= operatorStack.pop();
  	             if (("/%".contains(optr))&&(opd2==0)) {
  	            	  showInfo(" 0 as divisor !");		        	  
 		   	          operandStack.clear();
 			          operatorStack.clear(); 
 			          stack.clear();
 			          return;
  	             }                
                operandStack.push(arith(opd1,opd2,optr));
		 }
		 // show the result in TextField and clear three stacks 
		 /* when last result is not clear, it becomes  as the operand next time.
		 * So it has to be transformed into stack (store original arithmetic 
		 * expresiion.
		 * when last result is cleared, it has to clear the screen on textField
		 * and tree stacks is also cleared (including operandStack ,operatorStack
		 * and stack
		 * 
		 * */ 
		 if (!operandStack.isEmpty()) {
			 String st=""+operandStack.pop();
			 showInfo(st);
		     stack.clear();
		     //the operand left from operandStack into stack as next operand
		     for(int i=0;i<st.length();i++) 
		     { String str =""+st.charAt(i);
		    	 stack.push(str);		}     
		 }else 	
		// clear tree stack;	 
		 stack.clear();
	     operandStack.clear();
	     operatorStack.clear(); 
	    }
	private void showInfo(String str) {
		CalculatorPanel.setText("");
		CalculatorPanel.setText(str);
	}
	//the process of Action events which is from
	//class CalculatorPenal
    public void actionPerformed(ActionEvent e) {
    	// An event happens
    	//pick up this event
    	String str= e.getActionCommand();

    	switch(str) { 
    	
           //del a bit; pop a top from stack;
    	   // clear the textField on the screen
    	  // show new expression;
    	  case "del":{if (!stack.isEmpty()) 
    		               stack.pop() ;
    	                   CalculatorPanel.setText(toString(stack));
    	                   break;}
    	  //clear current expression
    	  //clear the show on the screen
    	  case "C":  {if (!stack.isEmpty()) 
    		               stack.clear(); 
    	                   CalculatorPanel.setText("");
    	                   break;}
    	  //when encounted "=", finished the input of arithmetic expresion
    	  //next step, analysis on expression and calculate it.
    	  case "=": {  
    		        	  calculator();
    		              break;}
    	  //push all  numbers and "." and all operators(including +,-,*,/,^ and %)into stack
    	  // forming the arithmetic expression
    	  default: stack.push(str);
    	           CalculatorPanel.setText(toString(stack));
    	  }
    	str="";
    }
    //combine an arithmetic expression into the string to process
    private String toString(Stack<String> stack) {
    	String st="";
    	for(int i=0;i<stack.size();i++) {
    		st += stack.get(i);
    	}
    	return st;
    }
    //this main program to debug for convenience
    public static void main(String[] args) {
    	CalculatorPanel calculator= new CalculatorPanel();
      
      }	
}
