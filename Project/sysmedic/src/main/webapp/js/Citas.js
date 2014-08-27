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


function DisableCitas(){
    document.getElementById("tabViewAgenda:frmNuevaCita:ci_paciente").disabled = true;
     document.getElementById("tabViewAgenda:frmNuevaCita:inputFecha").disabled = true;
}

function EnableCitas(){
    document.getElementById("tabViewAgenda:frmNuevaCita:ci_paciente").disabled = false;
     document.getElementById("tabViewAgenda:frmNuevaCita:inputFecha").disabled = false;
}

       function isNumberKey(evt)
       {
          var charCode = (evt.which) ? evt.which : evt.keyCode;
          if (charCode != 46 && charCode > 31 
            && (charCode < 48 || charCode > 57))
             return false;

          return true;
       }