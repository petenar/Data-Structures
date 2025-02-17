// *********************************************************************************
// GenericTree.java                                                                 
// Created on December 2, 2022                                               
// GenericTree that creates a Generic Binary Tree to sort Generic Instance Nodes.                             
// Author Petr Bowles                                                                 
// *********************************************************************************
package ds_ass07b_bowlesp;

import java.util.ArrayList;

/**
 *
 * @author Petr
 */
public class GenericTree {
    protected GenericNode root;
    protected int size, travRef;
    ArrayList <Generic> travResult;
    CollegeMember dummy;
    
    public GenericTree(){
        root = null;
        size = 0;
        travRef = 0;
        travResult = null;
        dummy = new CollegeMember();
    }//end of constructor
    
    public void addRoot(Generic thisItem){
        if(root==null) {
            root = new GenericNode();
            root.nInfo.modifyMe(thisItem);
            root.nLeft = root.nRight = null;
            size++;
        }
    }//end of addRoot
    
    public void addLeftLeaf(GenericNode thisLeaf, Generic thisItem){
        GenericNode newNode = new GenericNode();
        newNode.nInfo.modifyMe(thisItem);
        newNode.nLeft = newNode.nRight = null;
        thisLeaf.nLeft = newNode;
        size++;
    }//end of addLeftLeaf
    
    public void addRightLeaf(GenericNode thisLeaf, Generic thisItem){
        GenericNode newNode = new GenericNode();
        newNode.nInfo.modifyMe(thisItem);
        newNode.nLeft = newNode.nRight = null;
        thisLeaf.nRight = newNode;
        size++;
    }//end of addRightLeaf
    
    public void addLeftSubtree(GenericNode thisNode, GenericNode newNode){
        GenericNode Temp = new GenericNode(), leftEnd = new GenericNode();
        Temp = thisNode.nLeft;  
        leftEnd = newNode; 
        while(leftEnd.nLeft!=null)
            leftEnd = leftEnd.nLeft;   
        thisNode.nLeft = newNode; 
        leftEnd.nLeft = Temp;
        setSize(thisNode);
    }//end of addLeftSubtree
    
    public void addRightSubtree(GenericNode thisNode, GenericNode newNode){
        GenericNode Temp = new GenericNode(), rightEnd = new GenericNode();
        Temp = thisNode.nRight;  
        rightEnd = newNode; 
        while(rightEnd.nRight!=null)
            rightEnd = rightEnd.nRight;   
        thisNode.nRight = newNode; 
        rightEnd.nRight = Temp;
        setSize(thisNode);
    }//end of addRightSubtree
    
    public void insert(GenericNode thisNode, Generic thisItem){
        GenericNode newNode, currentNode; 
        if(thisNode == null) {
            newNode = new GenericNode(thisItem); 
            newNode.nInfo.modifyMe(thisItem); 
            newNode.nLeft = newNode.nRight = null;
            root = newNode; 
        }
        else{
            currentNode = findInsertionPoint(thisNode, thisItem);
            if(thisItem.getID() < currentNode.nInfo.getID()){
                addLeftLeaf(currentNode, thisItem);
            }
            else {
                addRightLeaf(currentNode, thisItem); 
            }
        }
        size++;
    }//end of insert
    
    public GenericNode findInsertionPoint(GenericNode thisNode, Generic thisItem){
        GenericNode insertPoint = thisNode;
        if(thisItem.getID() < insertPoint.nInfo.getID()) {
            if(insertPoint.nLeft!=null){ 
                insertPoint = findInsertionPoint(insertPoint.nLeft, thisItem);
            }
            else 
                if (insertPoint.nRight!=null){ 
                    insertPoint = findInsertionPoint(insertPoint.nRight, thisItem);
                }
        }
        return insertPoint;
    }//end of findInsertionPoint
    
    public void removeSubtree(GenericNode thisNode){
        if(thisNode.nLeft == null && thisNode.nRight == null) {
            kill(thisNode);
            size--;
        }
        else {
            if(thisNode.nLeft != null) 
                removeSubtree(thisNode.nLeft); 
            if(thisNode.nRight != null) 
                removeSubtree(thisNode.nRight); 
            kill(thisNode);
        }  
    }//end of removeSubtree
    
    public void kill(GenericNode thisNode) {
        int key;
        try {
            key = thisNode.getID();
            root = killRec(root, key);
            size--;
        }
        catch (NullPointerException e) {

        }               
    }
    
    public GenericNode killRec(GenericNode Root, int key) {

        if (Root == null) {return Root;}
        
        if (key < Root.getID()) {
            Root.nLeft = killRec(Root.nLeft, key);
        } 
        else if (key > Root.getID()) {
            Root.nRight = killRec(Root.nRight, key);
        } 
        else {
            if (Root.nLeft == null) {return Root.nRight;} 
            else if (Root.nRight == null) {
                return Root.nLeft;
            }
            key = minValue(Root.nRight);
            Root.nRight = killRec(Root.nRight, key);
        }
        return Root;
    }
    
    public int minValue(GenericNode Root) {
        int value = Root.getID();
        while (Root.nLeft != null) {
            value = Root.nLeft.getID();
            Root = Root.nLeft;
        }
        return value;
    }
    
    public GenericNode search(Generic searchArg){
        GenericNode currentNode, soughtNode;
        currentNode = root; soughtNode= null;
        try{
            while(currentNode.nInfo.getID()!=searchArg.getID() && currentNode.nInfo!=null) {
                if(searchArg.getID()<currentNode.nInfo.getID())
                    currentNode = currentNode.nLeft;
                else
                    currentNode = currentNode.nRight;
            }
            if(currentNode.nInfo.getID()== searchArg.getID()) {
                soughtNode = new GenericNode();
                soughtNode.modifyMe(currentNode);
                soughtNode = currentNode;
            }
        }
        catch(NullPointerException e){}
        return soughtNode;  
    }//end of search
    
    public void modifyMe(GenericNode thisNode, Generic thisItem){
        thisNode.nInfo.modifyMe(thisItem);
    }//end of modifyMe
    
    public void clearTree(){
        removeSubtree(root);
    }//end of clearTree
    
    public Generic getInfo(GenericNode thisNode){
        return thisNode.nInfo;
    }//end of getInfo
    
    public GenericNode getNode(GenericNode thisNode){
        return thisNode;
    }//end of getNode
    
    public int getSize(){
        size = 0;
        setSize(root);
        return size;
    }//end of getSize
    
    public boolean isEmpty() {
        boolean flag;
        if(getSize()==0){flag = true;}
        else{flag = false;}
        return flag;
    }
    
    public void inOrderPrep() {
        travResult = new <Generic> ArrayList();
        setSize(root);
    }
    
    public void inOrderTraversal(GenericNode thisNode) {
        Generic anyItem;
        if(thisNode!=null) {
            inOrderTraversal(thisNode.nLeft);
            anyItem = new Generic(dummy);  
            anyItem.modifyMe(thisNode.nInfo); 
            travResult.add(anyItem);
            inOrderTraversal(thisNode.nRight);
        }
    }
    
    public void setSize(GenericNode thisNode){
        GenericNode currentNode;
        currentNode = thisNode;
        if(currentNode!=null) {
            if(currentNode==root)
                size++;
            if(currentNode.nLeft!=null) {
                size++;
                currentNode=currentNode.nLeft;
                setSize(currentNode);
            }
            if(currentNode.nRight!=null) {
                size++;
                currentNode= currentNode.nRight;
                setSize(currentNode);
            }
        }  
    }//end of setSize
    
}//end of GenericTree class
