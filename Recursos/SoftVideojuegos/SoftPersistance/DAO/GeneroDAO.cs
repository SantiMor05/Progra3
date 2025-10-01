using SoftVideojuegos.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPersistance.DAO
{
    public interface GeneroDAO
    {
        int Insertar(GeneroDTO genero);

        GeneroDTO ObtenerPorId(int generoId);

        BindingList<GeneroDTO> ListarTodos();
    }
}
