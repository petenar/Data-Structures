// *********************************************************************************
// Country.java                                                                 
// Created on 10/2/2022                                                
// Allows for the definition of Country objects.                             
// Author Petr Bowles                                                              
// *********************************************************************************

package ds_ass03a_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class Country 
{
 // Define Data Items
 protected int cNumber;
 protected String cName;
 protected double cGNI, cPCI, cStandard;
 protected int cPopulation;
   
 // Constructor
 public Country() 
 {
   cNumber = 0; 
   cName = ""; 
   cGNI = 0;
   cPCI = 0;
   cPopulation = 0;
 } // End Constructor
 
 // Overloaded Constructor
 public Country(int thisNumber) 
 {
   cNumber = thisNumber; 
   cName = "No Name"; 
 } // End Overloaded Constructor
  
 // Patron Modification Method
 public void modifyMe(Country thisCountry) 
 {
   cNumber = thisCountry.cNumber; 
   cName = thisCountry.cName; 
   cGNI = thisCountry.cGNI;
   cPCI = thisCountry.cPCI;
   cPopulation = thisCountry.cPopulation;
 } // End Patron Modification Method
 
   //InputData Method
   public void inputData(int x)
   {
       
     String countryHeading = "Country Information Entry";
     String cNumberString = JOptionPane.showInputDialog(null, "Please Enter Country " + x + " Identification Number: ", countryHeading, JOptionPane.QUESTION_MESSAGE);
     cNumber = Integer.parseInt(cNumberString);
     cName = JOptionPane.showInputDialog(null, "Please Enter Country Name for Country " + x, countryHeading, JOptionPane.QUESTION_MESSAGE);
     cGNI = Double.parseDouble(JOptionPane.showInputDialog(null, "Please Enter Country's GNI for Country  " + x, countryHeading, JOptionPane.QUESTION_MESSAGE));
     cPopulation = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter Population of Country  " + x, countryHeading, JOptionPane.QUESTION_MESSAGE));
     cPCI = cGNI/cPopulation;
   } // End of inputData Method
   
    // Print Specification Method
    public String printMe()
    {
     String printString = "Country Number: " + cNumber + "\n" + "Name: " + cName + "\n" +
       "Gross National Income: " + cGNI + "\n" + "Population: " + cPopulation + "\n" + "Per Capita Income: " + cPCI + "\n" + "Standard Deviation: " + cStandard;
     return printString;
     } // End of Print Specification Method
    
    // getCountry Method
    public int getCountry()
    {
      return cNumber;
    } // End of getCountry Method
    
    public double getPCI(){//start of getpci
        return cPCI;
    }//end of getpci
    
    public double setPCI(double thisPCI){//start of setpci
        cPCI = thisPCI;
        return thisPCI;
    }//end of setpci
    
    // Cleanup method
    protected void finalize()throws java.lang.Throwable
    { destroyMe(this); }
   
    // Item Destruction method 
    protected void destroyMe(Object thisObj)
    {
      thisObj = null;
      System.gc(); // Call the Java garbage collector
    } // End of Item Destruction method 
} // End of Country