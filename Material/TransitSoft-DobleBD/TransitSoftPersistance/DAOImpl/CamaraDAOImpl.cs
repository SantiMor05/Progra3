using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Management.Instrumentation;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.DAO;
using TransitSoft.DAOImpl.Util;
using TransitSoft.Model;

namespace TransitSoft.DAOImpl
{
    public class CamaraDAOImpl : DAOImplBase, CamaraDAO
    {
        private Camara camara;
        public CamaraDAOImpl() : base("camara")
        {
            this.camara = null;
            this.retornarLlavePrimaria = true;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("id", true, true));
            this.listaColumnas.Add(new Columna("modelo", false, false));
            this.listaColumnas.Add(new Columna("codigo_serie", false, false));
            this.listaColumnas.Add(new Columna("latitud", false, false));
            this.listaColumnas.Add(new Columna("longitud", false, false));
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            AgregarParametro("@id", this.camara.Id);
        }
        public int? Eliminar(int id)
        {
            this.camara = new Camara();
            this.camara.Id = id;
            return base.Eliminar();
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            AgregarParametro("@modelo", this.camara.Modelo);
            AgregarParametro("@codigo_serie", this.camara.CodigoSerie);
            AgregarParametro("@latitud", this.camara.Latitud);
            AgregarParametro("@longitud", this.camara.Longitud);
        }

        public int? Insertar(Camara camara)
        {
            this.camara = camara;
            return base.Insertar();
        }

        public IList<Camara> ListarTodos()
        {
            return base.ListarTodos().Cast<Camara>().ToList();
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            AgregarParametro("@modelo", this.camara.Modelo);
            AgregarParametro("@codigo_serie", this.camara.CodigoSerie);
            AgregarParametro("@latitud", this.camara.Latitud);
            AgregarParametro("@longitud", this.camara.Longitud);
            AgregarParametro("@id", this.camara.Id);
        }

        public int? Modificar(Camara camara)
        {
            this.camara = camara;
            return base.Modificar();
        }

        public Camara ObtenerPorId(int id)
        {
            this.camara = new Camara();
            this.camara.Id = id;
            base.ObtenerPorId();
            return this.camara;
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            AgregarParametro("@id", this.camara.Id);
        }
        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.camara = new Camara();

            this.camara.Id = this.lector.GetInt32(0);
            this.camara.Modelo = this.lector.GetString(1);
            this.camara.CodigoSerie = this.lector.GetString(2);
            this.camara.Latitud = this.lector.GetInt32(3);
            this.camara.Longitud = this.lector.GetInt32(4);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.camara = null;
        }

        protected override void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.camara);
        }
    }
}
