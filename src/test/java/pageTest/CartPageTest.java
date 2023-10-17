package pageTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.Base;
import java.util.Map;

public class CartPageTest extends Base {
    @Test
    public void addItemToCartFromAllCategoryTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromAllCategory();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void addItemToCartFromBiographyTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromBiography();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void addItemToCartFromFictionTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromFiction();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void addItemToCartFromMystery() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromMystery();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void addItemToCartFromFantasy() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromFantasy();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void addItemToCartFromRomanceTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        Map<String, String> cartItem = cart.addItemToCartFromRomance();
        cart.navigateToCart();
        String name = cart.getItemNameInCart();
        String price = cart.getItemPriceInCart();
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals(name, cartItem.get("title"), cartItem.get("title") + " not in the cart");
        Assert.assertEquals(price, cartItem.get("price"), cartItem.get("price") + " is not for" + name);
    }
    @Test
    public void removeItemFromCartTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        cart.addItemToCartFromBiography();
        cart.navigateToCart();
        cart.removeItemInCart();
        String cartMessage = cart.cardTitle().getText();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEquals("Shopping cart is empty", cartMessage, "Cart is not empty");
    }
    @Test
    public void increaseAndDecreaseItemInCartTest() {
        CartPage cart = new CartPage(getDriver(), wait);
        cart.addItemToCartFromFiction();
        cart.navigateToCart();
        Map<String, Double> price1 = cart.getCurrentPrices();
        // Increase item by 2
        cart.increaseItemInCart();
        cart.increaseItemInCart();
        Map<String, Double> price2 = cart.getCurrentPrices();
        Map<String, Double> currentPrice1 = cart.calculatePrices(price1, 2, "increase");
        Assert.assertEqualsDeep(currentPrice1, price2, "Invalid price increment");
        // Decrease item by 1
        cart.decreaseItemInCart();
        Map<String, Double> price3 = cart.getCurrentPrices();
        Map<String, Double> currentPrice2 = cart.calculatePrices(price2, 1, "decrease");
        cart.clearItemInCart();
        cart.continueShopping();
        cart.goBack();
        Assert.assertEqualsDeep(currentPrice2, price3, "Invalid price decrement");
    }

}
