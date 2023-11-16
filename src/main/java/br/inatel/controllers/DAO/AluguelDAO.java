package br.inatel.controllers.DAO;

import br.inatel.models.Aluguel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for CREATE, READ, UPDATE objects of the table "aluguel"
 */
public class AluguelDAO extends ConnectionDAO<Aluguel> {

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO aluguel(data_locacao, id_cliente, id_locadora) VALUES(?, ?, ?)";
    }

    @Override
    protected void setInsertValues(PreparedStatement pst, Aluguel aluguel) throws SQLException {
        pst.setString(1, aluguel.getdataLocacao());
        pst.setInt(2, aluguel.getIdCliente());
        pst.setInt(3, aluguel.getIdLocadora());
    }
}
