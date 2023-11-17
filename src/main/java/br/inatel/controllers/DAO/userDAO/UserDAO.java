package br.inatel.controllers.DAO.userDAO;

import br.inatel.controllers.DAO.ConnectionDAO;
import br.inatel.views.utils.ColorPrinter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe para manipular DAO com ID
 * @param <T> Model usado no DAO
 */
public abstract class UserDAO<T> extends ConnectionDAO<T> {

    /**
     * Método abstrato para obter a query de seleção por id para cada tabela
     * @return query de busca
     */
    protected abstract String getSelectByIdQuery();

    /**
     * Método abstrato para mapear o ResultSet em um model
     * @return Objeto T mapeado no ResultSet
     */
    protected abstract T getMapper();

    /**
     * Busca um objeto T em pelo id sua respectiva tabela
     * @param id do objeto selecionado
     * @return Objeto T selecionado
     */
    public T selectById(int id){
        connectToDB();

        String sql = getSelectByIdQuery();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs != null && rs.next()) {
                return getMapper();
            }

            sucesso = true;
        } catch (SQLException e) {
            ColorPrinter.printErro(e);
            sucesso = false;
        } finally {
            try {
                con.close();
                // st.close();
            } catch (SQLException e) {
                ColorPrinter.printErro(e);
            }
        }
        return null;
    }

    /**
     * Insere um objeto T que teha index em sua respectiva tabela
     * @param object objeto a ser inserido
     * @return boolean var (true: inseriu | false: falhou)
     */
    @Override
    public int insert(T object) {
        int id = -1;

        connectToDB();

        String sql = getInsertQuery();
        try {
            pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setInsertValues(pst, object);
            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                    if(generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                    } else {
                        System.out.println("Erro ao recupera o ID gerado");
                    }
                }
            } else {
                System.out.println("Nenhum registro foi inserido");
            }

            sucesso = true;
        } catch (SQLException e) {
            ColorPrinter.printErro(e);
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException e) {
                ColorPrinter.printErro(e);
            }
        }

        return id;
    }
}
