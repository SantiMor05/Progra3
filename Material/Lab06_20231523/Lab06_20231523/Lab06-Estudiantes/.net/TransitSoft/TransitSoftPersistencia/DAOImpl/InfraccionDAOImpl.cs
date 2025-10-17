using PUCP.TransitSoft.Modelo;
using PUCP.TransitSoft.Persistencia.DAO;
using PUCP.TransitSoft.Db;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Data;

namespace PUCP.TransitSoft.Persistencia.DAOImpl {
    public class InfraccionDAOImpl : BaseDAOImpl<Infraccion>, IInfraccionDAO
    {
        protected override DbCommand ComandoActualizar(DbConnection conn, Infraccion modelo)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoCrear(DbConnection conn, Infraccion modelo)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandType = CommandType.StoredProcedure;

            cmd.CommandText = "insertarInfraccion";

            añadirParametros(cmd, modelo);
            return cmd;
        }

        private void añadirParametros(DbCommand cmd, Infraccion modelo)
        {
            AgregarParametroEntrada(cmd, "@p_placa", DbType.String, modelo.Placa);
            AgregarParametroEntrada(cmd, "@p_velocidad", DbType.Double, modelo.Velocidad);
            AgregarParametroEntrada(cmd, "@p_limite", DbType.Double, modelo.Limite);
            AgregarParametroEntrada(cmd, "@p_exceso", DbType.Double, modelo.Exceso);
            AgregarParametroEntrada(cmd, "@p_vehiculo_marca", DbType.String, modelo.MarcaVehiculo);
            AgregarParametroEntrada(cmd, "@p_vehiculo_modelo", DbType.String, modelo.ModeloVehiculo);
            AgregarParametroEntrada(cmd, "@p_vehiculo_anho", DbType.Int32, modelo.AnhoVehiculo);
            AgregarParametroEntrada(cmd, "@p_propietario_dni", DbType.String, modelo.DniPropietario);
            AgregarParametroEntrada(cmd, "@p_propietario_nombres", DbType.String, modelo.NombresPropietario);
            AgregarParametroEntrada(cmd, "@p_propietario_apellidos", DbType.String, modelo.ApellidosPropietario);
            AgregarParametroEntrada(cmd, "@p_propietario_direccion", DbType.String, modelo.DireccionPropietario);
            AgregarParametroEntrada(cmd, "@p_camara_modelo", DbType.String, modelo.ModeloCamara);
            AgregarParametroEntrada(cmd, "@p_camara_codigo_serie", DbType.String, modelo.CodigoSerieCamara);
            AgregarParametroEntrada(cmd, "@p_camara_latitud", DbType.Int32, modelo.Latitud);
            AgregarParametroEntrada(cmd, "@p_camara_longitud", DbType.Int32, modelo.Longitud);
            AgregarParametroEntrada(cmd, "@p_monto", DbType.Double, modelo.Monto);
            AgregarParametroEntrada(cmd, "@p_fecha_captura", DbType.DateTime, modelo.FechaCaptura);
            AgregarParametroEntrada(cmd, "@p_fecha_registro", DbType.DateTime, modelo.FechaRegistro);
            AgregarParametroSalida(cmd, "@p_id", DbType.Int32);


        }

        protected override DbCommand ComandoEliminar(DbConnection conn, int id)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoLeer(DbConnection conn, int id)
        {
            throw new NotImplementedException();
        }

        protected override DbCommand ComandoLeerTodos(DbConnection conn)
        {
            DbCommand cmd = conn.CreateCommand();
            cmd.CommandType = CommandType.StoredProcedure;

            cmd.CommandText = "listarInfracciones";

            return cmd;
        }

        protected override Infraccion MapearModelo(DbDataReader reader)
        {
            Infraccion modelo = new Infraccion();
            modelo.Id = reader.GetInt32(0);
            modelo.Placa = reader.GetString(1);
            modelo.Velocidad = reader.GetFloat(2);
            modelo.Limite = reader.GetFloat(3);
            modelo.Exceso = reader.GetFloat(4);
            modelo.MarcaVehiculo = reader.GetString(5);
            modelo.ModeloVehiculo = reader.GetString(6);
            modelo.AnhoVehiculo = reader.GetInt32(7);
            modelo.DniPropietario = reader.GetString(8);
            modelo.NombresPropietario = reader.GetString(9);
            modelo.ApellidosPropietario = reader.GetString(10);
            modelo.DireccionPropietario = reader.GetString(11);
            modelo.ModeloCamara = reader.GetString(12);
            modelo.CodigoSerieCamara = reader.GetString(13);
            modelo.Latitud = reader.GetInt32(14);
            modelo.Longitud = reader.GetInt32(15);
            modelo.Monto = reader.GetDouble(16);
            modelo.FechaCapturaTimestamp = reader.GetInt32(17);
            modelo.FechaRegistroTimestamp = reader.GetInt32(18);

            return modelo;
        }



    }
}
