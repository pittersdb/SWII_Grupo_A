/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.MedicamentoLote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.event.ValueChangeEvent;
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
    
    
//    public MedicamentoLote getLote(Long lote, int idMedicamento) {
//        //TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByCodigoLote", MedicamentoLote.class);
//        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamentoAndLote", MedicamentoLote.class);
//        query.setParameter("medicamentoId", idMedicamento);
//        query.setParameter("codigoLote", lote);
//        List lotes = query.getResultList();
//        if(lotes == null || lotes.isEmpty())
//            return null;
//        else
//            return query.getResultList().get(0);
//    }
    
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
            return ((long) query.getResultList().get(lotes.size()-1).getCodigoLote()) + ((long) 1);
        }
    }
    
    public List<MedicamentoLote> getLotesbyMedicamento(int idMed){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamento", MedicamentoLote.class);
        query.setParameter("medicamentoId", idMed);
        List lotes = query.getResultList();
        
        return lotes;
    }
    
    public void updateEstado(int id){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.updateEstadoLote", MedicamentoLote.class);
        query.setParameter("id", id);
        int updated = query.executeUpdate();
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
