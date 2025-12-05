/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.transitsoft.services.camara;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import pe.edu.pucp.transitsoft.bo.CapturaBO;
import pe.edu.pucp.transitsoft.bo.CapturaBOImpl;
import pe.edu.pucp.transitsoft.modelo.Captura;

@Path("Capturas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioCaptura {

    private CapturaBO capturaBO;
    
    public ServicioCaptura(){
        this.capturaBO = new CapturaBOImpl();
    }
    
    @POST
    public Response insertar(Captura captura){
        int respuesta = this.capturaBO.insertar(captura);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); 
        return Response.status(Response.Status.CREATED).entity(captura).build();
    }
    
    @PUT
    public Response modificar(Captura captura){
        int respuesta = this.capturaBO.modificar(captura);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(captura).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id){
        int respuesta = this.capturaBO.eliminar(id);
        if(respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") int id){
        Captura captura =  this.capturaBO.obtenerPorId(id);
        if(captura == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(captura).build();
    }
    
    @GET
    public List<Captura> listarTodos(){
        return this.capturaBO.leerTodos();
    }
    
    @GET
    @Path("/Velocidad")
    public List<Captura> obtenerCapturasConExcesoDeVelocidad(){
        return this.capturaBO.obtenerCapturasConExcesoDeVelocidad();
    }
    
}
