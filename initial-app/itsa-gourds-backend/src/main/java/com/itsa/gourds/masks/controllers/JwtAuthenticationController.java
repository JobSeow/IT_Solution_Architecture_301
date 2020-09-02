package com.itsa.gourds.masks.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.itsa.gourds.masks.daos.CartDAO;
import com.itsa.gourds.masks.daos.UserDAO;
import com.itsa.gourds.masks.entities.*;
import com.itsa.gourds.masks.jwt.JwtTokenUtil;
import com.itsa.gourds.masks.jwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
public class JwtAuthenticationController {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private JwtUserDetailsService userDetailsService;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test() throws Exception {
      return "Hello World";
  }

  @RequestMapping(value = "/users/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    UserDAO dao = new UserDAO();
    CartDAO cartDAO = new CartDAO();
    JsonNode cartContent = null;

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    final String token = jwtTokenUtil.generateToken(userDetails);
    long userId = dao.getUser(userDetails.getUsername()).getId();
    CartResponse cart = cartDAO.getCart(String.valueOf(userId));
    if (cart != null)
      cartContent = cartDAO.getCart(String.valueOf(userId)).getContent();
    return ResponseEntity.ok(new JwtResponse(userId, userDetails.getUsername(), token, cartContent));
  }

  @RequestMapping(value = "/users/register", method = RequestMethod.POST)
  public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
    return ResponseEntity.ok(userDetailsService.save(user));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
