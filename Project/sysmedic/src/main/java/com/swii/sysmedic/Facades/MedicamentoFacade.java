/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Medicamento;
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
public class MedicamentoFacade extends AbstractFacade<Medicamento> {

    //private Medicamento medicamento = new Medicamento();
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoFacade() {
        super(Medicamento.class);
    }
    
    public Medicamento GetMedicamento(String name) {
        TypedQuery<Medicamento> query = em.createNamedQuery("Medicamento.findByNombre", Medicamento.class);
        query.setParameter("nombre", name.toLowerCase());
        List medicamento = query.getResultList();
        if(medicamento == null || medicamento.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public boolean existsMedicamento(String name){
        return GetMedicamento(name) != null;
    }
    
    public void Save(Medicamento medicamento){
        create(medicamento);
    }
    
    @Override
    public List<Medicamento> findAll() {
        List<Medicamento> contacts = em.createNamedQuery("Medicamento.findAll", Medicamento.class).getResultList();
        return contacts;
    }
    
}
