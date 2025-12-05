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
import pe.edu.pucp.transitsoft.bo.CapturaBO;
import pe.edu.pucp.transitsoft.bo.CapturaBOImpl;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 * @author santi
 */
@WebService(serviceName = "ServicioCaptura",
        targetNamespace = "http://services.transitsoft.pucp.edu.pe/")
public class ServicioCaptura {

    private CapturaBO capturaBO;
    
    public ServicioCaptura(){
        this.capturaBO = new CapturaBOImpl();
    }
    
  
    @WebMethod(operationName = "obtenerCapturasConExcesoDeVelocidad")
    List<Captura> obtenerCapturasConExcesoDeVelocidad(){
        return this.capturaBO.obtenerCapturasConExcesoDeVelocidad();
    }
    
    @WebMethod(operationName = "actualizarCaptura")
    boolean actualizarCaptura(@WebParam(name = "id") int id, 
            @WebParam(name = "placa") String placa, 
            @WebParam(name = "velocidad") double velocidad, 
            @WebParam(name = "fechaCaptura") Date fechaCaptura, 
            @WebParam(name = "camara") Camara camara,
            @WebParam(name = "estado") EstadoCaptura estado, 
            @WebParam(name = "vehiculo") Vehiculo vehiculo){
        return this.capturaBO.actualizar(id, placa, velocidad, fechaCaptura, camara, estado, vehiculo);
    }
    
    @WebMethod(operationName = "leerTodosCapturas")
    public List<Captura> leerTodosCapturas(){
        return this.capturaBO.leerTodos();
    }
    
    @WebMethod(operationName = "insertarCaptura")
    public Integer insertarCaptura(@WebParam(name = "placa") String placa, 
            @WebParam(name = "velocidad") double velocidad, 
            @WebParam(name = "fechaCaptura") Date fechaCaptura, 
            @WebParam(name = "camara") Camara camara,
            @WebParam(name = "estado") EstadoCaptura estado, 
            @WebParam(name = "vehiculo") Vehiculo vehiculo){
        return this.capturaBO.insertar(placa, velocidad, fechaCaptura, camara, estado, vehiculo);
    }
    
    @WebMethod(operationName = "modificarCaptura")
    public Integer modificarCaptura(@WebParam(name = "id") int id, 
            @WebParam(name = "placa") String placa, 
            @WebParam(name = "velocidad") double velocidad, 
            @WebParam(name = "fechaCaptura") Date fechaCaptura, 
            @WebParam(name = "camara") Camara camara,
            @WebParam(name = "estado") EstadoCaptura estado, 
            @WebParam(name = "vehiculo") Vehiculo vehiculo){
        return this.capturaBO.modificar(id, placa, velocidad, fechaCaptura, camara, estado, vehiculo);
    }
    
    @WebMethod(operationName = "eliminarCaptura")
    public Integer eliminarCaptura(@WebParam(name = "id") int id){
        return this.capturaBO.eliminar(id);
    }
    
    @WebMethod(operationName = "obtenerCapturaPorId")
    public Captura obtenerCapturaPorId(@WebParam(name = "id") int id){
        return this.capturaBO.obtenerPorId(id);
    }
    
    
}
