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
import pe.edu.pucp.transitsoft.bo.PropietarioBO;
import pe.edu.pucp.transitsoft.bo.PropietarioBOImpl;
import pe.edu.pucp.transitsoft.modelo.Propietario;

@Path("Propietarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioPropietario {

    private PropietarioBO propietarioBO;
    
    public ServicioPropietario(){
        this.propietarioBO = new PropietarioBOImpl();
    }
    
    @POST
    public Response insertar(Propietario propietario){
        int respuesta = this.propietarioBO.insertar(propietario);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); 
        return Response.status(Response.Status.CREATED).entity(propietario).build();
    }
    
    @PUT
    public Response modificar(Propietario propietario){
        int respuesta = this.propietarioBO.modificar(propietario);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(propietario).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id){
        int respuesta = this.propietarioBO.eliminar(id);
        if(respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") int id){
        Propietario propietario =  this.propietarioBO.obtenerPorId(id);
        if(propietario == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(propietario).build();
    }
    
    @GET
    public List<Propietario> listarTodos(){
        return this.propietarioBO.listarTodos();
    }
    
}
