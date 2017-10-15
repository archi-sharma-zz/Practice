package com.datapract

import java.sql
import java.text.SimpleDateFormat
import java.util.Date

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{DataFrame, Row, SQLContext}
import org.apache.spark.sql.types.DateType

/* Created By archi On 15/10/17 */
object Test1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("rand")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)
    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load("/home/archi/Downloads/SalesJan2009.csv")
    val finaDf = extractMinDate(
      df,
      List("Transaction_date", "Account_Created", "Last_Login"))
    finaDf.show()
  }

  def extractMinDate(inputDf: DataFrame,
                     listOfDateCols: List[String]): DataFrame = {

    val a = inputDf.map { row =>
      val d = listOfDateCols.map(dateColName => row.getAs[String](dateColName))
      val convDate = d.map(c => getDateObj(c, "MM/d/yy HH:mm"))
      val leastDate = convDate.sorted.headOption.orNull
      val sqlDate = new sql.Date(leastDate.getTime)
      val newRow = row.toSeq :+ sqlDate
      Row.fromSeq(newRow)
    }
    val outDf = inputDf.sqlContext
      .createDataFrame(a, inputDf.schema.add("Least", DateType))
    outDf
  }

  def getDateObj(dateString: String, datePattern: String): Date = {
    val dateFormat = new SimpleDateFormat(datePattern)
    if (dateString == null || dateString.isEmpty) null
    else dateFormat.parse(dateString)
  }
}
