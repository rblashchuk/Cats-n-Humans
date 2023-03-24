import org.junit.jupiter.api.Test;
import ru.blashchuk.*;

import java.util.Calendar;
import java.util.Date;

public class CatTest {
    @Test
    public void canCreateCat() {
        Human human = new Human();
        HumanDao humanDao = new HumanDao();
        humanDao.setName("Vasya");
        humanDao.setBirthdate(new Date(1993, Calendar.FEBRUARY, 18));
        human.createHuman(humanDao);

        Cat cat = new Cat();
        CatDao catDao = new CatDao();
        catDao.setBreed(Breed.PERSIAN);
        catDao.setColor(Color.GREY);
        catDao.setBirthdate(new Date(2022, Calendar.MARCH, 11));
        catDao.setOwner(humanDao);
        catDao.setName("cat №23");
        cat.createCat(catDao);

        human.addCat(humanDao, catDao);

    }
}
