import java.math.BigDecimal;

class OfferDto {
    private int quantity;
    private String sku;
    private BigDecimal totalAmmountWithDiscount;

    public OfferDto(String sku, int quantity, BigDecimal totalAmmountWithDiscount){
        this.quantity = quantity;
        this.sku = sku;
        this.totalAmmountWithDiscount = totalAmmountWithDiscount;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getTotalAmmountWithDiscount() {
        return this.totalAmmountWithDiscount;
    }

    public void setTotalAmmountWithDiscount(BigDecimal totalAmmountWithDiscount) {
        this.totalAmmountWithDiscount = totalAmmountWithDiscount;
    }


}