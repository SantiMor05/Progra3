/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoimpl.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Propietario;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 * @author santi
 */
public class VehiculoBOImpl implements VehiculoBO{
    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBOImpl(){
        this.vehiculoDAO = new VehiculoDAOImpl();
    }

    @Override
    public Integer eliminar(int id) {
        return this.vehiculoDAO.eliminar(id);
    }

    @Override
    public Vehiculo obtenerPorId(int id) {
        Vehiculo vehiculo = this.vehiculoDAO.obtenerPorId(id);
        if(vehiculo == null){
            vehiculo = new Vehiculo();
            vehiculo.setId(-1);
        }
        return vehiculo;
    }

    @Override
    public List<Vehiculo> listarTodos() {
        return this.vehiculoDAO.listarTodos();
    }

    @Override
    public Integer insertar(String placa, String marca, String modelo, int anho, Propietario propietario) {
        Vehiculo vehiculo = new Vehiculo();
        
        vehiculo.setAnho(anho);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPlaca(placa);
        vehiculo.setPropietario(propietario);
        
        return this.vehiculoDAO.insertar(vehiculo);
    }

    @Override
    public Integer modificar(int id, String placa, String marca, String modelo, int anho, 
            Propietario propietario) {
        Vehiculo vehiculo = new Vehiculo();
        
        vehiculo.setId(id);
        vehiculo.setAnho(anho);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPlaca(placa);
        vehiculo.setPropietario(propietario);
        
        return this.vehiculoDAO.modificar(vehiculo);
    }
    
    
    
}
