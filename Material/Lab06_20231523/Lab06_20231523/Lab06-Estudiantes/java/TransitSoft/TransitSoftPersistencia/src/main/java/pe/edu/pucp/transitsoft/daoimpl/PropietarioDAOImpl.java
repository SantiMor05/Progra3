/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.Columna;
import pe.edu.pucp.transitsoft.modelo.Propietario;

/**
 *
 * @author santi
 */
public class PropietarioDAOImpl extends DAOImplBase implements PropietarioDAO {
    
    private Propietario propietario;

    public PropietarioDAOImpl() {
        super("propietario");
        this.propietario = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("dni", false, false));
        this.listaColumnas.add(new Columna("nombres", false, false));
        this.listaColumnas.add(new Columna("apellidos", false, false));
        this.listaColumnas.add(new Columna("direccion", false, false));        
    }

    @Override
    public Integer insertar(Propietario propietario) {
        this.propietario = propietario;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.propietario.getDni());
        this.statement.setString(2, this.propietario.getNombres());
        this.statement.setString(3, this.propietario.getApellidos());
        this.statement.setString(4, this.propietario.getDireccion());
    }

    
    @Override
    public Integer modificar(Propietario propietario) {
        this.propietario = propietario;
        return super.modificar();
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirValorDeParametrosParaInsercion();
        this.statement.setInt(5, this.propietario.getId());
    }
    

    @Override
    public Integer eliminar(int id) {
        this.propietario = new Propietario();
        this.propietario.setId(id);
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.propietario.getId());
    }
    
   
    @Override
    public Propietario obtenerPorId(int id) {
        this.propietario = new Propietario();
        this.propietario.setId(id);
        super.obtenerPorId();
        return this.propietario;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.propietario.getId());
    }

    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.propietario = new Propietario();
        this.propietario.setId(this.resultSet.getInt("id"));
        this.propietario.setDni(this.resultSet.getString("dni"));
        this.propietario.setNombres(this.resultSet.getString("nombres"));
        this.propietario.setApellidos(this.resultSet.getString("apellidos"));
        this.propietario.setDireccion(this.resultSet.getString("direccion"));
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.propietario);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.propietario = null;
    }
 
    @Override
    public List<Propietario> listarTodos(){
        return (ArrayList<Propietario>) super.listarTodos();
    }
    
}
