/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siireportes.interfacesnegocio;

import com.siireportes.excepciones.NonexistentEntityException;
import com.siireportes.excepciones.PreexistingEntityException;
import com.siireportes.objetosnegocio.Usuario;

/**
 *
 * @author LV-322
 */
public interface IAdmonUsuariosYPerfiles {
    
    Usuario alta(Usuario u) throws PreexistingEntityException, Exception;
    Usuario cambio(Usuario u) throws NonexistentEntityException, Exception;
    Usuario baja(Usuario u) throws NonexistentEntityException, Exception;
    
}
