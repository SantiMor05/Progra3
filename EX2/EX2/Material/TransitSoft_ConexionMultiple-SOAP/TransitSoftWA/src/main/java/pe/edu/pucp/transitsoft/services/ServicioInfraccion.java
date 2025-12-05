/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.transitsoft.bo.InfraccionBO;
import pe.edu.pucp.transitsoft.bo.InfraccionBOImpl;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

/**
 *
 * @author santi
 */
@WebService(serviceName = "ServicioInfraccion",
        targetNamespace = "http://services.transitsoft.pucp.edu.pe/")
public class ServicioInfraccion {

    private InfraccionBO infraccionBO;
    
    public ServicioInfraccion(){
        this.infraccionBO = new InfraccionBOImpl();
    }
    
    
    
    @WebMethod(operationName = "crearInfracciones")
    List<Infraccion> crearInfracciones(
            @WebParam(name = "capturasConExceso") List<Captura> capturasConExceso){
        return this.infraccionBO.crearInfracciones(capturasConExceso);
    }
    
    
    @WebMethod(operationName = "insertarInfraccion")
    public Integer insertarInfraccion(@WebParam(name = "placa") String placa, 
            @WebParam(name = "velocidad") double velocidad, 
            @WebParam(name = "limite") double limite, 
            @WebParam(name = "exceso") double exceso, 
            @WebParam(name = "marcaVehiculo") String marcaVehiculo, 
            @WebParam(name = "modeloVehiculo") String modeloVehiculo,
            @WebParam(name = "anhoVehiculo") int anhoVehiculo, 
            @WebParam(name = "dniPropietario") String dniPropietario, 
            @WebParam(name = "nombresPropietario") String nombresPropietario, 
            @WebParam(name = "apellidosPropietario") String apellidosPropietario,
            @WebParam(name = "direccionPropietario") String direccionPropietario, 
            @WebParam(name = "modeloCamara") String modeloCamara, 
            @WebParam(name = "codigoSerieCamara") String codigoSerieCamara, 
            @WebParam(name = "latitud") long latitud, 
            @WebParam(name = "longitud") long longitud,
            @WebParam(name = "monto") double monto, 
            @WebParam(name = "fechaCaptura") Date fechaCaptura, 
            @WebParam(name = "fechaRegistro") Date fechaRegistro, 
            @WebParam(name = "idCamara") int idCamara){
        
        return this.infraccionBO.insertar(placa, velocidad, limite, exceso, marcaVehiculo, 
                modeloVehiculo, anhoVehiculo, dniPropietario, nombresPropietario, 
                apellidosPropietario, direccionPropietario, modeloCamara, codigoSerieCamara, 
                latitud, longitud, monto, fechaCaptura, fechaRegistro, idCamara);
    }
    
 
    @WebMethod(operationName = "modificarInfraccion")
    public Integer modificarInfraccion(@WebParam(name = "placa") String placa, 
            @WebParam(name = "velocidad") double velocidad, 
            @WebParam(name = "limite") double limite, 
            @WebParam(name = "exceso") double exceso, 
            @WebParam(name = "marcaVehiculo") String marcaVehiculo, 
            @WebParam(name = "modeloVehiculo") String modeloVehiculo,
            @WebParam(name = "anhoVehiculo") int anhoVehiculo, 
            @WebParam(name = "dniPropietario") String dniPropietario, 
            @WebParam(name = "nombresPropietario") String nombresPropietario, 
            @WebParam(name = "apellidosPropietario") String apellidosPropietario,
            @WebParam(name = "direccionPropietario") String direccionPropietario, 
            @WebParam(name = "modeloCamara") String modeloCamara, 
            @WebParam(name = "codigoSerieCamara") String codigoSerieCamara, 
            @WebParam(name = "latitud") long latitud, 
            @WebParam(name = "longitud") long longitud,
            @WebParam(name = "monto") double monto, 
            @WebParam(name = "fechaCaptura") Date fechaCaptura, 
            @WebParam(name = "fechaRegistro") Date fechaRegistro, 
            @WebParam(name = "idInfraccion") int idInfraccion, 
            @WebParam(name = "idCamara") int idCamara){
        
        return this.infraccionBO.modificar(placa, velocidad, limite, exceso, marcaVehiculo, 
                modeloVehiculo, anhoVehiculo, dniPropietario, nombresPropietario, 
                apellidosPropietario, direccionPropietario, modeloCamara, codigoSerieCamara, 
                latitud, longitud, monto, fechaCaptura, fechaRegistro, idInfraccion, idCamara);
    }
    
    @WebMethod(operationName = "eliminarInfraccion")
    public Integer eliminarInfraccion(@WebParam(name = "id") int id){
        return this.infraccionBO.eliminar(id);
    }
    
    
    @WebMethod(operationName = "obtenerInfraccionPorId")
    public Infraccion obtenerInfraccionPorId(@WebParam(name = "id") int id){
        return this.infraccionBO.obtenerPorId(id);
    }
    
    @WebMethod(operationName = "listarTodosInfracciones")
    public List<Infraccion> listarTodosInfracciones(){
        return this.infraccionBO.listarTodos();
    }
    
}
