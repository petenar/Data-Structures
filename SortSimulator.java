// *********************************************************************************
// SortSimulator.java                                                                 
// Created on November 14, 2022                                               
// Sorts a list of Country objects.                             
// Author Petr Bowles                                                                 
// *********************************************************************************

package ds_ass06b_bowlesp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.MAX_VALUE;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class SortSimulator 
{
    public static Country[] countriesList;
    public static String inputString;
    public static final String countrySorterHEAD = "Country Sorter of Petr Bowles";
    public static final int DEFAULT_NUMBER = 0;
    static FileOutputStream CountryFileWriter;
    static ObjectOutputStream CountryObjectWriter;
    static final String CountryFileString = "JavaDataFiles" + File.separator + "Country.dat";
    static FileInputStream CountryFileReader;
    static ObjectInputStream CountryObjectReader;
    static Vector <Country> countryVector;
    static double noCompTime = 0;
    
    // Main Method
    public static void main(String[] args) 
    {
     // Declare Variables
     final String promptString = "1. Add Countries \n" + "2. List Countries \n" + "3. Merge-sort the list \n" 
              + "4. Bubble-sort the list \n" + "5. Quick-sort the list \n" + "6. Exchange Selection Sort the list \n" + "7. Straight Selection Sort the list \n" + "8. Create File \n"+ "9. Delete File \n"+"0. Exit Program \n";
     boolean exitTime = false;
     int userOption, i; Country[] copyList = countriesList = null;
 
     while (!exitTime) // While Not Exit-time
     {
        if(countryVector != null) {
        countriesList = vToArray(countryVector);
            }
       // Present the menu
       inputString = JOptionPane.showInputDialog(null, promptString, countrySorterHEAD, JOptionPane.QUESTION_MESSAGE);
       userOption = Integer.parseInt(inputString);
        
       // Respond to the request
       switch (userOption)
       {
         case 1: {inputCountries(); break;}
         case 2: {if (countriesList.length > 0) listCountries(countriesList,noCompTime); break;}
         case 3: {if (countriesList.length > 0) 
                  copyList = new Country[countriesList.length];
                  for (i = 1; i <= countriesList.length; i++) {copyList[i-1] = new Country(); copyList[i-1].modifyMe(countriesList[i-1]);}
                  mergeSortController(copyList); 
                  break;}
         case 4: {if (countriesList.length > 0) 
                  copyList = new Country[countriesList.length];
                  for (i = 1; i <= countriesList.length; i++) {copyList[i-1] = new Country(); copyList[i-1].modifyMe(countriesList[i-1]);}
                  bubbleSort(copyList); 
                  break;}
         case 5: {if (countriesList.length > 0)
                  copyList = new Country[countriesList.length];
                  for (i = 1; i <= countriesList.length; i++) {copyList[i-1] = new Country(); copyList[i-1].modifyMe(countriesList[i-1]);}
                  quickSortController(copyList);
                  break;}
         case 6: {if (countriesList.length > 0) 
                  copyList = new Country[countriesList.length];
                  for (i = 1; i <= countriesList.length; i++) {copyList[i-1] = new Country(); copyList[i-1].modifyMe(countriesList[i-1]);}
                  exchangeSelection(copyList); 
                  break;}
         case 7: {if (countriesList.length > 0) 
                  copyList = new Country[countriesList.length];
                  for (i = 1; i <= countriesList.length; i++) {copyList[i-1] = new Country(); copyList[i-1].modifyMe(countriesList[i-1]);}
                  straightSelection(copyList); 
                  break;}
         case 8: {createFile(); break;}
         case 9: {deleteFile(); break;}
        case 0: {exitTime = true; break;}
       }
     } // End-While Not Exit-time
    } // End main
    
    // inputCountries Method
   public static void inputCountries()
    {
        int loadSize, x, cLimit;
        Country[] thisList = null;
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Fills the countryList vector
        if(thisList != null)
            loadSize = thisList.length; 
        else
            loadSize = 0;
        countryVector = new Vector(); 
        countryVector.ensureCapacity(loadSize); 
        for (x =1; x <= loadSize; x++) countryVector.add(x-1, thisList[x-1]);
        
        cLimit = Integer.parseInt(JOptionPane.showInputDialog(null, "Input how many Countries you would like to record.", countrySorterHEAD, JOptionPane.QUESTION_MESSAGE));
        countryVector.ensureCapacity(loadSize + cLimit); 
        for(x=1; x <= cLimit; x++)
        {
            Country dummy = new Country();
            dummy.inputData(x);
            countryVector.add(dummy); 
        }//End of Loop
        //Writes an array from the vector
        thisList = new Country[countryVector.size()]; 
        thisList = vToArray(countryVector); 
        
        try
        {
            //Writes the array to file
            CountryFileWriter = new FileOutputStream(CountryFileString, true); // opens the file in APPEND node
            CountryObjectWriter = new ObjectOutputStream(CountryFileWriter); 

            CountryObjectWriter.writeObject(thisList); 
            CountryObjectWriter.flush();
            CountryObjectWriter.close();
        }
        //Catches Exceptions
        catch (IOException Ex1)
        { 
            JOptionPane.showMessageDialog(null, Ex1.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
        }
        catch (Exception Ex2)
        { 
            JOptionPane.showMessageDialog(null, Ex2.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
        }
    }//End of inputCountries
    public static void listCountries(Country[] thisList, double compTime)
    {
        String outString = "Members of the list are: \n";
        String compString = "Computation time is: " + compTime;
        int x;
        for (x = 1; x <= thisList.length; x++) { outString += thisList[x-1].printMe() + "\n"; }
        JOptionPane.showMessageDialog(null, outString + compString, countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE);
    } // End of listCountries Method
 
    // The mergeSortController method
    public static void mergeSortController(Country[] thisList)
    {
       double startTime, endTime,compTime;
       startTime = java.lang.System.nanoTime();
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
       thisList = mergeSortCountries(thisList, 0, thisList.length - 1);
       endTime = java.lang.System.nanoTime();
       compTime = (endTime - startTime);
       listCountries(thisList,compTime);
    } // End of mergeSortController method
    
    // The mergeSortCountries method
    public static Country[] mergeSortCountries(Country[] thisList, int first, int last)
    {
       int k, l, middle; 
       middle = (first + last)/2; 
       if (first < last)
       {    
           Country[] thisLeft = new Country[middle - first + 1];
           for (k = 0; k <= middle; k++) {thisLeft[k] = new Country(); thisLeft[k].modifyMe(thisList[k]);}
           thisLeft = mergeSortCountries(thisLeft, 0, middle);  
           
           Country[] thisRight = new Country[last - middle];
           for (k = 0, l = middle + 1; l <= last; k++, l++) {thisRight[k] = new Country(); thisRight[k].modifyMe(thisList[l]);}
           thisRight = mergeSortCountries(thisRight, 0, last - middle - 1);
           
           thisList = mergeLeftRight(thisList, thisLeft, thisRight);  
       }  
       return thisList;
    } // End of mergeSortCountries method
    
    // The mergeLeftRight method
    public static Country[] mergeLeftRight(Country[] thisList, Country[] leftList, Country[] rightList)
    {
       int x, y, z, first; first = 0; int last = leftList.length + rightList.length - 1;
         
       for (x = y = z = first; z <= last; x++, y++, z++)
       {
           if  (x >= leftList.length) thisList[z].modifyMe(rightList[y]);     
           else if  (y >= rightList.length) thisList[z].modifyMe(leftList[x]);  
           else // ((x  <  leftList.length) AND (y < rightList.length))
           {
                if  (leftList[x].getCountry() < rightList[y].getCountry()) {thisList[z].modifyMe(leftList[x]); y--;}
                else {thisList[z].modifyMe(rightList[y]); x--;} 
           }
           // z++; y++; x++;
       } // End of For-Each 
       return thisList; 
    } // End of mergeLeftRight method
    
    // Bubble Sort
    public static void bubbleSort(Country[] thisList)
    {
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
      Country dummyCountry = new Country();
      int x, pass;
      int thisLimit = thisList.length;
      boolean isSorted;
      double startTime, endTime,compTime;
      startTime = java.lang.System.nanoTime();
      
      // Bubble Sort Looping
      for (pass = 1; pass <=thisLimit; pass++)
      {
          isSorted = true;
          for (x =  1; (x <= (thisLimit - pass)); x++) // Inner loop
          {
              if (thisList[x-1].getCountry() > thisList[x].getCountry()) 
              {
                  dummyCountry.modifyMe(thisList[x-1]);
                  thisList[x-1].modifyMe(thisList[x]);
                  thisList[x].modifyMe(dummyCountry);
                  isSorted = false;
              }
          } // End of inner loop
          if (isSorted) break;
      } // End of outer loop
      endTime = java.lang.System.nanoTime();
      compTime = (endTime - startTime);
      listCountries(thisList,compTime); // Now display the sorted list
    } // End of bubbleSort
    public static void exchangeSelection(Country[] thisList)
    {
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
      Country dummyCountry = new Country();
      int counter1, counter2;
      int thisLimit = thisList.length;
      double startTime, endTime,compTime;
      startTime = java.lang.System.nanoTime();
      
      // exchangeSelection Looping
      for(counter1 = 1; counter1 <= thisLimit; counter1++){//exchangeSelection outer loop
         for(counter2 = (counter1 + 1); counter2 <= thisLimit; counter2 ++){//exchange selection inner loop
             if(thisList[counter1 - 1].getCountry() > thisList[counter2 - 1].getCountry()){//start of if
                 dummyCountry = thisList[counter1 - 1];
                 thisList[counter1 - 1] = thisList[counter2 - 1];
                 thisList[counter2 - 1] = dummyCountry;
             }//end of if
         }//end of inner loop
      }//end of outer loop
      endTime = java.lang.System.nanoTime();
      compTime = (endTime - startTime);
      listCountries(thisList,compTime); // Now display the sorted list
    } // End of exchangeSelection
    
    public static void straightSelection(Country[] thisList){
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Country[] dummyList = new Country[thisList.length];
        int x,y,index = 0, smallest = MAX_VALUE;
        double startTime, endTime,compTime;
        startTime = java.lang.System.nanoTime();
        for(y = 0; y <= thisList.length; y++){
        for(x = 0; x <= thisList.length; x++){
            if (thisList[x-1].getCountry() < smallest){
                smallest = thisList[x-1].getCountry();
                index = x-1;
                thisList[x-1].destroyMe(thisList);
            }
        }//end inner for
        dummyList[y-1] = thisList[index];
        }//end outer for
        endTime = java.lang.System.nanoTime();
        compTime = (endTime - startTime);
        listCountries(dummyList,compTime);
    }

     // The quickSortController method
    public static void quickSortController(Country[] thisList)
    {
       double startTime, endTime,compTime;
       startTime = java.lang.System.nanoTime();
        try {
            thisList = loadCountries();
        } catch (Exception ex) {
            Logger.getLogger(SortSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
       thisList = quickSortCountries(thisList, 0, thisList.length - 1);
       endTime = java.lang.System.nanoTime();
       compTime = (endTime - startTime);
       listCountries(thisList,compTime);
    } // End of quickSortController method

    // The Quicksort method
    public static Country[] quickSortCountries(Country[] thisList, int first, int last)
    {
       Country dummyCountry;
       int k, l, p;
       boolean isSorted = false;

       if (thisList.length == 1) isSorted = true;
       if ((thisList.length == 2) && (thisList[0].getCountry() <= thisList[1].getCountry())) isSorted = true;

       if (!isSorted) // If the list is not sorted
       {
        // Initialize
        k =  first; l = last; p = (first + last)/2; // Note that p represents the pivot
        dummyCountry = new Country();

        // Arrange items around the pivot  with smaller items on the left, and larger items on the right
        while  (k <= l)
        { // While items between the ends of the list
            while (thisList[k].getCountry() < thisList[p].getCountry()) k++; // Find first item from left >= Pivot
            while (thisList[l].getCountry() > thisList[p].getCountry()) l--; // Find first from right <= Pivot

            // Switch the items if they are on the wrong side of the pivot
            if (k <= l)
            {
                dummyCountry.modifyMe(thisList[k]); thisList[k].modifyMe(thisList[l]); thisList[l].modifyMe(dummyCountry);
                k++; l--; // Continue advancing in toward the pivot from both sides
            }
        } // End of While items between the ends of the list

        // Now sort the left and right sublist if necessary
        if (first < l) thisList = quickSortCountries(thisList, first, p);
        if (k < last) thisList = quickSortCountries(thisList, p, last);
       } // End-If list is not sorted

       return thisList;
    } // End of quickSortCountries method
    
    public static Country[] vToArray (Vector <Country> thisVector)
    {
        int x, numCountries;
        Country[] thisList = null;
        // Determine the appropriate size and use it for the conversion
        numCountries = thisVector.size(); thisList = new Country[numCountries];
        for (x = 1; x <= numCountries; x++)
        { // For each patron
            thisList[x-1] = new Country(); // instantiate
            thisList[x-1].modifyMe(thisVector.get(x-1)); // update
        } // End-For
        
        return thisList; 
    } // End of vToArray method
    
    public static Country [] loadCountries() throws Exception, IOException
    {
       Country[] thisList;
       try
       {    
            CountryFileReader = new FileInputStream(CountryFileString); 
            CountryObjectReader = new ObjectInputStream(CountryFileReader);  
            thisList = (Country[]) CountryObjectReader.readObject(); 
       }
       catch (IOException Ex)
       {JOptionPane.showMessageDialog(null, Ex.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE);throw Ex; }
       catch (Exception Ex2)
       {JOptionPane.showMessageDialog(null, Ex2.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); throw Ex2; }
        
       return thisList; 
    }  // End of the loadPatrons method
    
        public static void createFile() //make a file to read from
    {
        try 
        {
            File createFile = new File(CountryFileString);
            if (createFile.createNewFile()) 
            {
                JOptionPane.showMessageDialog(null, "File created: " + createFile.getName(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "File already exists.", countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
            }
        }
        catch (IOException Ex1) 
        {
            JOptionPane.showMessageDialog(null, Ex1.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
        }
    }//end of file creation
     
     public static void deleteFile() //start of delete file method
    {
        try 
        {
            File myObj = new File(CountryFileString); 
            if (myObj.delete()) 
            { 
                JOptionPane.showMessageDialog(null, "Deleted the file: " + myObj.getName(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
            } 
            else 
            {
                JOptionPane.showMessageDialog(null, "Failed to delete the file. Please Restart the Program and try again.", countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE);          
            } 
        }
        catch (Exception Ex1) 
        {
            JOptionPane.showMessageDialog(null, Ex1.toString(), countrySorterHEAD, JOptionPane.INFORMATION_MESSAGE); 
        }
    }//end of delete file method
} // End of SortSimulator class