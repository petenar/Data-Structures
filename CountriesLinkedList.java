// **********************************************************************************
// Class Name: CountriesLinkedList
// Purpose: Creates a linked list of Country objects.
// Date Created: 10/8/2022
// Author: Petr Bowles
// **********************************************************************************

package ds_ass03b_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class CountriesLinkedList 
{
    // Global Data Items
    protected CountryNode nFirst, nLast;
    protected int length;
    
    // Constructor
    public CountriesLinkedList() 
    {
        nFirst = nLast = null;
        length = 0;
    } // End of Constructor
    
    // The addFirst method
    public void addFirst(Country thisCountry)
    {
        CountryNode newNode = new CountryNode();
        newNode.nInfo.modifyMe(thisCountry);
        newNode.nNext = nFirst;
        nFirst = newNode; // nFirst.modifyMe(thisPatron);
        length++;
        if  (length == 1)nLast = nFirst; // nLast.modifyMe(nFirst);
    } // End of addFirst method
    
     // The addLast method
    public void addLast(Country thisCountry)
    {
        CountryNode newNode = new CountryNode();
        newNode.nInfo.modifyMe(thisCountry);
        nLast.nNext = newNode;
        newNode.nNext = null;
        nLast = newNode;
        length++;
        if  (length == 1)nFirst = nLast;
    } // End of addLast method
    
    // The addMiddle method
    public void addMiddle(int pos, Country thisCountry)
    {
        int x;
        CountryNode nMark2 = nFirst;
        CountryNode nMark1 = null; CountryNode newNode;
        
        // Insert the patron at the appropriate position
        if (pos == 1) addFirst(thisCountry);
        else // The insertion will be made between nMark1 and nMark2.
        {   
            for (x = 1; x < pos; x++)
            {if (nMark2.nNext == null)break; else {nMark1 = nMark2; nMark2 = nMark2.nNext;}}
            newNode = new CountryNode();
            newNode.nInfo.modifyMe(thisCountry);
            newNode.nNext = nMark2;
            nMark1.nNext = newNode;
            length++;
        } 
    } // End of addMiddle method
    
     // The removeFirst method
    public void removeFirst()
    {
        CountryNode nCurrent = nFirst;
        nFirst = nFirst.nNext;
        destroyMe(nCurrent); length--;
    } // End of removeFirst method
    
     // The removeLast method
    public void removeLast()
    {
        CountryNode nCurrent, nPenultimate;
        if (nFirst == nLast){nCurrent = nLast; nFirst = nLast = null; destroyMe(nCurrent);}
        else // Find the penultimate node and delete the node after it
        {
           nPenultimate = nFirst;
           while (nPenultimate.nNext != nLast) nPenultimate = nPenultimate.nNext;
           nCurrent = nLast;
           nPenultimate.nNext = null;
           destroyMe(nCurrent);
           nLast = nPenultimate;
        }
        length--;
    } // End of removeLast method
    
      // The removeMiddle method
    public void removeMiddle(int pos)
    {
       // Find the deletion point and mark it as nCurrent.
        int x = 1;
        CountryNode nCurrent = nFirst;
        CountryNode prevCurrent = nFirst;
        
        // Remove the node at deletion point
        if (pos == 1) removeFirst();
        if ((pos == length) && (nFirst != nLast)) removeLast();
      
        if ((pos != 1) && (pos != length)) 
        {
            while ((nCurrent.nNext != null)&&(x < pos)){prevCurrent = nCurrent; nCurrent = nCurrent.nNext; x++;}
            prevCurrent.nNext = nCurrent.nNext;
            destroyMe(nCurrent);
            if (prevCurrent.nNext == null) nLast = prevCurrent;
            length--;
        } 
    } // End of removeMiddle method
    
    // Node Modification method
    public void modifyMe(int pos, Country thisCountry)
    {
        // Find the modification point and modify the node there.
        int x = 1;
        CountryNode nCurrent = nFirst;
        while ((nCurrent.nNext != null)&&(x < pos)){nCurrent = nCurrent.nNext; x++;}
        nCurrent.nInfo.modifyMe(thisCountry);
    } // End of Node Modification method
    
    // The getSize method
    public int getSize()
    {return length;}
        
    // The clearList method
    public void clearList()
    {
        CountryNode nCurrent = nFirst;
        CountryNode prevCurrent = nFirst;
        while (nCurrent != null){prevCurrent = nCurrent; nCurrent = nCurrent.nNext; destroyMe(prevCurrent);}
        nFirst = nLast = null; length = 0;
    } // End of the clearList method
    
    // The getInfo method
    public Country getInfo(int pos)
    {
      // Find the retrieval point in the list and return the information at that location.
        int x = 1;
        Country [] tempList = new Country[length];
        tempList = toArray(); 
        return tempList[pos]; 
        //PatronNode nCurrent = nFirst;
        //while ((nCurrent.nNext != null)&&(x < pos)){nCurrent = nCurrent.nNext; x++;}
        //return nCurrent.nInfo;
    } // End of getInfo method
    
    // The toArray method
    public Country[] toArray()
    {
        // Copy the information in each node to an array and return the array.
        int x = 1;
        Country[] countryArray = new Country[length];
        CountryNode nCurrent = nFirst;
        for (x = 1; ((nCurrent!= null) && (x <= length)); x++)
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
