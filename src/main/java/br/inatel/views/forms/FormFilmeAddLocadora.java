package br.inatel.views.forms;

import br.inatel.Main;
import br.inatel.models.FilmeDisplay;

public class FormFilmeAddLocadora extends FormFilme {
    private final int idInfoFilme;

    public FormFilmeAddLocadora(int idInfoFilme) {
        super();
        this.idInfoFilme = idInfoFilme;
    }

    @Override
    public boolean render() {
        String idFilmeAux = "" + Main.context.getLocadoraId() + idInfoFilme;
        int idFilme = Integer.parseInt(idFilmeAux);

        FilmeDisplay filme = controller.getFilmeById(idFilme);

        if (filme == null)
            System.out.println("Nenhuma cópia na locadora.");
        else {
            System.out.println("---------------------------------------");
            showFilme(filme);
            System.out.println("---------------------------------------");
        }

        int qnt = intInput("Adicionar quantas cópias? ");

        if (controller.adicionaFilmeLocadora(idInfoFilme, qnt) == -1) {
            printVermelho("Falha ao adicionar filme");
            return false;
        }

        printVerde("Adicionado ");
        printVermelho(qnt + "");
        printVerde(" cópias do filme: ");
        printVermelho(idFilme + "");
        printVerde(", com sucesso\n");

        return true;
    }

}
