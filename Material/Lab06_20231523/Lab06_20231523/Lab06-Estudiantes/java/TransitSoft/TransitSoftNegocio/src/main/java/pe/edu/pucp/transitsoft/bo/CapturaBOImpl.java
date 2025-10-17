package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.transitsoft.dao.CapturaDAO;
import pe.edu.pucp.transitsoft.daoimpl.CapturaDAOImpl;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class CapturaBOImpl implements CapturaBO {
    private final CapturaDAO capturaDao;
    
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
    public void actualizar(Captura modelo) {
        // TODO: Llamar a capturaDao.actualizar(modelo)
        capturaDao.actualizar(modelo);
    }
}
