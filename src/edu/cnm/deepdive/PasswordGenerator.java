package edu.cnm.deepdive;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

  private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String DIGITS = "1234567890";
  private static final String PUNCTUATION = "`~!@#$%&*()-_+={}[]\\:;,./?";
  private static final String AMBIGUOUS = "[O0l1]";

  private final Random rng;
  private final char[] pool;
  private final boolean upperCaseRequired;
  private final boolean lowerCaseRequired;
  private final boolean digitsRequired;
  private final boolean punctuationRequired;
  private final boolean ambiguousAllowed;

  private PasswordGenerator(Random rng, char[] pool, boolean upperCaseRequired,
      boolean lowerCaseRequired, boolean digitsRequired, boolean punctuationRequired,
      boolean ambiguousAllowed) {
    this.rng = rng;
    this.pool = pool;
    this.upperCaseRequired = upperCaseRequired;
    this.lowerCaseRequired = lowerCaseRequired;
    this.digitsRequired = digitsRequired;
    this.punctuationRequired = punctuationRequired;
    this.ambiguousAllowed = ambiguousAllowed;
  }

  public String generate(int length){
    return null; // TODO Implement according to spec.
  }

  public static class Builder {

    private Random rng;
    private boolean upperCaseAllowed = true;
    private boolean lowerCaseAllowed = true;
    private boolean digitsAllowed = true;
    private boolean punctuationAllowed = true;
    private boolean upperCaseRequired;
    private boolean lowerCaseRequired;
    private boolean digitsRequired;
    private boolean punctuationRequired;
    private boolean ambiguousAllowed;

    public Builder allowUpperCase() {
      return allowUpperCase(true);
    }

    public Builder allowUpperCase(boolean allowed) {
      upperCaseAllowed = allowed;
      return this;
    }

    public Builder allowLowerCase() {
      return allowLowerCase(true);
    }

    public Builder allowLowerCase(boolean allowed) {
      lowerCaseAllowed = allowed;
      return this;
    }

    public Builder allowDigits() {
      return allowDigits(true);
    }

    public Builder allowDigits(boolean allowed) {
      digitsAllowed = allowed;
      return this;
    }

    public Builder allowPunctuation() {
      return allowPunctuation(true);
    }

    public Builder allowPunctuation(boolean allowed) {
      punctuationAllowed = allowed;
      return this;
    }

    public Builder requireUpperCase() {
      return requireUpperCase(true);
    }

    public Builder requireUpperCase(boolean required) {
      upperCaseRequired = required;
      return this;
    }

    public Builder requireLowerCase() {
      return requireLowerCase(true);
    }

    public Builder requireLowerCase(boolean required) {
      lowerCaseRequired = required;
      return this;
    }

    public Builder requireDigits() {
      return requireDigits(true);
    }

    public Builder requireDigits(boolean required) {
      digitsRequired = required;
      return this;
    }

    public Builder requirePunctuation() {
      return requirePunctuation(true);
    }

    public Builder requirePunctuation(boolean required) {
      punctuationRequired = required;
      return this;
    }

    public Builder allowAmbiguous() {
      return allowAmbiguous(true);
    }

    public Builder allowAmbiguous(boolean allowed) {
      ambiguousAllowed = allowed;
      return this;
    }

    public Builder useRng(Random rng) {
      this.rng = rng;
      return this;
    }

    public PasswordGenerator build() throws IllegalStateException {
      if (upperCaseRequired && !upperCaseAllowed) {
        throw new IllegalStateException("Upper-case cannot be both required and allowed.");
      } else if (lowerCaseRequired && !lowerCaseAllowed) {
        throw new IllegalStateException("Lower-case cannot be both required and allowed.");
      } else if (digitsRequired && !digitsAllowed) {
        throw new IllegalStateException("Digits cannot be both required and allowed.");
      } else if (punctuationRequired && !punctuationAllowed) {
        throw new IllegalStateException("Punctuation cannot be both required and allowed.");
      }
      StringBuilder pool = new StringBuilder();
      if (upperCaseAllowed) {
        pool.append(UPPER_CASE);
      }
      if (lowerCaseAllowed) {
        pool.append(LOWER_CASE);
      }
      if (digitsAllowed) {
        pool.append(DIGITS);
      }
      if (punctuationAllowed) {
        pool.append(PUNCTUATION);
      }
      String generatorPool =
          ambiguousAllowed ? pool.toString() : pool.toString().replaceAll(AMBIGUOUS, "");
      return new PasswordGenerator(
          (rng != null) ? rng : new SecureRandom(),
          generatorPool.toCharArray(),
          upperCaseRequired,
          lowerCaseRequired,
          digitsRequired,
          punctuationRequired,
          ambiguousAllowed
      );
    }

  }

}
