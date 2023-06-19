package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {
    CreditAccount TestAccount;

    @BeforeEach
    public void testEquipment() {
        int creditLimit = 50;
        int rate = 50;
        TestAccount = new CreditAccount(
                creditLimit,
                rate);
    }

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                5_000,
                15
        );

        account.setBalance(0);
        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldNotCreateWhenCreditLimitLessThanZero() {
        int creditLimit = -1;
        int rate = 50;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldNotCreateWhenRateLessThanZero() {
        int creditLimit = 50;
        int rate = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(
                    creditLimit,
                    rate);
        });
    }

    @Test
    public void shouldNotThrowsExceptionWhenRateEqualsZero() {
        int creditLimit = 50;
        int rate = 0;
        Assertions.assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(
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
        int creditLimit = 50;
        int rate = 10;
        CreditAccount account = new CreditAccount(creditLimit, rate);
        account.setBalance(-49);

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
    public void shouldSubtractBalanceWithPay() {
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
        TestAccount.setBalance(49);
        boolean act = TestAccount.add(1);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAddToBalance() {
        TestAccount.setBalance(0);
        TestAccount.add(50);
        int exp = 50;
        int act = TestAccount.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAddToBalanceWithNegativeBalance() {
        int creditLimit = 50;
        int rate = 5;
        CreditAccount account = new CreditAccount(creditLimit, rate);
        account.setBalance(-50);

        int exp = 0;
        account.add(50);
        int act = account.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldBeEqualBalanceAndCreditLimit() {
        int creditLimit = 50;
        int rate = 5;
        CreditAccount account = new CreditAccount(creditLimit, rate);

        int exp = 50;
        int act = account.getBalance();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldBeIfBalanceLessThanZero() {
        int creditLimit = 200;
        int rate = 15;
        CreditAccount account = new CreditAccount(creditLimit, rate);
        account.setBalance(-200);

        int exp = -30;
        int act = account.yearChange();

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldAlwaysBeZeroIfBalanceMoreThanZero() {
        int creditLimit = 50;
        int rate = 15;
        CreditAccount account = new CreditAccount(creditLimit, rate);
        account.setBalance(200);

        int exp = 0;
        int act = account.yearChange();

        Assertions.assertEquals(exp, act);
    }
    @Test
    public void shouldReturnTrueWhenSetBalance() {
        boolean act = TestAccount.setBalance(50);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }
    @Test
    public void shouldReturnTrueWhenSetBalanceWithZero() {
        boolean act = TestAccount.setBalance(0);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }
    @Test
    public void shouldReturnTrueWhenSetBalanceWithNegative() {
        boolean act = TestAccount.setBalance(-40);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldReturnFalseWhenSetBalanceWithLargeAboveCreditLim() {
        boolean act = TestAccount.setBalance(600_000_000);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldNotAddToCreditAccWithAboveThanCreditLim() {
        int amount = 600_000;
        int balanceBefore_creditAcc = TestAccount.getBalance();

        TestAccount.add(amount);

        int balanceAfter_creditAcc = TestAccount.getBalance();

        Assertions.assertEquals(balanceBefore_creditAcc, balanceAfter_creditAcc);
        Assertions.assertFalse(TestAccount.add(amount));
    }
}