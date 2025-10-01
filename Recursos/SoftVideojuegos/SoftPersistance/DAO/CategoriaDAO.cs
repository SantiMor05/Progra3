using SoftVideojuegos.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPersistance.DAO
{
    public interface CategoriaDAO
    {
        int Insertar(CategoriaDTO categoria);

        CategoriaDTO ObtenerPorId(int categoriaID);

        BindingList<CategoriaDTO> ListarTodos();
    }
}
