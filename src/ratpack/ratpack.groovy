import com.target.platform.connector.ratpack.groovy.Platform
import com.target.platform.connector.ratpack.guice.PlatformModule
import com.target.platform.connector.ratpack.XForwardedForNcsaRequestLogger
import ratpack.health.HealthCheckHandler

import static ratpack.groovy.Groovy.ratpack

ratpack {
  serverConfig(Platform.connect {
    sysProps()
    env()
  })
  bindings {
    module(PlatformModule)
    bind(HealthCheckHandler)
  }
  handlers {

    get("health", HealthCheckHandler)
    all(XForwardedForNcsaRequestLogger.INSTANCE)

    get {
      render "Hello World!"
    }
  }
}
