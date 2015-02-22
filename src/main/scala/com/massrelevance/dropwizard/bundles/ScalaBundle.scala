package com.massrelevance.dropwizard.bundles

import com.massrelevance.dropwizard.scala.validation.ScalaValidatorsBundle
import io.dropwizard.Bundle
import io.dropwizard.setup.{Bootstrap, Environment}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.massrelevance.dropwizard.scala.inject.ScalaCollectionsQueryParamInjectableProvider

class ScalaBundle extends Bundle {
  val validatorsBundle = new ScalaValidatorsBundle

  def initialize(bootstrap: Bootstrap[_]) {
    bootstrap.getObjectMapper.registerModule(new DefaultScalaModule())
    validatorsBundle.initialize(bootstrap)
  }

  def run(environment: Environment) {
    environment.jersey.getResourceConfig.getClasses.add(classOf[ScalaCollectionsQueryParamInjectableProvider])
    validatorsBundle.run(environment)
  }
}
