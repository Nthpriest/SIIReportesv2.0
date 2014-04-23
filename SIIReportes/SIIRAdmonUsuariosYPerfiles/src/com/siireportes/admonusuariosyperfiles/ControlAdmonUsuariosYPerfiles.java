/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siireportes.admonusuariosyperfiles;

import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.interfacespersistencia.IPersistencia;
import com.siireportes.objetosnegocio.Usuario;
import com.siireportes.persistencia.FPersistencia;

/**
 *
 * @author LV-322
 */
public class ControlAdmonUsuariosYPerfiles {
    
    IPersistencia pe;
    
    public ControlAdmonUsuariosYPerfiles() {
    }
    
    protected Usuario alta(Usuario u) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.alta(u);
    }
    
    protected Usuario cambio(Usuario u) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.cambio(u);
    }
    
    protected Usuario baja(Usuario u) throws PreexistingEntityException, Exception {
        pe = new FPersistencia();
        return pe.baja(u);
    }
}
