package br.inatel.views;

import br.inatel.Main;

/**
 * Tela do Funcionario
 */
public class TelaFuncionario extends Tela {
    /**
     * Mostra Tela do Funcionario
     */
    public boolean render() {
        //TODO
        System.out.println("Funcionario Logado");
        System.out.println("ID: " + Main.context.getUserId());
        Main.sc.nextLine();

        return true;
    }
}