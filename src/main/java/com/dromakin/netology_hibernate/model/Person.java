/*
 * File:     Person
 * Package:  com.dromakin.netology_hibernate.model
 * Project:  netology_hibernate
 *
 * Created by dromakin as 06.10.2023
 *
 * author - dromakin
 * maintainer - dromakin
 * version - 2023.10.06
 * copyright - ORGANIZATION_NAME Inc. 2023
 */
package com.dromakin.netology_hibernate.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "persons", schema = "public")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private int age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city_of_living")
    private String city;
}
