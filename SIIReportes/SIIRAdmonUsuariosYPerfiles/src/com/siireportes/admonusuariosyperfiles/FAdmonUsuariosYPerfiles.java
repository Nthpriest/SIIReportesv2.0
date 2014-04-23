/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siireportes.admonusuariosyperfiles;

import com.siireportes.excepciones.NonexistentEntityException;
import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.interfacesnegocio.IAdmonUsuariosYPerfiles;
import com.siireportes.objetosnegocio.Usuario;

/**
 *
 * @author LV-322
 */
public class FAdmonUsuariosYPerfiles implements IAdmonUsuariosYPerfiles {

    ControlAdmonUsuariosYPerfiles caup;
    
    public FAdmonUsuariosYPerfiles() {        
    }
    
    @Override
    public Usuario alta(Usuario u) throws PreexistingEntityException, Exception {
        caup = new ControlAdmonUsuariosYPerfiles();
        return caup.alta(u);
    }

    @Override
    public Usuario cambio(Usuario u) throws NonexistentEntityException, Exception {
        caup = new ControlAdmonUsuariosYPerfiles();
        return caup.cambio(u);
    }

    @Override
    public Usuario baja(Usuario u) throws NonexistentEntityException, Exception {
        caup = new ControlAdmonUsuariosYPerfiles();
        return caup.baja(u);
    }
    
}
