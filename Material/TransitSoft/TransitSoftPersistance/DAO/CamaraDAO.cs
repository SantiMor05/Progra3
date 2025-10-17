using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.Model;

namespace TransitSoft.DAO
{
    public interface CamaraDAO
    {
        int? Insertar(Camara camara);
        int? Modificar(Camara camara);
        int? Eliminar(int id);
        Camara ObtenerPorId(int id);
        IList<Camara> ListarTodos();
    }
}
