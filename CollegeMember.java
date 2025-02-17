// *********************************************************************************
// CollegeMember.java                                                                 
// Created on December 2, 2022                                               
// CollegeMember inheritable for Generic Instance Class.                             
// Author Petr Bowles                                                                 
// *********************************************************************************
package ds_ass09b_bowlesp;

import java.time.Instant;
import static java.time.Instant.now;
import java.time.Year;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Petr
 */



public class CollegeMember {
    protected int mID_Number;
    protected String mFirstName, mLastName, mGender;
    protected int mDateOfBirth;
    protected String mTelephone, mEmail;
    public static final int DEFAULT_ID = 0;
    
public CollegeMember(){
     mID_Number =DEFAULT_ID;
    mDateOfBirth = DEFAULT_ID;
    mFirstName = "";
    mLastName = "";
    mGender = "";
    mTelephone = "";
    mEmail = "";   
}   //constructor 
public CollegeMember(CollegeMember thisMember){
    mID_Number = thisMember.mID_Number;
    mDateOfBirth = thisMember.mDateOfBirth;
    mFirstName = thisMember.mFirstName;
    mLastName = thisMember.mLastName;
    mGender = thisMember.mGender;
    mTelephone = thisMember.mTelephone;
    mEmail = thisMember.mEmail;
}  //end of overloadedconstructor

public void modifyMe(CollegeMember thisMember){
    mID_Number = thisMember.mID_Number;
    mDateOfBirth = thisMember.mDateOfBirth;
    mFirstName = thisMember.mFirstName;
    mLastName = thisMember.mLastName;
    mGender = thisMember.mGender;
    mTelephone = thisMember.mTelephone;
    mEmail = thisMember.mEmail;
}//end of modifyMe

public String printMe(){
    String printString = "ID Number: " + mID_Number +"\nName: " + mFirstName + " " + mLastName
    +  "\nGender: " + mGender +"\nDate of Birth: " + mDateOfBirth + "\nTelephone: " +
    mTelephone +  "\nE-Mail: " + mEmail;
    return printString;
}//end of printMe

public void inputData(int x, String inCategory){
    String inputID, inputTele, inputFName, inputLName, inputDOB;
    
    inputID = JOptionPane.showInputDialog(null, "Member ID: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE); 
    while(!validateID(inputID)){
        JOptionPane.showMessageDialog(null,"ID number must be numeric");
        inputID = JOptionPane.showInputDialog(null, "Member ID: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    }
    
    inputFName = JOptionPane.showInputDialog(null, "First Name: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);   
    while((!(Character.isLetter(inputFName.charAt(x-1))))){
    JOptionPane.showMessageDialog(null,"Name must begin with a letter");
    inputFName = JOptionPane.showInputDialog(null, "First Name: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    }   
    
    inputLName = JOptionPane.showInputDialog(null, "Last Name: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);   
    while((!(Character.isLetter(inputLName.charAt(x-1))))){
    JOptionPane.showMessageDialog(null,"Name must begin with a letter");
    inputLName = JOptionPane.showInputDialog(null, "Last Name: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    }
    
    mEmail = JOptionPane.showInputDialog(null, "E-Mail: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    mGender = JOptionPane.showInputDialog(null, "Gender: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    
    inputTele = JOptionPane.showInputDialog(null, "Telephone: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    while(!validateTele(inputTele)){
        JOptionPane.showMessageDialog(null,"Telephone number is not in the required format");
        inputTele = JOptionPane.showInputDialog(null, "Telephone: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    }
    
    inputDOB = JOptionPane.showInputDialog(null, "Date of Birth: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE);
    while(!validateDOB(inputDOB)){
        JOptionPane.showMessageDialog(null, "Invalid Date of Birth");
        inputDOB = JOptionPane.showInputDialog(null, "Date of Birth: ", "Enter College Member Data", JOptionPane.QUESTION_MESSAGE); 
    }
    mID_Number = Integer.parseInt(inputID);
    mFirstName = inputFName;
    mLastName = inputLName;
    mTelephone = inputTele;
    mDateOfBirth = Integer.parseInt(inputDOB);
    
}//end of inputData

public boolean validateTele(String thisTele){
        boolean isValid = true;    
        int x;
        for (x=0; x == 12; x++){
            char c = thisTele.charAt(x);
            switch(x){
                case 4:
                    if(c != '-')
                        isValid = false;
                    break;
                case 8:
                    if(c != '-')
                        isValid = false;
                default:
                    if(!Character.isDigit(c-1))
                        isValid = false;
            }//end of switch
        }//end of loop
        return isValid;
    }//End of Method

public boolean validateID(String thisID){
   boolean isValid = true;
       int x;
       for(x = 1; x <= thisID.length(); x++){
         if(!Character.isDigit(thisID.charAt(x-1))){isValid = false;}//end if
       }//end for
       return isValid; 
}//end of validateID

public boolean validateDOB(String thisDate){
        boolean isValid = true, isNumbers = true; 
        isNumbers = validateID(thisDate);
        if(thisDate.length()==8 && isNumbers) {
            int x, Day, Month, Year;
            int[] mCheck = new int[13];
            boolean leapYear = false;
            int CurrentYear = Calendar.getInstance().get(Calendar.YEAR);

            Year = Integer.parseInt(thisDate.substring(0,4));
            Month = Integer.parseInt(thisDate.substring(5,6));
            Day = Integer.parseInt(thisDate.substring(7,8));
            if(Year%400==0 || Year%4==0 && Year%100==0) 
                leapYear = true;
            mCheck[0] = 0;
            mCheck[2] = 28;
            if(leapYear)
                mCheck[2] = 29;
            mCheck[1] = mCheck[3] = mCheck[5] = mCheck[7] = mCheck[8] = mCheck[10] = mCheck[12] = 31;
            mCheck[4] = mCheck[6] = mCheck[9] = mCheck[11] = 30;

            if(Year > CurrentYear)
                isValid = false;
            else if(Month < 1 || Month >  12)
                isValid = false;
            else if(Day > mCheck[Month])
                isValid = false;
        }
        else
            isValid = false;
        return isValid;
    }//End of validateDOB

public String toString(){
    return "College Memeber: " + this.printMe();
}//end of toString

public int getID(){
    return mID_Number;
}//end of getID

    protected void finalize()throws java.lang.Throwable
    { Destruct(this); } // End of finalize method
   
    // The Destruct Method - called by the finalize method
    protected void Destruct(Object ThisMem)
    { ThisMem = null; 
      System.gc(); // Call the Java collector
    } // End of Destruct method

public boolean equals(Object thisObject){
    boolean instanceMatch = false;
      if (thisObject instanceof CollegeMember)
      {
         CollegeMember ThisMember = new CollegeMember((CollegeMember)thisObject); 
         if (mID_Number == ThisMember.mID_Number) instanceMatch = true;
      }
      return instanceMatch;
}//end of equals

}//end of CollegeMember
