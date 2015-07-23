package com.rea.typesafety


object OptionalExercises1 {
  val config = Map[String, String]("host" -> "rea.com", "port" -> "8080")

  def getFromConfig(key: String): Option[String] = config.get(key)

  def lengthOfHost(): Option[Int] = getFromConfig("host").map(_.length)

  def portPlus1000(): Option[Int] = getFromConfig("port").map(_.toInt + 1000)
}

object OptionalExercises2 {

  val hosts = Map("host1" -> "rea.com", "host2" -> "test.rea.com", "host3" -> "netflix.com")
  val envs = Map("rea.com" -> "prod", "test.rea.com" -> "test", "amazon.com" -> "stage")

  def getEnvForHost(host: String): String = {
    val maybeHost: Option[String] = hosts.get(host)
    val maybeEnv: Option[String] = maybeHost.flatMap(envs.get(_))
    maybeEnv.getOrElse("couldn't resolve")
  }

  // See how many ways you can implement this.
  // Will either return "Connected to rea.com" or "not connected"
  def connectToReaHostsOnly(host: String): String = {
    val maybeREAHost: Option[String] = hosts.get(host).filter(_.endsWith("rea.com"))
    val connection: Option[String] = maybeREAHost.flatMap(y => Option(createConnection(y)))
    connection.getOrElse("not connected")
  }

  def createConnection(domain: String): String = s"connected to $domain"

}

object OptionalExercises3 {

  sealed trait Maybe[+A]

  case class Just[A](get: A) extends Maybe[A]

  case object Nothing extends Maybe[Nothing]

  def flatMap[A, B](m: Maybe[A], f: A => Maybe[B]): Maybe[B] = ???

  def map[A, B](m: Maybe[A], f: A => B): Maybe[B] = ???

  def fold[A, B](m: Maybe[A], default: => B, f: A => B): B = ???

  def orElse[A](m: Maybe[A], otherwise: => Maybe[A]): Maybe[A] = ???

  def orSome[A](m: Maybe[A], default: => A): A = ???

  def map2[A, B, C](f: (A, B) => C)(m1: Maybe[A], m2: Maybe[B]): Maybe[C] = ???

  def sequence[A](l: List[Maybe[A]]): Maybe[List[A]] = ???

  def ap[A, B](m1: Maybe[A], m2: Maybe[A => B]): Maybe[B] = ???

}

