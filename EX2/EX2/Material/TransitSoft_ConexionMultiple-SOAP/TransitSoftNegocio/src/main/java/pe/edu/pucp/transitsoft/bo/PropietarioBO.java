/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Propietario;

/**
 *
 * @author santi
 */
public interface PropietarioBO {
    public Integer insertar(String dni, String nombres, String apellidos, String direccion) ;
    public Integer modificar(int id, String dni, String nombres, String apellidos,
            String direccion) ;
    public Integer eliminar(int id);
    public Propietario obtenerPorId(int id);
    public List<Propietario> listarTodos();
}
