/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Antecedente;
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
public class AntecedenteFacade extends AbstractFacade<Antecedente> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntecedenteFacade() {
        super(Antecedente.class);
    }
    
    public Antecedente findByNombreOrCreate(String nombre){
        TypedQuery<Antecedente> query = em.createNamedQuery("Antecedente.findByNombre", Antecedente.class);
        query.setParameter("nombre", nombre);
        List<Antecedente> result = query.getResultList();
        if(result != null && !result.isEmpty()){
            return result.get(0);
        }else{
            Antecedente newAntecedente = new Antecedente();
            newAntecedente.setNombre(nombre);
            this.create(newAntecedente);
            return newAntecedente;
        }            
    }
    
}
