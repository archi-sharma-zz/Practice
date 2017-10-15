package com.datapract

/* Created By archi On 6/10/17 */
object App1 {

  def main(args: Array[String]): Unit = {
    println(logic)
  }

  def doubleNpi(npi: String): List[Int] = {
    val s = npi
      .split("")
      .toList
      .map(_.toInt)
    val ds = s.zipWithIndex
      .filter { case (_, index) => index % 2 == 0 }
      .map(value => value._1 * 2)
    val dss = ds.map(
      x =>
        x.toString
          .split("")
          .map(_.toInt)
          .sum)
    dss
  }

  def logic(inputString: String): Boolean = {
    if (inputString.length != 10) {
      println("input is not 10 digit")
      false
    } else {
      val str = s"""80840${inputString.init}"""
      val separatedDigits = doubleNpi(str.reverse)
      val sumOfSeparatedDigits = separatedDigits.sum
      val alternateDigits = str
        .split("")
        .toList
        .zipWithIndex
        .filter(x => x._2 % 2 == 0)
        .map(_._1.toInt)
      val sumOfAlternateDigits = alternateDigits.sum
      val sumOfBothDigits = sumOfSeparatedDigits + sumOfAlternateDigits
      //Find the no. to be added to the sum to get it to highest 10
      val t = sumOfBothDigits % 10
      val p = 10 - t
      val higherTen = sumOfBothDigits + p
      val sub = higherTen - sumOfBothDigits
      val lastDigitOfNpi = inputString.last.toString.toInt
      if (sub == lastDigitOfNpi)
        true
      else
        false
    }
  }

  def logic: Boolean = {
    logic(readLine)
  }
}
