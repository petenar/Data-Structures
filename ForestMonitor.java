// *********************************************************************************
// ForestMonitor.java                                                                 
// Created on December 2, 2022                                               
// Controller for our GenericBinaryTree.                             
// Author Petr Bowles                                                                 
// *********************************************************************************
package ds_ass07b_bowlesp;

import javax.swing.JOptionPane;

/**
 *
 * @author Petr
 */
public class ForestMonitor {
    final static String HEADING = "Petr Bowles Binary Tree Forest Monitor";
    static GenericTree employeeTree;
    static GenericTree studentTree;
    static GenericTree alumnusTree;
    
    public static void main(String[] args) {
        boolean exitTime = false;
        int userOption = 0;
        initialize();
        //Loops until the user tells it to stop
        while(exitTime != true)
        {
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, 
                    "Input a number for the following:\n" +
                    "1. Enter Employee Info          2. Enter Student Info             3. Enter Alumnus Info\n" + 
                    "4. Query Employee Info         5. Query Student Info            6. Query Alumnus Info\n" +
                    "7. Remove Employee Info      8. Remove Student Info        9. Remove Alumnus Info\n" +
                    "10. Modify Employee Info      11. Modify Student Info        12. Modify Alumnus Info\n" +
                    "13. Traverse Employee Tree  14. Traverse Student Tree   15. Traverse Alumnus Tree\n" +
                    "16. Check Tree Size             17. Empty Tree                        90.Exit"
                    , HEADING, JOptionPane.QUESTION_MESSAGE));
            
            //Handles the user's input and runs a method
            switch(userOption)
            {
                case 1:{inputEmployees();break;} 
                case 2:{inputStudents();break;} 
                case 3:{inputAlumnus();break;}
                case 4:{queryEmployees();break;} 
                case 5:{queryStudents();break;} 
                case 6:{queryAlumnus();break;}
                case 7:{removeEmployees();break;} 
                case 8:{removeStudents();break;} 
                case 9:{removeAlumnus();break;}
                case 10:{modifyEmployees();break;} 
                case 11:{modifyStudents();break;} 
                case 12:{modifyAlumnus();break;}
                case 13:{traverseEmployees();break;} 
                case 14:{traverseStudents();break;} 
                case 15:{traverseAlumnus();break;}
                case 16:{checkTreeSize();break;} 
                case 17:{emptyTree();break;} 
                case 90:{exitTime = true;break;}
                default:{break;}
                    
            }//End of Switch
        }//End of loop
    }//end of main
    
    public static void initialize(){
        employeeTree = new GenericTree();
        studentTree = new GenericTree();
        alumnusTree = new GenericTree();
    }//end of initialize
    
    public static void inputEmployees(){
        int x, limit;
        Generic currentEmp;
        CollegeMember dummyC = new CollegeMember();
        limit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many employees would you like to add?", HEADING, JOptionPane.QUESTION_MESSAGE));
        for(x=1; x<=limit; x++) {
            currentEmp = new Generic(dummyC);
            currentEmp.setDataType(1);
            currentEmp.inputData(x);
            employeeTree.insert(employeeTree.root, currentEmp);
        }//End of For 
    }//end of inputEmployees
    
    public static void inputStudents(){
        int x, limit;
        Generic currentStd;
        CollegeMember dummyC = new CollegeMember();
        limit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many Students would you like to add?", HEADING, JOptionPane.QUESTION_MESSAGE));
        for(x=1; x<=limit; x++) {
            currentStd = new Generic(dummyC);
            currentStd.setDataType(2);
            currentStd.inputData(x);
            studentTree.insert(studentTree.root, currentStd);
        }//End of For 
    }//end of inputStudnets
    
    public static void inputAlumnus(){
        int x, limit;
        Generic currentAlum;
        CollegeMember dummyC = new CollegeMember();
        limit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many Alumnus would you like to add?", HEADING, JOptionPane.QUESTION_MESSAGE));
        for(x=1; x<=limit; x++) {
            currentAlum = new Generic(dummyC);
            currentAlum.setDataType(3);
            currentAlum.inputData(x);
            alumnusTree.insert(alumnusTree.root, currentAlum);
        }//End of For 
    }//end of inputAlumnus
    
    public static void queryEmployees(){
        int searchArg;
        GenericNode foundEmp;
        Generic soughtEmp;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm;
        if(!employeeTree.isEmpty()) {
            while(loop != true) {
                foundEmp = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to search for?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtEmp = new Generic(dummyC);
                soughtEmp.setDataType(1);
                soughtEmp.mID_Number = searchArg;
                foundEmp = employeeTree.search(soughtEmp);
                if(foundEmp!=null)
                    JOptionPane.showMessageDialog(null, foundEmp.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End of While
        }//End of If    
    }//end of queryEmployees
    
    public static void queryStudents(){
        int searchArg;
        GenericNode foundStd;
        Generic soughtStd;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm;
        if(!studentTree.isEmpty()) {
            while(loop != true) {
                foundStd = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to search for?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtStd = new Generic(dummyC);
                soughtStd.setDataType(2);
                soughtStd.mID_Number = searchArg;
                foundStd = studentTree.search(soughtStd);
                if(foundStd!=null)
                    JOptionPane.showMessageDialog(null, foundStd.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End of While
        }//End of If  
    }//end of queryStudents
    
    public static void queryAlumnus(){
        int searchArg;
        GenericNode foundAlum;
        Generic soughtAlum;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm;
        if(!alumnusTree.isEmpty()) {
            while(loop != true) {
                foundAlum = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to search for?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtAlum = new Generic(dummyC);
                soughtAlum.setDataType(3);
                soughtAlum.mID_Number = searchArg;
                foundAlum = alumnusTree.search(soughtAlum);
                if(foundAlum!=null)
                    JOptionPane.showMessageDialog(null, foundAlum.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                else 
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End of While
        }//End of If  
    }//end of queryAlumnus
    
    public static void removeEmployees(){
        int searchArg;
        GenericNode foundEmp;
        Generic soughtEmp;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, deleteConfirmation;
        if(!employeeTree.isEmpty()) {
            while(loop != true) {
                foundEmp = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to remove?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtEmp = new Generic(dummyC);
                soughtEmp.setDataType(1);
                soughtEmp.mID_Number = searchArg;
                foundEmp = employeeTree.search(soughtEmp);
                if(foundEmp!=null) {
                    JOptionPane.showMessageDialog(null, foundEmp.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    deleteConfirmation = JOptionPane.showInputDialog(null, "This will delete the entire subtree starting at this object.\nDo you wish to continue with this? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(deleteConfirmation.equalsIgnoreCase("y"))
                        employeeTree.removeSubtree(foundEmp);
                }//End If   
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of removeEmployees
    
    public static void removeStudents(){
        int searchArg;
        GenericNode foundStd;
        Generic soughtStd;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, deleteConfirmation;
        if(!studentTree.isEmpty()) {
            while(loop != true) {
                foundStd = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to remove?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtStd = new Generic(dummyC);
                soughtStd.setDataType(2);
                soughtStd.mID_Number = searchArg;
                foundStd = studentTree.search(soughtStd);
                if(foundStd!=null) {
                    JOptionPane.showMessageDialog(null, foundStd.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    deleteConfirmation = JOptionPane.showInputDialog(null, "This will delete the entire subtree starting at this object.\nDo you wish to continue with this? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(deleteConfirmation.equalsIgnoreCase("y"))
                        studentTree.removeSubtree(foundStd);
                }//End If   
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of removeStudents
    
    public static void removeAlumnus(){
        int searchArg;
        GenericNode foundAlum;
        Generic soughtAlum;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, deleteConfirmation;
        if(!alumnusTree.isEmpty()) {
            while(loop != true) {
                foundAlum = null;
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Which ID would you like to remove?", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtAlum = new Generic(dummyC);
                soughtAlum.setDataType(3);
                soughtAlum.mID_Number = searchArg;
                foundAlum = alumnusTree.search(soughtAlum);
                if(foundAlum!=null) {
                    JOptionPane.showMessageDialog(null, foundAlum.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    deleteConfirmation = JOptionPane.showInputDialog(null, "This will delete the entire subtree starting at this object.\nDo you wish to continue with this? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(deleteConfirmation.equalsIgnoreCase("y"))
                        alumnusTree.removeSubtree(foundAlum);
                }//End If   
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of removeAlumnus
    
    public static void modifyEmployees(){
        int searchArg;
        GenericNode foundEmp;
        Generic soughtEmp, revisedEmp;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, modifyConfirm;
        if(!employeeTree.isEmpty()) {
            while(loop != true) {
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID that you want to modify.", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtEmp = new Generic(dummyC);
                soughtEmp.setDataType(1);
                soughtEmp.mID_Number = searchArg;
                foundEmp = employeeTree.search(soughtEmp);
                if(foundEmp!=null) {
                    JOptionPane.showMessageDialog(null, foundEmp.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    modifyConfirm = JOptionPane.showInputDialog(null, "Do you wish to continue with modifing this object? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(modifyConfirm.equalsIgnoreCase("y")) {
                        revisedEmp = new Generic(dummyC);
                        revisedEmp.setDataType(1);
                        revisedEmp.inputData(1);
                        foundEmp.nInfo.modifyMe(revisedEmp);
                    }//End If
                }//End If
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of modifyEmployees
    
    public static void modifyStudents(){
        int searchArg;
        GenericNode foundStd;
        Generic soughtStd, revisedStd;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, modifyConfirm;
        if(!studentTree.isEmpty()) {
            while(loop != true) {
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID that you want to modify.", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtStd = new Generic(dummyC);
                soughtStd.setDataType(2);
                soughtStd.mID_Number = searchArg;
                foundStd = studentTree.search(soughtStd);
                if(foundStd!=null) {
                    JOptionPane.showMessageDialog(null, foundStd.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    modifyConfirm = JOptionPane.showInputDialog(null, "Do you wish to continue with modifing this object? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(modifyConfirm.equalsIgnoreCase("y")) {
                        revisedStd = new Generic(dummyC);
                        revisedStd.setDataType(2);
                        revisedStd.inputData(1);
                        foundStd.nInfo.modifyMe(revisedStd);
                    }//End If
                }//End If
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of modifyStudents
    
    public static void modifyAlumnus(){
        int searchArg;
        GenericNode foundStd;
        Generic soughtAlum, revisedAlum;
        CollegeMember dummyC = new CollegeMember();
        boolean loop = false;
        String loopConfirm, modifyConfirm;
        if(!alumnusTree.isEmpty()) {
            while(loop != true) {
                searchArg = Integer.parseInt(JOptionPane.showInputDialog(null, "Input ID that you want to modify.", HEADING, JOptionPane.QUESTION_MESSAGE));
                soughtAlum = new Generic(dummyC);
                soughtAlum.setDataType(3);
                soughtAlum.mID_Number = searchArg;
                foundStd = alumnusTree.search(soughtAlum);
                if(foundStd!=null) {
                    JOptionPane.showMessageDialog(null, foundStd.nInfo.printMe(), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    modifyConfirm = JOptionPane.showInputDialog(null, "Do you wish to continue with modifing this object? (Y/N)", HEADING, JOptionPane.WARNING_MESSAGE);
                    if(modifyConfirm.equalsIgnoreCase("y")) {
                        revisedAlum = new Generic(dummyC);
                        revisedAlum.setDataType(3);
                        revisedAlum.inputData(1);
                        foundStd.nInfo.modifyMe(revisedAlum);
                    }//End If
                }//End If
                else
                    JOptionPane.showMessageDialog(null, "ID not found in tree.", HEADING, JOptionPane.ERROR_MESSAGE);
                loopConfirm = JOptionPane.showInputDialog(null, "Would you like to continue? (Y/N)", HEADING, JOptionPane.QUESTION_MESSAGE);
                if(loopConfirm.equalsIgnoreCase("n"))
                    loop = true;
            }//End While
        }//End If
    }//end of modifyAlumnus
    
    public static void traverseEmployees(){
        int x;
        String outputS = "";
        if(!employeeTree.isEmpty()) {
            employeeTree.inOrderPrep();
            employeeTree.inOrderTraversal(employeeTree.root);
            for(x=1; x<=employeeTree.travResult.size(); x++) {
                outputS = outputS + employeeTree.travResult.get(x-1).printMe() + "\n";
            }//End For
            JOptionPane.showMessageDialog(null, "The in-order traversal of this tree is:\n" + outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
        }//End If 
    }//end of traverseEmployees
    
    public static void traverseStudents(){
        int x;
        String outputS = "";
        if(!studentTree.isEmpty()) {
            studentTree.inOrderPrep();
            studentTree.inOrderTraversal(studentTree.root);
            for(x=1; x<=studentTree.travResult.size(); x++) {
                outputS = outputS + studentTree.travResult.get(x-1).printMe() + "\n";
            }//End For
            JOptionPane.showMessageDialog(null, "The in-order traversal of this tree is:\n" + outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
        }//End If 
    }//end of traverseStudents
    
    public static void traverseAlumnus(){
        int x;
        String outputS = "";
        if(!alumnusTree.isEmpty()) {
            alumnusTree.inOrderPrep();
            alumnusTree.inOrderTraversal(alumnusTree.root);
            for(x=1; x<=alumnusTree.travResult.size(); x++) {
                outputS = outputS + alumnusTree.travResult.get(x-1).printMe() + "\n";
            }//End For
            JOptionPane.showMessageDialog(null, "The in-order traversal of this tree is:\n" + outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
        }//End If 
    }//end of traverseAlumnus
    
    public static void checkTreeSize(){
        boolean exitTime = false;
        int userOption = 0;
        int buffer = 1;
        while(exitTime != true){
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, "What would you like to do?\n1. Check Employee Tree\n2. Check Student Tree\n3. Check Alumnus Tree\n4.Return", HEADING, JOptionPane.QUESTION_MESSAGE));
            switch(userOption) {
                case 1:
                    JOptionPane.showMessageDialog(null, "The size of the employees tree is " + (buffer + employeeTree.getSize()), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break; 
                case 2:
                    JOptionPane.showMessageDialog(null, "The size of the students tree is " + (buffer + studentTree.getSize()), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break; 
                case 3:
                    JOptionPane.showMessageDialog(null, "The size of the alumnus tree is " + (buffer + alumnusTree.getSize()), HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    exitTime = true;
                    break;
                default:
                    break;
            }//End of Switch
        }//End While
    }//end of checkTreeSize
    
    public static void emptyTree(){
        boolean exitTime = false;
        int userOption = 0;
        while(exitTime != true)
        {
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, "What would you like to do?\n1. Empty Employee Tree\n2. Empty Student Tree\n3. Empty Alumnus Tree\n4.Return", HEADING, JOptionPane.QUESTION_MESSAGE));
            switch(userOption) {
                case 1:
                    employeeTree.clearTree();
                    JOptionPane.showMessageDialog(null, "Employee Tree Emptied.", HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break; 
                case 2:
                    studentTree.clearTree();
                    JOptionPane.showMessageDialog(null, "Student Tree Emptied.", HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break; 
                case 3:
                    alumnusTree.clearTree();
                    JOptionPane.showMessageDialog(null, "Alumnus Tree Emptied.", HEADING, JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    exitTime = true;
                    break;
                default:
                    break;
            }//End of Switch
        }//End While
    }//end of emptyTree
    
}//end of ForestMonitor class
