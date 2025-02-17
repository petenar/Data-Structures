// *********************************************************************************
// Generic.java                                                                 
// Created on December 2, 2022                                               
// Generic Instance Class.                             
// Author Petr Bowles                                                                 
// *********************************************************************************
package ds_ass09b_bowlesp;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Petr
 */
public class Generic extends CollegeMember{
    protected String alumAcadDept;
    protected String alumMajor;
    protected String alumCurrentEmployer;
    protected String alumJobTitle;
    protected String stdAcadDept;
    protected String stdAcadMajor;
    protected String empJobDept;
    protected String empJobSpecialization;
    protected String empJobTitle;
    protected int setDataType;
    protected String dataTypeString;
    
    public Generic(){
        alumAcadDept = null;
        alumMajor = null;
        alumCurrentEmployer = null;
        alumJobTitle = null;
        stdAcadDept = null;
        stdAcadMajor = null;
        empJobDept = null;
        empJobSpecialization = null;
        empJobTitle = null;
        setDataType = 1;
        dataTypeString = "";
    }
    
    public Generic(CollegeMember thisMember){
        super(thisMember);
        
        switch(setDataType){
            case 1:{empJobDept = ""; empJobSpecialization = ""; empJobTitle = ""; break;}
            case 2:{stdAcadDept = "";stdAcadMajor = "";break;}
            case 3:{alumAcadDept = "";alumMajor = "";alumCurrentEmployer = "";break;}
    }
    }//end of constructor
    
    public void modifyMe(Generic thisItem){
        int x;
        super.modifyMe(thisItem);
        setDataType = thisItem.setDataType;
        switch(setDataType){
            case 1:{empJobDept = thisItem.empJobDept; empJobSpecialization = thisItem.empJobSpecialization; empJobTitle= thisItem.empJobTitle; break;}
            case 2:{stdAcadDept = thisItem.stdAcadDept; stdAcadMajor= thisItem.stdAcadMajor; break;}
            case 3:{alumAcadDept = thisItem.alumAcadDept; alumMajor= thisItem.alumMajor; alumCurrentEmployer = thisItem.alumCurrentEmployer; alumJobTitle= thisItem.alumJobTitle; break;}
        }
    }//end of modifyMe
    
    public void inputData(int x){
        super.inputData(x, dataTypeString);
        switch(setDataType){
            case 1:{
                empJobDept = JOptionPane.showInputDialog(null, "What is the Job Department for this Employee");
                empJobSpecialization = JOptionPane.showInputDialog(null, "What is the Job Specialization for this Employee");
                empJobTitle = JOptionPane.showInputDialog(null, "What is the Job Title for this Employee");
                break;
            }
            case 2:{
                stdAcadDept = JOptionPane.showInputDialog(null, "What is the Academic Department for this Student");
                stdAcadMajor = JOptionPane.showInputDialog(null, "What is the Academic Major for this Student");
                break;
            }
            case 3:{
                alumAcadDept = JOptionPane.showInputDialog(null, "What is the Academic Department for this Alumni");
                alumMajor = JOptionPane.showInputDialog(null, "What is the Academic Major for this Alumni");
                alumCurrentEmployer = JOptionPane.showInputDialog(null, "Who is the Current Employer for this Alumni");
                alumJobTitle = JOptionPane.showInputDialog(null, "What is the Job Title for this Alumni");
                break;
            }
        }
    }//end of inputData
    
    public String printMe(){
        String printString = "";
        switch(setDataType){
            case 1:{printString = super.printMe() + "\nDepartment: " + empJobDept + "\nSpecialization: " + empJobSpecialization + "\nJob Title: " + empJobTitle; break;}
            case 2:{printString = super.printMe() + "\nDepartment: " + stdAcadDept + "\nMajor: " + stdAcadMajor; break;}
            case 3:{printString = super.printMe() + "\nDepartment: " + alumAcadDept + "\nMajor: " + alumMajor + "\nCurrent Employer: " + alumCurrentEmployer + "\nJob Title: " + alumJobTitle; break;}
        }
        return printString;
    }//end of printMe
    
    public String toString(){
        return dataTypeString + this.printMe();
    }//end of toString
    
    protected void finalize()throws java.lang.Throwable{ 
        Destruct(this); 
    } // End of finalize
   
    // The Destruct Method - called by the finalize method
    protected void Destruct(Object ThisMem){ 
        ThisMem = null; 
        System.gc(); // Call the Java collector
    } // End of Destruct
    
    public boolean equals(Object thisObject){
    boolean instanceMatch = false;
      if (thisObject instanceof Generic)
      {
         Generic ThisMember = new Generic((Generic)thisObject); 
         if (mID_Number == ThisMember.mID_Number) instanceMatch = true;
      }
      return instanceMatch;
}//end of equals
    
    public void setDataType(int dataType){
        setDataType = dataType;
        switch(setDataType){
            case 1:{
                dataTypeString = "Employee";
                break;
            }
            case 2:{
                dataTypeString = "Student";
                break;
            }
            case 3:{
                dataTypeString = "Alumnus";
                break;  
            }
        }
    }
}
