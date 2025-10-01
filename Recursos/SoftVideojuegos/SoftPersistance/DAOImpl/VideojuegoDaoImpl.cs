using SoftInv.DAOImpl;
using SoftPersistance.DAO;
using SoftVideojuegos.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftPersistance.DAOImpl
{
    public class VideojuegoDaoImpl : DAOImplBase, VideojuegoDAO
    {
        private VideojuegoDTO videojuego;
        public VideojuegoDaoImpl() : base("videojuego") {
            this.retornarLlavePrimaria = true;
            this.videojuego = null;
        }

        public int Insertar(VideojuegoDTO videojuego)
        {
            //Nohace nada
            return 1;
        }

        public VideojuegoDTO ObtenerPorId(int videojuegoId)
        {
            //No hace nada
            return this.videojuego;
        }

        protected override void ConfigurarListaDeColumnas()
        {
           //No hace nada

        }

        BindingList<VideojuegoDTO> VideojuegoDAO.ListarTodos()
        {
            String sql = "LISTAR_VIDEOJUEGOS";
            BindingList<Object> lista= base.EjecutarProcedimientoSelect(sql, null, null);
            BindingList<VideojuegoDTO> retorno = new BindingList<VideojuegoDTO>();
            foreach(VideojuegoDTO objeto in lista)
                retorno.Add(objeto);
            return retorno;
        }


        protected override void AgregarObjetoALaLista(BindingList<Object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.videojuego);
        }

        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.videojuego = new VideojuegoDTO();

            this.videojuego.Id_videojuego = lector.GetInt32(0);
            this.videojuego.Nombre_videojuego = lector.GetString(1);
            this.videojuego.Fecha_lanzamiento = lector.GetDateTime(2);
            this.videojuego.Precio = lector.GetDouble(3);
            this.videojuego.Num_jugadores = lector.GetInt32(4);

            CategoriaDTO categoria = new CategoriaDTO();
            GeneroDTO genero = new GeneroDTO();

            categoria.Id = lector.GetChar(6);
            genero.Id_genero = lector.GetInt32(5);

            this.videojuego.Categoria = categoria;
            this.videojuego.Genero = genero;

        }

    }
}
