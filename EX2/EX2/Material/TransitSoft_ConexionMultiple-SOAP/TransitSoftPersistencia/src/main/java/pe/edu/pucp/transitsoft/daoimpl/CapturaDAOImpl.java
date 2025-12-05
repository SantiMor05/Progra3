package pe.edu.pucp.transitsoft.daoimpl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.util.Columna;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Propietario;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

// TODO: Implementar CapturaDAOImpl
public class CapturaDAOImpl extends DAOImplBase implements CapturaDAO {

    private Captura captura;

    public CapturaDAOImpl() {
        this(1);
    }
    
    public CapturaDAOImpl(int bd) {
        super("captura", bd);
        this.captura = null;
        this.retornarLlavePrimaria = true;
    }
    
    @Override
    protected void configurarListaDeColumnas() {
        this.listaColumnas.add(new Columna("id", true, true));
        this.listaColumnas.add(new Columna("id_camara", false, false));
        this.listaColumnas.add(new Columna("placa", false, false));
        this.listaColumnas.add(new Columna("velocidad", false, false));
        this.listaColumnas.add(new Columna("fecha_captura", false, false));
        this.listaColumnas.add(new Columna("estado", false, false));
        
    }
    
    @Override
    protected void agregarObjetoALaLista(List lista) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.captura);
    }
    
    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.captura = new Captura();
        Camara camara = new Camara();
        Vehiculo vehiculo = new Vehiculo();
        Propietario propietario = new Propietario();
        String estado;
        
        this.captura.setId(this.resultSet.getInt("id"));
        this.captura.setPlaca(this.resultSet.getString("placa"));
        this.captura.setVelocidad(this.resultSet.getDouble("velocidad"));
        this.captura.setFechaCaptura(this.resultSet.getDate("fecha_captura"));
        estado = this.resultSet.getString("estado");
        if("Registrado".equals(estado))
            this.captura.setEstado(EstadoCaptura.REGISTRADO);
        else
            this.captura.setEstado(EstadoCaptura.PROCESADO);
        
       camara.setId(this.resultSet.getInt("id_camara"));
       camara.setModelo(this.resultSet.getString("camara_modelo"));
       camara.setCodigoSerie(this.resultSet.getString("camara_codigo_serie"));
       camara.setLatitud(this.resultSet.getLong("camara_latitud"));
       camara.setLongitud(this.resultSet.getLong("camara_longitud"));
       
       propietario.setId(this.resultSet.getInt("id_propietario"));
       propietario.setDni(this.resultSet.getString("propietario_dni"));
       propietario.setNombres(this.resultSet.getString("propietario_nombres"));
       propietario.setApellidos(this.resultSet.getString("propietario_apellidos"));
       propietario.setDireccion(this.resultSet.getString("propietario_direccion"));
       
       vehiculo.setId(this.resultSet.getInt("id_vehiculo"));
       vehiculo.setPlaca(this.resultSet.getString("vehiculo_placa"));
       vehiculo.setMarca(this.resultSet.getString("vehiculo_marca"));
       vehiculo.setModelo(this.resultSet.getString("vehiculo_modelo"));
       vehiculo.setAnho(this.resultSet.getInt("vehiculo_anho"));
       
       vehiculo.setPropietario(propietario);
       captura.setVehiculo(vehiculo);
       captura.setCamara(camara);

    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.captura = null;
    }
    
    @Override
    public List<Captura> leerTodos() {
        String sql = "{call listarCapturas()}";
        return (List <Captura>) super.listarTodos(sql, null, null);
    }

    @Override
    public boolean actualizar(Captura captura) {
        
        Object parametros = captura;
        
        String sql = "{call modificarEstadoCaptura  (?, ?)}";
        Boolean conTransacion = true;
        
        return this.ejecutarProcedimientoAlmacenado(sql, this::incluirValorDeParametros, parametros, conTransacion)!= 0;
         
    }

    private void incluirValorDeParametros(Object objetoParametros){
        Captura parametros = (Captura) objetoParametros;
        try {
            this.statement.setString(1, parametros.getEstado().toString());
            this.statement.setInt(2, parametros.getId());
        } catch (SQLException ex) {
            System.getLogger(CapturaDAOImpl.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }

    
    @Override
    public Integer insertar(Captura captura) {
        this.captura = captura;
        return super.insertar(); 
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.statement.setInt(1, this.captura.getCamara().getId());
        this.statement.setString(2, this.captura.getPlaca());
        this.statement.setDouble(3, this.captura.getVelocidad());
        this.statement.setDate(4, (Date) this.captura.getFechaCaptura());
        this.statement.setString(5, this.captura.getEstado().toString());
    }

    
    @Override
    public Integer modificar(Captura captura) {
        this.captura = captura;
        return super.modificar(); 
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirValorDeParametrosParaInsercion();
        this.statement.setInt(6, this.captura.getId());
    }

    
    @Override
    public Integer eliminar(int id) {
        this.captura = new Captura();
        this.captura.setId(id);
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {

        this.statement.setInt(1, this.captura.getId());
    }

    
    @Override
    public Captura obtenerPorId(int id) {
        this.captura = new Captura();
        this.captura.setId(id);
        super.obtenerPorId();
        return this.captura;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.statement.setInt(1, this.captura.getId());
    }
}