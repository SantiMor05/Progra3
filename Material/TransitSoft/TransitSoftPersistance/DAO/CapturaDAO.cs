using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.Model;

namespace TransitSoft.DAO
{
    public interface CapturaDAO
    {
        List<Captura> LeerTodos();
        bool Actualizar(Captura captura);
        int? Insertar(Captura captura);
        int? Modificar(Captura captura);
        int? Eliminar(int id);
        Captura ObtenerPorId(int id);
    }
}
