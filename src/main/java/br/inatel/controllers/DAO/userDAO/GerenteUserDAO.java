package br.inatel.controllers.DAO.userDAO;

import br.inatel.models.Gerente;
import br.inatel.views.utils.ColorPrinter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for CREATE, READ, UPDATE objects of the table "gerente"
 */
public class GerenteUserDAO extends UserDAO<Gerente> {

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

    /**
     * Método para obter a query de seleção por id do Gerente
     * @return "SELECT * FROM gerente WHERE id_funcionario=?"
     */
    @Override
    protected String getSelectByIdQuery() {
        return "SELECT * FROM gerente WHERE id_funcionario=?";
    }

    /**
     * Função para mapear um ResultSet em um model.Gerente
     * @return Objeto Gerente mapeado do ResultSet
     */
    @Override
    protected Gerente getMapper() {
        try {
            return new Gerente(
                    rs.getInt("id_funcionario")
            );
        } catch (SQLException e) {
            ColorPrinter.printErro(e);
            return null;
        }
    }
}
