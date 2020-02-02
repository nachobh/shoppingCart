import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CheckoutImpl implements Checkout {

    private final static String SPLIT_CHAR = ",";
    private static Map<String, BigDecimal> priceList = null;
    private static List<OfferDto> offerList = null;

    private final static String ITEM1_SKU = "A";
    private final static BigDecimal ITEM1_PRICE = new BigDecimal("50");
    private final static String ITEM2_SKU = "B";
    private final static BigDecimal ITEM2_PRICE = new BigDecimal("30");
    private final static String ITEM3_SKU = "C";
    private final static BigDecimal ITEM3_PRICE = new BigDecimal("20");
    private final static String ITEM4_SKU = "D";
    private final static BigDecimal ITEM4_PRICE = new BigDecimal("15");

    private final static String SKU_OFFER_1 = "A";
    private final static int QUANTITY_OFFER_1  = 3;
    private final static BigDecimal TOTAL_AMMOUNT_OFFER1 = new BigDecimal("130");

    private final static String SKU_OFFER_2 = "B";
    private final static int QUANTITY_OFFER_2  = 2;
    private final static BigDecimal TOTAL_AMMOUNT_OFFER2 = new BigDecimal("45");

    @Override
    public BigDecimal getTotal(final String skus) {
        initPriceList();
        intiOfferList();
        final List<ItemDto> items = getItemsFromStringInput(skus);
        final BigDecimal totalGross = sumPrices(items);
        return getTotalNet(items, totalGross);
    }

    private void initPriceList() {
        if (priceList == null) {
            priceList = new HashMap<>();
            priceList.put(ITEM1_SKU, ITEM1_PRICE);
            priceList.put(ITEM2_SKU, ITEM2_PRICE);
            priceList.put(ITEM3_SKU, ITEM3_PRICE);
            priceList.put(ITEM4_SKU, ITEM4_PRICE);
        }
    }

    private void intiOfferList() {
        if (offerList == null) {
            offerList = new ArrayList<>();
            offerList.add(new OfferDto(SKU_OFFER_1,QUANTITY_OFFER_1,TOTAL_AMMOUNT_OFFER1));
            offerList.add(new OfferDto(SKU_OFFER_2,QUANTITY_OFFER_2,TOTAL_AMMOUNT_OFFER2));
        }
    }

    private List<ItemDto> getItemsFromStringInput(final String skus) {
        String[] arrayItems = skus.split(SPLIT_CHAR);
        List<ItemDto> items = new ArrayList<>();
        for (String sku : arrayItems) {
            if (priceList.get(sku) != null) {
                items.add(new ItemDto(sku, priceList.get(sku)));
            }
        }
        return items;
    }

    private BigDecimal sumPrices(List<ItemDto> items) {
        return items.stream().map(ItemDto::getPrice)
                .reduce(BigDecimal::add).get();
    }

    private BigDecimal getTotalNet(List<ItemDto> items, BigDecimal totalGross) {
        BigDecimal totalDiscounted = new BigDecimal(0);
        for (final OfferDto offer : offerList) {
            final List<ItemDto> itemsWithOffer = items.stream().filter(t->t.getSku().equals(offer.getSku()))
                    .collect(Collectors.toList());
            final BigDecimal itemPrice = itemsWithOffer.get(0).getPrice();
            final BigDecimal undiscountedPrice = itemPrice.multiply(new BigDecimal(offer.getQuantity()));
            final int timesToApplyTheOffer = itemsWithOffer.size() / offer.getQuantity();
            final BigDecimal unitaryDiscount = undiscountedPrice.subtract(offer.getTotalAmmountWithDiscount());
            final BigDecimal totalOffer = new BigDecimal(timesToApplyTheOffer).multiply(unitaryDiscount);
            totalDiscounted = totalDiscounted.add(totalOffer);
        }
        return totalGross.subtract(totalDiscounted).setScale(2, RoundingMode.HALF_UP);
    }

}