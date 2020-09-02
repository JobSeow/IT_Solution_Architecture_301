package com.itsa.gourds.masks.daos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itsa.gourds.masks.entities.Cart;
import com.itsa.gourds.masks.entities.CartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CartDAO {
  private static final Logger logger = LoggerFactory.getLogger(CartDAO.class);

  public static final String TABLE_NAME = "\"cart\"";
  DBController controller = DBController.getController();

  public CartResponse getCart(String userid) {
    PreparedStatement pstmt = null;
    String sql = "SELECT * FROM " + TABLE_NAME + " WHERE userid = ?";

    try {
      pstmt = controller.getConnection().prepareStatement(sql);
      pstmt.setString(1, userid);

      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        return readCartInfo(rs);
      }
    } catch (SQLException e) {
      logger.debug("Error: " + e.getMessage());
      e.printStackTrace();
    }

    logger.debug("End");

    return null;
  }

  public boolean createCart(Cart cart) {
    System.out.println("createCartDAO");
    PreparedStatement pstmt;

    String sql = "INSERT INTO " + TABLE_NAME + " (userid, content) VALUES (?, ?) ON CONFLICT (userid) DO UPDATE SET " +
        "userid = ?, content = ?";

    try {
      pstmt = controller.getConnection().prepareStatement(sql);
      pstmt.setString(1, cart.getUserId());
      pstmt.setObject(2, cart.getContent(), Types.OTHER);
      pstmt.setString(3, cart.getUserId());
      pstmt.setObject(4, cart.getContent(), Types.OTHER);

      pstmt.execute();
      System.out.println(pstmt);
    } catch (SQLException e) {
      logger.debug("Error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }

    System.out.println("end");
    logger.debug("End");

    return true;
  }

  public boolean updateCart(Cart cart) {
    PreparedStatement pstmt;

    String sql = "UPDATE " + TABLE_NAME + " " +
        "SET content = ? " +
        "WHERE userid = ?";

    try {
      pstmt = controller.getConnection().prepareStatement(sql);
      pstmt.setObject(1, cart.getContent(), Types.OTHER);
      pstmt.setString(2, cart.getUserId());

      pstmt.execute();
    } catch (SQLException e) {
      logger.debug("Error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }

    logger.debug("End");

    return true;
  }

  public boolean deleteCart(String userId) {
    PreparedStatement pstmt = null;
    String sql = "DELETE FROM " + TABLE_NAME + " WHERE userid = ?";

    try {
      pstmt = controller.getConnection().prepareStatement(sql);
      pstmt.setString(1, userId);

      pstmt.execute();
    } catch (SQLException e) {
      logger.debug("Error: " + e.getMessage());
      e.printStackTrace();
      return false;
    }

    logger.debug("End");

    return true;
  }

  public CartResponse readCartInfo(ResultSet rs) throws SQLException {
    String id = rs.getString("id");
    String userId = rs.getString("userid");
    String content = rs.getString("content");

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode jsonNode = null;

    if (content != null && !content.equals("")) {
      try {
        jsonNode = objectMapper.readTree(content);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return new CartResponse(id, userId, jsonNode);
  }
}
