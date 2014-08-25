/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package com.swii.sysmedic.Views;

import com.swii.sysmedic.Facades.MedicoFacade;
import com.swii.sysmedic.Facades.TurnoFacade;
import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Consulta;
import com.swii.sysmedic.entities.Medico;
import com.swii.sysmedic.entities.Turno;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author LUCAS
 */
public class TurnoView {
    
    
    @EJB
    private TurnoFacade turnoFacade;
    @EJB
    private MedicoFacade medicoFacade;
    private Turno turno = new Turno();
    private List<Turno> orderedTurnos = new ArrayList<Turno>();
    private Turno current;
    private Turno next;
    private int selectedMedicoId;
    private Medico selectedMedico;
    
    
    /**
     * Creates a new instance of TurnoView
     */
    public TurnoView() {
        
    }
    
    @PostConstruct
    public void init() {
        List<Medico> allMedicos = this.medicoFacade.findAll();
        Medico medicoTarget = null;
        if(!allMedicos.isEmpty() && !UsersView.getLoggedUser().isMedic())
            medicoTarget = allMedicos.get(0);
        
        if(UsersView.getLoggedUser().isMedic())
            medicoTarget = UsersView.getLoggedUser().getMedico();
        
        if(medicoTarget != null){
            this.orderedTurnos.addAll(this.turnoFacade.GetAllTurnosByMedico(medicoTarget));
            this.selectedMedicoId = medicoTarget.getId();
            this.setSelectedMedico(medicoTarget);
        }
    }
    
    public Turno getTurno() {
        return turno;
    }
    
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    
    public List<Turno> getOrderedTurnos() {
        if(this.orderedTurnos.isEmpty()){
            this.orderedTurnos.addAll(this.turnoFacade.GetAllTurnosByMedico(getSelectedMedico()));
        }
        return orderedTurnos;
    }
    
    public void setOrderedTurnos(List<Turno> resultSet) {
        this.orderedTurnos = resultSet;
    }
    
    public Turno getCurrent() {
        if(current == null){
            if(this.orderedTurnos != null ){
                if(this.orderedTurnos.isEmpty())
                    this.getOrderedTurnos();
                if(!this.orderedTurnos.isEmpty()){
                    current = this.orderedTurnos.get(0);
                }
            }
        }
        if(current != null && current.getCita().getConsulta() == null)
            current.getCita().setConsulta(new Consulta());
        return current;
    }
    
    public void setCurrent(Turno current) {
        this.current = current;
    }
    
    public Turno getNext() {
        if(next == null){
            if(this.orderedTurnos != null ){
                if(this.orderedTurnos.isEmpty())
                    this.getOrderedTurnos();
                if(!this.orderedTurnos.isEmpty()){
                    if(this.orderedTurnos.size() >= 2)
                        next = this.orderedTurnos.get(1);
                }
            }
        }
        if(next != null && next.getCita().getConsulta() == null)
            next.getCita().setConsulta(new Consulta());
        return next;
    }
    
    public void setNext(Turno next) {
        this.next = next;
    }
    
    public int getSelectedMedicoId() {
        return selectedMedicoId;
    }
    
    public void setSelectedMedicoId(int selectedMedico) {
        this.selectedMedicoId = selectedMedico;
    }
    
    public Medico getSelectedMedico() {
         if(selectedMedico == null || (selectedMedico != null && selectedMedico.getId() != selectedMedicoId))      
            selectedMedico = this.medicoFacade.find(selectedMedicoId);
        return selectedMedico;
    }
    
    public void setSelectedMedico(Medico selectedMedico) {
        this.selectedMedico = selectedMedico;
    }
    
    public void UpdateOrderedTurnos(){
        this.orderedTurnos.clear();
        this.orderedTurnos.addAll(this.turnoFacade.GetAllTurnosByMedico(getSelectedMedico()));
    }
    
    public void Assign(Cita cita){
        try{
            Turno turnoToAssign = this.turnoFacade.Assign(cita);
            this.orderedTurnos.add(turnoToAssign);
            if( !this.orderedTurnos.isEmpty())
                this.setCurrent(this.orderedTurnos.get(0));
            else
                this.setCurrent(null);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void CancelCita(Turno turn){
        try{
            this.turnoFacade.CancelTurno(turn,true);
            this.orderedTurnos.remove(turn);
            if( !this.orderedTurnos.isEmpty())
                this.setCurrent(this.orderedTurnos.get(0));
            else
                this.setCurrent(null);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void Descartar(Turno turn){
        try{
            this.turnoFacade.CancelTurno(turn,false);
            this.orderedTurnos.remove(turn);
            if( !this.orderedTurnos.isEmpty())
                this.setCurrent(this.orderedTurnos.get(0));
            else
                this.setCurrent(null); 
            //System.out.println("CONSULTA DESC: "+ this.getCurrent().getCita().getPaciente().getNombres());
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void FinishAndNext(){
        try{
            Turno nextTurno = this.turnoFacade.FinishCitaAndNextTurno(current, orderedTurnos);
            this.setCurrent(nextTurno);
        }catch(Exception e){
            FacesContext.getCurrentInstance().validationFailed();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
//    public void Posponer(){
//        try{
//            int index = this.orderedTurnos.indexOf(current);
//            if(index+1 == this.orderedTurnos.size()){//There is not a next turn
//                FacesContext.getCurrentInstance().validationFailed();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Atencion", "Este es el ultimo turno del dia por ahora"));
//            }else{
//                this.turnoFacade.Posporner(orderedTurnos);
//                this.setCurrent(this.orderedTurnos.get(0));
//            }
//        }catch(Exception e){
//            FacesContext.getCurrentInstance().validationFailed();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Error del Sistema", "Contacte a soporte tecnico para gestionar este error. \n "+e.getMessage()));
//            Logger.getLogger(TurnoView.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
}
