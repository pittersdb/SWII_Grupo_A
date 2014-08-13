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
    
    
    public MedicamentoLote GetLote(Long lote, int idMedicamento) {
        //TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByCodigoLote", MedicamentoLote.class);
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamentoAndLote", MedicamentoLote.class);
        query.setParameter("medicamentoId", idMedicamento);
        query.setParameter("codigoLote", lote);
        List lotes = query.getResultList();
        if(lotes == null || lotes.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
//    public boolean existsLote(Long lote, int idMedicamento){
//        return GetLote(lote, idMedicamento) != null;
//    }
//    
    public long getNewCodigoLote(int idMedicamento){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamento", MedicamentoLote.class);
        query.setParameter("medicamentoId", idMedicamento);
        List lotes = query.getResultList();
        if(lotes.isEmpty()){
            return (long)1;
        }
        else{
        //query.getResultList().get(lotes.size());
            return 1;
        }
    }
    
    public void SaveLote(MedicamentoLote lote){
        create(lote);
    }
    
    @Override
    public List<MedicamentoLote> findAll() {
        List<MedicamentoLote> contacts = em.createNamedQuery("MedicamentoLote.findAll", MedicamentoLote.class).getResultList();
        return contacts;
    }
}
