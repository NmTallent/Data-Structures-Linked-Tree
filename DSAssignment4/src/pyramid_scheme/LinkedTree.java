/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pyramid_scheme;

import ADTs.TreeADT;
import DataStructures.MultiTreeNode;
import Exceptions.ElementNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author pmele
 * @param <T>
 * @version 3/28/2019
 */
public class LinkedTree<T> implements TreeADT<T> {
    /**
     * The root of the tree.
     */
    protected MultiTreeNode<T> root;
    
    /**
     * Normal constructor 
     * Create the root node which holds the input data element 
     * as in the parameter variable startElem
     * 
     * @param startElem 
     */
    public LinkedTree(T startElem) {
        root = new MultiTreeNode<>(startElem);
    }
    
    /**
     * Normal constructor 
     * Use the input startNode as the root node 
     *
     * 
     * @param startNode 
     */
    public LinkedTree(MultiTreeNode<T> startNode) {
        root = startNode;
    }
    
    /**
     * Returns the element stored at the root
     * 
     * @return root element
     */
    @Override
    public T getRootElement() {
        return (T) root.getElement();

    }
    
    /**
     * Adds a node containing the child element to another node that is already
     * in the tree containing the parent element. This particular overload of
     * the addChild method is responsible for locating the parent node, then
     * calling the other overload to complete the task.
     * @param parent An element contained by a particular node in the tree,
     * which will ultimately be given a child node
     * @param child The element to be packaged into a node and added to the tree
     * @throws ElementNotFoundException 
     */
    public void addChild(T parent, T child) throws ElementNotFoundException {
    	
    	MultiTreeNode<T> parentNode = findNode(parent);
    	
    	
    	if(parent == null || child == null) return; 
    	
    	if(parentNode == null)
    		throw new ElementNotFoundException("The inputted parent node was not found.");
    	
    		addChild(parentNode, child);
    	
        
    }
    
    /**
     * Adds a node containing the child element to another node that is already
     * in the tree containing the parent element. This particular overload of
     * the addChild method is responsible for creating a node for child and
     * adding it to the children of parentNode.
     * @param parentNode the node receiving a child node
     * @param child the element to be packaged and added as a child node
     */
    protected void addChild(MultiTreeNode<T> parentNode, T child) {
    	MultiTreeNode<T>childNode = new MultiTreeNode<T>(child);
    	parentNode.addChild(childNode);
    	
    	return;
    }
    
    /**
     * Finds the node that contains a target element. Calls the recursive
     * overload to search for target.
     * @param target the element being searched for
     * @return the MultiTreeNode containing the target, or null if not found
     */
    public MultiTreeNode<T> findNode(T target) {
        if (target == null) return null;
        return findNode(root, target);
    }
    
    /**
     * Finds the node that contains a target element. This checks the current
     * node, and if it is the target, returns it. Otherwise, it recursively
     * checks each of the current node's children, to see if they can find it.
     * If none of this node's children can find it either,
     * then null is returned.
     * @param node the node currently being examined
     * @param target the element being searched for
     * @return the MultiTreeNode containing the target, or null if not found
     */
    private MultiTreeNode<T> findNode(MultiTreeNode<T> node, T target) {
        //If this node is the one holding the target...
        if (node.getElement().equals(target)) {
            return node; //...Return this node, so it is passed upwards.
        } else { //Otherwise...
            MultiTreeNode<T> temp;
            //For each child of this node...
            for (MultiTreeNode<T> child : node.getChildren()) {
                //...Call this method, seeing if the child can find our target.
                temp = findNode(child, target);
                //If it isn't null, then the child has found our target, and...
                if (temp != null) {
                    return temp; //...The target is passed upwards.
                }
            }
            //If none of the children found the target, return null.
            return null; //This signifies that no target was found.
        }    
    }

    /**
     * Tries to find a node that contains the target element, and indicates
     * true if it can, false if it cannot.
     * @param target the element being searched for.
     * @return a boolean representing whether or not the tree contains
     * a node with the target element.
     */
    @Override
    public boolean contains(T target) {
    	if(findNode(root, target) != null) {
    		return true;
    	}
        return false;
    }
    
    /**
     * Returns the size of the tree, measured by how many nodes are in it.
     * Calls the recursive method countDown to determine this.
     * @return the size of the tree.
     */
    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return countDown(root);
    }
    
    /**
     * Recursively counts the current node, its children, and so on.
     * Used by size() to determine how many nodes are in the tree.
     * @param node the node currently being examined.
     * @return the amount of nodes from the starting node down.
     */
    private int countDown(MultiTreeNode<T> node) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (node == null) return 0;
        
        if (node.getNumChildren() == 0) return 1;
        
        int count = 1;
        for (MultiTreeNode<T> child : node.getChildren()){
            count += countDown(child);
        }
        return count;
    }
    
    /**
     * 
     * @return A series of lines demonstrating elements on each layer.
     */
    @Override
    public String toString() {
        String print = "Linked Tree: \nLayer 1 (Root): " + root.getElement();
        ArrayList<MultiTreeNode<T>> layer = root.getChildren();
        ArrayList<MultiTreeNode<T>> nextLayer;
        int layerNum = 2;
        while (!layer.isEmpty()) {
            print += "\nLayer " + layerNum + ":";
            nextLayer = new ArrayList<>();
            for (MultiTreeNode<T> node : layer) {
                nextLayer.addAll(node.getChildren());
                print += " " + node;
            }
            layer = nextLayer;
            layerNum++;
        }
        return print;
    }
}
