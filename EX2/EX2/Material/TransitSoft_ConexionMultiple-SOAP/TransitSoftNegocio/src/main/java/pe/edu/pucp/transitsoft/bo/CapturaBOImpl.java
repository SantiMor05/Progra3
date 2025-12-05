package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Camara;
import pe.edu.pucp.transitsoft.modelo.Captura;
import pe.edu.pucp.transitsoft.modelo.EstadoCaptura;
import pe.edu.pucp.transitsoft.modelo.Vehiculo;

public class CapturaBOImpl implements CapturaBO {
    private CapturaDAO capturaDao;
    
    public CapturaBOImpl() {
        this.capturaDao = new CapturaDAOImpl();
    }
    
    @Override
    public List<Captura> obtenerCapturasConExcesoDeVelocidad() {
        // TODO: LLamar a capturaDao.leerTodos() y luego
        // devolver solo las capturas donde la velocidad excede
        // el limite permitido
        List<Captura> capturas= capturaDao.leerTodos();
        List<Captura> capturaInfractoras = new ArrayList<>();
        for(var captura : capturas){
            if(captura.getVelocidad() > 50)
                capturaInfractoras.add(captura);
        }
        return capturaInfractoras;
    }

    @Override
    public List<Captura> leerTodos() {
        return this.capturaDao.leerTodos();
    }
    
    @Override
    public Integer eliminar(int id) {
        return this.capturaDao.eliminar(id);
    }

    @Override
    public Captura obtenerPorId(int id) {
        Captura captura =  this.capturaDao.obtenerPorId(id);
        if(captura == null){
            captura = new Captura();
            captura.setId(-1);
        }
        return captura;
    }

    @Override
    public boolean actualizar(int id, String placa, double velocidad, Date fechaCaptura, Camara camara, EstadoCaptura estado, Vehiculo vehiculo) {
        Captura captura = new Captura();
        captura.setId(id);
        captura.setCamara(camara);
        captura.setEstado(estado);
        captura.setFechaCaptura(fechaCaptura);
        captura.setPlaca(placa);
        captura.setVehiculo(vehiculo);
        captura.setVelocidad(velocidad);
        
        return this.capturaDao.actualizar(captura);
    }

    @Override
    public Integer insertar(String placa, double velocidad, Date fechaCaptura, Camara camara, EstadoCaptura estado, Vehiculo vehiculo) {
        Captura captura = new Captura();
        captura.setCamara(camara);
        captura.setEstado(estado);
        captura.setFechaCaptura(fechaCaptura);
        captura.setPlaca(placa);
        captura.setVehiculo(vehiculo);
        captura.setVelocidad(velocidad);
        
        return this.capturaDao.insertar(captura);
    }

    @Override
    public Integer modificar(int id, String placa, double velocidad, Date fechaCaptura, Camara camara, EstadoCaptura estado, Vehiculo vehiculo) {
        Captura captura = new Captura();
        captura.setId(id);
        captura.setCamara(camara);
        captura.setEstado(estado);
        captura.setFechaCaptura(fechaCaptura);
        captura.setPlaca(placa);
        captura.setVehiculo(vehiculo);
        captura.setVelocidad(velocidad);
        
        return this.capturaDao.modificar(captura);
    }
}
