package com.itsa.gourds.masks.jwt.service;

import com.itsa.gourds.masks.daos.UserDAO;
import com.itsa.gourds.masks.entities.User;
import com.itsa.gourds.masks.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserDAO userDAO;

  @Autowired
  private PasswordEncoder bcryptEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDAO.getUser(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        new ArrayList<>());
  }

  public com.itsa.gourds.masks.entities.User save(UserDTO user) {
    com.itsa.gourds.masks.entities.User newUser = new com.itsa.gourds.masks.entities.User();
    newUser.setUsername(user.getUsername());
    newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
    newUser.setName(user.getName());
    return userDAO.createUser(newUser);
  }
}
