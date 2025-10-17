using System.Collections.Generic;

namespace PUCP.TransitSoft.Persistencia.DAO {
    public interface IPersistible<T, I> {
        I Crear(T modelo);
        bool Actualizar(T modelo);
        bool Eliminar(int id);
        T Leer(int id);
        List<T> LeerTodos();
        //int EjecutarProcedimientoDML(string procedure, System.Action<T> añadirParametros);
    }
}
