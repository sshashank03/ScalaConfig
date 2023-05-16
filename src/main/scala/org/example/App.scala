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
    val specificitem = sparkConfig.getString("app-name")
    //val appName = sparkConfig.getString(BatchConstants.GET_APP_NAME)
    println(sparkConfig)
    println(mysqlConfig)
    println(specificitem)


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

    //###########################Reading Multiple SQL Statements
    val url1 = getClass.getResource("/multipletest.sql")
    val properties1: Properties = new Properties()

    if (url1 != null) {
      val source1 = Source.fromURL(url1)
      properties1.load(source1.bufferedReader())
    }
    else {
      //logger.error("properties file cannot be loaded at path " + path)
      throw new FileNotFoundException("Properties file cannot be loaded")
    }

    var sq1 = properties1.getProperty("sql1")
    var sq2 = properties1.getProperty("sql2")

    println(sq1)
    println(sq2)

    sq1 = sq1.replace("$a_value", "'hello1'")
      .replace("$b_value", "'hello2'")
    sq2 = sq2.replace("$c_value", "'hello3'")
      .replace("$d_value", "'hello4'")

    println(sq1)
    println(sq2)


  }

}
