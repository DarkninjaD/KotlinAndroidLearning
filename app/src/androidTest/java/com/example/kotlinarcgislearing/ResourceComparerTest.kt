package com.example.kotlinarcgislearing


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.Test
import io.kotest.matchers.booleans.shouldBeTrue

@Test
internal class ResourceComparerTest : DescribeSpec( {
  describe("Compare Android Context id with string passed in") {
      val resourceComparer = ResourceComparer()
    it("should be true") {
      val context = ApplicationProvider.getApplicationContext<Context>()
    val result = resourceComparer.isEqual(context, R.string.app_name, "Age in Mins")
    result.shouldBeTrue()
    }
  }
})
