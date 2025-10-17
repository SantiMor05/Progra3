/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.InfraccionDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.Columna;
import pe.edu.pucp.transitsoft.dto.Infraccion;

/**
 *
 *
 */
public class InfraccionDAOImpl extends DAOImplBase implements InfraccionDAO {

    private Infraccion infraccion;

    public InfraccionDAOImpl() {
        super("infraccion");
        this.infraccion = null;
        this.retornarLlavePrimaria = true;
    }

    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("velocidad", false, false));
        this.listaColumnas.add(new Columna("limite", false, false));
        this.listaColumnas.add(new Columna("exceso", false, false));
        this.listaColumnas.add(new Columna("vehiculo_marca", false, false));
        this.listaColumnas.add(new Columna("vehiculo_modelo", false, false));
        this.listaColumnas.add(new Columna("vehiculo_anho", false, false));
        this.listaColumnas.add(new Columna("propietario_dni", false, false));
        this.listaColumnas.add(new Columna("propietario_nombres", false, false));
        this.listaColumnas.add(new Columna("propietario_apellidos", false, false));
        this.listaColumnas.add(new Columna("propietario_direccion", false, false));
        this.listaColumnas.add(new Columna("id_camara", false, false));
        this.listaColumnas.add(new Columna("camara_modelo", false, false));
        this.listaColumnas.add(new Columna("camara_codigo_serie", false, false));
        this.listaColumnas.add(new Columna("camara_latitud", false, false));
        this.listaColumnas.add(new Columna("camara_longitud", false, false));
        this.listaColumnas.add(new Columna("monto", false, false));
        this.listaColumnas.add(new Columna("fecha_captura", false, false));
        this.listaColumnas.add(new Columna("fecha_registro", false, false));
    }

    @Override
    public Integer insertar(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.insertar();
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setString(1, this.infraccion.getPlaca());
        this.statement.setDouble(2, this.infraccion.getvelocidad());
        this.statement.setDouble(3, this.infraccion.getLimite());
        this.statement.setDouble(4, this.infraccion.getExceso());
        this.statement.setString(5, this.infraccion.getMarcaVehiculo());
        this.statement.setString(6, this.infraccion.getModeloVehiculo());
        this.statement.setInt(7, this.infraccion.getAnhoVehiculo());
        this.statement.setString(8, this.infraccion.getDniPropietario());
        this.statement.setString(9, this.infraccion.getNombresPropietario());
        this.statement.setString(10, this.infraccion.getApellidosPropietario());
        this.statement.setString(11, this.infraccion.getDireccionPropietario());
        this.statement.setInt(12, this.infraccion.getIdCamara());
        this.statement.setString(13, this.infraccion.getModeloCamara());
        this.statement.setString(14, this.infraccion.getCodigoSerieCamara());
        this.statement.setInt(15, (int) this.infraccion.getLatitud());
        this.statement.setInt(16, (int) this.infraccion.getLongitud());
        this.statement.setDouble(17, this.infraccion.getMonto());
        this.statement.setDate(18, (Date) this.infraccion.getFechaCaptura());
        this.statement.setDate(19, (Date) this.infraccion.getFechaRegistro());
    }

    @Override
    public Integer modificar(Infraccion infraccion) {
        this.infraccion = infraccion;
        return super.modificar();
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirValorDeParametrosParaInsercion();
        this.statement.setInt(20, this.infraccion.getIdInfraccion());
    }

    @Override
    public Integer eliminar(int id) {
        this.infraccion = new Infraccion();
        this.infraccion.setIdInfraccion(id);
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.statement.setInt(1, this.infraccion.getIdInfraccion());
    }

    @Override
    public Infraccion obtenerPorId(int id) {
        this.infraccion = new Infraccion();
        this.infraccion.setIdInfraccion(id);
        super.obtenerPorId();
        return this.infraccion;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.infraccion.getIdInfraccion());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.infraccion = new Infraccion();
        this.infraccion.setIdInfraccion(this.resultSet.getInt("id"));
        this.infraccion.setPlaca(this.resultSet.getString("placa"));
        this.infraccion.setvelocidad(this.resultSet.getDouble("velocidad"));
        this.infraccion.setLimite(this.resultSet.getDouble("limite"));
        this.infraccion.setExceso(this.resultSet.getDouble("exceso"));
        this.infraccion.setMarcaVehiculo(this.resultSet.getString("vehiculo_marca"));
        this.infraccion.setModeloVehiculo(this.resultSet.getString("vehiculo_modelo"));
        this.infraccion.setAnhoVehiculo(this.resultSet.getInt("vehiculo_anho"));
        this.infraccion.setDniPropietario(this.resultSet.getString("propietario_dni"));
        this.infraccion.setNombresPropietario(this.resultSet.getString("propietario_nombres"));
        this.infraccion.setApellidosPropietario(this.resultSet.getString("propietario_apellidos"));
        this.infraccion.setDireccionPropietario(this.resultSet.getString("propietario_direccion"));
        this.infraccion.setIdCamara(this.resultSet.getInt("id_camara"));
        this.infraccion.setModeloCamara(this.resultSet.getString("camara_modelo"));
        this.infraccion.setCodigoSerieCamara(this.resultSet.getString("camara_codigo_serie"));
        this.infraccion.setLatitud(this.resultSet.getInt("camara_latitud"));
        this.infraccion.setLongitud(this.resultSet.getInt("camara_longitud"));
        this.infraccion.setMonto(this.resultSet.getDouble("monto"));
        this.infraccion.setFechaCaptura(this.resultSet.getDate("fecha_captura"));
        this.infraccion.setFechaRegistro(this.resultSet.getDate("fecha_registro"));

    }

    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.infraccion);
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.infraccion = null;
    }
    
    @Override
    public List<Infraccion> listarTodos(){
        return (ArrayList<Infraccion>) super.listarTodos();
    }

}
