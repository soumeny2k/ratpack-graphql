package com.gql.example

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import ratpack.server.RatpackServer

import static ratpack.groovy.Groovy.Script.appWithArgs

@CompileStatic
@Slf4j
class Main {

  /*
   * Run this from classpath of "graphql-server-development_main" so data-sources are defined
   */
  static void main(String... args) throws Exception {
    RatpackServer.start(appWithArgs(false, ['ratpack.groovy'] as String[], args))
  }

}
