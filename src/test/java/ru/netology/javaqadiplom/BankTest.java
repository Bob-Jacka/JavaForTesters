package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    Bank bank;
    CreditAccount creditAccount;
    SavingAccount savingAccount;

    @BeforeEach
    public void TestEquipment() {
        bank = new Bank();
        creditAccount = new CreditAccount(100, 50);
        savingAccount = new SavingAccount(50, 10, 70, 15);
    }

    @Test
    public void shouldTransferToNegativeBalance() {
        int amount = 20;
        creditAccount.setBalance(-50);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(savingAccount, creditAccount, amount);
        int balanceAfterCA = creditAccount.getBalance();
        int balanceAfterSA = savingAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
        Assertions.assertEquals(balanceBefore_CA + amount, balanceAfterCA);
        Assertions.assertEquals(balanceBefore_SA - amount, balanceAfterSA);
    }

    @Test
    public void shouldTransferFromCreditToSavingAccount() {
        int amount = 10;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferFromSavingAccountToSavingAcc() {
        int amount = 10;
        SavingAccount savingAccount1 = new SavingAccount(50, 20, 70, 15);
        int balanceBefore_SA1 = savingAccount1.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(savingAccount, savingAccount1, amount);

        int balanceAfter_SA1 = savingAccount1.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
        Assertions.assertEquals(balanceBefore_SA1 + amount, balanceAfter_SA1);
        Assertions.assertEquals(balanceBefore_SA - amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferToSavingAccountWithNegativeCreditBalance() {
        int amount = 10;
        creditAccount.setBalance(-50);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithLessThanMinBalance() {
        int amount = 70;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithNegative() {
        int amount = -70;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
    }

    @Test
    public void shouldNotTransferToSavingAccountWithAboveMaxBalance() {
        int amount = 100;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(creditAccount, savingAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertNotEquals(balanceBefore_CA - amount, balanceAfter_CA);
        Assertions.assertNotEquals(balanceBefore_SA + amount, balanceAfter_SA);
    }

    @Test
    public void shouldTransferToCreditAccount() {
        int amount = 10;
        creditAccount.setBalance(50);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
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

        boolean confirmation = bank.transfer(creditAccount, creditAccount1, amount);

        int balanceAfter_CA1 = creditAccount1.getBalance();
        int balanceAfter_CA = creditAccount.getBalance();

        Assertions.assertEquals(true, confirmation);
        Assertions.assertEquals(balanceBefore_CA1 + amount, balanceAfter_CA1);
        Assertions.assertEquals(balanceBefore_CA - amount, balanceAfter_CA);
    }

    @Test
    public void shouldNotTransferToCreditAccount() {
        int amount = 100_000;
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_SA = savingAccount.getBalance();

        boolean confirmation = bank.transfer(savingAccount, creditAccount, amount);

        int balanceAfter_CA = creditAccount.getBalance();
        int balanceAfter_SA = savingAccount.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
        Assertions.assertEquals(balanceBefore_CA, balanceAfter_CA);
    }

    @Test
    public void transferSavingToSavingBothShouldReturnFalse() {
        SavingAccount savingAccount2 = new SavingAccount(50, 10, 70, 15);
        int amount = 50;
        int balanceBefore_SA = savingAccount.getBalance();
        int balanceBefore_SA2 = savingAccount2.getBalance();

        boolean confirmation = bank.transfer(savingAccount, savingAccount2, amount);

        int balanceAfter_SA = savingAccount.getBalance();
        int balanceAfter_SA2 = savingAccount2.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_SA, balanceAfter_SA);
        Assertions.assertEquals(balanceBefore_SA2, balanceAfter_SA2);
    }

    @Test
    public void transferCreditToCredit1stShouldReturnFalse() {
        int amount = 20;
        CreditAccount creditAccount2 = new CreditAccount(100, 50);
        creditAccount.setBalance(-90);
        creditAccount2.setBalance(50);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_CA2 = creditAccount2.getBalance();

        boolean confirmation = bank.transfer(creditAccount, creditAccount2, amount);

        int balanceAfterCA = creditAccount.getBalance();
        int balanceAfterCA2 = creditAccount2.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_CA, balanceAfterCA);
        Assertions.assertEquals(balanceBefore_CA2, balanceAfterCA2);
    }

    @Test
    public void transferCreditToCredit2ndShouldReturnFalse() {
        int amount = 20;
        CreditAccount creditAccount2 = new CreditAccount(100, 50);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_CA2 = creditAccount2.getBalance();

        boolean confirmation = bank.transfer(creditAccount, creditAccount2, amount);

        int balanceAfterCA = creditAccount.getBalance();
        int balanceAfterCA2 = creditAccount2.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_CA, balanceAfterCA);
        Assertions.assertEquals(balanceBefore_CA2, balanceAfterCA2);
    }

    @Test
    public void transferCreditToCreditBothShouldReturnFalse() {
        int amount = 20;
        CreditAccount creditAccount2 = new CreditAccount(100, 50);
        creditAccount.setBalance(-90);
        int balanceBefore_CA = creditAccount.getBalance();
        int balanceBefore_CA2 = creditAccount2.getBalance();

        boolean confirmation = bank.transfer(creditAccount, creditAccount2, amount);

        int balanceAfterCA = creditAccount.getBalance();
        int balanceAfterCA2 = creditAccount2.getBalance();

        Assertions.assertEquals(false, confirmation);
        Assertions.assertEquals(balanceBefore_CA, balanceAfterCA);
        Assertions.assertEquals(balanceBefore_CA2, balanceAfterCA2);
    }
}