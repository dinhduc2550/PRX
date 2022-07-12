/*

*/
package entity;

public class News {

    private int id;
    private String urlImage;
    private int view;
    private String title,urlTxt;
    private int type,amount;
    private float price;
    private String productName;
    public News() {
    }

    public News(int id, String urlImage, int view, String title, String urlTxt) {
        this.id = id;
        this.urlImage = urlImage;
        this.view = view;
        this.title = title;
        this.urlTxt = urlTxt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlTxt() {
        return urlTxt;
    }

    public void setUrlTxt(String urlTxt) {
        this.urlTxt = urlTxt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return id+"\n"+urlImage+"\n"+view+"\n"+title+"\n"+type+"\n"+amount+"\n"+price+"\n"+productName;
    }

   
    
}