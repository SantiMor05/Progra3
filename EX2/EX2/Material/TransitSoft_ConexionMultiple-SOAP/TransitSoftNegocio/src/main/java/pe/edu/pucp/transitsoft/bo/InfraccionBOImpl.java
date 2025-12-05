package pe.edu.pucp.transitsoft.bo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import pe.edu.pucp.transitsoft.bo.mappers.CapturaToInfraccionMapper;
import pe.edu.pucp.transitsoft.dao.InfraccionDAO;
import pe.edu.pucp.transitsoft.daoimpl.InfraccionDAOImpl;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class InfraccionBOImpl implements InfraccionBO {
    
    private InfraccionDAO infraccionDAO;
    
    public InfraccionBOImpl(){
        this.infraccionDAO = new InfraccionDAOImpl();
    }
    
    @Override
    public List<Infraccion> crearInfracciones(List<Captura> capturasConExceso) {
        var mapper = new CapturaToInfraccionMapper();
        return capturasConExceso.stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public Integer eliminar(int id) {
        return this.infraccionDAO.eliminar(id);
    }

    @Override
    public Infraccion obtenerPorId(int id) {
        Infraccion infraccion = this.infraccionDAO.obtenerPorId(id);
        if(infraccion == null){
            infraccion = new Infraccion();
            infraccion.setIdInfraccion(-1);
        }
        return infraccion;
    }

    @Override
    public List<Infraccion> listarTodos() {
        return this.infraccionDAO.listarTodos();
    }

    @Override
    public Integer insertar(String placa, double velocidad, double limite, double exceso, 
            String marcaVehiculo, String modeloVehiculo, int anhoVehiculo, String dniPropietario, 
            String nombresPropietario, String apellidosPropietario, String direccionPropietario, 
            String modeloCamara, String codigoSerieCamara, long latitud, long longitud, 
            double monto, Date fechaCaptura, Date fechaRegistro, int idCamara) {

        Infraccion infraccion = new Infraccion();
        
        infraccion.setAnhoVehiculo(anhoVehiculo);
        infraccion.setApellidosPropietario(apellidosPropietario);
        infraccion.setCodigoSerieCamara(codigoSerieCamara);
        infraccion.setDireccionPropietario(direccionPropietario);
        infraccion.setDniPropietario(dniPropietario);
        infraccion.setExceso(exceso);
        infraccion.setFechaCaptura(fechaCaptura);
        infraccion.setFechaRegistro(fechaRegistro);
        infraccion.setIdCamara(idCamara);
        infraccion.setLatitud(latitud);
        infraccion.setLimite(limite);
        infraccion.setLongitud(longitud);
        infraccion.setMarcaVehiculo(marcaVehiculo);
        infraccion.setModeloCamara(modeloCamara);
        infraccion.setModeloVehiculo(modeloVehiculo);
        infraccion.setMonto(monto);
        infraccion.setPlaca(placa);
        infraccion.setvelocidad(velocidad);
        infraccion.setNombresPropietario(nombresPropietario);
        
        return this.infraccionDAO.insertar(infraccion);
    }

    @Override
    public Integer modificar(String placa, double velocidad, double limite, double exceso, 
            String marcaVehiculo, String modeloVehiculo, int anhoVehiculo, String dniPropietario, 
            String nombresPropietario, String apellidosPropietario, String direccionPropietario, 
            String modeloCamara, String codigoSerieCamara, long latitud, long longitud, double monto, 
            Date fechaCaptura, Date fechaRegistro, int idInfraccion, int idCamara) {
        Infraccion infraccion = new Infraccion();
        
        infraccion.setAnhoVehiculo(anhoVehiculo);
        infraccion.setApellidosPropietario(apellidosPropietario);
        infraccion.setCodigoSerieCamara(codigoSerieCamara);
        infraccion.setDireccionPropietario(direccionPropietario);
        infraccion.setDniPropietario(dniPropietario);
        infraccion.setExceso(exceso);
        infraccion.setFechaCaptura(fechaCaptura);
        infraccion.setFechaRegistro(fechaRegistro);
        infraccion.setIdCamara(idCamara);
        infraccion.setLatitud(latitud);
        infraccion.setLimite(limite);
        infraccion.setLongitud(longitud);
        infraccion.setMarcaVehiculo(marcaVehiculo);
        infraccion.setModeloCamara(modeloCamara);
        infraccion.setModeloVehiculo(modeloVehiculo);
        infraccion.setMonto(monto);
        infraccion.setPlaca(placa);
        infraccion.setvelocidad(velocidad);
        infraccion.setNombresPropietario(nombresPropietario);
        
        infraccion.setIdInfraccion(idInfraccion);
        
        return this.infraccionDAO.modificar(infraccion);
    }
    
    
    
    
}
