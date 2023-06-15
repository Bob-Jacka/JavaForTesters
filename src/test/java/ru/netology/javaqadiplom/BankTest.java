package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    Bank bank;
    Account from;
    Account to;
    CreditAccount creditAccount;
    SavingAccount savingAccount;

    @BeforeEach
    public void TestEquipment() {
        bank = new Bank();
        from = new Account();
        to = new Account();

        from.balance = 10;
        to.balance = 10;

        creditAccount = new CreditAccount(35, 50);
        savingAccount = new SavingAccount(50, 10, 70, 15);
    }

    @Test
    public void shouldTransferWithPositiveAmount() {
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();
        int amount = 15;

        bank.transfer(from, to, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From + amount, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To + amount, balanceAfter_To);
    }

    @Test
    public void shouldNotTransferWithZeroAmount() {
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();
        int amount = 0;

        bank.transfer(from, to, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
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

        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_To + amount, balanceAfter_To);
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
        int BalanceBefore_From = from.getBalance();

        bank.transfer(from, to, amount);

        int balanceAfter_From = from.getBalance();

        Assertions.assertEquals(BalanceBefore_From - amount, balanceAfter_From);
    }

    @Test
    public void shouldChangeBalanceTo() {
        int amount = 10;
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, to, amount);

        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_To + amount, balanceAfter_To);
    }

    @Test
    public void shouldNotTransferFromOneToTheSame() {
        int amount = 10;
        int balanceBefore_From = from.getBalance();
        int balanceBefore_To = to.getBalance();

        bank.transfer(from, from, amount);

        int balanceAfter_From = from.getBalance();
        int balanceAfter_To = to.getBalance();

        Assertions.assertEquals(balanceBefore_From, balanceAfter_From);
        Assertions.assertEquals(balanceBefore_To, balanceAfter_To);
    }

    @Test
    public void shouldTransferToSavingAccount() {
        int amount = 10;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferFromSavingAccountToSavingAcc() {
        int amount = 10;
        SavingAccount savingAccount1 = new SavingAccount(50, 20, 70, 15);
        int balanceBefore_SA1 = savingAccount1.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(savingAccount, savingAccount1, amount);

        int balanceAfter_SA1 = savingAccount1.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_SA1 + amount, balanceAfter_SA1);
        Assertions.assertEquals(balanceBefore_SA - amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferToSavingAccountWithNegativeCreditBalance() {
        int amount = 10;
        creditAccount.setBalance(-100);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithLessThanMinBalance() {
        int amount = 70;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithNegative() {
        int amount = -70;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithAboveMaxBalance() {
        int amount = 100_000;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertNotEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertNotEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferToCreditAccount() {
        int amount = 10;
        creditAccount.creditLimit = 50;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_SA - amount, balanceAfter_SA);
        Assertions.assertEquals(balanceBefore_CA + amount, balanceAfter_CA);
    }

    @Test
    public void shouldTransferFromCreditAccountToCreditAcc() {
        int amount = 10;
        CreditAccount creditAccount1 = new CreditAccount(70, 15);
        creditAccount1.creditLimit = 100;
        int balanceBefore_CA1 = creditAccount1.getBalance();
        int balanceBefore_CA = creditAccount.getBalance();

        bank.transfer(creditAccount, creditAccount1, amount);

        int balanceAfter_CA1 = creditAccount1.getBalance();
        int balanceAfter_CA = creditAccount.getBalance();

        Assertions.assertEquals(balanceBefore_CA1 + amount, balanceAfter_CA1);
        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
    }

    @Test
    public void shouldNotTransferToCreditAccount() {
        int amount = 100_000;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
    }

    @Test
    public void shouldNotTransferToCreditAccountWithNegSA() {
        int amount = 100_000;
        savingAccount.balance = -10;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
    }

}