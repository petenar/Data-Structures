/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ds_ass05b_bowlesp;

import static java.lang.Integer.MAX_VALUE;
import javax.swing.JOptionPane;

/**
 *
 * @author Petr
 */
public class clinicMonitor {

    static clinicQueue[] theClinic;
    static boolean[] openFlag;
    static String[] theDoctor;
    final static String HEADING = "The Clinic Monitor of Petr Bowles";
    final static int MAX = 10;
    
    public static void main(String[] args) {
        // Declare main variables
        String promptString = "1. Open Queues \n" + "2. Enqueue Patients \n" + "3. Dequeue Patients \n" +
                "4. List Patients \n" + "5. Reassign Patients \n" + "6. Check Queue Size \n" + "7.Empty Queue  \n" + "8. Close Queue \n" + "9. Quit Processing";  
        boolean exitTime = false;
        int userOption;
        initialize();
        while (!exitTime) // While user wishes to continue
        {
            // Present menu and process user's request
            userOption = Integer.parseInt(JOptionPane.showInputDialog(null, promptString, HEADING, JOptionPane.QUESTION_MESSAGE));
            switch (userOption)
            {
                case 1: {openQueue(); break;} 
                case 2: {enqueuePatients(); break;}
                case 3: {dequeuePatients(); break;}
                case 4: {listPatients(); break;}
                case 5: {reassignPatients(); break;}
                case 6: {checkSize(); break;}
                case 7: {emptyQueue(); break;}
                case 8: {closeQueue(); break;}
                case 9: {exitTime = true; break;}
            } // Check whether user wishes to continue
        } // End While
    }//End of main
    
    public static void initialize(){//start of initialize method
        int x;
        for(x =1; x <= MAX; x++){
            theClinic[x-1] = null;
            theDoctor[x-1] = JOptionPane.showInputDialog(null, "What is the name of this Physician?");
            openFlag[x-1] = false;
        }
    }//end of initialize
    
    public static void openQueue(){//start of openQueue
        int go = 1;
        while(go == 1){
            int qChoice;
            JOptionPane.showMessageDialog(null, "These are the available doctors: " + theDoctor);
            qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which doctor would the patients like to see?"));
            if(qChoice > 1 && qChoice < MAX){
                openFlag[qChoice - 1] = true;
            go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));
            }
            JOptionPane.showMessageDialog(null,"Invalid doctor option, please try again");
        }
    }//end of openQueue
    
    public static void enqueuePatients(){//start of enqueue
        int x, qChoice, Limit;
        int go = 1;
        clinicPatient currentPatient = new clinicPatient();
        
        while(go == 1){
        qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which doctor would the patients like to see?"));
        if(qChoice > 1 && qChoice < MAX){//validate qChoice
           if(openFlag[qChoice -1] == true){
            Limit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many patients are being Enqueued?"));
            for(x = 1; x <= Limit; x++){
                currentPatient = new clinicPatient();
                currentPatient.inputData(x);
                theClinic[qChoice -1].addRear(currentPatient);
            }//end for
        }//end if 
        }//end validation
        else{ JOptionPane.showMessageDialog(null, "Invalid doctor selection");}
        go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));
        }//end while
    }///end of enqueue
    
    public static void dequeuePatients(){//start of dequeue
        int qStart, qStop, x, qChoice, Limit, go = 1;
        
        while(go == 1){
           qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue would you like to remove patients from?"));
           if(qChoice > 1 && qChoice < MAX){//validate qChoice
               
           if(openFlag[qChoice - 1] == true){
               qStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Where in queue are you starting from?"));
               qStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Where in queue are you ending?"));
               while(qStop < qStart || qStart < 0){
                   JOptionPane.showMessageDialog(null, "Invalid Range Specified");
                   qStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Where in queue are you starting from?"));
                   qStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Where in queue are you ending?"));
               }//end while
               
               for(x = qStart; qStart <= qStop; x++){
                   theClinic[qChoice-1].removeFront();
               }//end for
           }//end if
           else{ JOptionPane.showMessageDialog(null, "Invalid queue selection");}
           go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));//prompt to continue            
           
           }//end of validation

        }//end while
    }//end of dequeue
    
    public static void listPatients(){//start of listPatients
        int qChoice = 0, go = 1,x;
        String outputS = "The members of the queue are: ";
        while(go == 1){
            qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue would you like to check the patients of?"));
            
            if(qChoice > 1 && qChoice < MAX){//validate qChoice
            if(openFlag[qChoice - 1] == true){
            clinicPatient[] patientsArray = new clinicPatient[theClinic[qChoice-1].length];
            patientsArray = theClinic[qChoice-1].toArray();
            for(x = 1; x <= patientsArray.length; x++){ 
            outputS += patientsArray[x - 1];  
            }
            JOptionPane.showMessageDialog(null, outputS);
                    }//end if
            else{
                JOptionPane.showMessageDialog(null, "The queue is not opened.");
                }//end else
            
        go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));   
        
        }//end of validation
        }//end while
    }//end of list patients
    
    public static void reassignPatients(){//start of reassign
        int x, sourceQ, targetQ,go = 1;
        
        while(go == 1){
            sourceQ = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue would you like to reassign patients from?"));
            if(sourceQ > 1 && sourceQ < MAX){//validate sourceQ
            if(openFlag[sourceQ-1] == true){
                while(theClinic[sourceQ-1].nFront != null){
                    targetQ = findSmallestQ(theClinic, sourceQ);
                    theClinic[targetQ-1].addRear(theClinic[sourceQ - 1].nFront.nInfo);
                    theClinic[sourceQ-1].removeFront();
                }//end while
                JOptionPane.showMessageDialog(null,"Your queue members have been successfully reassigned!");
            }//end if
            else{JOptionPane.showMessageDialog(null, "The source queue needs to be opened");}
            go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));//prompt to continue     
            }//end of validate sourceQ
        }//end while
    }//end of reassign
    
    public static int findSmallestQ(clinicQueue[] aClinic, int refQ){
        int smallestSize = MAX_VALUE;
        int x = 1, smallestQ = 1;
        int tempSize;
        
        for(x = 1; x <= MAX; x++){
            tempSize = aClinic[x-1].getSize();
            if(tempSize < smallestSize && openFlag[x-1] == true && x != refQ){
                smallestSize = tempSize;
                smallestQ = x;
            }//end if
        }//end for
        return smallestQ;
    }
    
    public static void checkSize(){//start of checksize
        int qChoice = 0, go = 1;
        while(go == 1){
            qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue size would you like to check?"));
            
            if(qChoice > 1 && qChoice < MAX){//validate qChoice
            if(openFlag[qChoice - 1] == true){
            String outputS = "The size of the queue" + qChoice + " is " + theClinic[qChoice-1].getSize();
            JOptionPane.showMessageDialog(null, outputS);
                    }//end if
            else{
                JOptionPane.showMessageDialog(null, "The queue is not opened.");
                }//end else
            
        go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));   
        
        }//end of validation
        }//end while
        
    }//end of checksize
    
    public static void emptyQueue(){//start of emptyqueue, empties a queue without reassigning it's contents
        int qChoice,x, go = 1;
        while(go == 1){
          qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue would you like to delete?"));  
          if(qChoice > 1 && qChoice < MAX){//validate qChoice
            if(openFlag[qChoice - 1] == true){
             for(x = 1; x <=  theClinic[x-1].length;x++){
                 theClinic[x-1].destroyMe(x);
             }//end for
          }//end if
          else{ JOptionPane.showMessageDialog(null, "Invalid queue selection");}
          go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));//prompt to continue  
          }
        }//end of while
    }//end of empty
    
    public static void closeQueue(){//start of close queue, closes a queue and reassignes it's patients to another queue
        int qChoice, x, go = 1,targetQ;
        
        while(go == 1){
            qChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "Which queue would you like to close?")); 
            if(qChoice > 1 && qChoice < MAX){//validate sourceQ
            if(openFlag[qChoice-1] == true){
                while(theClinic[qChoice-1].nFront != null){
                    targetQ = findSmallestQ(theClinic, qChoice);
                    theClinic[targetQ-1].addRear(theClinic[qChoice - 1].nFront.nInfo);
                    theClinic[qChoice-1].removeFront();
                    theClinic[qChoice-1].destroyMe(qChoice-1);
                }//end while
                JOptionPane.showMessageDialog(null,"Your queue members have been successfully reassigned!");
            }//end if
            else{JOptionPane.showMessageDialog(null, "The source queue needs to be opened");}
            go = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to continue? (1 for yes)"));//prompt to continue
            }//end of validate sourceQ
        }//end while
    }//end of close queue
    
    
}
