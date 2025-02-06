package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import entidades.Usuario;

public class UsuarioDao {
    Connection conect;

    public UsuarioDao() {
        conect = ConectDao.getConect();
    }

    public void salvar(Usuario usuario) {
        try {
            Statement stm = conect.createStatement();
            String sql = "insert into usuarios (nome) values ('" + usuario.getNome() + "')";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
