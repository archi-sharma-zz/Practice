package com.datapract

/* Created By archi On 13/10/17 */
object QuickSort {

  def main(args: Array[String]): Unit = {
    val arr = Array(23, 1, 4, 2, 6, 3, 27, 66, 44)
    quickSort(arr, 0, arr.length - 1)
  }

  def quickSort(input: Array[Int], low: Int, high: Int): Unit = {
    if (low < high) {
      val pi = partition(input, low, high)
      quickSort(input, low, pi - 1)
      quickSort(input, pi + 1, high)
    }
  }

  def partition(input: Array[Int], low: Int, high: Int): Int = {
    val pivot = input(high)
    var i = low - 1
    for (j <- low until high - 1) {
      if (input(j) <= pivot) {
        i += 1
        val t = input(i)
        input(i) = input(j)
        input(j) = t
      }
    }
    val t = input(i + 1)
    input(i + 1) = input(high)
    input(high) = t
    i + 1
  }
}
