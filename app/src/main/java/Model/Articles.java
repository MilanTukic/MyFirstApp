package Model;

/**
 * Created by Tukic on 4/26/2017.
 */

public class Articles {
    private long id;
    private String name;
    private boolean isActive=false;

    public Articles() {
    }

    public Articles(long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
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

    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return name ;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
