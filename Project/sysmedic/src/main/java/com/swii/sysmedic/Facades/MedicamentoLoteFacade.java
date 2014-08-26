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
    
    public long getNewCodigoLote(int idMedicamento){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamento", MedicamentoLote.class);
        query.setParameter("medicamentoId", idMedicamento);
        List lotes = query.getResultList();
        if(lotes.isEmpty()){
            return (long)1;
        }
        else{
            return getCodeOfLastLote(lotes) + 1;//((long) query.getResultList().get(lotes.size()-1).getCodigoLote()) + ((long) 1);
        }
    }
    
    public long getCodeOfLastLote(List<MedicamentoLote> lotes){
        long last=0;
        for(MedicamentoLote a: lotes){
            if(a.getCodigoLote() > last){
                last = a.getCodigoLote();
            }
        }
        return last;
    }
    
    public List<MedicamentoLote> getLotesbyMedicamento(int idMed){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByMedicamentoAndEstado", MedicamentoLote.class);
        query.setParameter("medicamentoId", idMed);
        List lotes = query.getResultList();
        
        return lotes;
    }
    
    public List<MedicamentoLote> getLotesbyEstado(String a){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.findByEstado", MedicamentoLote.class);
        query.setParameter("estado", a);
        List lotes = query.getResultList();
        
        return lotes;
    }
        
    public void updateEstadoA(String estado, int id){
        TypedQuery<MedicamentoLote> query = em.createNamedQuery("MedicamentoLote.updateEstadoLoteA", MedicamentoLote.class);
        query.setParameter("estado", estado);
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
