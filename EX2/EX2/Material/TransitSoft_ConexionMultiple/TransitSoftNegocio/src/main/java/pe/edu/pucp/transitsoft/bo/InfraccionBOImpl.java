package pe.edu.pucp.transitsoft.bo;

import java.util.List;
import java.util.stream.Collectors;
import pe.edu.pucp.transitsoft.bo.mappers.CapturaToInfraccionMapper;
import pe.edu.pucp.transitsoft.dao.InfraccionDAO;
import pe.edu.pucp.transitsoft.daoimpl.InfraccionDAOImpl;
import pe.edu.pucp.transitsoft.dto.Infraccion;
import pe.edu.pucp.transitsoft.modelo.Captura;

public class InfraccionBOImpl implements InfraccionBO {
    
    private InfraccionDAO infraccionDAO;
    
    public InfraccionBOImpl(){
        this.infraccionDAO = new InfraccionDAOImpl();
    }
    
    @Override
    public List<Infraccion> crearInfracciones(List<Captura> capturasConExceso) {
        var mapper = new CapturaToInfraccionMapper();
        return capturasConExceso.stream()
            .map(mapper::map)
            .collect(Collectors.toList());
    }

    @Override
    public Integer insertar(Infraccion infraccion) {
        return this.infraccionDAO.insertar(infraccion);
    }

    @Override
    public Integer modificar(Infraccion infraccion) {
        return this.infraccionDAO.modificar(infraccion);
    }

    @Override
    public Integer eliminar(int id) {
        return this.infraccionDAO.eliminar(id);
    }

    @Override
    public Infraccion obtenerPorId(int id) {
        Infraccion infraccion = this.infraccionDAO.obtenerPorId(id);
        if(infraccion == null){
            infraccion = new Infraccion();
            infraccion.setIdInfraccion(-1);
        }
        return infraccion;
    }

    @Override
    public List<Infraccion> listarTodos() {
        return this.infraccionDAO.listarTodos();
    }
}
