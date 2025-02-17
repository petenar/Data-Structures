// **************************************************************************************************************
// Class Name: CountriesMonitor
// Purpose: Implements a Stack of Country objects
// Date Written 10/17/2022
// Author: Petr Bowles
// **************************************************************************************************************

package ds_ass04c1_bowlesp;
import java.util.Stack;
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class CountriesMonitor 
{
    // Global Data Items
    //public static ArrayList countriesList;
    public static Stack countriesList;
    static Country richestC = new Country();
    static Country poorestC = new Country();
    public static final String HEADING = "Countries Stack of Petr Bowles";
    public static final int DEFAULT_NUMBER = 0;
    static double totalPCI, averagePCI, stdDevPCI;
   
    // main method
    public static void main(String[] args) 
    {
        // Declare main variables
        String promptString = "1. Push Countries \n" + "2. Query a Country \n" + "3. List Countries \n" +
                "4. Pop Country \n" + "5. Check Size of List \n" + "6. Pop the Entire List \n" + "7. Display Summary \n" + "8. Display Sorted List" + "\n" + "0. Quit Processing";  
        boolean exitTime = false;
        totalPCI = 0;
        int userOption, countriesListSize;
        
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
                case 7: {if (countriesListSize > 0) summarizeCountries(countriesList); break;}
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
      
      for (x =1; x <= numberOfCountries; x++)
      {
        currentCountry = new Country();  
        currentCountry.inputData(x); // Prompt For and Accept Country Data
        countriesList.push(currentCountry);
        
      }   // End For    
      
      
    } // End of inputCountries Method

    // The queryCountry Method   
    public static void queryCountry(Stack <Country> thisList)
    {
        // Declarations
        String outString; 
        int nextUserAction, searchNumber;
        Country foundCountry; 
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
    public static void listCountries(Stack <Country> thisList)
    {
        String outString = "Members of the list are: \n"; int x;
        for (x = 1; x <= thisList.size(); x++)
        { outString += ( thisList.get(x-1)).printMe() + "\n\n"; }
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of listCountries Method
    
    public static void sortCountries(Stack <Country> thisList){
       String outString = "Members of the list are: \n";
       Country sortedCountry = new Country();
       Stack <Country> newList = thisList;
       int limit = newList.size();
       
       for(int x = 1; x <= limit; x++){
           
           for(int y = x + 1; y <= limit; y++){
               
               if(newList.get(x-1).getPCI() > newList.get(y - 1).getPCI()){
                   sortedCountry.modifyMe(newList.get(x - 1));//x
                   newList.get(x-1).modifyMe(newList.get(y - 1));//x,y
                   newList.get(y - 1).modifyMe(sortedCountry);//x,sort
               }
           }
       }
        
       for(int z = 1; z <= limit; z++){
           outString = outString + newList.get(z - 1).printMe() + "\n\n";
       }
       JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void removeCountries()
    {
        String removalPrompt, removalHeading = "Removal of Items from the List";
        int x, nextUserAction,popAmount;
        
         popAmount = Integer.parseInt(JOptionPane.showInputDialog(null, "How many items do you wish to pop? ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        while ((popAmount > countriesList.size()) || (popAmount <= 0)) // While invalid range
        {
            JOptionPane.showMessageDialog(null, "The stack does not have that many items", removalHeading, JOptionPane.ERROR_MESSAGE);
            popAmount = Integer.parseInt(JOptionPane.showInputDialog(null, "How many items do you wish to pop? ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        } // End of While invalid range
        
        // Allow user to confirm removal of items
        removalPrompt = popAmount + " items are about to be popped from the stack.\n" + "Click Yes to remove the items. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION)
        {for (x = 1; x <= popAmount; x++) countriesList.pop();} 
    } // End of removeCountries Method
    
    public static void summarizeCountries(Stack <Country> thisList){
        int x, cLim;
        Stack newList = thisList;
        String outputS;
        cLim = newList.size();
        Country thisCountry;
        
        
        initializeSummary();
        for(x = 1; x <= cLim; x++){
            thisCountry = thisList.get(x-1);
            totalPCI += thisCountry.getPCI();  
            highLow(thisList.get(x-1));
        }
        
        averagePCI = totalPCI/cLim;
        stdDevPCI = standardDev(thisList, cLim, averagePCI);
        
        outputS = "Here is a summary of the list: ";
        outputS += richestC.printMe();
        outputS += poorestC.printMe();
        outputS += ("Total PCI: " + totalPCI + "\n");
        outputS += ("Average PCI: " + averagePCI + "\n");
        outputS += ("Total PCI: " + stdDevPCI + "\n");
        JOptionPane.showMessageDialog(null, outputS);
    }
    
        public static void highLow(Country thisCountry){//the high low method
       if (thisCountry.getPCI() > richestC.getPCI()){
           richestC.modifyMe(thisCountry);
       }
       if (thisCountry.getPCI() < poorestC.getPCI()){
           poorestC.modifyMe(thisCountry);
       }
    }//end of high low
    
    public static double standardDev(Stack <Country> thisList,int thisLim,double thisAvg){
        int x;
        double standard, diff, totalDiff;
        totalDiff = 0;
        
        for(x = 1; x <= thisLim; x++){
            diff = Math.pow(thisList.get(x-1).getPCI() - thisAvg,2);
            totalDiff += diff;
            
            
        }
        
            standard = Math.sqrt(totalDiff/thisLim);
            
       // JOptionPane.showMessageDialog(null, "The standard deviation for this list is: " + standard + "\n"+ "The lowest Per Capita income is: " + lowest + "\n"+ "The highest Per Captia Income is: " + highest + "\n"+ "The average Per Captia Income is: " + averagePCI);
       return standard;
    }
    

    
    private static void initializeSummary(){
        poorestC.setPCI(Integer.MAX_VALUE);
        richestC.setPCI(Integer.MIN_VALUE);
        totalPCI = 0;
        averagePCI = 0;
        stdDevPCI = 0;
    }
    
      // Initialize Method
       public static void initializeList()
    { 
        countriesList = new Stack(); // Creates a default array-list of Country objects
    }
    
    
    
    // The checkSize Method
    public static void checkSize(Stack <Country> thisList)
    {
       JOptionPane.showMessageDialog(null, "There are " + thisList.size() + " countries in the list", HEADING, JOptionPane.INFORMATION_MESSAGE);
    } // End of checkSize Method
    
  

    // The empty Method
    public static void empty()
    {   
        int nextUserAction;
        String removalPrompt = "You are about to empty the list. " + "Click Yes to Empty. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION) countriesList.clear(); // {for (x = 1; x <= ThisList.size(); x++) ThisList.remove(x-1);} 
    } // End of empty Method
} // End of CountriesMonitor class