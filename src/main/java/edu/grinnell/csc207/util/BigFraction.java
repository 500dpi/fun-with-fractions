/**
 * BigFraction.java
 *
 * @author Sara Jaljaa
 * @course CSC-207-01
 *
 */

package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * A class that creates a fraction from two BigIntegers.
 */
public class BigFraction {

  /**
   * The numerator of the BigFraction.
   */
  private BigInteger numerator;

  /**
   * The denominator of the BigFraction.
   */
  private BigInteger denominator;

  /**
   * Default numerator value for a constructed BigFraction.
   */
  private static final BigInteger NUMDEFAULT = BigInteger.ZERO;

  /**
   * Default denominator value for a constructed BigFraction.
   */
  private static final BigInteger DENDEFAULT = BigInteger.ONE;

  /**
   * Constructs a BigFraction from two BigIntegers.
   *
   * @param num
   *    The numerator of the fraction.
   * @param den
   *    The denominator of the fraction.
   */
  public BigFraction(BigInteger num, BigInteger den) {
    if (den.compareTo(BigInteger.ZERO) == 0) {
      this.numerator = NUMDEFAULT;
      this.denominator = DENDEFAULT;
    } else if (den.compareTo(BigInteger.ZERO) < 0) {
      this.numerator = num.negate();
      this.denominator = den.negate();
    } else {
      this.numerator = num;
      this.denominator = den;
    } // if
  } // BigFraction(BigInteger, BigInteger)

  /**
   * Constructs a BigFraction from two integers.
   *
   * @param num
   *    The numerator of the fraction.
   * @param den
   *    The denominator of the fraction.
   */
  public BigFraction(int num, int den) {
    this.numerator = BigInteger.valueOf(num);
    this.denominator = BigInteger.valueOf(den);
  } // BigFraction(int, int)

  /**
   * Constructs a BigFraction from a single integer.
   *
   * @param number
   *    The single integer representing the numerator.
   */
  public BigFraction(int number) {
    this.numerator = BigInteger.valueOf(number);
    this.denominator = BigInteger.ONE;
  } // BigFraction(int)

  /**
   * Constructs a BigFraction from string.
   *
   * @param frac
   *    The string consisiting of backslash to indicate
   *    the numerator and denominator.
   */
  public BigFraction(String frac) {

    // Checks for divisor symbol between the fraction
    String[] strFrac = frac.split("/");

    if (strFrac.length == 1) {
      this.denominator = BigInteger.ONE;
    } else {
      this.denominator = new BigInteger(strFrac[1]);
    } // if
    this.numerator = new BigInteger(strFrac[0]);
  } // BigFraction(String)

  /**
   * Reduces a fraction to its lowest form.
   *
   * @return
   *    A BigFraction that is simplified.
   */
  public BigFraction reduce() {
    BigInteger gcd = this.numerator.gcd(this.denominator);
    BigInteger num = this.numerator.divide(gcd);
    BigInteger den = this.denominator.divide(gcd);
    return new BigFraction(num, den);
  } // reduce()

  /**
   * Adds two fractions together.
   *
   * @param addend
   *    The fraction to add.
   * @return
   *    A simplified BigFraction that represents the sum.
   */
  public BigFraction add(BigFraction addend) {

    // Declaring numerator and denominator
    BigInteger num;
    BigInteger den;

    num = (this.numerator.multiply(addend.denominator)).add(this.denominator.multiply(addend.numerator));
    den = (this.denominator.multiply(addend.denominator));
    return new BigFraction(num, den).reduce();
  } // add(BigFraction)

  /**
   * Subtracts two fractions.
   *
   * @param subtrahend
   *    The fraction to subtract.
   * @return
   *    A simplified BigFraction that represents the difference.
   */
  public BigFraction subtract(BigFraction subtrahend) {

    // Declaring numerator and denominator
    BigInteger num;
    BigInteger den;

    // Need to fix length (add converter?)
    num = (this.numerator.multiply(subtrahend.denominator)).subtract(this.denominator.multiply(subtrahend.numerator));
    den = (this.denominator.multiply(subtrahend.denominator));
    return new BigFraction(num, den).reduce();
  } // subtract(BigFraction)

  /**
   * Multiplies two fractions.
   *
   * @param multiplier
   *    The fraction to multiply.
   * @return
   *    A simplified BigFraction that represents the product.
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger num = this.numerator.multiply(multiplier.numerator);
    BigInteger den = this.denominator.multiply(multiplier.denominator);
    return new BigFraction(num, den).reduce();
  } // multiply(BigFraction)

  /**
   * Divides two fractions.
   *
   * @param divisor
   *    The fraction to divide by.
   * @return
   *    A simplified BigFraction that represents the quotient.
   */
  public BigFraction divide(BigFraction divisor) {
    BigInteger num = this.numerator.multiply(divisor.denominator);
    BigInteger den = this.denominator.multiply(divisor.numerator);
    return new BigFraction(num, den).reduce();
  } // divide(BigFraction)

  /**
   * Clears a fraction by setting it to the default fraction, 0/1.
   *
   * @return
   *    A new BigFraction of the form 0/1.
   */
  public BigFraction clear() {
    return new BigFraction(BigInteger.ZERO, BigInteger.ONE);
  } // clear()

  /**
   * Converts a BigFraction to a string.
   *
   * @return
   *    The BigFraction as a string.
   */
  public String toString() {
    if (this.numerator == BigInteger.ZERO) {
      return "0";
    } else if (this.denominator == BigInteger.ZERO) {
      return "Undefined";
    } else if (this.denominator == BigInteger.ONE) {
      return this.numerator.toString();
    } // if
    this.reduce();
    return this.numerator.toString() + "/" + this.denominator.toString();
  } // toString()

  /**
   * The value of the numerator.
   *
   * @return
   *    A BigInteger numerator value.
   */
  public BigInteger numerator() {
    return this.numerator;
  } // numerator()

  /**
   * The value of the denominator.
   *
   * @return
   *    A BigInteger denominator value.
   */
  public BigInteger denominator() {
    return this.denominator;
  } // denominator()

  /**
   * Converts a fraction to an integer.
   *
   * @return
   *    A BigInteger representing the fraction as a whole number.
   */
  public BigInteger fracToWhole() {
    if (this.denominator.remainder(this.numerator) == BigInteger.ZERO) {
      BigInteger quotient = this.denominator.divide(this.numerator);
      return quotient;
    } // if
    return BigInteger.ZERO;
  } // fracToWhole()
} // class BigFraction
