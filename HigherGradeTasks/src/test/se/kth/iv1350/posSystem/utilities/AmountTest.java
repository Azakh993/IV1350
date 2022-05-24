package se.kth.iv1350.posSystem.utilities;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AmountTest {
    private Amount firstAmount;

    @BeforeEach
    void setUp() {
        this.firstAmount = new Amount(10);
    }

    @AfterEach
    void tearDown() {
        this.firstAmount = null;
    }

    @Test
    void testEquals() {
        double secondValue = 10;
        Amount secondAmount = new Amount(secondValue);
        boolean expectedResult = true;
        boolean result = this.firstAmount.equals(secondAmount);
        assertEquals(expectedResult, result, "Instances with same states are not equal!");
    }

    @Test
    void testNotEqual() {
        double secondValue = 9;
        Amount secondAmount = new Amount(secondValue);
        boolean expectedResult = false;
        boolean result = this.firstAmount.equals(secondAmount);
        assertEquals(expectedResult, result, "Instances with different states are equal!");
    }


    @Test
    void testPlus() {
        double firstValue = 10;
        double secondValue = 5;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue + secondValue);
        Amount sum = this.firstAmount.plus(secondAmount);
        assertEquals(expectedSum, sum, "Sum-mismatch; incorrect calculation");
    }

    @Test
    void testPlusZero() {
        double firstValue = 10;
        double secondValue = 0;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue + secondValue);
        Amount sum = this.firstAmount.plus(secondAmount);
        assertEquals(expectedSum, sum, "Sum-mismatch; incorrect calculation");
    }

    @Test
    void testPlusWithNegativeResult() {
        double firstValue = 10;
        double secondValue = -30;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue + secondValue);
        Amount sum = this.firstAmount.plus(secondAmount);
        assertEquals(expectedSum, sum, "Sum-mismatch; incorrect calculation");
    }

    @Test
    void testMultipliedWith() {
        double firstValue = 10;
        double secondValue = 10;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue * secondValue);
        Amount sum = this.firstAmount.multipliedWith(secondAmount);
        assertEquals(expectedSum, sum, "Multiplication-mismatch; incorrect calculation");
    }

    @Test
    void testMultipliedWithNegativeResult() {
        double firstValue = 10;
        double secondValue = -10;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue * secondValue);
        Amount sum = this.firstAmount.multipliedWith(secondAmount);
        assertEquals(expectedSum, sum, "Multiplication-mismatch; incorrect calculation");
    }

    @Test
    void testMultipliedWithZero() {
        double firstValue = 10;
        double secondValue = 0;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue * secondValue);
        Amount sum = this.firstAmount.multipliedWith(secondAmount);
        assertEquals(expectedSum, sum, "Multiplication-mismatch; incorrect calculation");
    }

    @Test
    void minus() {
        double firstValue = 10;
        double secondValue = 5;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue - secondValue);
        Amount sum = this.firstAmount.minus(secondAmount);
        assertEquals(expectedSum, sum, "Subtraction-mismatch; incorrect calculation");
    }

    @Test
    void minusWithANegativeResult() {
        double firstValue = 10;
        double secondValue = -30;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue - secondValue);
        Amount sum = this.firstAmount.minus(secondAmount);
        assertEquals(expectedSum, sum, "Subtraction-mismatch; incorrect calculation");
    }

    @Test
    void minusWithZero() {
        double firstValue = 10;
        double secondValue = 0;
        Amount secondAmount = new Amount(secondValue);
        Amount expectedSum = new Amount(firstValue - secondValue);
        Amount sum = this.firstAmount.minus(secondAmount);
        assertEquals(expectedSum, sum, "Subtraction-mismatch; incorrect calculation");
    }
}