/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.entities;

import com.swii.sysmedic.Facades.AntecedenteFacade;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;

/**
 *
 * @author LUCAS
 */
public class AntecedentesGroup {
    
            //BEGIN fIxed antencedentes
    private PacienteAntecedente tipoSangre;
    private PacienteAntecedente tabaquismo;
    private PacienteAntecedente alcoholismo ;
    private PacienteAntecedente diabetes;
    private PacienteAntecedente hipertension;
    private PacienteAntecedente infartos;
    private PacienteAntecedente sida;
    private PacienteAntecedente sifilis;
    private PacienteAntecedente hemopatias;
    private PacienteAntecedente cardiopatias;
    private PacienteAntecedente ateroesclerosis ;
    private PacienteAntecedente neuropatias;
    private PacienteAntecedente artropatias;
    private PacienteAntecedente toxicomanias;
    private PacienteAntecedente alergias;
    
     
    
    public AntecedentesGroup(AntecedenteFacade antecedenteFacade){        
        tipoSangre = new PacienteAntecedente(antecedenteFacade.findByNombre("tipo_sangre"),"Op");
        tabaquismo  = new PacienteAntecedente(antecedenteFacade.findByNombre("tabaquismo"),"n");
        alcoholismo  = new PacienteAntecedente(antecedenteFacade.findByNombre("alcoholismo"),"n");
        diabetes  = new PacienteAntecedente(antecedenteFacade.findByNombre("diabetes"),"no");
        hipertension  = new PacienteAntecedente(antecedenteFacade.findByNombre("hipertension"),"no");
        infartos  = new PacienteAntecedente(antecedenteFacade.findByNombre("infartos"),"0");
        sida  = new PacienteAntecedente(antecedenteFacade.findByNombre("sida"),"no");
        sifilis  = new PacienteAntecedente(antecedenteFacade.findByNombre("sifilis"),"no");
        hemopatias  = new PacienteAntecedente(antecedenteFacade.findByNombre("hemopatias"),"no");
        cardiopatias  = new PacienteAntecedente(antecedenteFacade.findByNombre("cardiopatias"),"no");
        ateroesclerosis  = new PacienteAntecedente(antecedenteFacade.findByNombre("ateroesclerosis"),"no");
        neuropatias  = new PacienteAntecedente(antecedenteFacade.findByNombre("neuropatias"),"no");
        artropatias  = new PacienteAntecedente(antecedenteFacade.findByNombre("artropatias"),"no");
        toxicomanias  = new PacienteAntecedente(antecedenteFacade.findByNombre("toxicomanias"),"no");
        alergias  = new PacienteAntecedente(antecedenteFacade.findByNombre("alergias"),"Ninguna");
    }
    
    //End fixed antecedentes
    
    
    public void setPacienteAntecedentePK(Paciente paciente){      
        
        this.setPacienteAntecedenteItemPK(tipoSangre, paciente);
        this.setPacienteAntecedenteItemPK(tabaquismo, paciente);
        this.setPacienteAntecedenteItemPK(alcoholismo, paciente);
        this.setPacienteAntecedenteItemPK(diabetes, paciente);
        this.setPacienteAntecedenteItemPK(hipertension, paciente);
        this.setPacienteAntecedenteItemPK(infartos, paciente);
        this.setPacienteAntecedenteItemPK(sida, paciente);
        this.setPacienteAntecedenteItemPK(sifilis, paciente);
        this.setPacienteAntecedenteItemPK(hemopatias, paciente);
        this.setPacienteAntecedenteItemPK(cardiopatias, paciente);
        this.setPacienteAntecedenteItemPK(ateroesclerosis, paciente);
        this.setPacienteAntecedenteItemPK(neuropatias, paciente);
        this.setPacienteAntecedenteItemPK(artropatias, paciente);
        this.setPacienteAntecedenteItemPK(toxicomanias, paciente);
        this.setPacienteAntecedenteItemPK(alergias, paciente);
    }

    private void  setPacienteAntecedenteItemPK(PacienteAntecedente  pacienteAnt, Paciente paciente){
        if(!paciente.getPacienteAntecedenteCollection().contains(pacienteAnt)){ 
            pacienteAnt.setPaciente(paciente);
            pacienteAnt.setPacienteAntecedentePK(new PacienteAntecedentePK(paciente.getId(), pacienteAnt.getAntecedente().getId()));         
             paciente.getPacienteAntecedenteCollection().add(pacienteAnt);
        }
    }
    
    public void setAntecedentes(Paciente paciente){
        Collection<PacienteAntecedente> antecedentesFromPaciente = paciente.getPacienteAntecedenteCollection();
        if(antecedentesFromPaciente.isEmpty()) return;
        
        this.setTipoSangre(findPacienteAntecedente(antecedentesFromPaciente,this.getTipoSangre()));
        this.setTabaquismo(findPacienteAntecedente(antecedentesFromPaciente,this.getTabaquismo()));
        this.setAlcoholismo(findPacienteAntecedente(antecedentesFromPaciente,this.getAlcoholismo()));
        this.setDiabetes(findPacienteAntecedente(antecedentesFromPaciente,this.getDiabetes()));
        this.setHipertension(findPacienteAntecedente(antecedentesFromPaciente,this.getHipertension()));
        this.setInfartos(findPacienteAntecedente(antecedentesFromPaciente,this.getInfartos()));
        this.setSida(findPacienteAntecedente(antecedentesFromPaciente,this.getSida()));
        this.setSifilis(findPacienteAntecedente(antecedentesFromPaciente,this.getSifilis()));
        this.setHemopatias(findPacienteAntecedente(antecedentesFromPaciente,this.getHemopatias()));
        this.setCardiopatias(findPacienteAntecedente(antecedentesFromPaciente,this.getCardiopatias()));
        this.setAteroesclerosis(findPacienteAntecedente(antecedentesFromPaciente,this.getAteroesclerosis()));
        this.setNeuropatias(findPacienteAntecedente(antecedentesFromPaciente,this.getNeuropatias()));
        this.setArtropatias(findPacienteAntecedente(antecedentesFromPaciente,this.getArtropatias()));
        this.setToxicomanias(findPacienteAntecedente(antecedentesFromPaciente,this.getToxicomanias()));
        this.setAlergias(findPacienteAntecedente(antecedentesFromPaciente,this.getAlergias()));
    }
    
    private PacienteAntecedente findPacienteAntecedente(Collection<PacienteAntecedente> collection, PacienteAntecedente target){
        for(PacienteAntecedente pa : collection){
            if(pa.getAntecedente().getNombre().equals(target.getAntecedente().getNombre())){
                return pa;
            }
        }
        return null;
    }
    
    public PacienteAntecedente getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(PacienteAntecedente tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public PacienteAntecedente getTabaquismo() {
        return tabaquismo;
    }

    public void setTabaquismo(PacienteAntecedente tabaquismo) {
        this.tabaquismo = tabaquismo;
    }

    public PacienteAntecedente getAlcoholismo() {
        return alcoholismo;
    }

    public void setAlcoholismo(PacienteAntecedente alcoholismo) {
        this.alcoholismo = alcoholismo;
    }

    public PacienteAntecedente getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(PacienteAntecedente diabetes) {
        this.diabetes = diabetes;
    }

    public PacienteAntecedente getHipertension() {
        return hipertension;
    }

    public void setHipertension(PacienteAntecedente hipertension) {
        this.hipertension = hipertension;
    }

    public PacienteAntecedente getInfartos() {
        return infartos;
    }

    public void setInfartos(PacienteAntecedente infartos) {
        this.infartos = infartos;
    }

    public PacienteAntecedente getSida() {
        return sida;
    }

    public void setSida(PacienteAntecedente sida) {
        this.sida = sida;
    }

    public PacienteAntecedente getSifilis() {
        return sifilis;
    }

    public void setSifilis(PacienteAntecedente sifilis) {
        this.sifilis = sifilis;
    }

    public PacienteAntecedente getHemopatias() {
        return hemopatias;
    }

    public void setHemopatias(PacienteAntecedente hemopatias) {
        this.hemopatias = hemopatias;
    }

    public PacienteAntecedente getCardiopatias() {
        return cardiopatias;
    }

    public void setCardiopatias(PacienteAntecedente cardiopatias) {
        this.cardiopatias = cardiopatias;
    }

    public PacienteAntecedente getAteroesclerosis() {
        return ateroesclerosis;
    }

    public void setAteroesclerosis(PacienteAntecedente ateroesclerosis) {
        this.ateroesclerosis = ateroesclerosis;
    }

    public PacienteAntecedente getNeuropatias() {
        return neuropatias;
    }

    public void setNeuropatias(PacienteAntecedente neuropatias) {
        this.neuropatias = neuropatias;
    }

    public PacienteAntecedente getArtropatias() {
        return artropatias;
    }

    public void setArtropatias(PacienteAntecedente artropatias) {
        this.artropatias = artropatias;
    }

    public PacienteAntecedente getToxicomanias() {
        return toxicomanias;
    }

    public void setToxicomanias(PacienteAntecedente toxicomanias) {
        this.toxicomanias = toxicomanias;
    }

    public PacienteAntecedente getAlergias() {
        return alergias;
    }

    public void setAlergias(PacienteAntecedente alergias) {
        this.alergias = alergias;
    }
    
    
}