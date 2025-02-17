/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds_ass09b_bowlesp;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JOptionPane;

public class CollegeHashController {

public static Hashtable <Integer, Generic> studentsList; 
public static Hashtable <Integer, Generic> employeesList; 
public static Hashtable <Integer, Generic> alumnusList; 
public static ArrayList keyValues;
public static final String HEADING = "CollegeMember Hash Table";
public static final int DEFAULT_NUMBER = 0;

    public static void main(String[] args) {
        String promptString = "1. Add Students \n" + "2. Add Employees \n" + "3. Add Alumnus \n" + "4. Query Student \n" + "5. Query Employee \n" +
                "6. Query Alumnus \n" +  "7. List Students \n" + "8. List Employees \n" + "9. List Alumnus \n" + "10. Remove Students \n" +
                 "11. Remove Employees \n" + "12. Remove Alumnus \n" + "13. Check Size of Students Hash Table \n" + "14. Check Size of Employees Hash Table \n" +
                 "15. Check Size of Alumnus Hash Table \n" + "16. Empty Students Hash Table \n" + "17. Empty Employees Hash Table \n" + "18. Empty Alumnus Hash Table \n" +
                 "0. Quit Processing";
        boolean exitTime = false;
        int nextUserAction, userOption;
        
        initializeLists();
        while(!exitTime){
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, promptString, HEADING, JOptionPane.QUESTION_MESSAGE));
            
            switch (userOption){
                case 0:{exitTime = true; break;}
                case 1:{inputStudents();break;}
                case 2:{inputEmployees();break;}
                case 3:{inputAlumnus();break;}
                case 4:{queryStudents();break;}
                case 5:{queryEmployees();break;}
                case 6:{queryAlumnus();break;}
                case 7:{listStudents();break;}
                case 8:{listEmployees();break;}
                case 9:{listAlumnus();break;}
                case 10:{removeStudents();break;}
                case 11:{removeEmployees();break;}
                case 12:{removeAlumnus();break;}
                case 13:{checkStudentSize();break;}
                case 14:{checkEmployeeSize();break;}
                case 15:{checkAlumniSize();break;}
                case 16:{emptyStudents();break;}
                case 17:{emptyEmployees();break;}
                case 18:{emptyAlumnus();break;}
            }//end switch
        }//end while
    }//end of main
    
    public static void inputStudents(){
        int numberOfStudents, x, studentsListSize;
        Generic currentStudent;
        studentsListSize = studentsList.size();
        numberOfStudents = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Students: ", HEADING, + JOptionPane.QUESTION_MESSAGE));
        
        keyValues.ensureCapacity(studentsListSize + numberOfStudents);
        for(x = 1; x<= numberOfStudents; x++){
            currentStudent = new Generic();
            currentStudent.setDataType(2);
            currentStudent.inputData(x);
            studentsList.put(currentStudent.getID(), currentStudent);
            keyValues.add(x-1, currentStudent.getID());
        }//end for
    }//end of inputStudents
    
    public static void inputEmployees(){
        int numberOfEmployees, x, employeesListSize;
        Generic currentEmployee;
        employeesListSize = employeesList.size();
        numberOfEmployees = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Employees: ", HEADING, + JOptionPane.QUESTION_MESSAGE));
        
        keyValues.ensureCapacity(employeesListSize + numberOfEmployees);
        for(x = 1; x<= numberOfEmployees; x++){
            currentEmployee = new Generic();
            currentEmployee.setDataType(1);
            currentEmployee.inputData(x);
            employeesList.put(currentEmployee.getID(), currentEmployee);
            keyValues.add(x-1, currentEmployee.getID());
        }//end for
    }//end of inputEmployees
    
    public static void inputAlumnus(){
        int numberOfAlumnus, x, alumnusListSize;
        Generic currentAlumni;
        alumnusListSize = alumnusList.size();
        numberOfAlumnus = Integer.parseInt(JOptionPane.showInputDialog(null, "Number of Alumnus: ", HEADING, + JOptionPane.QUESTION_MESSAGE));
        
        keyValues.ensureCapacity(alumnusListSize + numberOfAlumnus);
        for(x = 1; x<= numberOfAlumnus; x++){
            currentAlumni = new Generic();
            currentAlumni.setDataType(3);
            currentAlumni.inputData(x);
            alumnusList.put(currentAlumni.getID(), currentAlumni);
            keyValues.add(x-1, currentAlumni.getID());
        }//end for
    }
    
    public static void queryStudents(){
        String outString;
        int nextUserAction, searchKey;
        Generic soughtStudent;
        String qHeading = "Student Hash Table Query";
        boolean exitNow = false;
        
        while(!exitNow){
            searchKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Student Key Value: ", qHeading, + JOptionPane.QUESTION_MESSAGE));
            soughtStudent = new Generic();
            soughtStudent.setDataType(2);
            
            if(studentsList.containsKey(searchKey)){
                soughtStudent.modifyMe(studentsList.get(searchKey));
                outString = soughtStudent.printMe();
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end if
            else{
                outString = "Student specified is not in the list";
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end else
            
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit");
            if((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end queryStudents
    
    public static void queryEmployees(){
        String outString;
        int nextUserAction, searchKey;
        Generic soughtEmployee;
        String qHeading = "Employee Hash Table Query";
        boolean exitNow = false;
        
        while(!exitNow){
            searchKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Employee Key Value: ", qHeading, + JOptionPane.QUESTION_MESSAGE));
            soughtEmployee = new Generic();
            soughtEmployee.setDataType(1);
            
            if(employeesList.containsKey(searchKey)){
                soughtEmployee.modifyMe(employeesList.get(searchKey));
                outString = soughtEmployee.printMe();
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end if
            else{
                outString = "Employee specified is not in the list";
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end else
            
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit");
            if((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end of queryEmployees
    
    public static void queryAlumnus(){
        String outString;
        int nextUserAction, searchKey;
        Generic soughtAlumni;
        String qHeading = "Alumni Hash Table Query";
        boolean exitNow = false;
        
        while(!exitNow){
            searchKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Alumni Key Value: ", qHeading, + JOptionPane.QUESTION_MESSAGE));
            soughtAlumni = new Generic();
            soughtAlumni.setDataType(3);
            
            if(alumnusList.containsKey(searchKey)){
                soughtAlumni.modifyMe(alumnusList.get(searchKey));
                outString = soughtAlumni.printMe();
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end if
            else{
                outString = "Alumni specified is not in the list";
                JOptionPane.showMessageDialog(null, outString, qHeading, JOptionPane.INFORMATION_MESSAGE);
            }//end else
            
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to query another. Click No or Cancel to exit");
            if((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end of queryAlumnus
    
    public static void listStudents(){
        int x, studentListSize;
        studentListSize = studentsList.size();
        String outString = "The hash table contains the following: \n";
        
        for(x = 1; x<= studentListSize;x++){
            outString += studentsList.get(keyValues.get(x-1)).printMe() + "\n";
        }//end for
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    }//end of listStudents
    
    public static void listEmployees(){
        int x, employeeListSize;
        employeeListSize = employeesList.size();
        String outString = "The hash table contains the following: \n";
        
        for(x = 1; x<= employeeListSize;x++){
            outString += employeesList.get(keyValues.get(x-1)).printMe() + "\n";
        }//end for
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    }//end of listEmployees
    
    public static void listAlumnus(){
        int x, alumniListSize;
        alumniListSize = alumnusList.size();
        String outString = "The hash table contains the following: \n";
        
        for(x = 1; x<= alumniListSize;x++){
            outString += alumnusList.get(keyValues.get(x-1)).printMe() + "\n";
        }//end for
        JOptionPane.showMessageDialog(null, outString, HEADING, JOptionPane.INFORMATION_MESSAGE);
    }//end of listAlumnus
    
    public static void removeStudents(){
        String removalPrompt, Heading = "Removal of Students from the Hash Table";
        int x, removalKey, nextUserAction; 
        boolean exitNow = false;
       
        while (!exitNow){
            removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the student that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            
            while (!studentsList.containsKey(removalKey)){//while invalid
                JOptionPane.showMessageDialog(null, "The hash table does not contain the specified key value", Heading, + JOptionPane.ERROR_MESSAGE);
                removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the student that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            }//end while invalid
            
            removalPrompt = "Student " + removalKey + " is about to be removed from the hash table.\n" + "Click Yes to remove the items." + "Click No or Cancel to exit.";
            nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
            if (nextUserAction == JOptionPane.YES_OPTION){
                studentsList.remove(removalKey); 
                keyValues.remove(keyValues.indexOf(removalKey));
            }//end if
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to remove another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end of removeStudents
    
    public static void removeEmployees(){
        String removalPrompt, Heading = "Removal of Employees from the Hash Table";
        int x, removalKey, nextUserAction; 
        boolean exitNow = false;
       
        while (!exitNow){
            removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the employee that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            
            while (!employeesList.containsKey(removalKey)){//while invalid
                JOptionPane.showMessageDialog(null, "The hash table does not contain the specified key value", Heading, + JOptionPane.ERROR_MESSAGE);
                removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the student that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            }//end while invalid
            
            removalPrompt = "Employee " + removalKey + " is about to be removed from the hash table.\n" + "Click Yes to remove the items." + "Click No or Cancel to exit.";
            nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
            if (nextUserAction == JOptionPane.YES_OPTION){
                employeesList.remove(removalKey); 
                keyValues.remove(keyValues.indexOf(removalKey));
            }//end if
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to remove another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end of removeStudents
    
    public static void removeAlumnus(){
        String removalPrompt, Heading = "Removal of Alumnus from the Hash Table";
        int x, removalKey, nextUserAction; 
        boolean exitNow = false;
       
        while (!exitNow){
            removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the alumni that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            
            while (!alumnusList.containsKey(removalKey)){//while invalid
                JOptionPane.showMessageDialog(null, "The hash table does not contain the specified key value", Heading, + JOptionPane.ERROR_MESSAGE);
                removalKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Specify the key value of the student that you wish to remove: " + Heading, JOptionPane.QUESTION_MESSAGE));
            }//end while invalid
            
            removalPrompt = "Alumni " + removalKey + " is about to be removed from the hash table.\n" + "Click Yes to remove the items." + "Click No or Cancel to exit.";
            nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
            if (nextUserAction == JOptionPane.YES_OPTION){
                alumnusList.remove(removalKey); 
                keyValues.remove(keyValues.indexOf(removalKey));
            }//end if
            nextUserAction = JOptionPane.showConfirmDialog(null, "Click Yes to remove another. Click No or Cancel to exit.");
            if ((nextUserAction == JOptionPane.CANCEL_OPTION) || (nextUserAction == JOptionPane.NO_OPTION)){
                exitNow = true;
            }//end if
        }//end while
    }//end of removeAlumnus
    
    public static void checkStudentSize(){
        JOptionPane.showMessageDialog(null, "There are " + studentsList.size() + " students in the hash table",HEADING, + JOptionPane.INFORMATION_MESSAGE);
    }//end of checkStudentSize
    
    public static void checkEmployeeSize(){
        JOptionPane.showMessageDialog(null, "There are " + employeesList.size() + " students in the hash table",HEADING, + JOptionPane.INFORMATION_MESSAGE);
    }//end of checkEmployeeSize
    
    public static void checkAlumniSize(){
        JOptionPane.showMessageDialog(null, "There are " + alumnusList.size() + " students in the hash table",HEADING, + JOptionPane.INFORMATION_MESSAGE);
    }//end of checkAlumniSize
    
    public static void initializeLists(){
        studentsList = new Hashtable <Integer, Generic>();
        employeesList = new Hashtable <Integer, Generic>();
        alumnusList = new Hashtable <Integer, Generic>();
        keyValues = new ArrayList();
    }//end of initializeLists
    
    public static void emptyStudents(){
        int x, nextUserAction;
        String removalPrompt = "You are about to empty the hash table." + "Click Yes to Empty. Click No or Cancel to exit";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if(nextUserAction==JOptionPane.YES_OPTION){
            studentsList.clear();
            keyValues.clear();
        }//end if
    }//end of emptyStudents
    
    public static void emptyEmployees(){
        int x, nextUserAction;
        String removalPrompt = "You are about to empty the hash table." + "Click Yes to Empty. Click No or Cancel to exit";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if(nextUserAction==JOptionPane.YES_OPTION){
            employeesList.clear();
            keyValues.clear();
        }//end if
    }//end of emptyEmployees
    
    public static void emptyAlumnus(){
        int x, nextUserAction;
        String removalPrompt = "You are about to empty the hash table." + "Click Yes to Empty. Click No or Cancel to exit";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if(nextUserAction==JOptionPane.YES_OPTION){
            alumnusList.clear();
            keyValues.clear();
        }//end if
    }//end of emptyAlumnus
}//end of public class
