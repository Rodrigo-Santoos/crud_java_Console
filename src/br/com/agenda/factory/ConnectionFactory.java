package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //nome usuario no mysql
    private static final String USERNAME = "root";

    //senha usuario no mysql
    private static final String PASSWORD = "root";

    //caminho do banco, porta e o nome do banco
    private static final  String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda?autoReconnect=true&useSSL=false";

    /*
        Conexao com o banco
    */

    public static Connection createConnectionToMysql () {
        //fazendo a classe ser carregada pela JVM
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void main(String[] args) throws Exception {
        //Recuperar uma conexao com o banco de dados
        Connection con = createConnectionToMysql();

        //testar se a conexao é nula
        if(con != null){
            //System.out.println("Conexao foi um sucesso");
            con.close();
        }
    }
}
