package com.itsa.gourds.masks.daos;

import com.itsa.gourds.masks.entities.Mask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaskDAO {
    private static final Logger logger = LoggerFactory.getLogger(MaskDAO.class);

    public static final String TABLE_NAME = "\"mask\"";
    DBController controller = DBController.getController();

    public Mask getMask(String id) {
        PreparedStatement pstmt;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        try {
            pstmt = controller.getConnection().prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));

            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                return readMaskInfo(rs);
            }
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
        }
        logger.debug("End");

        return null;
    }

    public boolean createMask(Mask mask) {
        PreparedStatement pstmt;

        String sql = "INSERT INTO " + TABLE_NAME + " (name, description, price, quantity, image) VALUES (?, ?, ?, ?, ?)";

        try {
            pstmt = controller.getConnection().prepareStatement(sql);
            pstmt.setString(1, mask.getName());
            pstmt.setString(2, mask.getDescription());
            pstmt.setString(3, mask.getPrice());
            pstmt.setString(4, mask.getQuantity());
            pstmt.setString(5, mask.getImage());

            pstmt.execute();
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        logger.debug("End");

        return true;
    }

    public boolean updateMask(String id, Mask mask) {
        PreparedStatement pstmt;

        String sql = "UPDATE " + TABLE_NAME + " " +
            "SET name = ?, " +
            "description = ?, " +
            "price = ?, " +
            "quantity = ?, " +
            "image = ? " +
            "WHERE id = ?";
        try {
            pstmt = controller.getConnection().prepareStatement(sql);
            pstmt.setString(1, mask.getName());
            pstmt.setString(2, mask.getDescription());
            pstmt.setString(3, mask.getPrice());
            pstmt.setString(4, mask.getQuantity());
            pstmt.setString(5, mask.getImage());
            pstmt.setInt(6, Integer.parseInt(id));

            pstmt.execute();
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        logger.debug("End");

        return true;
    }

    public List<Mask> getMasks() {
        List<Mask> maskList = new ArrayList<>();

        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM " + TABLE_NAME ;

        try {
            pstmt = controller.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                maskList.add(readMaskInfo(rs));
            }
        } catch (SQLException e) {
            logger.debug("Error: " + e.getMessage());
            e.printStackTrace();
        }

        logger.debug("End");

        return maskList;
    }

    public Mask readMaskInfo(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String price = rs.getString("price");
        String quantity = rs.getString("quantity");
        String image = rs.getString("image");

        return new Mask(id, name, description, price, quantity, image);
    }
}
