package com.gql.example.schema

import com.gql.example.query.UserQuery
import com.gql.example.mutation.UserMutation
import graphql.annotations.GraphQLAnnotations
import graphql.schema.GraphQLSchema

import static graphql.schema.GraphQLSchema.newSchema

class UserSchema {
    private final GraphQLSchema schema

    UserSchema() throws IllegalAccessException, NoSuchMethodException, InstantiationException {
        schema = newSchema().query(GraphQLAnnotations.object(UserQuery.class))
                .mutation(GraphQLAnnotations.object(UserMutation.class))
                .build()
    }

    GraphQLSchema getSchema() {
        return schema
    }
}
