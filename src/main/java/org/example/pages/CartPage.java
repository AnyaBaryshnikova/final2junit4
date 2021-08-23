package org.example.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//div[@data-widget='header']/div/div/div[1]")
    WebElement cartTitle;

    // выбрать все товары
    @FindBy(xpath = "//label[contains(text(), 'Выбрать все')]")
    WebElement checkAll;

    //Удалить выбранные
    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    WebElement deleteChecked;

    @FindBy(xpath = "//div[contains(text(), 'Перейти к оформлению')]/../..")
    WebElement oformit;

    @FindBy(xpath = "//div[contains(text(), 'Удаление товаров')]/../..")
    WebElement deleteItemsWindow;

    @FindBy(xpath = "//div[contains(text(), 'Удалить')]/../..")
    WebElement deleteBtn;

    //Чтобы проверить кол-во товаров в корзине
    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]/../span[2]")
    WebElement yourCart;

    @FindBy(xpath = "//h1")
    WebElement emptyTitle;

    @FindBy(xpath = "//div[@id='split-Main-0']/div//span[@style='color: rgb(0, 26, 52);']")
    List<WebElement> cartElementsList;


    /**
     * Проверяем, что мы в корзине
     *
     * @return
     */
    public CartPage checkCartPageOpen() {
        waitUtilElementToBeVisible(cartTitle);
        Assert.assertTrue("Мы не в корзине", cartTitle.getText().equals("Корзина"));

        return this;
    }

    /**
     * Удалить все из корзины
     * @return
     */
    public CartPage deleteAllFromCart() {

        if (!oformit.isEnabled()) {
            checkAll.click();
            waitUtilElementToBeClickable(oformit);
        }
        deleteChecked.click();
        waitUtilElementToBeVisible(deleteItemsWindow);
        deleteBtn.click();
        return this;
    }

    /**
     * Проверяем, количество товаров в корзине
     *
     * @return
     */
    public CartPage checkAmountOfCart() {
        String yourCartAmount = yourCart.getText().trim();
        yourCartAmount = yourCartAmount.substring(0, yourCartAmount.indexOf(" "));
        int num = Integer.parseInt(yourCartAmount);

        Assert.assertTrue("Количество товаров не совпадает с количеством, которое добвляли"
                , itemNames.size() == num);

        return this;
    }

    /**
     * Проверяем, что корзина пуста
     * @return
     */
    public CartPage checkCartEmpty(){
        Assert.assertTrue("Корзина не пуста", emptyTitle.getText().contains("Корзина пуста"));
        return this;

    }

    /**
     * Проверяем, что в корзине то, что добавляли
     * @return
     */
    public CartPage checkItemsInCart(){
        String str = itemNames.toString();
        for(int i = 0; i < cartElementsList.size(); ++i)
            Assert.assertTrue("Корзина содержит не те элементы, которые добавляли",
                    str.contains(cartElementsList.get(i).getText()));

        return this;
    }

    public String getItemList(){
        final String[] str = {""};
        itemNames.forEach(entry -> str[0] += (entry + "\n"));

        return str[0];
    }
}


