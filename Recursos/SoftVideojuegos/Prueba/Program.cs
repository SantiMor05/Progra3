using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using SoftPersistance.DAO;
using SoftPersistance.DAOImpl;
using SoftVideojuegos.Db;
using SoftVideojuegos.Model;


namespace Prueba
{
    internal class Program
    {
        static void Main(string[] args)
        {
            String contra = Convert.ToBase64String(System.Text.Encoding.Unicode.GetBytes("prog31inf30"));
            Console.WriteLine(contra);
            Console.WriteLine(System.Text.Encoding.Unicode.GetString(Convert.FromBase64String(contra)));

            DbConnection con = DBManager.Instance.Connection;
            if (con != null)
            {
                Console.WriteLine("Conexion exitosa");
            }
            else Console.WriteLine("Conexion fallida");

            VideojuegoDAO videojuegoDAO = new VideojuegoDaoImpl();

            BindingList<VideojuegoDTO> lista = videojuegoDAO.ListarTodos();

            foreach(VideojuegoDTO juego in lista)
            {
                Console.WriteLine(juego.Nombre_videojuego);
            }

        }
    }
}
