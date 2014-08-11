/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.Util.Config;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.embeddable.EJBContainer;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author LUCAS
 */
public class UsersFacadeTest {
//
//    public UsersFacadeTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of create method, of class UsersFacade.
//     */
//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.create(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of edit method, of class UsersFacade.
//     */
//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.edit(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class UsersFacade.
//     */
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.remove(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of find method, of class UsersFacade.
//     */
//    @Test
//    public void testFind() throws Exception {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        Users expResult = null;
//        Users result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findRange method, of class UsersFacade.
//     */
//    @Test
//    public void testFindRange() throws Exception {
//        System.out.println("findRange");
//        int[] range = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        List<Users> expResult = null;
//        List<Users> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//
//    public UsersFacadeTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of create method, of class UsersFacade.
//     */
//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.create(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of edit method, of class UsersFacade.
//     */
//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.edit(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class UsersFacade.
//     */
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.remove(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of find method, of class UsersFacade.
//     */
//    @Test
//    public void testFind() throws Exception {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        Users expResult = null;
//        Users result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findRange method, of class UsersFacade.
//     */
//    @Test
//    public void testFindRange() throws Exception {
//        System.out.println("findRange");
//        int[] range = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        List<Users> expResult = null;
//        List<Users> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//
//    public UsersFacadeTest() {
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of create method, of class UsersFacade.
//     */
//    @Test
//    public void testCreate() throws Exception {
//        System.out.println("create");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.create(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of edit method, of class UsersFacade.
//     */
//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.edit(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class UsersFacade.
//     */
//    @Test
//    public void testRemove() throws Exception {
//        System.out.println("remove");
//        Users entity = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.remove(entity);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of find method, of class UsersFacade.
//     */
//    @Test
//    public void testFind() throws Exception {
//        System.out.println("find");
//        Object id = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        Users expResult = null;
//        Users result = instance.find(id);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findRange method, of class UsersFacade.
//     */
//    @Test
//    public void testFindRange() throws Exception {
//        System.out.println("findRange");
//        int[] range = null;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        List<Users> expResult = null;
//        List<Users> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of count method, of class UsersFacade.
//     */
//    @Test
//    public void testCount() throws Exception {
//        System.out.println("count");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        int expResult = 0;
//        int result = instance.count();
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of GetUser method, of class UsersFacade.
//     */
    @Ignore
    public void testGetUser() throws Exception {
        String nickName = "gchavez";
        
        Map<String, Object> props = new HashMap<String, Object>();
        
        props.put(EJBContainer.MODULES, new File("target/classes"));
        props.put("org.glassfish.ejb.embedded.glassfish.configuration.file", Config.getInstance().getProperty("server.config.file"));
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer(props);
        
        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
        System.out.println("Inserting entities...");
        
        String expResultName = "Gabriel";
        String resultName = instance.GetUser(nickName).getName();
        assertEquals(expResultName, resultName);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
//        fail("Data cannot be retrieved.");
    }
//
//    /**
//     * Test of LoadCompleteUser method, of class UsersFacade.
//     */
//    @Test
//    public void testLoadCompleteUser() throws Exception {
//        System.out.println("LoadCompleteUser");
//        String nickname = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        Users expResult = null;
//        Users result = instance.LoadCompleteUser(nickname);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of existsUser method, of class UsersFacade.
//     */
//    @Test
//    public void testExistsUser() throws Exception {
//        System.out.println("existsUser");
//        String nickname = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        boolean expResult = false;
//        boolean result = instance.existsUser(nickname);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of Save method, of class UsersFacade.
//     */
//    @Test
//    public void testSave() throws Exception {
//        System.out.println("Save");
//        Users user = null;
//        String selectedRol = "";
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.Save(user, selectedRol);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of SaveAsMedic method, of class UsersFacade.
//     */
//    @Test
//    public void testSaveAsMedic() throws Exception {
//        System.out.println("SaveAsMedic");
//        Users user = null;
//        int selectedEspecialidad = 0;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        instance.SaveAsMedic(user, selectedEspecialidad);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of UpdateWithConstraints method, of class UsersFacade.
//     */
//    @Test
//    public void testUpdateWithConstraints() throws Exception {
//        System.out.println("UpdateWithConstraints");
//        Users user = null;
//        String selectedRol = "";
//        int selectedEspecialidad = 0;
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        Users expResult = null;
//        Users result = instance.UpdateWithConstraints(user, selectedRol, selectedEspecialidad);
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class UsersFacade.
//     */
//    @Test
//    public void testFindAll() throws Exception {
//        System.out.println("findAll");
//        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
//        UsersFacade instance = (UsersFacade)container.getContext().lookup("java:global/classes/UsersFacade");
//        List<Users> expResult = null;
//        List<Users> result = instance.findAll();
//        assertEquals(expResult, result);
//        container.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
