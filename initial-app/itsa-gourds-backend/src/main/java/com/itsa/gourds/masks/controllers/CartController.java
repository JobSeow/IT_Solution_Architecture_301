package com.itsa.gourds.masks.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.itsa.gourds.masks.daos.CartDAO;
import com.itsa.gourds.masks.daos.UserDAO;
import com.itsa.gourds.masks.entities.*;
import com.itsa.gourds.masks.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
public class CartController {

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @RequestMapping(path = "/users/{userid}/carts", method = RequestMethod.GET)
  public CartResponse getCart(@PathVariable(value = "userid") String userId) {
    CartDAO dao = new CartDAO();

    return dao.getCart(userId);
  }

  @RequestMapping(path = "/users/{userid}/carts", method = RequestMethod.POST)
  public ResponseEntity<?> createCart(
      @RequestHeader HttpHeaders headers,
      @PathVariable(value = "userid") String userid,
      @RequestBody Cart cart) {

    String username = jwtTokenUtil.getUsernameFromToken(headers.get(HttpHeaders.AUTHORIZATION).get(0).substring(7));
    UserDAO userDAO = new UserDAO();
    User user = userDAO.getUser(username);

    if (user.getId() == Long.parseLong(userid)) {
      cart.setUserId(userid);

      CartDAO dao = new CartDAO();
      return ResponseEntity.ok(dao.createCart(cart));
    }

    return ResponseEntity.status(401).build();
  }

  @RequestMapping(path = "/users/{userid}/carts", method = RequestMethod.DELETE)
  public boolean delete(
      @PathVariable(value = "userid") String userid) {

    CartDAO dao = new CartDAO();

    return dao.deleteCart(userid);
  }
}
