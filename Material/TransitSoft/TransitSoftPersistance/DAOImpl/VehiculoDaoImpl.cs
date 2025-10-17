using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.DAO;
using TransitSoft.DAOImpl;
using TransitSoft.DAOImpl.Util;
using TransitSoft.Model;

namespace TransitSoftPersistance.DAOImpl
{
    public class VehiculoDaoImpl : DAOImplBase, VehiculoDAO
    {
        private Vehiculo vehiculo;
        public VehiculoDaoImpl() : base("vehiculo")
        {
            this.vehiculo = null;
            this.retornarLlavePrimaria = true;
        }

        protected override void ConfigurarListaDeColumnas()
        {
            this.listaColumnas.Add(new Columna("id", true, true));
            this.listaColumnas.Add(new Columna("placa", false, false));
            this.listaColumnas.Add(new Columna("marca", false, false));
            this.listaColumnas.Add(new Columna("modelo", false, false));
            this.listaColumnas.Add(new Columna("anho", false, false));
        }
        public int? Eliminar(int id)
        {
            this.vehiculo = new Vehiculo();
            this.vehiculo.Id = id;
            return base.Eliminar();
        }

        protected override void IncluirValorDeParametrosParaEliminacion()
        {
            AgregarParametro("@id", this.vehiculo.Id);
        }

        public int? Insertar(Vehiculo vehiculo)
        {
            this.vehiculo = vehiculo;
            return base.Insertar();
        }

        protected override void IncluirValorDeParametrosParaInsercion()
        {
            AgregarParametro("@placa", this.vehiculo.Placa);
            AgregarParametro("@marca", this.vehiculo.Marca);
            AgregarParametro("@modelo", this.vehiculo.Modelo);
            AgregarParametro("@anho", this.vehiculo.Anho);
        }

        public IList<Vehiculo> ListarTodos()
        {
            return base.ListarTodos().Cast<Vehiculo>().ToList();
        }

        public int? Modificar(Vehiculo vehiculo)
        {
            this.vehiculo = vehiculo;
            return base.Insertar();
        }

        protected override void IncluirValorDeParametrosParaModificacion()
        {
            AgregarParametro("@id", this.vehiculo.Id);
            this.IncluirValorDeParametrosParaInsercion();
        }

        public Vehiculo ObtenerPorId(int id)
        {
            this.vehiculo = new Vehiculo();
            this.vehiculo.Id = id;
            base.ObtenerPorId();
            return this.vehiculo;
        }

        protected override void IncluirValorDeParametrosParaObtenerPorId()
        {
            AgregarParametro("@id", this.vehiculo.Id);
        }
        protected override void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            this.vehiculo = new Vehiculo();

            this.vehiculo.Id = this.lector.GetInt32(0);
            this.vehiculo.Placa = this.lector.GetString(1);
            this.vehiculo.Marca = this.lector.GetString(2);
            this.vehiculo.Modelo = this.lector.GetString(3);
            this.vehiculo.Anho = this.lector.GetInt32(4);
        }

        protected override void LimpiarObjetoDelResultSet()
        {
            this.vehiculo = null;
        }

        protected override void AgregarObjetoALaLista(IList<Object> lista, DbDataReader lector)
        {
            this.InstanciarObjetoDelResultSet(lector);
            lista.Add(this.vehiculo);
        }

        //TODO: Conecta vehiculo con propietario con la tabla intermedia
    }
}
