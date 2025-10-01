using SoftVideojuegos.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPersistance.DAO
{
    public interface VideojuegoDAO
    {
        int Insertar(VideojuegoDTO videojuego);

        VideojuegoDTO ObtenerPorId(int videojuegoId);

        BindingList<VideojuegoDTO> ListarTodos();
    }
}
