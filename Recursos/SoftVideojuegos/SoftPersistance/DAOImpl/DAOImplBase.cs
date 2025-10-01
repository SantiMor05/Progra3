using SoftInv.DAOImpl.Util;
using SoftVideojuegos.Db;
using SoftVideojuegos.Model;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SoftInv.DAOImpl
{
    public abstract class DAOImplBase
    {
        protected string nombreDeTabla;
        protected BindingList<Columna> listaColumnas;
        protected DbConnection conexion;
        protected DbTransaction transaccion;
        protected DbCommand comando;
        protected DbDataReader lector;
        protected bool mostrarSentenciaSQL;
        protected bool retornarLlavePrimaria;
        protected bool usarTransaccion;

        public DAOImplBase(String nombreDeTabla)
        {
            this.nombreDeTabla = nombreDeTabla;
            this.retornarLlavePrimaria = false;
            this.mostrarSentenciaSQL = true;
            this.usarTransaccion = true;
            this.IncluirListaDeColumnas();
        }

        private void IncluirListaDeColumnas()
        {
            this.listaColumnas = new BindingList<Columna>();
            this.ConfigurarListaDeColumnas();
        }

        protected abstract void ConfigurarListaDeColumnas();

        protected void AbrirConexion()
        {
            this.conexion = DBManager.Instance.Connection;
            this.conexion.Open();
        }

        protected void CerrarConexion()
        {
            if (this.conexion != null)
            {
                this.conexion.Close();
            }
        }

        protected void IniciarTransaccion()
        {
            this.AbrirConexion();
            this.transaccion = this.conexion.BeginTransaction();
        }

        protected void ComitarTransaccion()
        {
            this.transaccion.Commit();
            this.transaccion = null;
        }

        protected void RollbackTransaccion()
        {
            if (this.transaccion != null)
            {
                this.transaccion.Rollback();
            }
            this.transaccion = null;
        }

        protected void ColocarSQLenComando(string sql)
        {
            this.comando.Connection = this.conexion;
            this.comando.CommandText = sql;
            this.comando.CommandType = System.Data.CommandType.Text;
        }

        protected void EjecutarConsultaEnBD()
        {
            this.lector = this.comando.ExecuteReader();
        }

        protected int Insertar()
        {
            return this.Ejecuta_DML(TipoOperacionDML.INSERTAR);
        }

        protected int Modificar()
        {
            return this.Ejecuta_DML(TipoOperacionDML.MODIFICAR);
        }

        protected int Eliminar()
        {
            return this.Ejecuta_DML(TipoOperacionDML.ELIMINAR);
        }

        private int Ejecuta_DML(TipoOperacionDML tipoOperacion)
        {
            int resultado = 0;
            try
            {
                if (this.usarTransaccion)
                {
                    this.IniciarTransaccion();
                }
                else
                {
                    this.AbrirConexion();
                }
                using (this.comando = DBManager.Instance.CrearComando()) ;
                string sql = "";
                switch (tipoOperacion)
                {
                    case TipoOperacionDML.INSERTAR:
                        sql = this.GenerarSQLParaInsercion();
                        break;
                    case TipoOperacionDML.MODIFICAR:
                        sql = this.GenerarSQLParaModificacion();
                        break;
                    case TipoOperacionDML.ELIMINAR:
                        sql = this.GenerarSQLParaEliminacion();
                        break;
                }
                this.ColocarSQLenComando(sql);
                switch (tipoOperacion)
                {
                    case TipoOperacionDML.INSERTAR:
                        this.IncluirValorDeParametrosParaInsercion();
                        break;
                    case TipoOperacionDML.MODIFICAR:
                        this.IncluirValorDeParametrosParaModificacion();
                        break;
                    case TipoOperacionDML.ELIMINAR:
                        this.IncluirValorDeParametrosParaEliminacion();
                        break;
                }
                this.comando.ExecuteNonQuery();
                if (this.retornarLlavePrimaria)
                {
                    int id = this.RetornarUltimoAutoGenerado();
                    resultado = id;
                }
                if (this.usarTransaccion)
                {
                    this.ComitarTransaccion();
                }
            }
            catch (Exception ex)
            {
                if (this.usarTransaccion)
                {
                    this.RollbackTransaccion();
                }
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return resultado;
        }

        protected string GenerarSQLParaInsercion()
        {
            //sentencia SQL a generar es similar a 
            //INSERT INTO INV_ALMACENES (NOMBRE, ALMACEN_CENTRAL) VALUES (?,?)
            string sql = "INSERT INTO ";
            sql += this.nombreDeTabla;
            sql += "(";
            string sql_columnas = "";
            string sql_parametros = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)!columna.EsAutoGenerado)
                {
                    if (!string.IsNullOrWhiteSpace(sql_columnas))
                    {
                        sql_columnas += ", ";
                        sql_parametros += ", ";
                    }
                    sql_columnas += columna.Nombre;
                    sql_parametros += "@" + columna.Nombre;
                }
            }
            sql += sql_columnas;
            sql += ") VALUES (";
            sql += sql_parametros;
            sql += ")";
            return sql;
        }

        protected string GenerarSQLParaModificacion()
        {
            //sentencia SQL a generar es similar a 
            //UPDATE INV_ALMACENES SET NOMBRE=?, ALMACEN_CENTRAL=? WHERE ALMACEN_ID=?
            string sql = "UPDATE ";
            sql += this.nombreDeTabla;
            sql += " SET ";
            string sql_columnas = "";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
                else
                {
                    if (!string.IsNullOrWhiteSpace(sql_columnas))
                    {
                        sql_columnas += ", ";
                    }
                    sql_columnas += columna.Nombre;
                    sql_columnas += "=@" + columna.Nombre;
                }
            }
            sql += sql_columnas;
            sql += " WHERE ";
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaEliminacion()
        {
            //sentencia SQL a generar es similar a 
            //DELETE FROM INV_ALMACENES WHERE ALMACEN_ID=?
            string sql = "DELETE FROM ";
            sql += this.nombreDeTabla;
            sql += " WHERE ";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
            }
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaObtenerPorId()
        {
            //sentencia SQL a generar es similar a 
            //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES WHERE ALMACEN_ID = ?
            string sql = "SELECT ";
            string sql_columnas = "";
            string sql_predicado = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if ((bool)columna.EsLlavePrimaria)
                {
                    if (!string.IsNullOrWhiteSpace(sql_predicado))
                    {
                        sql_predicado += ", ";
                    }
                    sql_predicado += columna.Nombre;
                    sql_predicado += "=@" + columna.Nombre;
                }
                if (!string.IsNullOrWhiteSpace(sql_columnas))
                {
                    sql_columnas += ", ";
                }
                sql_columnas += columna.Nombre;
            }
            sql += sql_columnas;
            sql += " FROM ";
            sql += this.nombreDeTabla;
            sql += " WHERE ";
            sql += sql_predicado;
            return sql;
        }

        protected string GenerarSQLParaListarTodos()
        {
            //sentencia SQL a generar es similar a 
            //SELECT ALMACEN_ID, NOMBRE, ALMACEN_CENTRAL FROM INV_ALMACENES
            string sql = "SELECT ";
            string sql_columnas = "";
            foreach (Columna columna in this.listaColumnas)
            {
                if (!string.IsNullOrWhiteSpace(sql_columnas))
                {
                    sql_columnas += ", ";
                }
                sql_columnas += columna.Nombre;
            }
            sql += sql_columnas;
            sql += " FROM ";
            sql += this.nombreDeTabla;
            return sql;
        }
        protected virtual void IncluirValorDeParametrosParaInsercion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void IncluirValorDeParametrosParaModificacion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void IncluirValorDeParametrosParaEliminacion()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        private int RetornarUltimoAutoGenerado()
        {
            int resultado = -1;
            String sql = DBManager.Instance.RetornarSQLParaUltimoAutoGenerado();
            this.ColocarSQLenComando(sql);
            this.EjecutarConsultaEnBD();

            if (this.lector.Read())
            {
                resultado = Convert.ToInt32(this.lector[0]);
            }

            ///if (this.lector.Read())
            //{
            //    resultado = this.lector.GetInt32(0);
            //}
            this.lector.Close();
            return resultado;
        }

        protected void ObtenerPorId()
        {
            this.comando = DBManager.Instance.CrearComando();
            try
            {
                this.AbrirConexion();
                string sql = this.GenerarSQLParaObtenerPorId();
                this.IncluirValorDeParametrosParaObtenerPorId();
                this.ColocarSQLenComando(sql);
                this.EjecutarConsultaEnBD();
                if (this.lector.Read())
                    this.InstanciarObjetoDelResultSet(this.lector);
                else
                    this.LimpiarObjetoDelResultSet();
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
        }

        protected virtual void IncluirValorDeParametrosParaObtenerPorId()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void LimpiarObjetoDelResultSet()
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected virtual void InstanciarObjetoDelResultSet(DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected BindingList<Object> ListarTodos()
        {
            BindingList<Object> lista = new BindingList<Object>();
            this.comando = DBManager.Instance.CrearComando();
            try
            {
                this.AbrirConexion();
                string sql = this.GenerarSQLParaListarTodos();
                this.ColocarSQLenComando(sql);
                this.EjecutarConsultaEnBD();
                while (this.lector.Read())
                {
                    this.AgregarObjetoALaLista(lista, this.lector);
                }
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
            finally
            {
                this.CerrarConexion();
            }
            return lista;
        }

        protected virtual void AgregarObjetoALaLista(BindingList<Object> lista, DbDataReader lector)
        {
            throw new NotImplementedException("Método no sobreescrito.");
        }

        protected void AgregarParametro(string nombre, object value)
        {
            DbParameter parametero = this.comando.CreateParameter();
            parametero.ParameterName = nombre;
            parametero.Value = value;
            this.comando.Parameters.Add(parametero);
        }



        public int EjecutarProcedimientoDML(String procedure, Object modelo, Action<DbCommand, Object> añadirParametros, int tipo)
        {

            using (DbConnection conn = DBManager.Instance.Connection)
            {
                DbCommand comando = conn.CreateCommand();
                comando.CommandType = CommandType.StoredProcedure;
                comando.CommandText = procedure;
                int n;
                try
                {
                    conn.Open();
                    if (añadirParametros != null)
                        añadirParametros(comando, modelo);
                    n = comando.ExecuteNonQuery();
                    //Tipo:   0: Insert    1:Update    2:Delete
                    if (n == 0)
                    {
                        Console.Error.WriteLine("No hubo en ningún registro");
                        return -1;
                    }
                    if (tipo == 0)
                        return 1; //RetornarUltimoAutoGenerado
                    else
                        return n;
                }
                catch (DbException e)
                {
                    Console.Error.WriteLine("Error SQL: " + e.Message);
                    throw new Exception("Error SQL", e);
                }
                catch (Exception e)
                {
                    Console.Error.WriteLine("Error inesperado: " + e.Message);
                    throw;
                }
            }

        }

        public BindingList<Object> EjecutarProcedimientoSelect(String procedure, Object modelo, Action<DbCommand, Object> añadirParametros)
        {
            using (DbConnection conn = DBManager.Instance.Connection)
            {
                DbCommand comando = conn.CreateCommand();
                comando.CommandType = CommandType.StoredProcedure;
                comando.CommandText = procedure;

                try
                {
                    conn.Open();
                    if (añadirParametros != null)
                        añadirParametros(comando, modelo);
                    DbDataReader reader = comando.ExecuteReader();

                    BindingList<Object> lista = new BindingList<Object>();

                    while (reader.Read())
                    {
                        this.AgregarObjetoALaLista(lista, reader); //InstaciarObjetos
                    }

                    return lista;
                }
                catch (DbException e)
                {
                    Console.Error.WriteLine("Error SQL: " + e.Message);
                    throw new Exception("Error SQL", e);
                }
                catch (Exception e)
                {
                    Console.Error.WriteLine("Error inesperado: " + e.Message);
                    throw;
                }
            }

        }

        protected void AgregarParametroSalida(DbCommand cmd, string nombre, DbType tipo)
        {
            DbParameter param = cmd.CreateParameter();
            param.ParameterName = nombre;
            param.DbType = tipo;
            param.Direction = ParameterDirection.Output;
            cmd.Parameters.Add(param);
        }


    }
}
