/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services.camara;

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
@WebService(serviceName = "ServicioCamara")
public class ServicioCamara {

    private CamaraBO camaraBO;
    
    public ServicioCamara(){
        this.camaraBO = new CamaraBOImpl();
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "insertarCamara")
    public Integer insertar(@WebParam(name = "camara") Camara camara){
        return this.camaraBO.insertar(camara);
    }
    
    public Integer modificar(Camara camara) ;
    
    public Integer eliminar(int id);
    
    public Camara obtenerPorId(int id);
    
    public List<Camara> listarTodos();
}
