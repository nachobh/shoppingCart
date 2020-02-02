import java.math.BigDecimal;

class ItemDto {
    private String sku;
    private BigDecimal price;

    public ItemDto(String sku, BigDecimal price){
        this.sku = sku;
        this.price = price;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setSku(BigDecimal price) {
        this.price = price;
    }


}