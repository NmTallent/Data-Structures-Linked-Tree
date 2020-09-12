package pyramid_scheme;

import DataStructures.MultiTreeNode;
import Exceptions.ElementNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Solution Test file for LinkedTree
 * 
 * @author pmele
 * @version 3/28/2019
 */
public class LinkedTreeTest {
    
    private LinkedTree<String> instance;
    private MultiTreeNode<String> root;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    
    /**
     * Sets up the later tests.
     */
    @Before
    public void initialize() {
        s1 = "Elem 1";
        s2 = "Elem 2";
        s3 = "Elem 3";
        s4 = "Elem 4";
        s5 = "Elem 5";
        s6 = null;
        root = new MultiTreeNode<>(s1);
        instance = new LinkedTree<>(root);
    }

    /**
     * Test of getRootElement method, of class LinkedTree.
     */
    @Test
    public void testGetRootElement() {
        //todo fail("Test not yet created");
        initialize();
        assertTrue(s1.equals(instance.getRootElement()));
    }

    /**
     * Test of addChild method, of class LinkedTree.
     */
    @Test
    public void testAddChild() {
        //todo fail("Test not yet created");
        initialize();
        try{
            //correct case
            instance.addChild(s1, s2);
            assertTrue(instance.size() == 2);
            MultiTreeNode<String> rootNode = instance.findNode(s1);
            assertTrue(s2.equalsIgnoreCase(rootNode.getChild(0).getElement()));
            assertTrue(rootNode.getNumChildren() == 1);
            
            instance.addChild(s1, s3);
            assertTrue(instance.size() == 3);
            assertTrue(s3.equalsIgnoreCase(rootNode.getChild(1).getElement()));
            assertTrue(rootNode.getNumChildren() == 2);
        }catch (Exception ex){
            if (ex instanceof ElementNotFoundException){
                assertTrue(true);
            }else {
                assertTrue(false);
            }
        }
        
        
        initialize();
        // failure cases, parent or child are null or parent node does not exist.
        
        // this tests the case that the parent node is not found in the tree, throws elementNotFound exception.
        try {
        	instance.addChild(s3, s4);
        	assertTrue(1 == 2);
        }catch(ElementNotFoundException e){
        	assertTrue(1 == 1);
        }
        
        
        // checks using a null parent node doesnt do anything.
        try {
        	root.setElement(null);
            instance.addChild(s6, s1);
            assertEquals(root.getChild(0), null);
        }catch(ElementNotFoundException e) {
        	assertEquals(1,2);
        }
        // tests if adding a null child node changes the tree ( it shouldnt)
         initialize();
         ArrayList<MultiTreeNode<String>> before = root.getChildren();
         instance.addChild(root,s6);
         ArrayList<MultiTreeNode<String>> after = root.getChildren();
         assertEquals(before, after);
        
         
        
    }

    /**
     * Test of findNode method, of class LinkedTree.
     */
    @Test
    public void testFindNode() {
        initialize();
        try {
            //Can find root
            assertEquals(s1, instance.findNode(s1).getElement());
            instance.addChild(s1, s2);
            //Can find a child node
            assertEquals(s2, instance.findNode(s2).getElement());
            instance.addChild(s2, s3); //Add several deep
            instance.addChild(s3, s4);
            instance.addChild(s4, s5);
            //Can find a child node deep within tree
            assertEquals(s5, instance.findNode(s5).getElement());
            //Trying to find things not in the tree returns null
            assertEquals(null, instance.findNode("Unreal element"));
        } catch (Exception ex) {
            fail("Unexpected Exception");
        }
    }

    /**
     * Test of contains method, of class LinkedTree.
     */
    @Test
    public void testContains() {
        //todo fail("Test not yet created");
        initialize();
        try {
            //Can find root
            assertTrue(instance.contains(s1));
            instance.addChild(s1, s2);
            //Can find a child node
            assertTrue(instance.contains(s2));
            instance.addChild(s2, s3); //Add several deep
            instance.addChild(s3, s4);
            instance.addChild(s4, s5);
            assertTrue(instance.contains(s3));
            assertTrue(instance.contains(s4));
            assertTrue(instance.contains(s5));
        } catch (Exception ex) {
            fail("Unexpected Exception");
        }
        
        // tests when adding a null child, the tree should not contain said child.
        initialize();
        try {
        	instance.addChild(s1, s6);
        	assertFalse(instance.contains(s6));
        }catch(ElementNotFoundException e) {
        	assertTrue(false);
        }
       
        
    }

    /**
     * Test of size method, of class LinkedTree.
     */
    @Test
    public void testSize() {
        //todo fail("Test not yet created");
        initialize();
        try {
            assertTrue(instance.size() == 1);
            instance.addChild(s1, s2);
            assertTrue(instance.size() == 2);
            instance.addChild(s2, s3); //Add several deep
            instance.addChild(s3, s4);
            instance.addChild(s4, s5);
            assertTrue(instance.size() == 5);
        } catch (Exception ex) {
            fail("Unexpected Exception");
        }
        //  tests that adding a null child does not change the size.
        initialize();
        try {
        	int beforeSize = instance.size();
            instance.addChild(s1, s6);
            int afterSize = instance.size();
            assertEquals(beforeSize, afterSize);
            
        }catch(ElementNotFoundException e) {
        	assertTrue(false);
        }
        
        // Test case: using a null root should result in size = 0
        initialize();
        root = null;
        instance = new LinkedTree<>(root);
        assertEquals(instance.size(), 0);
        
    }
    
}
