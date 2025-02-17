// *********************************************************************************
// ds_ass05b_bowlesp.java                                                                 
// Created on 11/1/2022                                              
// Allows for the definition of Clinic Patient objects.                             
// Author: Petr Bowles                                                              
// *********************************************************************************

package ds_ass05b_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class clinicNode 
{
    // Global Data Items
    protected clinicPatient nInfo;
    protected clinicNode nNext;
    
    // Constructor
    public clinicNode() 
    {
        nInfo = new clinicPatient();
        nNext = null;
    } // End of constructor
    
    // modifyMe method
    public void modifyMe(clinicNode thisNode)
    {
        nInfo.modifyMe(thisNode.nInfo);
        nNext = thisNode.nNext;
    } // End of modifyMe method
    
    // inputData method
    public void inputData(int x)
    {
        nInfo.inputData(x);
        nNext = null;
    } // End of inputData method
    
     // printMe method
    public String printMe()
    { return nInfo.printMe();} // End of printMe method
    
} // End of PatronNode class
