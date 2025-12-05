package pe.edu.pucp.transitsoft.db;

/**
 * Clase para gestionar conexiones a Oracle Database
 * Soporta tanto conexiones SID como Service Name
 */
public class DBManagerOracle extends DBManager {
    
    protected DBManagerOracle() {
        // Constructor protegido
    }
    
    @Override
    protected String getURL() {
        // Oracle soporta dos formatos de URL:
        // 1. Con SID: jdbc:oracle:thin:@hostname:port:SID
        // 2. Con Service Name: jdbc:oracle:thin:@//hostname:port/service_name
        
        String url = this.tipo_de_driver.concat("@");
        
        // Verificar si se debe usar Service Name (formato moderno recomendado)
        // Si base_de_datos contiene "/" se asume Service Name, sino SID
        if (this.base_de_datos.contains("/") || usarServiceName()) {
            // Formato con Service Name: jdbc:oracle:thin:@//host:port/service
            url = url.concat("//");
            url = url.concat(this.nombre_de_host);
            url = url.concat(":");
            url = url.concat(this.puerto);
            url = url.concat("/");
            url = url.concat(this.base_de_datos);
        } else {
            // Formato con SID (legacy): jdbc:oracle:thin:@host:port:SID
            url = url.concat(this.nombre_de_host);
            url = url.concat(":");
            url = url.concat(this.puerto);
            url = url.concat(":");
            url = url.concat(this.base_de_datos);
        }
        
        // System.out.println("Oracle URL: " + url);
        return url;
    }
    
    /**
     * Determina si se debe usar Service Name en lugar de SID
     * Por defecto, Oracle 12c+ recomienda Service Name
     */
    private boolean usarServiceName() {
        // Puedes agregar lógica adicional aquí si necesitas
        // Por ejemplo, leer una propiedad adicional del archivo de configuración
        return false; // Por defecto usa SID para compatibilidad
    }
    
    @Override
    public String retornarSQLParaUltimoAutoGenerado() {
        // Oracle no usa @@IDENTITY o @@last_insert_id
        // En su lugar, debes usar RETURNING INTO o consultar la secuencia
        // Este SQL obtiene el último ID de una secuencia específica
        
        // NOTA: En Oracle, típicamente usarías:
        // 1. RETURNING id INTO ? en el INSERT
        // 2. O consultar: SELECT nombre_secuencia.CURRVAL FROM DUAL
        
        // Como respuesta genérica, retornamos un placeholder
        // Deberás reemplazar 'NOMBRE_SECUENCIA' con tu secuencia real
        return "SELECT NOMBRE_SECUENCIA.CURRVAL as id FROM DUAL";
    }
}