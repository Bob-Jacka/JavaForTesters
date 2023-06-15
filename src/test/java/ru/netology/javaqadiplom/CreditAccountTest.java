package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }
}
