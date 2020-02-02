import java.math.BigDecimal;
import java.util.Scanner;

class Main {

    private final static  Checkout checkout = new CheckoutImpl();

    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println(getTotalShouldReturnCorrectPriceWhenItemsAreScanned(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("Something is wrong!");
            throw e;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static BigDecimal getTotalShouldReturnCorrectPriceWhenItemsAreScanned(final String skus) {
        return checkout.getTotal(skus);
    }

}