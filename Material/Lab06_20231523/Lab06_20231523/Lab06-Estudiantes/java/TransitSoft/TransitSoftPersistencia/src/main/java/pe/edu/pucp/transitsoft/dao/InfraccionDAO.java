/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.dao;

import java.util.List;
import pe.edu.pucp.transitsoft.dto.Infraccion;

/**
 *
 * @author santi
 */
public interface InfraccionDAO {
    public Integer insertar(Infraccion infraccion);
    public Integer modificar(Infraccion infraccion) ;
    public Integer eliminar(int id);
    public Infraccion obtenerPorId(int id);
    public List<Infraccion> listarTodos();
}
