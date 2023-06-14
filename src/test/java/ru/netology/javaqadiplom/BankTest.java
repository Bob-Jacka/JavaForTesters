package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    Bank bank;
    Account from;
    Account to;

    @BeforeEach
    public void TestEquipment() {
        bank = new Bank();
        from = new Account();
        to = new Account();

        from.balance = 10;
        to.balance = 10;
    }

    @Test
    public void shouldTransferWithPositiveAmount() {
        boolean act = bank.transfer(from, to, 15);
        boolean exp = true;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldNotTransferWithZeroAmount() {
        boolean act = bank.transfer(from, to, 0);
        boolean exp = false;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldNotTransferWithNegativeAmount() {
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();
        int amount = -100_000_000;

        bank.transfer(from, to, amount);

        int balanceAfterFrom = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfterFrom);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
    }

    @Test
    public void shouldTransferToNegativeBalance() {
        to.balance = -100;
        int amount = 100;
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, to, amount);

        int balanceAfter_To = balanceBefore_To + amount;

        Assertions.assertEquals(balanceAfter_To, to.getBalance());
    }

    @Test
    public void shouldNotTransferFromNegativeBalance() {
        from.balance = -100;
        int amount = 100;
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, to, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
    }

    @Test
    public void shouldNotTransferFromZeroBalance() {
        from.balance = 0;
        int amount = 100;
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, to, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
    }

    @Test
    public void shouldChangeBalanceFrom() {
        int amount = 10;
        int BalanceBeforeFrom = from.getBalance();

        bank.transfer(from, to, amount);

        int expBalanceFrom = BalanceBeforeFrom - amount;

        Assertions.assertEquals(expBalanceFrom, from.getBalance());
    }

    @Test
    public void shouldChangeBalanceTo() {
        int amount = 10;
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, to, amount);

        int expBalance_To = balanceBefore_To + amount;

        Assertions.assertEquals(expBalance_To, to.getBalance());
    }

    @Test
    public void shouldNotTransferFromToFrom() {
        int amount = 10;
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, from, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
    }
}
