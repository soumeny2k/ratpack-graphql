package com.gql.example.query

import com.gql.example.entity.User
import com.gql.example.utils.SchemaUtils
import graphql.annotations.GraphQLField
import graphql.annotations.GraphQLName
import graphql.schema.DataFetchingEnvironment

import javax.validation.constraints.NotNull

import static com.gql.example.handler.UserHandler.getUsers

@GraphQLName(SchemaUtils.QUERY)
class UserQuery {

    @GraphQLField
    static User retrieveUser(final DataFetchingEnvironment env,
                                    @NotNull @GraphQLName(SchemaUtils.ID) final String id) {
        final Optional<User> any = getUsers(env).stream()
                .filter { c -> 
                    c.getId() == Long.parseLong(id)
                }
                .findFirst()
        return any.orElse(null)
    }

    @GraphQLField
    static List<User> searchName(final DataFetchingEnvironment env,
                                        @NotNull @GraphQLName(SchemaUtils.NAME) final String name) {
        return getUsers(env).stream()
                .filter { c ->
                    c.getName().contains(name)
                }
                .collect()
    }

    @GraphQLField
    static List<User> listUsers(final DataFetchingEnvironment env) {
        return getUsers(env)
    }
}
