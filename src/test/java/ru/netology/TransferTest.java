package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransferPage;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @Test
    public void shouldTransferMoney() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        var dashboardPage1 = new DashboardPage();
        int atBeginFirstCardBalance = dashboardPage1.getFirstCardBalance();
        int atBeginSecondCardBalance = dashboardPage1.getSecondCardBalance();
        dashboardPage1.firstCardDeposit();
        var transferPage = new TransferPage();
        var depositInfo = DataHelper.getDepositInfo();
        transferPage.depositFromFirstCardToSecond(depositInfo);
        var dashboardPage2 = new DashboardPage();
        int firstCardBalance = dashboardPage2.getFirstCardBalance();
        int secondCardBalance = dashboardPage2.getSecondCardBalance();
        int firstCardBalanceExpected = atBeginFirstCardBalance + DataHelper.getDepositInfo().getAmount();
        int secondCardBalanceExpected = atBeginSecondCardBalance - DataHelper.getDepositInfo().getAmount();
        assertEquals(firstCardBalanceExpected, firstCardBalance);
        assertEquals(secondCardBalanceExpected, secondCardBalance);
    }

}