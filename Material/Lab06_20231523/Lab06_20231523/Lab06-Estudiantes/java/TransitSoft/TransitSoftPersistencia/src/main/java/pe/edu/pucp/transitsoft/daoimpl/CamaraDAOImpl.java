/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CamaraDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.Columna;
import pe.edu.pucp.transitsoft.modelo.Camara;

/**
 *
 * @author santi
 */
public class CamaraDAOImpl extends DAOImplBase implements CamaraDAO {
    
    private Camara camara;

    public CamaraDAOImpl() {
        super("camara");
        this.camara = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("modelo", false, false));
        this.listaColumnas.add(new Columna("codigo_serie", false, false));
        this.listaColumnas.add(new Columna("latitud", false, false));
        this.listaColumnas.add(new Columna("longitud", false, false));
    }
    
    
    @Override
    public Integer insertar(Camara camara){
        this.camara = camara;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.camara.getModelo());
        this.statement.setString(2, this.camara.getCodigoSerie());
        this.statement.setInt(3, (int) this.camara.getLatitud());
        this.statement.setInt(4, (int) this.camara.getLongitud());
    }
    
    
    
    @Override
    public Integer modificar(Camara camara) {
        this.camara = camara;
        return super.modificar();
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirValorDeParametrosParaInsercion();
        this.statement.setInt(5, this.camara.getId());
    }
    
    @Override
    public Integer eliminar(int id){
        this.camara = new Camara ();
        this.camara.setId(id);
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.camara.getId());
    }
    
    
    @Override
    public Camara obtenerPorId(int id){
        this.camara = new Camara ();
        this.camara.setId(id);
        super.obtenerPorId();
        return this.camara;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.camara.getId());
    }
    

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.camara = new Camara();
        this.camara.setId(this.resultSet.getInt("id"));
        this.camara.setModelo(this.resultSet.getString("modelo"));
        this.camara.setCodigoSerie(this.resultSet.getString("codigo_serie"));
        this.camara.setLatitud(this.resultSet.getInt("latitud"));
        this.camara.setLongitud(this.resultSet.getInt("longitud"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.camara = null;
    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.camara);
    }

    @Override
    public List<Camara> listarTodos(){
        return (ArrayList<Camara>) super.listarTodos();
    }
    
}
