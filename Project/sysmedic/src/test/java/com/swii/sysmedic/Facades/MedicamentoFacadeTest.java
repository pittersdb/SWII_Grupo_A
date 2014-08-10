/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Medicamento;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author software
 */
public class MedicamentoFacadeTest {
    
    public MedicamentoFacadeTest() {
    }
    
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

    /**
     * Test of create method, of class MedicamentoFacade.
     */
    @Ignore
    public void testCreate() throws Exception {
        System.out.println("create");
        Medicamento entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class MedicamentoFacade.
     */
    @Ignore
    public void testEdit() throws Exception {
        System.out.println("edit");
        Medicamento entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class MedicamentoFacade.
     */
    @Ignore
    public void testRemove() throws Exception {
        System.out.println("remove");
        Medicamento entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class MedicamentoFacade.
     */
    @Ignore
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        Medicamento expResult = null;
        Medicamento result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class MedicamentoFacade.
     */
    @Ignore
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        List<Medicamento> expResult = null;
        List<Medicamento> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class MedicamentoFacade.
     */
    @Ignore
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetMedicamento method, of class MedicamentoFacade.
     */
    @Ignore
    public void testGetMedicamento() throws Exception {
        System.out.println("GetMedicamento");
        String name = "asde";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        String expResult = "asde";
        Medicamento result = instance.GetMedicamento(name);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existsMedicamento method, of class MedicamentoFacade.
     */
    @Ignore
    public void testExistsMedicamento() throws Exception {
        System.out.println("existsMedicamento");
        String name = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        boolean expResult = false;
        boolean result = instance.existsMedicamento(name);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Save method, of class MedicamentoFacade.
     */
    @Ignore
    public void testSave() throws Exception {
        System.out.println("Save");
        Medicamento medicamento = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        instance.Save(medicamento);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class MedicamentoFacade.
     */
    @Ignore
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        MedicamentoFacade instance = (MedicamentoFacade)container.getContext().lookup("java:global/classes/MedicamentoFacade");
        List<Medicamento> expResult = null;
        List<Medicamento> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
