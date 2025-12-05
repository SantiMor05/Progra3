/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.transitsoft.bo.PropietarioBO;
import pe.edu.pucp.transitsoft.bo.PropietarioBOImpl;
import pe.edu.pucp.transitsoft.modelo.Propietario;

/**
 *
 * @author santi
 */
@WebService(serviceName = "ServicioPropietario",
        targetNamespace = "http://services.transitsoft.pucp.edu.pe/")
public class ServicioPropietario {

    private PropietarioBO propietarioBO;
    
    public ServicioPropietario(){
        this.propietarioBO = new PropietarioBOImpl();
    }
    
    @WebMethod(operationName = "insertarPropietario")
    public Integer insertarPropietario(@WebParam(name = "dni") String dni, 
            @WebParam(name = "nombres") String nombres, 
            @WebParam(name = "apellidos") String apellidos, 
            @WebParam(name = "direccion") String direccion){
        return this.propietarioBO.insertar(dni, nombres, apellidos, direccion);
    }
    
    
    @WebMethod(operationName = "modificarPropietario")
    public Integer modificarPropietario(@WebParam(name = "id") int id, 
            @WebParam(name = "dni") String dni, 
            @WebParam(name = "nombres") String nombres, 
            @WebParam(name = "apellidos") String apellidos,
            @WebParam(name = "direccion") String direccion){
        return this.propietarioBO.modificar(id, dni, nombres, apellidos, direccion);
    }
    
    
    @WebMethod(operationName = "eliminarPropietario")
    public Integer eliminarPropietario(@WebParam(name = "id") int id){
        return this.propietarioBO.eliminar(id);
    }
    
    
    @WebMethod(operationName = "obtenerPropietarioPorId")
    public Propietario obtenerPropietarioPorId(@WebParam(name = "id") int id){
        return this.propietarioBO.obtenerPorId(id);
    }
    
    
    @WebMethod(operationName = "listarTodosPropietarios")
    public List<Propietario> listarTodosPropietarios(){
        return this.propietarioBO.listarTodos();
    }
}
