package com.itsa.gourds.masks.entities;


import com.fasterxml.jackson.databind.JsonNode;

import java.io.Serializable;

public class JwtResponse implements Serializable {
  private static final long serialVersionUID = -8091879091924046844L;
  private long id;
  private String username;
  private final String jwttoken;
  private JsonNode cartContent;

  public JwtResponse(long id, String username, String jwttoken, JsonNode cartContent) {
    this.id = id;
    this.username = username;
    this.jwttoken = jwttoken;
    this.cartContent = cartContent;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getJwttoken() {
    return jwttoken;
  }

  public JsonNode getCartContent() {
    return cartContent;
  }

  public void setCartContent(JsonNode cartContent) {
    this.cartContent = cartContent;
  }
}
