using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.DAO;
using TransitSoft.DAOImpl.Util;
using TransitSoft.Model;

namespace TransitSoft.DAOImpl
{
    public class CapturaDAOImpl : DAOImplBase, CapturaDAO
    {
        private Captura captura;

        public CapturaDAOImpl() : base("captura")
        {
            this.captura = null;
            this.retornarLlavePrimaria = true;
        }
        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("id", true, true));
            this.listaColumnas.Add(new Columna("id_camara", false, false));
            this.listaColumnas.Add(new Columna("placa", false, false));
            this.listaColumnas.Add(new Columna("velocidad", false, false));
            this.listaColumnas.Add(new Columna("fecha_captura", false, false));
            this.listaColumnas.Add(new Columna("estado", false, false));
        }

        //public bool Actualizar(Captura captura)
        //{
        //    this.captura = captura;
        //    return bas
        //}

        public int? Eliminar(int id)
        {
            throw new NotImplementedException();
        }

        public int? Insertar(Captura captura)
        {
            throw new NotImplementedException();
        }

        public List<Captura> LeerTodos()
        {
            throw new NotImplementedException();
        }

        public int? Modificar(Captura captura)
        {
            throw new NotImplementedException();
        }

        public Captura ObtenerPorId(int id)
        {
            throw new NotImplementedException();
        }

        public bool Actualizar(Captura captura)
        {
            throw new NotImplementedException();
        }
    }
}
