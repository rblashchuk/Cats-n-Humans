package ru.blashchuk;

import ru.blashchuk.sessions.CatSession;

import java.util.List;

public class Cat {

    public Cat() {

    }
   public void createCat(CatDao catDao) {
       new CatSession().createCat(catDao);
   }

   public CatDao readCat(long id) {
       return new CatSession().readCat(id);
   }

   public void updateCat(CatDao catDao) {
       new CatSession().updateCat(catDao);
   }

   public void makeFriends(long firstCatId, long secondCatId){
       CatDao firstCatDao = new CatSession().readCat(firstCatId);
       CatDao secondCatDao = new CatSession().readCat(secondCatId);

       List<CatDao> firstFriends = firstCatDao.getFriends();
       List<CatDao> secondFriends = secondCatDao.getFriends();

       firstFriends.add(secondCatDao);
       secondFriends.add(firstCatDao);

       firstCatDao.setFriends(firstFriends);
       secondCatDao.setFriends(secondFriends);

       new CatSession().updateCat(firstCatDao);
       new CatSession().updateCat(secondCatDao);
   }

   public void deleteCat(long id) {
       new CatSession().deleteCat(id);
   }
}
