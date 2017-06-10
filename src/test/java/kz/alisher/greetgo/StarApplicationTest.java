package kz.alisher.greetgo;

import kz.alisher.greetgo.domain.Dictionary;
import kz.alisher.greetgo.domain.Star;
import kz.alisher.greetgo.mappers.DictionaryMapper;
import kz.alisher.greetgo.mappers.StarMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;

/**
 * Created by alisher on 6/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StarApplicationTest extends AbstractTestNGSpringContextTests {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StarMapper starMapper;

    @Autowired
    private DictionaryMapper dictMapper;

    @Test
    public void findStarByIdTest() {
        log.info("findStarByIdTest method is run....");
        Star star = starMapper.findStarById(1);
        Assert.assertNotNull(star);
    }

    @Test
    public void findAllDictionaries() {
        log.info("findAllDictionaries method is run....");
        List<Dictionary> list = dictMapper.findAllDictionary();
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 7);
    }
}
