/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function ClearCitas(){
    document.getElementById("tabViewAgenda:frmNuevaCita:ci_paciente").value = "";
    document.getElementById("tabViewAgenda:frmNuevaCita:nombre_paciente").value = "";
    if(document.getElementById("tabViewAgenda:frmNuevaCita:nombre_medico") !== null)
        document.getElementById("tabViewAgenda:frmNuevaCita:nombre_medico").value = "";
    document.getElementById("tabViewAgenda:frmNuevaCita:inputFecha").value = ""; 
}
