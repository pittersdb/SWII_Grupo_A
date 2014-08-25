/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Medicamento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
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
    
    public Medicamento LoadCompleteMedicina(String nombre){
        Medicamento medicina = GetMedicamento(nombre);
        
        return medicina;
    }
    
    public boolean existsMedicamento(String name){
        return GetMedicamento(name) != null;
    }
    
    public void Save(Medicamento medicamento){
        create(medicamento);
    }
    
    public List<SelectItem> list(){
        List<SelectItem> listMedicamentos = new ArrayList<SelectItem>();
        List<Medicamento> medicamentos = em.createNamedQuery("Medicamento.findAll", Medicamento.class).getResultList();
        
        for (Medicamento medicamento : medicamentos) {
            listMedicamentos.add(new SelectItem(medicamento.getNombre()));
        }
        
        return listMedicamentos;
    }
    
    @Override
    public List<Medicamento> findAll() {
        List<Medicamento> medicamentos = em.createNamedQuery("Medicamento.findAll", Medicamento.class).getResultList();
        return medicamentos;
    }
    
      public List<Medicamento> GetMedicamentoByNombre(String name) {
        TypedQuery<Medicamento> query = em.createNamedQuery("Medicamento.findByNombreCercano", Medicamento.class);
        query.setParameter("nombre", "%"+name.toLowerCase()+"%");
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList();
    }
    
}
