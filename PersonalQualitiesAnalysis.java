/*
 * **********************************************
 * Application title:
 * Author: Petr Bowles
 * Keene State College Computer Science
 * Date:
 * Purpose:
 * **********************************************
 */
package goodqualities;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author alexl
 */
public class PersonalQualitiesAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //empty string to pass on the name input
        String personName,hold = "";
        //is our loop continuing?
        String goAgain = "y";
        //how many times is our loop going to run?
        int intGo = 0;
        //we are storing values in this list
        ArrayList<Integer> intValues = new ArrayList<Integer>();
        //Actual information we are taking in for scores and passing scores
        double inBenchmark = 0, dblFactor = 0; 
        
        //while loop that allows program to run multiple times
        while(goAgain.equalsIgnoreCase("y")){
            // accepts person's name
        personName = JOptionPane.showInputDialog("What is this person's name?");
        //accepts loop parameters
        intGo = Integer.parseInt(JOptionPane.showInputDialog(null, "how many qualities does this person have: "));
        //for loop that populates list
        for(int x = 0; x < intGo; x ++){
            //fills empty string with quality name
            hold = JOptionPane.showInputDialog(null, "What is the graded quality: ");
            //actual score that populates list position
            int holdthis = Integer.parseInt(JOptionPane.showInputDialog(null,"What is their score: "));
            //add our int to the list
            intValues.add(holdthis);
        }
        //accepts how many points we are grading out of
        inBenchmark = Integer.parseInt(JOptionPane.showInputDialog(null, "Out of how many points is each person graded?"));
        //accepts what a passing average is
        dblFactor = Integer.parseInt(JOptionPane.showInputDialog(null, "What grade does someone need to pass?"));
        //call our method below
        assesQualities(personName,intValues,intGo,inBenchmark,dblFactor);
        //do you want to run the loop again?
        goAgain = JOptionPane.showInputDialog("Would you like to go again? Y for yes.");
        }
        
        }
        
    
    //method declared, brings in all required information
    public static void assesQualities(String personName,ArrayList<Integer> intValues, Integer intGo, Double inBenchmark, Double dblFactor){
       //declaring the total possible points
        double dblPossiblePoints = (intValues.size() * inBenchmark);
       //decalre our total and our score
        double dblTotal = 0, dblScore = 0;
        //need a pass or fail check for our output
        boolean pass = false;
        //for loop to calculate our average
        for(int x = 0; x < intValues.size(); x ++){
           int value = intValues.get(x);
           dblTotal += value;
        }
        //calculated pass fail score
        dblScore = (dblTotal/dblPossiblePoints)*100;
        //did we pass?
        if (dblScore > dblFactor){
            pass = true;
        }
        //pass message
        if (pass == true){
        JOptionPane.showMessageDialog(null, personName + " got a score of " + dblScore + ("%, which is passing!") );
        }
        //fail message
        if (pass == false){
        JOptionPane.showMessageDialog(null, personName + " got a score of " + dblScore + ("%, which is not passing!") );
        }
    }
}
