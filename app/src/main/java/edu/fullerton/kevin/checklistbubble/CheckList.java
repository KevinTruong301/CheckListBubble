package edu.fullerton.kevin.checklistbubble;

/**
 * Created by Kevin on 1/29/2018.
 */

public class CheckList {
    private int id;
    private String name;
    private String item;

    public CheckList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CheckList(int id, String item, String name){
        this.id = id;
        this.item = item;
        this.name = name;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
