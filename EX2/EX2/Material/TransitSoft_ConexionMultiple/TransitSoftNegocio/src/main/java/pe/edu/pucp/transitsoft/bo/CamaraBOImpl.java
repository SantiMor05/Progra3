/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.dao.CamaraDAO;
import pe.edu.pucp.transitsoft.daoimpl.CamaraDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Camara;

/**
 *
 * @author santi
 */
public class CamaraBOImpl implements CamaraBO{
    
    private CamaraDAO camaraDAO;
    
    public CamaraBOImpl(){
        this.camaraDAO = new CamaraDAOImpl();
    }

    @Override
    public Integer insertar(Camara camara) {
        return this.camaraDAO.insertar(camara);
    }

    @Override
    public Integer modificar(Camara camara) {
        return this.camaraDAO.modificar(camara);
    }

    @Override
    public Integer eliminar(int id) {
        return this.camaraDAO.eliminar(id);
    }

    @Override
    public Camara obtenerPorId(int id) {
        Camara camara = this.camaraDAO.obtenerPorId(id);
        if(camara == null){
            camara = new Camara();
            camara.setId(-1);
        }
        return camara;
    }

    @Override
    public List<Camara> listarTodos() {
        return this.camaraDAO.listarTodos();
    }
    
    
    
}
