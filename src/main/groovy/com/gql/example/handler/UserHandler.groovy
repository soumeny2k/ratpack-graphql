package com.gql.example.handler


import com.gql.example.entity.User
import com.gql.example.schema.UserSchema
import com.gql.example.utils.SchemaUtils
import graphql.ExecutionResult
import graphql.GraphQL
import graphql.schema.DataFetchingEnvironment
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.groovy.handling.GroovyContext
import ratpack.groovy.handling.GroovyHandler

import static ratpack.jackson.Jackson.json

@CompileStatic
@Slf4j
class UserHandler extends GroovyHandler {
    private GraphQL graphql
    private static List<User> users = new ArrayList<>()

    UserHandler() throws Exception {
        graphql = new GraphQL.Builder(new UserSchema().getSchema()).build()
    }

    @Override
    protected void handle(GroovyContext context) {
        context
                .parse(Map.class)
                .then { payload ->
                    Map<String, Object> parameters = (Map<String, Object>) payload.get("parameters")
                    ExecutionResult executionResult = graphql.execute(payload.get(SchemaUtils.QUERY)
                            .toString(), null, this, parameters)
                    Map<String, Object> result = new LinkedHashMap<>()
                    if (executionResult.getErrors()
                            .isEmpty()) {
                        result.put(SchemaUtils.DATA, executionResult.getData())
                    } else {
                        result.put(SchemaUtils.ERRORS, executionResult.getErrors())
                        log.warn("Errors: " + executionResult.getErrors())
                    }
                    context.render(json(result))
                }
    }

    static List<User> getUsers() {
        return users
    }

    static List<User> getUsers(DataFetchingEnvironment env) {
        return ((UserHandler) env.getSource()).getUsers()
    }

}
