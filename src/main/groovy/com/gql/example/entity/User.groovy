package com.gql.example.entity


import graphql.annotations.GraphQLField
import graphql.annotations.GraphQLName
import groovy.util.logging.Slf4j

@Slf4j
@GraphQLName(com.gql.example.utils.SchemaUtils.USER)
class User {
    @GraphQLField
    Long id;
    @GraphQLField
    String name;
    @GraphQLField
    String email;
    @GraphQLField
    Integer age;

    User(String name, String email, Integer age) {
        this.id = genId();
        this.name = name;
        this.email = email;
        this.age = age;
    }

    static Long genId() {
        Long id = 1L;
        try {
            List<User> users =  new com.gql.example.handler.UserHandler().getUsers();
            for (User user : users)
                id = (user.getId() > id ? user.getId() : id) + 1;

        } catch (Exception e) {
            log.error(e)
        }
        return id;
    }
}
