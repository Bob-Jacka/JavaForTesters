package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    CreditAccount TestAccount;

    @BeforeEach
    public void testEquipment() {
        int initialBalance = 50;
        int creditLimit = 50;
        int rate = 50;
        TestAccount = new CreditAccount(
                initialBalance,
                creditLimit,
                rate);
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldNotCreateWhenInitialBalanceLessThanZero() {
        int initialBalance = -1;
        int creditLimit = 50;
        int rate = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    initialBalance,
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldNotCreateWhenCreditLimitLessThanZero() {
        int initialBalance = 50;
        int creditLimit = -1;
        int rate = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    initialBalance,
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldNotCreateWhenRateLessThanZero() {
        int initialBalance = 50;
        int creditLimit = 50;
        int rate = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    initialBalance,
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldNotThrowsExceptionWhenRateEqualsZero() {
        int initialBalance = 50;
        int creditLimit = 50;
        int rate = 0;
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
                    initialBalance,
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldReturnFalseWhenPay() {
        boolean act = TestAccount.pay(-1);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldReturnFalseWhenPayIfBalanceLessThanCreditLimit() {
        int initialBalance = -49;
        int creditLimit = 50;
        int rate = 10;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        boolean act = account.pay(15);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldReturnTrueWhenPay() {
        boolean act = TestAccount.pay(1);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldSubstractBalanceWithPay() {
        TestAccount.pay(50);
        int act = TestAccount.getBalance();
        int exp = 0;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldReturnFalseWhenAdd() {
        boolean act = TestAccount.add(-1);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldReturnTrueWhenAdd() {
        boolean act = TestAccount.add(1);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAddToBalance() {
        TestAccount.add(50);
        int exp = 100;
        int act = TestAccount.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAddToBalanceWithNegativeBalance() {
        int initialBalance = -50;
        int creditLimit = 50;
        int rate = 5;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int exp = 0;
        account.add(50);
        int act = account.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldNotCreateWhenCreditLimitLessThanBalance() {
        int initialBalance = 100;
        int creditLimit = 50;
        int rate = 5;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    initialBalance,
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldBeEqualBalanceAndCreditLimit() {
        int initialBalance = 23;
        int creditLimit = 50;
        int rate = 5;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int exp = 50;
        int act = account.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldBeIfBalanceLessThanZero() {
        int initialBalance = -200;
        int creditLimit = 50;
        int rate = 15;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int exp = -30;
        int act = account.yearChange();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAlwaysBeZeroIfBalanceMoreThanZero() {
        int initialBalance = 200;
        int creditLimit = 50;
        int rate = 15;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int exp = 0;
        int act = account.yearChange();

        Assertions.assertEquals(exp, act);
    }
}