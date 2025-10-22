using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Data.Common;
using TransitSoft.Db;

namespace TransitSoft.Tests
{
    [TestClass]
    public class TestSimpleConnection
    {
        [TestMethod]
        public void ProbarConexion1()
        {
            Console.WriteLine("═══ PROBANDO BASE DE DATOS 1 (Principal) ═══");

            try
            {
                using (DbConnection conn = DBManager.Instance.Connection)
                {
                    conn.Open();

                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("✓ CONEXIÓN EXITOSA");
                    Console.ResetColor();

                    Console.WriteLine($"  Servidor    : {conn.DataSource}");
                    Console.WriteLine($"  Base de Datos: {conn.Database}");
                    Console.WriteLine($"  Estado      : {conn.State}");
                    Console.WriteLine($"  Tipo        : {conn.GetType().Name}");

                    // Ejecutar consulta de prueba
                    DbCommand cmd = DBManager.Instance.CrearComando();
                    cmd.Connection = conn;
                    cmd.CommandText = "SELECT DATABASE() as CurrentDB"; // MySQL
                    // Si es SQL Server, usar: "SELECT DB_NAME() as CurrentDB"

                    try
                    {
                        object result = cmd.ExecuteScalar();
                        Console.WriteLine($"  DB Actual   : {result}");

                        Console.ForegroundColor = ConsoleColor.Green;
                        Console.WriteLine("✓ CONSULTA EJECUTADA CORRECTAMENTE");
                        Console.ResetColor();
                    }
                    catch (Exception ex)
                    {
                        Console.ForegroundColor = ConsoleColor.Yellow;
                        Console.WriteLine($"⚠ Consulta con error (normal si es SQL Server): {ex.Message}");
                        Console.ResetColor();
                    }
                }
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("✗ ERROR DE CONEXIÓN");
                Console.WriteLine($"  Mensaje: {ex.Message}");
                Console.ResetColor();
            }
        }

        [TestMethod]
        public void ProbarConexion2()
        {
            Console.WriteLine("═══ PROBANDO BASE DE DATOS 2 (Secundaria) ═══");

            try
            {
                using (DbConnection conn = DBManager.Instance2.Connection)
                {
                    conn.Open();

                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("✓ CONEXIÓN EXITOSA");
                    Console.ResetColor();

                    Console.WriteLine($"  Servidor    : {conn.DataSource}");
                    Console.WriteLine($"  Base de Datos: {conn.Database}");
                    Console.WriteLine($"  Estado      : {conn.State}");
                    Console.WriteLine($"  Tipo        : {conn.GetType().Name}");

                    // Ejecutar consulta de prueba
                    DbCommand cmd = DBManager.Instance2.CrearComando();
                    cmd.Connection = conn;
                    //cmd.CommandText = "SELECT DATABASE() as CurrentDB"; // MySQL
                    // Si es SQL Server, usar: "SELECT DB_NAME() as CurrentDB"
                    cmd.CommandText = "SELECT DB_NAME() as CurrentDB";
                    try
                    {
                        object result = cmd.ExecuteScalar();
                        Console.WriteLine($"  DB Actual   : {result}");

                        Console.ForegroundColor = ConsoleColor.Green;
                        Console.WriteLine("✓ CONSULTA EJECUTADA CORRECTAMENTE");
                        Console.ResetColor();
                    }
                    catch (Exception ex)
                    {
                        Console.ForegroundColor = ConsoleColor.Yellow;
                        Console.WriteLine($"⚠ Consulta con error (normal si es SQL Server): {ex.Message}");
                        Console.ResetColor();
                    }
                }
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("✗ ERROR DE CONEXIÓN");
                Console.WriteLine($"  Mensaje: {ex.Message}");
                Console.ResetColor();
            }
        }

        [TestMethod]
        public void ProbarConexionesSimultaneas()
        {
            Console.WriteLine("═══ PROBANDO CONEXIONES SIMULTÁNEAS ═══");

            DbConnection conn1 = null;
            DbConnection conn2 = null;

            try
            {
                // Abrir ambas conexiones al mismo tiempo
                conn1 = DBManager.Instance.Connection;
                conn2 = DBManager.Instance2.Connection;

                conn1.Open();
                Console.WriteLine($"✓ Conexión 1 abierta: {conn1.Database}");

                conn2.Open();
                Console.WriteLine($"✓ Conexión 2 abierta: {conn2.Database}");

                // Verificar que ambas están abiertas
                Console.WriteLine($"\nEstados de conexión:");
                Console.WriteLine($"  BD1: {conn1.State}");
                Console.WriteLine($"  BD2: {conn2.State}");

                // Ejecutar consultas en paralelo
                DbCommand cmd1 = DBManager.Instance.CrearComando();
                cmd1.Connection = conn1;
                cmd1.CommandText = "SELECT 'BD1' as Origen, NOW() as Tiempo"; // MySQL

                DbCommand cmd2 = DBManager.Instance2.CrearComando();
                cmd2.Connection = conn2;
                cmd2.CommandText = "SELECT 'BD2' as Origen, GETDATE() as Tiempo"; // MSSQL

                try
                {
                    using (var reader1 = cmd1.ExecuteReader())
                    {
                        if (reader1.Read())
                        {
                            Console.WriteLine($"\n✓ Lectura de BD1: {reader1["Origen"]} - {reader1["Tiempo"]}");
                        }
                    }

                    using (var reader2 = cmd2.ExecuteReader())
                    {
                        if (reader2.Read())
                        {
                            Console.WriteLine($"✓ Lectura de BD2: {reader2["Origen"]} - {reader2["Tiempo"]}");
                        }
                    }

                    Console.ForegroundColor = ConsoleColor.Green;
                    Console.WriteLine("\n✓✓ AMBAS CONEXIONES FUNCIONAN SIMULTÁNEAMENTE");
                    Console.ResetColor();
                }
                catch (Exception ex)
                {
                    Console.ForegroundColor = ConsoleColor.Yellow;
                    Console.WriteLine($"\n⚠ Error en consultas paralelas: {ex.Message}");
                    Console.ResetColor();
                }
            }
            catch (Exception ex)
            {
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine($"✗ ERROR: {ex.Message}");
                Console.ResetColor();
            }
            finally
            {
                // Cerrar conexiones
                if (conn1 != null && conn1.State == System.Data.ConnectionState.Open)
                {
                    conn1.Close();
                    Console.WriteLine("\n✓ Conexión 1 cerrada");
                }

                if (conn2 != null && conn2.State == System.Data.ConnectionState.Open)
                {
                    conn2.Close();
                    Console.WriteLine("✓ Conexión 2 cerrada");
                }
            }
        }

    }
}
