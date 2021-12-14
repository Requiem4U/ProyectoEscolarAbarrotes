/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesSistema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author DIRECCION
 */
public class FechaHora {
    
    private String fechaHora;
    
    FechaHora(){ 
        LocalDateTime date = LocalDateTime.now();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY   hh;mm a");
        fechaHora = dtf.format(date);
    }

    public String getFechaHora() { 
        return fechaHora;
    }
    
    
}
