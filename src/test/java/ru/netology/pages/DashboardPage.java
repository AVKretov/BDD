package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void ReplanishFirstCard(String summ){
        var temp = $x("//*[contains (text(), '0001')]/text()[3]").getOwnText();
        $("[data-test-id='action-deposit']").click();
        $x("//h1[contains(text() , 'Пополнение карты')]").shouldBe(visible);
        $("[data-test-id='amount']  .input__control").setValue(summ);
        $("[data-test-id='from']  .input__control").setValue("5559000000000002");
        $("[data-test-id='action-transfer'] .button__content").click();
        var tempTwo = Integer.parseInt (temp) + Integer.parseInt (summ);
        $x("//*[contains (text(), '0001')]/text()[3]").shouldHave(text(Integer.toString(tempTwo)));

    }
}