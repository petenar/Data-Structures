// **********************************************************************************
// Class Name: CountriesLinkedList
// Purpose: Creates a linked list of Country objects.
// Date Created: 10/17/2022
// Author: Petr Bowles
// **********************************************************************************

package ds_ass04b1_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class CountryStack
{
    // Global Data Items
    protected CountryNode nTop, nBottom;
    protected int sLength;
    
    // Constructor
    public CountryStack() 
    {
        nTop = nBottom = null;
        sLength = 0;
    } // End of Constructor
    
    // The push method
    public void push(Country thisCountry)
    {
        CountryNode newNode = new CountryNode();
        newNode.nInfo.modifyMe(thisCountry);
        newNode.nNext = nTop;
        nTop = newNode; // First.modifyMe(thisCountry);
        sLength++;
        if  (sLength == 1)nBottom = nTop; 
    } // End of push method
    
    // The pop method
    public Country pop()
    {
        CountryNode nCurrent = nTop;
        CountryNode nCurrentCopy = new CountryNode();
        nCurrentCopy.modifyMe(nCurrent);
        nTop = nTop.nNext;
        destroyMe(nCurrent); sLength--;
        return nCurrentCopy.nInfo;
    } // End of pop method
  
    // Node Modification method
    public void modifyMe(int pos, Country thisCountry)
    {
        // Find the modification point and modify the node there.
        int x = sLength;
        CountryNode nCurrent = nTop;
        while ((nCurrent.nNext != null)&&(x > pos)){nCurrent = nCurrent.nNext; x--;}
        if (x == pos)nCurrent.nInfo.modifyMe(thisCountry);
    } // End of Node Modification method
    
    // The getSize method
    public int getSize()
    {return sLength;}
        
    // The clearList method
    public void clearStack()
    {
        CountryNode nCurrent = nTop;
        CountryNode nCurrentCopy;
        while (nCurrent != null){nCurrentCopy = nCurrent; nCurrent = nCurrent.nNext; destroyMe(nCurrentCopy);}
        nTop = nBottom = null; sLength = 0;
    } // End of the clearList method
    
    // The getInfo method
    public Country getInfo(int pos)
    {
      // Find the retrieval point in the list and return the information at that location.
        int x = 1;
        Country [] tempList = new Country[sLength];
        tempList = toArray(); 
        return tempList[pos]; 
    } // End of getInfo method
    
    // The toArray method
    public Country[] toArray()
    {
        // Copy the information in each node to an array and return the array.
        int x = 1;
        Country[] countryArray = new Country[sLength];
        CountryNode nCurrent = nTop;
        for (x = 1; ((nCurrent!= null) && (x <= sLength)); x++)
        {
            countryArray[x-1] = new Country(); 
            countryArray[x-1].modifyMe(nCurrent.nInfo);
            nCurrent = nCurrent.nNext;
        }
        return countryArray;
    } // End of toArray method
    
    // Cleanup method
    protected void finalize()throws java.lang.Throwable
    { destroyMe(this); }
   
    // Item Destruction method 
    protected void destroyMe(Object thisObj)
    {
      thisObj = null;
      System.gc(); // Call the Java garbage collector
    } // End of Item Destruction method 
    
} // End of PatronsCrudeLinkedList class
