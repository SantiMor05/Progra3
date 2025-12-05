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
import pe.edu.pucp.transitsoft.bo.InfraccionBO;
import pe.edu.pucp.transitsoft.bo.InfraccionBOImpl;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;


@Path("Infracciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioInfraccion {

    private InfraccionBO infraccionBO;
    
    public ServicioInfraccion(){
        this.infraccionBO = new InfraccionBOImpl();
    }
    
    
    @POST
    public Response insertar(Infraccion infraccion){
        int respuesta = this.infraccionBO.insertar(infraccion);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_ACCEPTABLE).build(); 
        return Response.status(Response.Status.CREATED).entity(infraccion).build();
    }
    
    @PUT
    public Response modificar(Infraccion infraccion){
        int respuesta = this.infraccionBO.modificar(infraccion);
        if(respuesta == 0)
            return Response.status(Response.Status.NOT_MODIFIED).build();
        return Response.ok(infraccion).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response eliminar(@PathParam("id") int id){
        int respuesta = this.infraccionBO.eliminar(id);
        if(respuesta > 0)
            return Response.noContent().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @GET
    @Path("{id}")
    public Response obtenerPorId(@PathParam("id") int id){
        Infraccion infraccion =  this.infraccionBO.obtenerPorId(id);
        if(infraccion == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(infraccion).build();
    }
    
    @GET
    public List<Infraccion> listarTodos(){
        return this.infraccionBO.listarTodos();
    }
    
    @POST
    @Path("/Crear")
    List<Infraccion> crearInfracciones(List<Captura> capturasConExceso){
        return this.infraccionBO.crearInfracciones(capturasConExceso);
    }
    
}
