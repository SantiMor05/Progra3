
package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Camara;

/**
 *
 * 
 */
public interface CamaraDAO {
    public Integer insertar(Camara camara);
    public Integer modificar(Camara camara) ;
    public Integer eliminar(int id);
    public Camara obtenerPorId(int id);
    public List<Camara> listarTodos();
}
