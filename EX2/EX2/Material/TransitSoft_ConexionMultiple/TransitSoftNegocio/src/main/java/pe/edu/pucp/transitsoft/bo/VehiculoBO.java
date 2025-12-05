/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 * @author santi
 */
public interface VehiculoBO {
    public Integer insertar(Vehiculo vehiculo);
    public Integer modificar(Vehiculo vehiculo) ;
    public Integer eliminar(int id);
    public Vehiculo obtenerPorId(int id);
    public List<Vehiculo> listarTodos();
}
