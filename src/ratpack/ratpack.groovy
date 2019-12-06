import com.gql.example.handler.UserHandler
import ratpack.health.HealthCheckHandler

import static ratpack.groovy.Groovy.ratpack

ratpack {
  bindings {
    bind(HealthCheckHandler)
    bind(UserHandler)
  }
  handlers {

    get("health", HealthCheckHandler)
    get {
      ctx -> render("Welcome to Ratpack")
    }

    post("users", UserHandler)

    get("version") {
      render "1.0.0"
    }
  }
}
