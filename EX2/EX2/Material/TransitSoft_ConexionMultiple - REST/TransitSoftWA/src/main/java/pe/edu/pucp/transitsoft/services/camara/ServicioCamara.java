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
import pe.edu.pucp.transitsoft.bo.CamaraBO;
import pe.edu.pucp.transitsoft.bo.CamaraBOImpl;
import pe.edu.pucp.transitsoft.modelo.Camara;



@Path("Camaras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioCamara {

    private CamaraBO camaraBO;
    
    public ServicioCamara(){
        this.camaraBO = new CamaraBOImpl();
    }
    
    @POST
    public Response insertar(Camara camara){
        int respuesta = this.camaraBO.insertar(camara);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); 
        return Response.status(Response.Status.CREATED).entity(camara).build();
    }
    
    @PUT
    public Response modificar(Camara camara){
        int respuesta = this.camaraBO.modificar(camara);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(camara).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id){
        int respuesta = this.camaraBO.eliminar(id);
        if(respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") int id){
        Camara camara =  this.camaraBO.obtenerPorId(id);
        if(camara == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(camara).build();
    }
    
    @GET
    public List<Camara> listarTodos(){
        return this.camaraBO.listarTodos();
    }
    
    
}
