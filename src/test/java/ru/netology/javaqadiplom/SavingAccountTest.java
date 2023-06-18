package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    //должен создать аккаунт
    @Test
    public void shouldCreateSavingAccount() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(minBalance, account.getMinBalance());
        Assertions.assertEquals(maxBalance, account.getMaxBalance());
        Assertions.assertEquals(rate, account.getRate());
    }

    //должен создать аккаунт с одинаковыми минимальным и максимальным балансами
    @Test
    public void shouldCreateSavingAccountMinAndMaxEqual() {
        int initialBalance = 100;
        int minBalance = 100;
        int maxBalance = 100;
        int rate = 10;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(minBalance, account.getMinBalance());
        Assertions.assertEquals(maxBalance, account.getMaxBalance());
    }

    //должен создавать аккаунт с минимальным балансом равным 0
    @Test
    public void shouldCreateSavingAccountMinBalanceEqualsZero() {
        int initialBalance = 100;
        int minBalance = 0;
        int maxBalance = 150;
        int rate = 10;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(minBalance, account.getMinBalance());
    }

    //должен создать аккаунт с одинаковыми минимальным и изначальным балансами
    @Test
    public void shouldCreateSavingAccountMinAndInitialEqual() {
        int initialBalance = 50;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(minBalance, account.getMinBalance());
    }

    //должен создать аккаунт с одинаковыми максимальным и изначальным балансами
    @Test
    public void shouldCreateSavingAccountMaxAndInitialEqual() {
        int initialBalance = 150;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(initialBalance, account.getBalance());
        Assertions.assertEquals(maxBalance, account.getMaxBalance());
    }

    //должен создать аккаунт со ставкой равной 0
    @Test
    public void shouldCreateSavingAccountRateZero() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 0;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(rate, account.getRate());
    }

    //не должен создавать аккаунт с минимальным балансом больше максимального
    @Test
    public void shouldNotCreateAccountWithMinBalanceMoreThanMax() {
        int initialBalance = 100;
        int minBalance = 100;
        int maxBalance = 50;
        int rate = 10;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    //не должен создавать аккаунт с негативной ставкой процента
    @Test
    public void shouldNotCreateAccountWithNegativeRate() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = -10;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    //не должен создавать аккаунт со ставкой процента больше 100
    @Test
    public void shouldNotCreateAccountWithRateMoreThan100() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 110;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    //не должен создавать аккаунт с негативным минимальным балансом
    @Test
    public void shouldNotCreateAccountWithNegativeMinBalance() {
        int initialBalance = 100;
        int minBalance = -50;
        int maxBalance = 150;
        int rate = 10;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    //не должен создавать аккаунт с текущим балансом меньше минимального
    @Test
    public void shouldNotCreateAccountWithBalanceLessThanMinBalance() {
        int initialBalance = 20;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);
        });
    }

    //оплата с карты, результат больше минимального баланса
    @Test
    public void shouldPayMoreThanMinBalance() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = 20;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(true, account.pay(amount));
        Assertions.assertEquals(initialBalance - amount, account.getBalance());
    }

    //не должен оплачивать с карты, если результат меньше минимального баланса
    @Test
    public void shouldNotPayIfLessThanMinBalance() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = 60;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(false, account.pay(amount));
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    //сумма покупки не должна быть отрицательной
    @Test
    public void shouldNotPayAmountIsNegative() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = -50;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(false, account.pay(amount));
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    //должен оплачивать, если результат равен минимальному балансу
    @Test
    public void shouldPayEqualsMin() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = 50;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(true, account.pay(amount));
        Assertions.assertEquals(initialBalance - amount, account.getBalance());
    }

    //должен пополнить, результат меньше максимального баланса
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(true, account.add(3_000));
        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    //должен пополнять, если результат равен максимальному балансу
    @Test
    public void shouldAddEqualsMax() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = 50;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(true, account.add(amount));
        Assertions.assertEquals(initialBalance + amount, account.getBalance());
    }

    //не должен пополнять, если результат больше чем максимальный баланс
    @Test
    public void shouldNotAddMoreThanMax() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = 100;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(false, account.add(amount));
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    //не должен пополнять, если сумма пополнения отрицательная
    @Test
    public void shouldNotAddIfAmountNegative() {
        int initialBalance = 100;
        int minBalance = 50;
        int maxBalance = 150;
        int rate = 10;
        int amount = -50;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(false, account.add(amount));
        Assertions.assertEquals(initialBalance, account.getBalance());
    }

    //должен рассчитать проценты за год
    @Test
    public void shouldCalculateYearChange() {
        int initialBalance = 12_330;
        int minBalance = 5_000;
        int maxBalance = 15_000;
        int rate = 7;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(initialBalance / 100 * rate, account.yearChange());
    }

    //должен рассчитать проценты за год, если баланс равен нулю
    @Test
    public void shouldCalculateYearChangeBalanceEqualsZero() {
        int initialBalance = 0;
        int minBalance = 0;
        int maxBalance = 15_000;
        int rate = 7;
        SavingAccount account = new SavingAccount(initialBalance, minBalance, maxBalance, rate);

        Assertions.assertEquals(0, account.yearChange());
    }
}
