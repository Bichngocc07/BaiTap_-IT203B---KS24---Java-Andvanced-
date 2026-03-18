package HN_K24_CNTT5_HaBichNgoc;

import java.util.*;

class ProductDatabase {
    private static ProductDatabase instance;
    private List<Produc> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(DigitalProduct p) {
        products.add(p);
    }

    public void updateProduct(String id, String newName, double newPrice) {
        for (Produc p : products) {
            if (p.getId().equals(id)) {
                p.setName(newName);
                p.setPrice(newPrice);
            }
        }
    }

    public void deleteProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Produc> getProducts() {
        return products;
    }

    public Produc[] getAll() {

    }
}