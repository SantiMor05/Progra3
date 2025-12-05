/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Propietario;

/**
 *
 * @author santi
 */
public interface PropietarioDAO {
    public Integer insertar(Propietario propietario);
    public Integer modificar(Propietario propietario) ;
    public Integer eliminar(int id);
    public Propietario obtenerPorId(int id);
    public List<Propietario> listarTodos();
}
