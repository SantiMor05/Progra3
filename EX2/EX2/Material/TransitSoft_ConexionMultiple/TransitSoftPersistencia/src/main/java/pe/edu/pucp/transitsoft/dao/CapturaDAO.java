package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Captura;

// TODO: Definir Interfaz CapturaDAO
public interface CapturaDAO {
    public List<Captura> leerTodos();
    public boolean actualizar(Captura captura);
    public Integer insertar(Captura captura);
    public Integer modificar(Captura captura) ;
    public Integer eliminar(int id);
    public Captura obtenerPorId(int id);
}
