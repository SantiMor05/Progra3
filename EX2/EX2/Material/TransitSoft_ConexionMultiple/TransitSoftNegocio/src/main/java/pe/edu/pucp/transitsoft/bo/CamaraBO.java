/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Camara;

/**
 *
 * @author santi
 */
public interface CamaraBO {
    public Integer insertar(Camara camara);
    public Integer modificar(Camara camara) ;
    public Integer eliminar(int id);
    public Camara obtenerPorId(int id);
    public List<Camara> listarTodos();
}
