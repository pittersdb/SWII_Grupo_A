/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.MedicamentoInventarioOperacion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabian
 */
@Stateless
public class MedicamentoInventarioOperacionFacade extends AbstractFacade<MedicamentoInventarioOperacion> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoInventarioOperacionFacade() {
        super(MedicamentoInventarioOperacion.class);
    }
    
}
