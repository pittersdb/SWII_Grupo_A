/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author fabian
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Transactional
    public Cliente UpdateWithConstraints(Cliente cliente){
            Cliente originalCliente = this.GetCliente(cliente.getRuc());
            originalCliente.set(cliente);
            //System.out.println("USER: "+originalUser.toString());
            this.edit(originalCliente);
            
            return originalCliente;
    }
    
    public Cliente GetCliente(String name) {
        TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findByRuc", Cliente.class);
        query.setParameter("nombre", name.toLowerCase());
        List cliente = query.getResultList();
        if(cliente == null || cliente.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    @Override
    public List<Cliente> findAll() {
        List<Cliente> contacts = em.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
        return contacts;
    }
    
    
}
