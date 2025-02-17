// *********************************************************************************
// ds_ass05b_bowlesp.java                                                                 
// Created on 11/1/2022                                              
// Allows for the definition of Clinic Patient objects.                             
// Author: Petr Bowles                                                              
// *********************************************************************************

package ds_ass05b_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class clinicPatient 
{
 // Define Data Items
    protected String pName,pPhone,pID;
   
 // Constructor
 public clinicPatient() 
 {
   pID = "";
   pName = "";
   pPhone = "000-000-000";

 } // End Constructor
  
 // Patron Modification Method
 public void modifyMe(clinicPatient thisPatient) 
 {
   pID = thisPatient.pID; 
   pName = thisPatient.pName; 
   pPhone = thisPatient.pPhone;
 } // End Patron Modification Method
 
     // Print Specification Method
 public String printMe()
 {
 String printString = "Patient's ID Number: " + pID + "\n" + "Name: " + pName + "\n" +
   "Telephone Number: " + pPhone + "\n";
  return printString;
 } // End of Print Specification Method
    
     // getPatronNumber Method
    public String getPatientNumber()
    {
      return pID;
    } // End of GetPAtronNumber Method
 
    
   //InputData Method
   public void inputData(int x)
   {
     String inputTele = "",inputNumber = "";
     String patientHeading = "Clinic Patient Information Entry";
     inputNumber = JOptionPane.showInputDialog(null, "Please Enter ID number for Patient " + x,patientHeading,JOptionPane.QUESTION_MESSAGE);
     while(!validateNumber(inputNumber)){
         JOptionPane.showMessageDialog(null, "ID Number must be numeric");
         inputNumber = JOptionPane.showInputDialog(null, "Please Enter ID number for Patient " + x,patientHeading,JOptionPane.QUESTION_MESSAGE);
     }
     pID = inputNumber;
     pName = JOptionPane.showInputDialog(null, "Please Enter Patient Name for Patient " + x, patientHeading, JOptionPane.QUESTION_MESSAGE);
     
     inputTele = JOptionPane.showInputDialog(null, "Please Enter Phone Number of Patient " + x, patientHeading, JOptionPane.QUESTION_MESSAGE);
     while(!validateTele(inputTele)){
         JOptionPane.showMessageDialog(null, "Telephone number is not in the required format");
         inputTele = JOptionPane.showInputDialog(null, "Please Enter Phone Number of Patient " + x, patientHeading, JOptionPane.QUESTION_MESSAGE);
     }
     pPhone = inputTele;
   } // End of inputData Method

   private boolean validateNumber(String thisID){
       boolean isValid = true;
       int x;
       for(x = 1; x <= thisID.length(); x++){
         if(!Character.isDigit(thisID.charAt(x-1))){isValid = false;}
       }
       return isValid;
   }
   
   private boolean validateTele(String thisTele)
   {
   boolean isValid = true;
   int x;
   for(x = 1; x <= 12; x++){
       switch(x){
           case 4:if(thisTele.charAt(x-1) != '-') {isValid = false;}
           case 8:if(thisTele.charAt(x-1) != '-') {isValid = false;}
           default:if(!Character.isDigit(thisTele.charAt(x-1))) {isValid = false;}
       }//end case  
   }//end for
   return isValid;
   }//end validate Tele
   
   
} // End of LibraryPatron
