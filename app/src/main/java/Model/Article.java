package Model;

/**
 * Created by Tukic on 4/26/2017.
 */

public class Article {
    private long id;
    private String name;
    private int price;
    private boolean isDone=false;

    public Article() {
    }

    public Article(long id, String name, int price, boolean isDone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        return name ;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
