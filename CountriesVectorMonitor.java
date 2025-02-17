// **************************************************************************************************************
// Class Name: CountriesVectorMonitor
// Purpose: Implements a Vector of Country objects via the ArrarList class
// Date Written 10/2/2022
// Author: Petr Bowles
// **************************************************************************************************************

package ds_ass03a_bowlesp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.
import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;

public class CountriesVectorMonitor 
{
    // Global Data Items
    //public static Vector countriesList;
     public static Vector <Country> countriesList; 
    public static final String HEADING = "Countries Array List of Petr Bowles";
    public static final int DEFAULT_NUMBER = 0;
    static double totalPCI, averagePCI, stdDevPCI;
   
    // main method
    public static void main(String[] args) 
    {
        // Declare main variables
        String promptString = "1. Add Countries \n" + "2. Query a Country \n" + "3. List Countries \n" +
                "4. Remove Country \n" + "5. Check Size of List \n" + "6. Empty the List \n" + "7. Display Summary \n" + "8. Display Sorted List" + "\n" + "0. Quit Processing";  
        boolean exitTime = false;
        totalPCI = 0;
        int nextUserAction, userOption, countriesListSize;
        
        initializeList(); // Initialize the array list
        while (!exitTime) // While user wishes to continue
        {
            // Present menu and process user's request
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, promptString, HEADING, JOptionPane.QUESTION_MESSAGE));
            countriesListSize = countriesList.size();
            switch (userOption)
            {
                case 0: {exitTime = true; break;}
                case 1: {inputCountries(); break;} 
                case 2: {if (countriesListSize > 0) 
                         queryCountry(countriesList); break; 
                          // Object[] PatronsArray = countriesList.toArray(); queryCountry(PatronsArray, countriesListSize);} break;
                        }
                case 3: {if (countriesListSize > 0) listCountries(countriesList); break;}
                case 4: {if (countriesListSize > 0) removeCountries(); break;}
                case 5: {checkSize(countriesList); break;}
                case 6: {if (countriesListSize > 0) empty(); break;}
                case 7: {if (countriesListSize > 0) standardDev(countriesList,averagePCI); break;}
                case 8: {if (countriesListSize > 0) sortCountries(countriesList); break;}
            }
            // Check whether user wishes to continue
            //nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to continue. Click No or Cancel to exit.");
            //if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;
        } // End While
    } // End of main method
    
    // The InputCountries Method
    public static void inputCountries()
    {
      int numberOfCountries, x;
      Country currentCountry;  
      numberOfCountries = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Countries: ", HEADING, JOptionPane.QUESTION_MESSAGE));
      
      countriesList.ensureCapacity(countriesList.size() + numberOfCountries); // Ensure correct size of list
      for (x =1; x <= numberOfCountries; x++)
      {
        currentCountry = new Country();  
        currentCountry.inputData(x); // Prompt For and Accept Country Data
        countriesList.add(x-1, currentCountry);
        totalPCI += currentCountry.getPCI();
      };   // End For    
      
      averagePCI = totalPCI/numberOfCountries;
    } // End of inputCountries Method

    // The queryCountry Method   
    public static void queryCountry(Vector thisList)
    {
        // Declarations
        String outString; 
        int nextUserAction, searchNumber;
        Country searchCountry, foundCountry; 
        String qHeading = "Country Query";
        boolean exitTime = false;
        boolean exitNow; 
        int thisLim = thisList.size();
        
        // Prompt for the then check it it's in the list
        if (thisLim > 0) // If there are patrons 
        {    
            while (!exitTime) // While more processing required 
            {    
                //SearchCountry = new Country(); searchCountry.inputData(1);
                searchNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the Country Number of Interest: ", qHeading, JOptionPane.QUESTION_MESSAGE));
                foundCountry = new Country();
                                
                // Search thisList for the Country object, then return it
                exitNow = false;
                for (int x = 1; ((x <= thisLim) && (!exitNow)); x++)
                {
                    if (searchNumber == ((Country) thisList.get(x-1)).getCountry())  
                    { foundCountry.modifyMe(((Country) thisList.get(x-1))); exitNow = true; } 
                } // End-For 
                if (foundCountry.getCountry() != DEFAULT_NUMBER) outString = foundCountry.printMe();
                else outString = "Country specified is not in the list.";
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
        
                // Check whether user wishes to continue
                nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit.");
                if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)) exitTime = true;
            } // End-While more processing required 
        } // End-If there are patrons
    } // End queryCountry       
  

    
    // The listCountries Method
    public static void listCountries(Vector <Country> thisList)
    {
        String outString = "Members of the list are: \n"; int x;
        for (x = 1; x <= thisList.size(); x++)
        { outString += ((Country) thisList.get(x-1)).printMe() + "\n\n"; }
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of listCountries Method
    
    public static void sortCountries(Vector <Country> thisList){
       String outString = "Members of the list are: \n";
       Country sortedCountry = new Country();
       Vector<Country> newList = thisList;
       int limit = newList.size();
       
       for(int x = 0; x < limit - 1; x++){
           
           for(int y = 0; y < limit - 1; y ++){
               
               if(newList.get(x).getPCI() > newList.get(y + 1).getPCI()){
                   sortedCountry.modifyMe(newList.get(y + 1));//x
                   newList.get(y + 1).modifyMe(newList.get(x));//x,y
                   newList.get(x).modifyMe(sortedCountry);//x,sort
               }
           }
       }
        
       for(int z = 1; z <= limit; z++){
           outString = outString + newList.get(z - 1).printMe() + "\n\n";
       }
       JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void standardDev(Vector<Country> thisList, double thisPCI){
        double standard, diff, totalDiff, lowest  = Integer.MAX_VALUE, highest = Integer.MIN_VALUE;
        int limit = thisList.size();
        totalDiff = 0;
        
        for(int x = 1; x <= limit; x++){
            diff = thisList.get(x-1).getPCI() - averagePCI;
            totalDiff += diff;
            if(thisList.get(x-1).getPCI() > highest){
                highest = thisList.get(x-1).getPCI();
            }
            if(thisList.get(x-1).getPCI() < lowest){
                lowest = thisList.get(x-1).getPCI();
            }
        }
        
            standard = Math.sqrt(totalDiff/limit);
            
        JOptionPane.showMessageDialog(null, "The standard deviation for this list is: " + standard + "\n"+ "The lowest Per Capita income is: " + lowest + "\n"+ "The highest Per Captia Income is: " + highest + "\n"+ "The average Per Captia Income is: " + averagePCI);
       
    }
    // The removeCountries Method
    public static void removeCountries()
    {
        String removalPrompt, removalHeading = "Removal of Items from the List";
        int x, rStart, rStop, nextUserAction;
        
        // Prompt for range of items to be removed
        rStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Starting Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        rStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Ending Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        while ((rStop < rStart) || (rStart < 0)) // While invalid range
        {
            JOptionPane.showMessageDialog(null, "Invalid range specified", removalHeading, JOptionPane.ERROR_MESSAGE);
            rStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Starting Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
            rStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Ending Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        } // End of While invalid range
        
        // Allow user to confirm removal of items
        removalPrompt = "Items " + rStart + " to " + rStop + " are about to be removed from the list.\n" + "Click Yes to remove the items. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION)
        {for (x = rStart; x < rStop; x++) countriesList.remove(x);} 
    } // End of removeCountries Method
    
    // The checkSize Method
    public static void checkSize(Vector thisList)
    {
       JOptionPane.showMessageDialog(null, "There are " + thisList.size() + " countries in the list", HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of checkSize Method
    
    // Initialize Method
    public static void initializeList()
    { 
        countriesList = new Vector(0); // Creates a default array-list of Country objects
    }
    
    // The empty Method
    public static void empty()
    {   
        int x, nextUserAction;
        String removalPrompt = "You are about to empty the list. " + "Click Yes to Empty. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION) countriesList.clear(); // {for (x = 1; x <= ThisList.size(); x++) ThisList.remove(x-1);} 
    } // End of empty Method
} // End of CountriesMonitor class