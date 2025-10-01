using System;
using System.Configuration;
using System.Data.Common;
using MySql.Data.MySqlClient;
using SoftMed.Db.Util;

namespace SoftMed.Db
{
    public class DBManager
    {
        private DbConnection conexion;
        private string baseDeDatos;
        private string nombreDeHost;
        private string puerto;
        private string usuario;
        private string contraseña;
        internal static DBManager dbManager;

        // Constructor privado para evitar instancias directas
        private DBManager() { }

        // Punto de entrada público (Singleton)
        public static DBManager Instance
        {
            get
            {
                if (dbManager == null)
                {
                    CreateInstance();
                }
                return dbManager;
            }
        }

        // Método para inicializar la instancia
        private static void CreateInstance()
        {
            if (dbManager == null)
            {
                dbManager = new DBManager();
                dbManager.LeerArchivoDeConfiguracion();
            }
        }

        private void LeerArchivoDeConfiguracion()
        {
            baseDeDatos = ConfigurationManager.AppSettings["baseDeDatos"];
            nombreDeHost = ConfigurationManager.AppSettings["nombreDeHost"];
            puerto = ConfigurationManager.AppSettings["puerto"];
            usuario = ConfigurationManager.AppSettings["usuario"];
            contraseña = ConfigurationManager.AppSettings["contraseña"];
        }

        public DbConnection Connection
        {
            get
            {
                if (conexion == null)
                {
                    string cadenaDeConexion = ObtenerCadenaDeConexion();
                    conexion = new MySqlConnection(cadenaDeConexion);
                }
                return conexion;
            }
        }

        private string ObtenerCadenaDeConexion()
        {
            MySqlConnectionStringBuilder csBuilder = new MySqlConnectionStringBuilder()
            {
                Server = nombreDeHost,
                Port = uint.Parse(puerto),
                Database = baseDeDatos,
                UserID = usuario,
                Password = Descifrar(contraseña)
            };
            return csBuilder.ConnectionString;
        }

        public DbCommand CrearComando()
        {
            return new MySqlCommand();
        }

        public string RetornarSQLParaUltimoAutoGenerado()
        {
            return "select @@last_insert_id as id";
        }

        public static string Cifrar(string texto)
        {
            return Convert.ToBase64String(System.Text.Encoding.Unicode.GetBytes(texto));
        }

        public static string Descifrar(string textoEncriptado)
        {
            return System.Text.Encoding.Unicode.GetString(Convert.FromBase64String(textoEncriptado));
        }
    }
}