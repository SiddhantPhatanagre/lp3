import java.util.Arrays;
import java.util.Comparator;

class Item {
    int weight;
    int value;

    Item(int w, int v) {
        weight = w;
        value = v;
    }
}

public class FractionalKnapsack {

    public static double fractionalKnapsack(Item[] items, int capacity) {

        // Step 1: Sort items by value/weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                double r1 = (double) a.value / a.weight;
                double r2 = (double) b.value / b.weight;
                return Double.compare(r2, r1);
            }
        });

        double totalValue = 0.0;

        System.out.println("Items taken:");
        System.out.println("Weight\tValue\tFraction Taken");

        // Step 2: Take items greedily
        for (Item item : items) {

            if (capacity == 0)
                break;

            if (item.weight <= capacity) {
                // Take whole item
                totalValue += item.value;
                System.out.println(item.weight + "\t" + item.value + "\t" + "1.0 (Full)");
                capacity -= item.weight;
            } else {
                // Take fractional part
                double fraction = (double) capacity / item.weight;
                totalValue += item.value * fraction;

                System.out.println(item.weight + "\t" + item.value + "\t" + String.format("%.2f", fraction) + " (Partial)");

                capacity = 0;   // knapsack full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {

        Item[] items = {
                new Item(10, 60),
                new Item(20, 100),
                new Item(30, 120)
        };

        int capacity = 50;
        System.out.println("Capacity is: "+ capacity);

        double maxValue = fractionalKnapsack(items, capacity);

        System.out.println("\nMaximum value in Knapsack = " + maxValue);
    }
}