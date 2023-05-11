package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    /*
    * CRUD
    */
//----------------------------------------------------------------------------------------------------------------------
    //Salvando no banco C - create
    public void salve(Contato contato){
        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        //verificando a conexao com o banco
        try {
            //cria conexao com o banco
            conn = ConnectionFactory.createConnectionToMysql();

            //valores espeados pela query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2,contato.getIdade());
            pstm.setDate(3,new Date(contato.getDataCadastro().getTime()));

            //execultando query
            pstm.execute();
            System.out.println("Cadastrado com Sucesso :)");

        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            //fechando conexao
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }

            }catch (SQLException sq){
                sq.printStackTrace();
            }
        }
    }

//------------------------------------------------------------------------------------------------------------------
    //listando o banco de dados R - read
    public List<Contato> getContatos(){

        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<>();

        //conexao com o banco
        Connection conn = null;

        PreparedStatement pstm = null;

        //classe que reculpera os dados do banco **SELECT
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMysql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()){
                Contato contato = new Contato();

                //Recuperar id
                contato.setId(rset.getInt("id"));

                //recuperando nome
                contato.setNome(rset.getString("nome"));

                //recuperando idade
                contato.setIdade(rset.getInt("idade"));

                //recuperando data
                contato.setDataCadastro(rset.getDate("datacadastro"));

                contatos.add(contato);

            }

        } catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                if (rset != null){
                    rset.close();
                }
                if (pstm != null){
                    pstm.close();
                }
                if (conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return contatos;
    }

//------------------------------------------------------------------------------------------------------------------
    //update no banco de dados U - update
    public void update(Contato contato){
        String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? WHERE id = ?  ";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //criando conexao com o banco
            conn = ConnectionFactory.createConnectionToMysql();

            //Criar classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            //adicionando valores para atualizar
            pstm.setString(1,contato.getNome());
            pstm.setInt(2,contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            //qual o id do registro para atualizar?
            pstm.setInt(4,contato.getId());

            //executando a query
            pstm.execute();
            System.out.println("Atualizado com sucesso");

        } catch (Exception e){
            e.printStackTrace();

        }finally {
            try {
                if (pstm != null){
                    pstm.close();
                }

                if (conn != null){
                    conn.close();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

//------------------------------------------------------------------------------------------------------------------
    //delete no banco de dados D - delete
    public void deleteById(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMysql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, id);

            pstm.execute();

            System.out.println("Usuario deletado");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null){
                    conn.close();
                }
                if (pstm != null){
                    pstm.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
