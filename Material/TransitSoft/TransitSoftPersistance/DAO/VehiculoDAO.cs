using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.Model;

namespace TransitSoft.DAO
{
    public interface VehiculoDAO
    {
        int? Insertar(Vehiculo vehiculo);
        int? Modificar(Vehiculo vehiculo);
        int? Eliminar(int id);
        Vehiculo ObtenerPorId(int id);
        IList<Vehiculo> ListarTodos();
    }
}
