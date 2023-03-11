package ru.blashchuk.banks.interfaces;

public interface Observer {
    public String getId();
    public void setId(String id);
    void update(String bankReport);
}
