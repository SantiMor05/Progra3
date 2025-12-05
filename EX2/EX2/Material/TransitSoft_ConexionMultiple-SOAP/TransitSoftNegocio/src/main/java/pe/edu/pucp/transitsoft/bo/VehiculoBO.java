/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import pe.edu.pucp.transitsoft.modelo.Propietario;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 * @author santi
 */
public interface VehiculoBO {
    public Integer insertar(String placa, String marca, String modelo, int anho, 
            Propietario propietario);
    public Integer modificar( int id,String placa, String marca, String modelo, int anho, 
            Propietario propietario);
    public Integer eliminar(int id);
    public Vehiculo obtenerPorId(int id);
    public List<Vehiculo> listarTodos();
}
