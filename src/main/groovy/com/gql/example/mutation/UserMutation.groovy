package com.gql.example.mutation


import com.gql.example.entity.User
import graphql.annotations.GraphQLField
import graphql.annotations.GraphQLName
import graphql.schema.DataFetchingEnvironment

import javax.validation.constraints.NotNull

import static com.gql.example.handler.UserHandler.getUsers

@GraphQLName(com.gql.example.utils.SchemaUtils.MUTATION)
class UserMutation {
    @GraphQLField
    static User createUser(final DataFetchingEnvironment env,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.NAME) final String name,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.EMAIL) final String email,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.AGE) final String age) {
        List<User> users = getUsers(env)
        final User user = new User(name, email, Integer.valueOf(age))
        users.add(user)
        return user
    }

    @GraphQLField
    static User updateUser(final DataFetchingEnvironment env,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.ID) final String id,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.NAME) final String name,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.EMAIL) final String email,
                           @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.AGE) final String age) {
        final Optional<User> user = getUsers(env).stream()
                .filter { c ->
                    c.getId() == Long.parseLong(id)
                }
                .findFirst()
        if (!user.isPresent()) {
            return null
        }
        user.get()
                .setName(name)
        user.get()
                .setEmail(email)
        user.get()
                .setAge(Integer.valueOf(age))
        return user.get()
    }

    @GraphQLField
    static User deleteUser(final DataFetchingEnvironment env,
                                  @NotNull @GraphQLName(com.gql.example.utils.SchemaUtils.ID) final String id) {
        final List<User> users = getUsers(env)
        final Optional<User> user = users.stream()
                .filter { c ->
                    c.getId() == Long.parseLong(id)
                }
                .findFirst()
        if (!user.isPresent()) {
            return null
        }
        users.removeIf { c -> 
            c.getId() == Long.parseLong(id)
        }
        return user.get()
    }
}
