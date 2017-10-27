package com.datapract

/* Created By archi On 24/10/17 */
object SetOperationsDemo {
  def main(args: Array[String]): Unit = {
    run()
  }
  def run(): Set[Int] ={
    val set1 = Set(1,2,3,4)
    val set2 = Set(2,4,5,6)
    val un = set1.union(set2)
    val inter = set1.intersect(set2)
    val zipwithind = inter.zipWithIndex.toList
    println(zipwithind)
    un
  }
}
