/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swii.sysmedic.Facades;

import com.swii.sysmedic.entities.Cita;
import com.swii.sysmedic.entities.Cliente;
import com.swii.sysmedic.entities.DetalleFacturaConsulta;
import com.swii.sysmedic.entities.Factura;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author fabian
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> {
    @PersistenceContext(unitName = "com.swii_sysmedic_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    @EJB
    private ClienteFacade clienteFacade;
    @EJB
    private DetalleFacturaConsultaFacade dfcFacade; 
    @EJB
    private CitaFacade citaFacade; 
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);        
    }
       
    public Factura GetFactura(Integer numero) {
        TypedQuery<Factura> query = em.createNamedQuery("Factura.findByNumero", Factura.class);
        query.setParameter("numero", numero);
        List factura = query.getResultList();
        if(factura == null || factura.isEmpty())
            return null;
        else
            return query.getResultList().get(0);
    }
    
    public void Insert(Factura factura){
        create(factura);
    }
    
    public void Insert(Cliente cliente,Factura factura,DetalleFacturaConsulta dfc){
        if(!this.clienteFacade.existsCliente(cliente.getRuc())){
            this.clienteFacade.Insert(cliente);
        }
        factura.setCliente(cliente);
        create(factura);
        dfc.setFactura(factura);
//         dfc.getConsulta().getCita().setEstado(Cita.Estado.Pagado.toString());
//         this.citaFacade.edit(dfc.getConsulta().getCita());
        this.dfcFacade.Insert(dfc);        
    } 
    
    @Override
    public List<Factura> findAll() {
        List<Factura> factura = em.createNamedQuery("Factura.findAll", Factura.class).getResultList();
        return factura;
    }
    
}
