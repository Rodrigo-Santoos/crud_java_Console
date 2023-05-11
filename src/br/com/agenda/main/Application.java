package br.com.agenda.main;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

import java.util.Date;

public class Application {
    public static void main(String[] args) {
        ContatoDAO contatoDAO = new ContatoDAO();
        Contato contato = new Contato();

        //salvando no banco
        contato.setNome("Rodrigo Oliveira");
        contato.setIdade(23);
        contato.setDataCadastro(new Date());

        //contatoDAO.salve(contato);

//----------------------------------------------------------------------------------------------------------------------
        //visualizaçao de todos os registro do banco
        for (Contato c : contatoDAO.getContatos()){
            System.out.println("ID: " + c.getId());
            System.out.println("Contato: " + c.getNome());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Data Cadastro: " + c.getDataCadastro());
            System.out.println();
        }

//----------------------------------------------------------------------------------------------------------------------
        //Atualizando o contado

        Contato contatoUp = new Contato();

        contatoUp.setNome("Kelly Oliveira");
        contatoUp.setIdade(21);
        contatoUp.setDataCadastro(new Date());
        contatoUp.setId(3);// id que esta no banco

      //  contatoDAO.update(contatoUp);

//----------------------------------------------------------------------------------------------------------------------
        //deletando o contato pelo id
        contatoDAO.deleteById(4);

    }
}
