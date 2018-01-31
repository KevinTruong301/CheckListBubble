package edu.fullerton.kevin.checklistbubble;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckList {
    private String name;
    private String item;

    public CheckList(String name, String item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public String getItem() {
        return item;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
