package br.com.AtivoOS.dal;

import java.sql.*;// traz tudo o que existe no banco de dados sql

public class ModuloConexao {

    // Metodo responsavel por manter a conexão com  banco de dados
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // Alinha abaicho chama o drive importado para biblioteca
        String driver = "com.mysql.jdbc.Driver";

        // Armazenando informações referentes ao banco de dados
        String url = "jdbc:mysql://localhost:3306/dbsistem_os";
        //String url = "jdbc:mysql://localhost:3306/dbsistem_os?characterEncoding=utf-8";//Tipo de configuração para o Iriporte de impressão

        String user = "root";
        String password = "Dente@007";
        //String user = "dba";
        //String password = "Dente@007";

        // Estabelecendo a conexão com o banco de dados
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;

        } catch (Exception e) {
            // Mostra se a erro de conexão e qual erro
            System.out.println(e);
            return null;

        }

    }

}
