/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.transitsoft.bo.VehiculoBO;
import pe.edu.pucp.transitsoft.bo.VehiculoBOImpl;
import pe.edu.pucp.transitsoft.modelo.Propietario;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 * @author santi
 */
@WebService(serviceName = "ServicioVehiculo",
        targetNamespace = "http://services.transitsoft.pucp.edu.pe/")
public class ServicioVehiculo {

    private VehiculoBO vehiculoBO;
    
    public ServicioVehiculo(){
        this.vehiculoBO = new VehiculoBOImpl();
    }
            
    
    @WebMethod(operationName = "insertarVehiculo")
    public Integer insertarVehiculo(@WebParam(name = "placa") String placa, 
            @WebParam(name = "marca") String marca, 
            @WebParam(name = "modelo") String modelo, 
            @WebParam(name = "anho") int anho, 
            @WebParam(name = "propietario") Propietario propietario){
        return this.vehiculoBO.insertar(placa, marca, modelo, anho, propietario);
    }
    
    @WebMethod(operationName = "modificarVehiculo")
    public Integer modificarVehiculo(@WebParam(name = "id") int id,
            @WebParam(name = "placa") String placa, 
            @WebParam(name = "marca") String marca, 
            @WebParam(name = "modelo") String modelo, 
            @WebParam(name = "anho") int anho, 
            @WebParam(name = "propietario") Propietario propietario){
        return this.vehiculoBO.modificar(id, placa, marca, modelo, anho, propietario);
    }
    
    @WebMethod(operationName = "eliminarVehiculo")
    public Integer eliminarVehiculo(@WebParam(name = "id") int id){
        return this.vehiculoBO.eliminar(id);
    }
    
    
    @WebMethod(operationName = "obtenerVehiculoPorId")
    public Vehiculo obtenerVehiculoPorId(@WebParam(name = "id") int id){
        return this.vehiculoBO.obtenerPorId(id);
    }
    
    
    @WebMethod(operationName = "listarTodosVehiculos")
    public List<Vehiculo> listarTodosVehiculos(){
        return this.vehiculoBO.listarTodos();
    }
}
