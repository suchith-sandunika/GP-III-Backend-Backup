package com.Cranco.Cranco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
//import org.neo4j.ogm.annotation.NodeEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@NodeEntity
@EqualsAndHashCode(exclude = "userId")
//@Node("User") // Node label for the Neo4j node
public class User {
    @Id
    @GeneratedValue // Generates a unique identifier for the node
    private int userId;

    private String user_name;
    private String email;
    private String phone_number;
    private String password;
}
