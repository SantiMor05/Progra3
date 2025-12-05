/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.daoimpl.PropietarioDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Propietario;

/**
 *
 * @author santi
 */
public class PropietarioBOImpl implements PropietarioBO{
    
    private PropietarioDAO propietarioDAO;
    
    public PropietarioBOImpl(){
        this.propietarioDAO = new PropietarioDAOImpl();
    }

    @Override
    public Integer insertar(Propietario propietario) {
        return this.propietarioDAO.insertar(propietario);
    }

    @Override
    public Integer modificar(Propietario propietario) {
        return this.propietarioDAO.modificar(propietario);
    }

    @Override
    public Integer eliminar(int id) {
        return this.propietarioDAO.eliminar(id);
    }

    @Override
    public Propietario obtenerPorId(int id) {
        Propietario propietario = this.propietarioDAO.obtenerPorId(id);
        if(propietario == null){
            propietario = new Propietario();
            propietario.setId(-1);
        }
        return propietario;
    }

    @Override
    public List<Propietario> listarTodos() {
        return this.propietarioDAO.listarTodos();
    } 
}
