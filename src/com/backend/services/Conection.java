package com.backend.services;

import java.sql.*;

public class Conection {

    final private String driver = "org.postgresql.Driver";
    final private String endereco_bs_dados = "jdbc:postgresql://" + System.getProperty("IP") + ":" + System.getProperty("db_port") + "/" + System.getProperty("db_base");
    private Connection conexao;
    private Connection conexaopst;
    public PreparedStatement pst;
    public Statement statement;
    public ResultSet resultset;
    Conection con_default;

    public Connection conectaBs() {
        //Connection result = null;
        try {
            System.out.println(endereco_bs_dados);
            System.out.println(System.getProperty("db_user"));
            System.out.println(System.getProperty("db_pass"));
            Class.forName(driver);
            conexao = DriverManager.getConnection(endereco_bs_dados, System.getProperty("db_user"), System.getProperty("db_pass"));
            conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return conexao;
        } catch (ClassNotFoundException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        } catch (IllegalStateException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        }
        return null;
    }

    public boolean conectapst() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexaopst = DriverManager.getConnection(endereco_bs_dados, System.getProperty("db_user"), System.getProperty("db_pass"));
            // conexaopst.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (ClassNotFoundException e) {
            result = false;
        } catch (Exception Fonte) {
            result = false;
        }
        return result;
    }

    public void desconecta() {
        try {
            conexao.close();
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        }
    }

    public void desconectapst() {
        try {
            conexao.close();
            conexaopst.close();
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        }
    }

    public void executeSQLpst(String sql) {
        try {
            pst = conexaopst.prepareStatement(sql);
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        }
    }

    public boolean executeQuery(String sql) {
        boolean validated = true;
        System.out.println("executeQuery");
        System.out.println(sql);
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultset = statement.executeQuery(sql);
        } catch (SQLException e) {
            validated = false;
            System.setProperty("MsgInvalid", e.toString());
            System.out.println(System.getProperty("MsgInvalid"));
        }
        return validated;
    }

    public boolean executeUpdate(String sql) {
        boolean validated = true;
        //System.out.println("executeUpdateSQL");
        //System.out.println(sql);
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.setProperty("MsgInvalid", e.toString());
            validated = false;
            System.out.println(System.getProperty("MsgInvalid"));
        }
        return validated;
    }
}
