/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Medicacion;
import com.swii.sysmedic.entities.Turno;
import javax.ejb.EJB;
import com.swii.sysmedic.entities.Paciente;
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
public class ConsultaFacade extends AbstractFacade<Consulta> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @EJB
    private TurnoFacade turnoFacade;
    @EJB
    private CitaFacade citaFacade;
    @EJB
    private MedicacionFacade medicacionFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ConsultaFacade() {
        super(Consulta.class);
    }
    
    public void SaveMediciones(Turno turno){
        Consulta consulta = turno.getCita().getConsulta();
        consulta.setCita(turno.getCita());
        boolean isEditable = false;
        if(!consulta.getCita().getEstado().equals(Cita.Estado.Terminado.toString())){
            if(consulta.getId() != null){
                isEditable = true;
            }
        }
        consulta.getCita().setEstado(Cita.Estado.EnProgreso.toString());
        this.citaFacade.edit(consulta.getCita());
        if(isEditable)
            this.edit(consulta);
        else
            this.create(consulta);
    }
    
    public void SaveCompleteConsulta(Consulta consulta){
        this.edit(consulta);
    }
    
    public Consulta GetByCita(Cita cita){
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findByCita", Consulta.class);
        query.setParameter("cita", cita);
        try{
            return query.getSingleResult();
        }catch(javax.persistence.NoResultException e){
            return new Consulta();
        }
    }
    
    
    public void AddMedicacion(Consulta consulta, Medicacion medicacion){
        consulta.getMedicacionCollection().add( medicacion);
        this.edit(consulta);
    }
    
    public void EditMedicacion(Consulta consulta){
        this.edit(consulta);
    }
    
    public void DeleteMedicacion(Consulta consulta, Medicacion medicacion){        
        //medicacionFacade.remove(medicacion);
         consulta.getMedicacionCollection().remove(medicacion);
        this.edit(consulta);       
    }
    
public Consulta GetConsultaByIdPaciente(Paciente paciente) {
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findPacienteById", Consulta.class);
        
//        paciente = paciente!=null ? paciente:new Paciente();
//        boolean nullPaciente = paciente.getId()==null;
//        query.setParameter("nullPaciente", nullPaciente);
        query.setParameter("paciente", paciente);
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public Consulta GetConsultaById(int id) {
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.findById", Consulta.class);
        
//        paciente = paciente!=null ? paciente:new Paciente();
//        boolean nullPaciente = paciente.getId()==null;
//        query.setParameter("nullPaciente", nullPaciente);
        query.setParameter("id", id);
        List matches = query.getResultList();
        if(matches == null || matches.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
}
