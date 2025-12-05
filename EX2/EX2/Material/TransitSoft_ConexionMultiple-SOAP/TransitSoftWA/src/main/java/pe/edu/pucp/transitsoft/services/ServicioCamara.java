/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.transitsoft.bo.CamaraBO;
import pe.edu.pucp.transitsoft.bo.CamaraBOImpl;
import pe.edu.pucp.transitsoft.modelo.Camara;

/**
 *
 * @author santi
 */
@WebService(serviceName = "ServicioCamara",
        targetNamespace = "http://services.transitsoft.pucp.edu.pe/")
public class ServicioCamara {

    private CamaraBO camaraBO;
    
    public ServicioCamara(){
        this.camaraBO = new CamaraBOImpl();
    }
    
    @WebMethod(operationName = "insertarCamara")
    public Integer insertarCamara(@WebParam(name = "modelo") String modelo, 
            @WebParam(name = "codigoSerie") String codigoSerie, 
            @WebParam(name = "latitud")long latitud, 
            @WebParam(name = "longitud")long longitud){
        return this.camaraBO.insertar(modelo, codigoSerie, latitud, longitud);
    }
    
    @WebMethod(operationName = "modificarCamara")
    public Integer modificarCamara(@WebParam(name = "id") int id, 
             @WebParam(name = "modelo")String modelo, 
             @WebParam(name = "codigoSerie")String codigoSerie, 
             @WebParam(name = "latitud")long latitud,
             @WebParam(name = "longitud")long longitud){
        return this.camaraBO.modificar(id, modelo, codigoSerie, latitud, longitud);
    }
    
    @WebMethod(operationName = "eliminarCamara")
    public Integer eliminarCamara(@WebParam(name = "id") int id){
        return this.camaraBO.eliminar(id);
    }
    
    @WebMethod(operationName = "obtenerCamaraPorId")
    public Camara obtenerCamaraPorId(@WebParam(name = "id") int id){
        return this.camaraBO.obtenerPorId(id);
    }
    
    @WebMethod(operationName = "listarCamaras")
    public List<Camara> listarCamaras(){
        return this.camaraBO.listarTodos();
    }
}
