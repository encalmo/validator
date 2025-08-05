package org.encalmo.validator

class StringMatchersSpec extends munit.ScalaCheckSuite {

  import Validator._

  test("StringMatchers.lengthMinMaxInclusive") {
    assert("a".lengthMinMaxInclusive(0, 1) == true)
    assert("a".lengthMinMaxInclusive(1, 2) == true)
    assert("a".lengthMinMaxInclusive(2, 3) == false)
    assert("a".lengthMinMaxInclusive(0, 3) == true)
    assert("abc".lengthMinMaxInclusive(0, 1) == false)
    assert("abc".lengthMinMaxInclusive(1, 2) == false)
    assert("abc".lengthMinMaxInclusive(2, 3) == true)
    assert("abc".lengthMinMaxInclusive(0, 3) == true)
  }

  test("StringMatchers.lengthMin") {
    assert("a".lengthMin(1) == true)
    assert("a".lengthMin(2) == false)
    assert("aa".lengthMin(2) == true)
    assert("aa".lengthMin(3) == false)
    assert("aaa".lengthMin(3) == true)
  }

  test("StringMatchers.lengthMax") {
    assert("a".lengthMax(1) == true)
    assert("a".lengthMax(2) == true)
    assert("aa".lengthMax(2) == true)
    assert("aa".lengthMax(3) == true)
    assert("aaa".lengthMax(3) == true)
    assert("aaa".lengthMax(2) == false)
    assert("aa".lengthMax(1) == false)
    assert("a".lengthMax(0) == false)
  }

  test("StringMatchers.isTrue") {
    def onlyDigits(s: String): Boolean = s.forall(_.isDigit)
    assert("a".isTrue(onlyDigits) == false)
    assert("a1a".isTrue(onlyDigits) == false)
    assert("1a2".isTrue(onlyDigits) == false)
    assert("a12".isTrue(onlyDigits) == false)
    assert("1".isTrue(onlyDigits) == true)
    assert("21".isTrue(onlyDigits) == true)
    assert("321".isTrue(onlyDigits) == true)
  }

  test("StringMatchers.isFalse") {
    def onlyDigits(s: String): Boolean = s.forall(_.isDigit)
    assert("a".isFalse(onlyDigits) == true)
    assert("a1a".isFalse(onlyDigits) == true)
    assert("1a2".isFalse(onlyDigits) == true)
    assert("a12".isFalse(onlyDigits) == true)
    assert("1".isFalse(onlyDigits) == false)
    assert("21".isFalse(onlyDigits) == false)
    assert("321".isFalse(onlyDigits) == false)
  }

}
