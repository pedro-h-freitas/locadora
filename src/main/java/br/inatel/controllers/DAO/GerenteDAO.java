package br.inatel.controllers.DAO;

import br.inatel.models.Gerente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for CREATE, READ, UPDATE objects of the table "gerente"
 */
public class GerenteDAO extends ConnectionDAO<Gerente> {

    /**
     * Método para obter a query de inserção específica do Gerente
     * @return "INSERT INTO gerente(id_funcionario) VALUES(?)"
     */
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO gerente(id_funcionario) VALUES(?)";
    }

    /**
     * Método para definir os valores de um Gerente para inserir
     * @param pst PreparedStatement
     * @param gerente objeto Gerente a ser inserido
     * @throws SQLException Exceção de SQL
     */
    @Override
    protected void setInsertValues(PreparedStatement pst, Gerente gerente) throws SQLException {
        pst.setInt(1, gerente.getIdFuncionario());
    }
}
