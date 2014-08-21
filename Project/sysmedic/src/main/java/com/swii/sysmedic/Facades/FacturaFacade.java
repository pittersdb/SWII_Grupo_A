/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Factura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabian
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
       
    public Factura GetFactura(Integer numero) {
        TypedQuery<Factura> query = em.createNamedQuery("Factura.findByNumero", Factura.class);
        query.setParameter("numero", numero);
        List factura = query.getResultList();
        if(factura == null || factura.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public void Insert(Factura factura){
        create(factura);
    }
    
    @Override
    public List<Factura> findAll() {
        List<Factura> factura = em.createNamedQuery("Factura.findAll", Factura.class).getResultList();
        return factura;
    }
    
}
