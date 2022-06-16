
package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement senderField = $("[data-test-id=from] input");
    private SelenideElement depositButton = $("[data-test-id=action-transfer]");

    public DashboardPage depositFromFirstCardToSecond(DataHelper.DepositInfo info) {
        amountField.setValue(String.valueOf(info.getAmount()));
        senderField.setValue(info.getBill());
        depositButton.click();
        return new DashboardPage();
    }
}