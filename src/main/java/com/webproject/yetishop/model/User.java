package com.webproject.yetishop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.util.Date;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "cpf")
    private Long cpf;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private boolean sex;

    @Column(name = "birthDate")
    private Date birthDate;

}
