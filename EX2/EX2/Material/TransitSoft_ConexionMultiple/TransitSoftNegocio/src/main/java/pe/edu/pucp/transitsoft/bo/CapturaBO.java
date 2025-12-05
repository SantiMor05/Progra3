package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Captura;

public interface CapturaBO {
    List<Captura> obtenerCapturasConExcesoDeVelocidad();
    boolean actualizar(Captura modelo);
    public List<Captura> leerTodos();
    public Integer insertar(Captura captura);
    public Integer modificar(Captura captura) ;
    public Integer eliminar(int id);
    public Captura obtenerPorId(int id);
}
