package org.example

import com.typesafe.config.ConfigFactory

import java.io.FileNotFoundException
import java.util.Properties
import scala.io.Source

/**
 * @author ${swapnil.shashank}
 */
object App {

  def main(args : Array[String]) {


    //Reading from JSON file -> /src/main/resources/application.conf
    val config = ConfigFactory.load("application.conf").getConfig("com.ram.batch")
    val sparkConfig = config.getConfig("spark")
    val mysqlConfig = config.getConfig("mysql")
    //val appName = sparkConfig.getString(BatchConstants.GET_APP_NAME)
    println(sparkConfig)
    println(mysqlConfig)


    //Reading from text file as key/value pair -> /src/main/resources/application.properties
    val url = getClass.getResource("/application.properties")
    val properties: Properties = new Properties()

    if (url != null) {
      val source = Source.fromURL(url)
      properties.load(source.bufferedReader())
    }
    else {
      //logger.error("properties file cannot be loaded at path " + path)
      throw new FileNotFoundException("Properties file cannot be loaded")
    }

    val table = properties.getProperty("hbase_table_name")
    val port = properties.getProperty("2181")

    println(table)
    println(port)

    //Reading from SQL File -> /src/main/resources/test.sql
    val file = scala.io.Source.fromFile("src/main/resources/test.sql")
    val readable = file.getLines.mkString

    println(readable)


  }

}
