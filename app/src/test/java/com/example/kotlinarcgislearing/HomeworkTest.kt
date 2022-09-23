package com.example.kotlinarcgislearing

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class HomeworkTest : DescribeSpec({
  describe("Fibonacci sequence functions should return") {
    it("the N-th number in the fibonacci") {
      val result0 = Homework.fib(0)
      val result1 = Homework.fib(1)
      val result2 = Homework.fib(2)
      val result3 = Homework.fib(3)
      val result4 = Homework.fib(4)
      val result5 = Homework.fib(5)
      val test = Homework.fib(-1)

      result0.shouldBe(0)
      result1.shouldBe(0)
      result2.shouldBe(1)
      result3.shouldBe(1)
      result4.shouldBe(2)
      result5.shouldBe(3)
      test.shouldBe(0)
    }
  }
})
