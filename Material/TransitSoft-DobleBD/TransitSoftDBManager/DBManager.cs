using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Common;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TransitSoft.Db.Util;

namespace TransitSoft.Db
{
    public abstract class DBManager
    {
        protected DbConnection conexion;
        protected string baseDeDatos;
        protected string nombreDeHost;
        protected string puerto;
        protected string usuario;
        protected string contraseña;
        protected static DBManager dbManager;

        // Nueva instancia para la segunda base de datos
        protected static DBManager dbManager2;

        protected DBManager()
        {
            //constructor protegido para evitar que se creen instancias.
            //Solo se podrá crear una instancia y esta debe hacerse usando el 
            //método getInstance()
        }

        // Instancia principal (mantiene compatibilidad con código existente)
        public static DBManager Instance
        {
            get
            {
                if (dbManager == null)
                {
                    DBManager.CreateInstance();
                }
                return dbManager;
            }
        }

        // Nueva propiedad para la segunda base de datos
        public static DBManager Instance2
        {
            get
            {
                if (dbManager2 == null)
                {
                    DBManager.CreateInstance2();
                }
                return dbManager2;
            }
        }

        private static void CreateInstance()
        {
            if (DBManager.dbManager == null)
            {
                if (DBManager.ObtenerMotorDeBaseDeDatos() == MotorDeBaseDeDatos.MYSQL)
                    DBManagerMySQL.CreateConcreteInstance();
                else
                    DBManagerMSSQL.CreateConcreteInstance();
                DBManager.dbManager.LeerArchivoDeConfiguracion(""); // BD principal
            }
        }

        // Nuevo método para crear la segunda instancia
        private static void CreateInstance2()
        {
            if (DBManager.dbManager2 == null)
            {
                if (DBManager.ObtenerMotorDeBaseDeDatos2() == MotorDeBaseDeDatos.MYSQL)
                    DBManagerMySQL.CreateConcreteInstance2();
                else
                    DBManagerMSSQL.CreateConcreteInstance2();
                DBManager.dbManager2.LeerArchivoDeConfiguracion("2"); // BD secundaria
            }
        }

        private static MotorDeBaseDeDatos ObtenerMotorDeBaseDeDatos()
        {
            string tipoDeBaseDeDatos = ConfigurationManager.AppSettings["tipoDeBaseDeDatos"];
            if (tipoDeBaseDeDatos.Equals(MotorDeBaseDeDatos.MYSQL.ToString()))
                return MotorDeBaseDeDatos.MYSQL;
            else
                return MotorDeBaseDeDatos.MSSQL;
        }

        // Nuevo método para obtener el motor de la segunda BD
        private static MotorDeBaseDeDatos ObtenerMotorDeBaseDeDatos2()
        {
            string tipoDeBaseDeDatos = ConfigurationManager.AppSettings["tipoDeBaseDeDatos2"];
            if (string.IsNullOrEmpty(tipoDeBaseDeDatos))
            {
                // Si no está configurada, usar el mismo tipo que la primera
                return ObtenerMotorDeBaseDeDatos();
            }
            if (tipoDeBaseDeDatos.Equals(MotorDeBaseDeDatos.MYSQL.ToString()))
                return MotorDeBaseDeDatos.MYSQL;
            else
                return MotorDeBaseDeDatos.MSSQL;
        }

        // Modificado para aceptar un sufijo
        private void LeerArchivoDeConfiguracion(string sufijo)
        {
            this.baseDeDatos = ConfigurationManager.AppSettings[$"baseDeDatos{sufijo}"];
            this.nombreDeHost = ConfigurationManager.AppSettings[$"nombreDeHost{sufijo}"];
            this.puerto = ConfigurationManager.AppSettings[$"puerto{sufijo}"];
            this.usuario = ConfigurationManager.AppSettings[$"usuario{sufijo}"];
            this.contraseña = ConfigurationManager.AppSettings[$"contraseña{sufijo}"];
        }

        public DbConnection Connection
        {
            get
            {
                string cadenaDeConexion = this.ObtenerCadenaDeConexion();
                this.RealizarConexionABaseDeDatos(cadenaDeConexion);
                return this.conexion;
            }
        }

        protected abstract void RealizarConexionABaseDeDatos(string cadenaDeConexion);

        protected abstract string ObtenerCadenaDeConexion();

        public abstract DbCommand CrearComando();

        public abstract string RetornarSQLParaUltimoAutoGenerado();

        public static string Cifrar(string texto)
        {
            return Convert.ToBase64String(Encoding.Unicode.GetBytes(texto));
        }

        public static string Descifrar(string textoEncriptado)
        {
            return Encoding.Unicode.GetString(Convert.FromBase64String(textoEncriptado));
        }
    }
}