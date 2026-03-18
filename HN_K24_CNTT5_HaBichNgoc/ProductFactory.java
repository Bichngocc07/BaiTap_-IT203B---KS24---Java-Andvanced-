package HN_K24_CNTT5_HaBichNgoc;

class ProductFactory {
    public static DigitalProduct createProduct(int type, String id, String name, double price, double extra) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extra);
        } else if (type == 2) {
            return new DigitalProduct(id, name, price, extra);
        }
        return null;
    }
}