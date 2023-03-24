package ru.blashchuk;

import ru.blashchuk.sessions.CatSession;
import ru.blashchuk.sessions.HumanSession;

import java.util.List;

public class Human {
    public Human() {

    }
    public void createHuman(HumanDao HumanDao) {
        new HumanSession().createHuman(HumanDao);
    }

    public HumanDao readHuman(long id) {
        return new HumanSession().readHuman(id);
    }

    public void updateHuman(HumanDao HumanDao) {
        new HumanSession().updateHuman(HumanDao);
    }

    public void deleteHuman(long id) {
        HumanDao humanDao = new HumanSession().readHuman(id);
        List<CatDao> cats = humanDao.getCats();
        for (CatDao catDao: cats){
            new CatSession().deleteCat(catDao.getId());
        }
        new HumanSession().deleteHuman(id);
    }

    public void addCat (HumanDao humanDao, CatDao catDao) {
        List<CatDao> cats = humanDao.getCats();
        catDao.setOwner(humanDao);
        cats.add(catDao);
        humanDao.setCats(cats);
        new HumanSession().updateHuman(humanDao);
        new CatSession().updateCat(catDao);
    }
}
