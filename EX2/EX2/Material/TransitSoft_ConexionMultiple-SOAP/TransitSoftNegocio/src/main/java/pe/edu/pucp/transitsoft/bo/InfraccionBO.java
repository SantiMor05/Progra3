package pe.edu.pucp.transitsoft.bo;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

/**
 *
 * @author eric
 */
public interface InfraccionBO {
    List<Infraccion> crearInfracciones(List<Captura> capturasConExceso);
    public Integer insertar(String placa, double velocidad, double limite, double exceso, String marcaVehiculo, String modeloVehiculo,
            int anhoVehiculo, String dniPropietario, String nombresPropietario, String apellidosPropietario,
            String direccionPropietario, String modeloCamara, String codigoSerieCamara, long latitud, long longitud,
            double monto, Date fechaCaptura, Date fechaRegistro, int idCamara);
    public Integer modificar(String placa, double velocidad, double limite, double exceso, String marcaVehiculo, String modeloVehiculo,
            int anhoVehiculo, String dniPropietario, String nombresPropietario, String apellidosPropietario,
            String direccionPropietario, String modeloCamara, String codigoSerieCamara, long latitud, long longitud,
            double monto, Date fechaCaptura, Date fechaRegistro, int idInfraccion, int idCamara);
    public Integer eliminar(int id);
    public Infraccion obtenerPorId(int id);
    public List<Infraccion> listarTodos();
}
