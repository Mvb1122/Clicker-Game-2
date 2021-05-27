package modules;

public class Item {
    public double price; // How much it costs to buy.
    public boolean available; // If you can buy it.
    public String name; // What the text should say on its button.
    public int effect; // How much it should increase the Clicks-per-tick.
    public int numOwned;

    public Item(int price, String name, int effect) {
        this.price = price;
        this.available = true;
        this.name = name;
        this.effect = effect;
        this.numOwned = 0;
    }

    public void makePurchased() {
        this.available = false;
        this.numOwned++;
    }

    public void setPrice(double input) {
        this.price = input;
    }

    public String getName() {
        String cutPrice = "" + this.price + "";
        int indexOfDecimal = cutPrice.indexOf(".");
        // System.out.println("String: " + cutPrice + " indexOfDecimal: " + indexOfDecimal);
        if (cutPrice.length() >= 5) {
            cutPrice = cutPrice.substring(0, indexOfDecimal);
        }
        this.name = "Increase by " + this.effect + " Cost: " + cutPrice;
        return this.name;
    }
}
