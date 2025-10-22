using Microsoft.Data.SqlClient;
using System;
using System.Collections.Generic;
using System.Data.Common;
//using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TransitSoft.Db
{
    public class DBManagerMSSQL : DBManager
    {
        private DBManagerMSSQL()
        {
            //construtor ocultado que no se puedan crear instancias desde fuera de la clase
        }

        public static void CreateConcreteInstance()
        {
            if (DBManager.dbManager == null)
                DBManager.dbManager = new DBManagerMSSQL();
        }

        // Nuevo método para crear la segunda instancia
        public static void CreateConcreteInstance2()
        {
            if (DBManager.dbManager2 == null)
                DBManager.dbManager2 = new DBManagerMSSQL();
        }

        protected override void RealizarConexionABaseDeDatos(string cadenaDeConexion)
        {
            this.conexion = new SqlConnection(cadenaDeConexion);
        }

        protected override string ObtenerCadenaDeConexion()
        {
            SqlConnectionStringBuilder csBuilder = new SqlConnectionStringBuilder()
            {
                DataSource = $"{this.nombreDeHost},{this.puerto}",
                InitialCatalog = this.baseDeDatos,
                UserID = this.usuario,
                Password = DBManager.Descifrar(this.contraseña),
                TrustServerCertificate = true
            };

            return csBuilder.ConnectionString;
        }

        public override DbCommand CrearComando()
        {
            return new SqlCommand();
        }

        public override string RetornarSQLParaUltimoAutoGenerado()
        {
            string sql = "select @@IDENTITY as id";
            return sql;
        }
    }
}