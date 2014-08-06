/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.MedicamentoLote;
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
public class MedicamentoLoteFacade extends AbstractFacade<MedicamentoLote> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoLoteFacade() {
        super(MedicamentoLote.class);
    }
    
    public MedicamentoLote GetLote(Long lote) {
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByCodigoLote", MedicamentoLote.class);
        query.setParameter("codigoLote", lote);
        List lotes = query.getResultList();
        if(lotes == null || lotes.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public boolean existsLote(Long lote){
        return GetLote(lote) != null;
    }
    
    public void SaveLote(MedicamentoLote lote){
        create(lote);
    }
}
