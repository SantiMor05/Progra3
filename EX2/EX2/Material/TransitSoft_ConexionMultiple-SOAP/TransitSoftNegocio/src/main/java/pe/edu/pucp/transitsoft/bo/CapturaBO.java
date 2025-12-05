package pe.edu.pucp.transitsoft.bo;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

public interface CapturaBO {
    List<Captura> obtenerCapturasConExcesoDeVelocidad();
    boolean actualizar(int id, String placa, double velocidad, Date fechaCaptura, Camara camara,
            EstadoCaptura estado, Vehiculo vehiculo);
    public List<Captura> leerTodos();
    public Integer insertar(String placa, double velocidad, Date fechaCaptura, Camara camara,
            EstadoCaptura estado, Vehiculo vehiculo);
    public Integer modificar(int id, String placa, double velocidad, Date fechaCaptura, Camara camara,
            EstadoCaptura estado, Vehiculo vehiculo);
    public Integer eliminar(int id);
    public Captura obtenerPorId(int id);
}
