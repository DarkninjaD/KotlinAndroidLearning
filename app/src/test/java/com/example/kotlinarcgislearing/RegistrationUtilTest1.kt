package com.example.kotlinarcgislearing

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

/// Here we are using Kotest to handle testing it mimics that of the React testing liberty in js.
// assertions is handle with the same framework. Kotest only works with JUnit 5 Platform.
// Big thing of note here is we are NOT messing with the UI. just Kotlin code (an Object really).

internal class RegistrationUtilTest : DescribeSpec({
  describe("Registration Util should return false if") {
    it("was given no username") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "",
        password = "123",
        confirmedPassword = "123"
      )
      result.shouldBeFalse()
    }
    it("was given no password") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "Bob",
        password = "",
        confirmedPassword = "123"
      )
      result.shouldBeFalse()
    }
    it("was given a password that does not match confirmed Password") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "Bob",
        password = "123",
        confirmedPassword = "1234"
      )
      result.shouldBeFalse()
    }
    it("was given a username that already exist") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "Carl",
        password = "123",
        confirmedPassword = "123"
      )
      result.shouldBeFalse()
    }
    it("password contains less than 2 digits should") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "Bob",
        password = "1",
        confirmedPassword = "1"
      )
      result.shouldBeFalse()
    }
  }
  describe("Registration Util should return true if") {
    it("username is unique and password matches confirm") {
      val result = RegistrationUtil.valRegistrationInput(
        username = "Bob",
        password = "123",
        confirmedPassword = "123"
      )
      result.shouldBeTrue()
    }
  }
})

////// Bellow is how JUnit 4 handles testing using google truth as it's assertions liberty
///// Use as an understanding on how the above code works.
/*
package com.example.kotlinarcgislearing


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{
  @Test
  fun `empty username returns false` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "",
      password = "123",
      confirmedPassword = "123"
    )
    assertThat(result).isFalse()
  }
  @Test
  fun `valid username and correctly repeated password returns true` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "Bob",
      password = "123",
      confirmedPassword = "123"
    )
    assertThat(result).isTrue()
  }
  @Test
  fun `username already exist should return false` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "Carl",
      password = "123",
      confirmedPassword = "123"
    )
    assertThat(result).isFalse()
  }
  @Test
  fun `empty password should return false ` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "Bob",
      password = "",
      confirmedPassword = ""
    )
    assertThat(result).isFalse()
  }
  @Test
  fun `password was repeated incorrectly should return false` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "Bob",
      password = "123",
      confirmedPassword = "1253"
    )
    assertThat(result).isFalse()
  }
  @Test
  fun `password contains less than 2 digits should return false` () {
    val result = RegistrationUtil.valRegistrationInput(
      username = "Bob",
      password = "1",
      confirmedPassword = "1"
    )
    assertThat(result).isFalse()
  }

}
*/
