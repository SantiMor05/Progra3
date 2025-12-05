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

    @Override
    public Integer insertar(String modelo, String codigoSerie, long latitud, long longitud) {
        Camara camara =  new Camara();
        camara.setCodigoSerie(codigoSerie);
        camara.setModelo(modelo);
        camara.setLatitud(latitud);
        camara.setLongitud(longitud);
        
        return this.camaraDAO.insertar(camara);
    }

    @Override
    public Integer modificar(int id, String modelo, String codigoSerie, long latitud, long longitud) {
        Camara camara =  new Camara();
        camara.setCodigoSerie(codigoSerie);
        camara.setModelo(modelo);
        camara.setLatitud(latitud);
        camara.setLongitud(longitud);
        camara.setId(id);
        
        return this.camaraDAO.modificar(camara);
    }
    
    
    
}
