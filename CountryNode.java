// **********************************************************************************
// Class Name: CountryNode
// Purpose: Facilitates node linking for a linked list.
// Date Created: 10/8/2022
// Author: Petr Bowles
// **********************************************************************************

package ds_ass03b_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class CountryNode 
{
    // Global Data Items
    protected Country nInfo;
    protected CountryNode nNext;
    
    // Constructor
    public CountryNode() 
    {
        nInfo = new Country();
        nNext = null;
    } // End of constructor
    
    // modifyMe method
    public void modifyMe(CountryNode thisNode)
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
