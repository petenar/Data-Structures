// *********************************************************************************
// ds_ass05b_bowlesp.java                                                                 
// Created on 11/1/2022                                              
// Allows for the definition of Clinic Patient objects.                             
// Author: Petr Bowles                                                              
// *********************************************************************************

package ds_ass05b_bowlesp;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class clinicQueue 
{
    // Global Data Items
    protected clinicNode nRear, nFront;
    protected int length;
    
    // Constructor
    public clinicQueue() 
    {
        nRear = nFront = null;
        length = 0;
    } // End of Constructor
    
    // The addRear method
    public void addRear(clinicPatient thisPatient)
    {
        clinicNode newNode = new clinicNode();
        newNode.nInfo.modifyMe(thisPatient);
        newNode.nNext = nRear;
        nRear = newNode; // nRear.modifyMe(thisPatient);
        length++;
        if  (length == 1)nFront = nRear; // nFront.modifyMe(nRear);
    } // End of addRear method
    
    // The removeFront method
    public clinicPatient removeFront()
    {
        clinicNode nCurrent, nPenultimate, nCurrentCopy;
        nCurrentCopy = new clinicNode();
        if (nRear == nFront){
            nCurrent = nFront; 
            nCurrentCopy.modifyMe(nCurrent);
            nRear = nFront = null; 
            destroyMe(nCurrent);
        }
        else // Find the penultimate node and delete the node after it
        {
           nPenultimate = nRear;
           while (nPenultimate.nNext != nFront) nPenultimate = nPenultimate.nNext;
           nCurrent = nFront;
           nPenultimate.nNext = null;
           nCurrentCopy.modifyMe(nCurrent);
           destroyMe(nCurrent);
           nFront = nPenultimate;
        }
        length--;
        return nCurrentCopy.nInfo;
    } // End of removeFront method
    
    // Node Modification method
    public void modifyMe(int pos, clinicPatient thisPatient)
    {
        // Find the modification point and modify the node there.
        int x = 1;
        clinicNode nCurrent = nRear;
        while ((nCurrent.nNext != null)&&(x < pos)){nCurrent = nCurrent.nNext; x++;}
        nCurrent.nInfo.modifyMe(thisPatient);
    } // End of Node Modification method
    
    // The getSize method
    public int getSize()
    {
        return length;
    }
        
    // The clearList method
    public void clearQueue()
    {
        clinicNode nCurrent = nRear;
        clinicNode prevCurrent = nRear;
        while (nCurrent != null){prevCurrent = nCurrent; nCurrent = nCurrent.nNext; destroyMe(prevCurrent);}
        nRear = nFront = null; length = 0;
    } // End of the clearList method
    
     // The getInfo method
    public clinicPatient getInfo(int pos)
    {
      // Find the retrieval point in the list and return the information at that location.
      clinicPatient[] thisList = toArray();
      return thisList[pos];
    } // End of getInfo method
    
    // The toArray method
    public clinicPatient[] toArray()
    {
        // Copy the information in each node to an array and return the array.
        int x = length;
        int prevLength = length;

        clinicPatient[] patientArray = new clinicPatient[length];
        clinicNode nCurrent = nRear;
        for (x = prevLength; ((nCurrent!= null) && (x >= 1)); x--)
        {
            patientArray[x-1] = new clinicPatient(); 
            patientArray[x-1].modifyMe(removeFront());
        }

        // Reload the queue
        for (x = prevLength; ((nCurrent!= null) && (x >= 1)); x--)
        { addRear(patientArray[x-1]); }

        return patientArray;
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
    
} // End of PatronsCrudeQueue class