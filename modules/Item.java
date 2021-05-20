package modules;

public class Item {
    public int price; // How much it costs to buy.
    public boolean available; // If you can buy it.
    public String name; // What the text should say on its button.
    public int effect; // How much it should increase the Clicks-per-tick.

    public Item(int price, String name, int effect) {
        this.price = price;
        this.available = true;
        this.name = name;
        this.effect = effect;
    }

    public void makePurchased() {
        this.available = false;
    }
}
