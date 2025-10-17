/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.Columna;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

/**
 *
 *
 */
public class VehiculoDAOImpl extends DAOImplBase implements VehiculoDAO {
    
    private Vehiculo vehiculo;

    public VehiculoDAOImpl() {
        super("vehiculo");
        this.vehiculo = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true,true));
        this.listaColumnas.add(new Columna("placa", false,false));
        this.listaColumnas.add(new Columna("marca", false,false));
        this.listaColumnas.add(new Columna("modelo", false,false));
        this.listaColumnas.add(new Columna("anho", false,false));
    }

    @Override
    public Integer insertar(Vehiculo vehiculo){
       this.vehiculo = vehiculo;
       return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1,this.vehiculo.getPlaca());
        this.statement.setString(2,this.vehiculo.getMarca());
        this.statement.setString(3,this.vehiculo.getModelo());
        this.statement.setInt(4,this.vehiculo.getAnho());
    }
   
    
    @Override
    public Integer modificar(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
        return super.modificar();
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        incluirValorDeParametrosParaInsercion();
        this.statement.setInt(5, this.vehiculo.getId());
    }
    
    
    @Override
    public Integer eliminar(int id){
        this.vehiculo = new Vehiculo();
        this.vehiculo.setId(id);
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.vehiculo.getId());
    }
    
    @Override
    public Vehiculo obtenerPorId(int id){
        this.vehiculo = new Vehiculo();
        this.vehiculo.setId(id);
        super.obtenerPorId();
        return this.vehiculo;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.vehiculo.getId());
    } 
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.vehiculo = new Vehiculo();
        this.vehiculo.setId(this.resultSet.getInt("id"));
        this.vehiculo.setPlaca(this.resultSet.getString("placa"));
        this.vehiculo.setMarca(this.resultSet.getString("marca"));
        this.vehiculo.setModelo(this.resultSet.getString("modelo"));
        this.vehiculo.setAnho(this.resultSet.getInt("anho"));
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.vehiculo);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vehiculo = null;
    }
    
    
    @Override
    public List<Vehiculo> listarTodos(){
        return (ArrayList<Vehiculo>) super.listarTodos();
    }
    
    //TODO: Conecta vehiculo con propietario con la tabla intermedia
    
}
