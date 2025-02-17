// *********************************************************************************
// GenericNode.java                                                                 
// Created on December 2, 2022                                               
// Node Item for Generic Instance Class.                             
// Author Petr Bowles                                                                 
// *********************************************************************************
package ds_ass07b_bowlesp;

/**
 *
 * @author Petr
 */
public class GenericNode {
    protected Generic nInfo;
    protected GenericNode nLeft,nRight;
    
    public GenericNode(){
        nInfo = new Generic();
        nLeft = null;
        nRight = null;    
    }//end of constructor   
    
    public GenericNode(Generic thisItem) {
        nInfo = thisItem;
        nLeft = null;
        nRight = null;
    }//end of overloadedConstructor
    
    public void modifyMe(GenericNode thisNode){
        nInfo.modifyMe(thisNode.nInfo);
        nLeft = thisNode.nLeft;
        nRight = thisNode.nRight;
    }//end of modifyMe
    
    public void inputData(int x){
        nInfo.inputData(x);
        nLeft = null;
        nRight = null;
    }//end of inputData
    
    public int getID() {
        return nInfo.getID();
    }//end of getID

    public String printMe(){
        return nInfo.printMe();
    }//end of string

}
