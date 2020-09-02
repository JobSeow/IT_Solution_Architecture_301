package com.itsa.gourds.masks.daos;

import com.itsa.gourds.masks.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    public static final String TABLE_NAME = "\"user\"";
    DBController controller = DBController.getController();

    public User getUser(String username) {
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE username = ?";

        try {
            pstmt = controller.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                return readUserInfo(rs);
            }
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
        }

        logger.debug("End");

        return null;
    }

    public User createUser(User user) {
        PreparedStatement pstmt;

        String sql = "INSERT INTO " + TABLE_NAME + " (name, username, password) VALUES (?, ?, ?)";

        try {
            pstmt = controller.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getPassword());

            pstmt.execute();
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        logger.debug("End");

        return user;
    }

    public User readUserInfo(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String username = rs.getString("username");
        String password = rs.getString("password");

        return new User(id, username, password, null);
    }
}
