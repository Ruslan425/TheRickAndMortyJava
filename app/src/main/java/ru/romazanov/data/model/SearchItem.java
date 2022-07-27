package ru.romazanov.data.model;

public class SearchItem implements Comparable<SearchItem> {
    public String section;
    public int id;
    public String name;
    public String image;


    public SearchItem(String section, int id, String name, String image) {
        this.section = section;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public int compareTo(SearchItem o) {
        return this.name.compareTo(((SearchItem) o).name);
    }
}
