package pe.edu.pucp.transitsoft.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.transitsoft.db.util.Cifrado;
import pe.edu.pucp.transitsoft.db.util.MotorDeBaseDeDatos;

public abstract class DBManager {

    private static final String ARCHIVO_CONFIGURACION = "db.properties";
    
    private Connection conexion;
    private String driver;
    protected String tipo_de_driver;
    protected String base_de_datos;
    protected String nombre_de_host;
    protected String puerto;
    private String usuario;
    private String contraseña;
    
    // Tres instancias estáticas independientes
    private static DBManager dbManager = null;   // BD Principal
    private static DBManager dbManager2 = null;  // BD Secundaria
    private static DBManager dbManager3 = null;  // BD Terciaria

    protected DBManager() {
        //constructor protegido para evitar que se creen instancias.
        //Solo se podrá crear una instancia y esta debe hacerse usando el 
        //método getInstance()
    }

    // Instancia principal (mantiene compatibilidad)
    public static DBManager getInstance() {
        if (DBManager.dbManager == null) {
            DBManager.createInstance();
        }
        return DBManager.dbManager;
    }

    // Segunda instancia
    public static DBManager getInstance2() {
        if (DBManager.dbManager2 == null) {
            DBManager.createInstance2();
        }
        return DBManager.dbManager2;
    }

    // Tercera instancia
    public static DBManager getInstance3() {
        if (DBManager.dbManager3 == null) {
            DBManager.createInstance3();
        }
        return DBManager.dbManager3;
    }

    private static void createInstance() {
        if (DBManager.dbManager == null) {
            MotorDeBaseDeDatos motor = DBManager.obtenerMotorDeBaseDeDato("");
            DBManager.dbManager = crearInstanciaSegunMotor(motor);
            DBManager.dbManager.leer_archivo_de_propiedades("");
        }
    }

    private static void createInstance2() {
        if (DBManager.dbManager2 == null) {
            MotorDeBaseDeDatos motor = DBManager.obtenerMotorDeBaseDeDato("2");
            DBManager.dbManager2 = crearInstanciaSegunMotor(motor);
            DBManager.dbManager2.leer_archivo_de_propiedades("2");
        }
    }

    private static void createInstance3() {
        if (DBManager.dbManager3 == null) {
            MotorDeBaseDeDatos motor = DBManager.obtenerMotorDeBaseDeDato("3");
            DBManager.dbManager3 = crearInstanciaSegunMotor(motor);
            DBManager.dbManager3.leer_archivo_de_propiedades("3");
        }
    }

    private static DBManager crearInstanciaSegunMotor(MotorDeBaseDeDatos motor) {
        if (motor == MotorDeBaseDeDatos.MYSQL) {
            return new DBManagerMySQL();
        } else if (motor == MotorDeBaseDeDatos.MSSQL) {
            return new DBManagerMSSQL();
        } else if (motor == MotorDeBaseDeDatos.ORACLE) {
            return new DBManagerOracle();
        }
        return null;
    }

    public Connection getConnection() {
        try {
            Class.forName(this.driver);
            this.conexion = DriverManager.getConnection(
                getURL(), 
                this.usuario, 
                Cifrado.descifrarMD5(this.contraseña)
            );
        } catch (ClassNotFoundException ex) {
            System.err.println("Error al generar la conexión - " + ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }

    protected abstract String getURL();

    private void leer_archivo_de_propiedades(String sufijo) {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;

            properties.load(this.getClass().getResourceAsStream(nmArchivoConf));
            this.driver = properties.getProperty("driver" + sufijo);
            this.tipo_de_driver = properties.getProperty("tipo_de_driver" + sufijo);
            this.base_de_datos = properties.getProperty("base_de_datos" + sufijo);
            this.nombre_de_host = properties.getProperty("nombre_de_host" + sufijo);
            this.puerto = properties.getProperty("puerto" + sufijo);
            this.usuario = properties.getProperty("usuario" + sufijo);
            this.contraseña = properties.getProperty("contrasenha" + sufijo);
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
    }
    
    private static MotorDeBaseDeDatos obtenerMotorDeBaseDeDato(String sufijo) {
        Properties properties = new Properties();
        try {
            String nmArchivoConf = "/" + ARCHIVO_CONFIGURACION;
            properties.load(DBManager.class.getResourceAsStream(nmArchivoConf));            
            String tipo_de_driver = properties.getProperty("tipo_de_driver" + sufijo);
            
            if (tipo_de_driver == null) {
                System.err.println("⚠ No se encontró tipo_de_driver" + sufijo + ", usando MySQL por defecto");
                return MotorDeBaseDeDatos.MYSQL;
            }
            
            // Verificar el tipo de driver por su prefijo
            if (tipo_de_driver.startsWith("jdbc:mysql")) {
                return MotorDeBaseDeDatos.MYSQL;
            } else if (tipo_de_driver.startsWith("jdbc:sqlserver")) {
                return MotorDeBaseDeDatos.MSSQL;
            } else if (tipo_de_driver.startsWith("jdbc:oracle")) {
                return MotorDeBaseDeDatos.ORACLE;
            }
            
            // Si no coincide con ninguno, intentar por nombre del driver
            String driver = properties.getProperty("driver" + sufijo);
            if (driver != null) {
                if (driver.contains("mysql")) {
                    return MotorDeBaseDeDatos.MYSQL;
                } else if (driver.contains("sqlserver")) {
                    return MotorDeBaseDeDatos.MSSQL;
                } else if (driver.contains("oracle")) {
                    return MotorDeBaseDeDatos.ORACLE;
                }
            }
            
            // Fallback a MySQL por defecto
            System.err.println("⚠ No se pudo determinar el motor de BD" + sufijo + ", usando MySQL por defecto");
            return MotorDeBaseDeDatos.MYSQL;
            
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo de propiedades - " + ex);
        }
        return MotorDeBaseDeDatos.MYSQL;
    }
    
    public abstract String retornarSQLParaUltimoAutoGenerado();
}