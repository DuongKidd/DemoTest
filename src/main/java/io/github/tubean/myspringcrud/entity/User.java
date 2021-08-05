package io.github.tubean.myspringcrud.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  public User() {}

  public User(String name, String email, String phone) {
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
  public User(Long id, String name, String email, String phone) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phone = phone;
  }
}
