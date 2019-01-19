import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.util.Assert;
import org.springframework.util.SocketUtils;
import page.MainPage;
import page.SearchPage;
import page.StockDetailPage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class StockDetailTest {

    static MainPage mainPage;
    static SearchPage searchPage;
    static StockDetailPage stockDetailPage;

    @BeforeAll
    public static void setUp(){
        mainPage= MainPage.start();
        searchPage=mainPage.gotoSearch();
    }

    @ParameterizedTest
    @CsvSource({"pdd, 加自选","alibaba, 加自选"})
    @DisplayName("2.取消自选")
    public void testDeleteSelect(String keyWord,String name){
        stockDetailPage = searchPage.search(keyWord).gotoStockDetailPage();
        stockDetailPage.deleteSelected();
        String content = stockDetailPage.getSelectBtnText();
        assertThat(content, equalTo(name));
    }

    @ParameterizedTest
    @CsvSource({"pdd, 设自选"})
    @DisplayName("1.增加自选")
    public void testAddSelect(String keyWord,String name){
        stockDetailPage = searchPage.search(keyWord).gotoStockDetailPage();

        stockDetailPage.addSelect();
        String content = stockDetailPage.getSelectBtnText();
        assertThat(content, equalTo(name));
    }

    @AfterEach
    public void afterCase(){
        searchPage = stockDetailPage.backSearchPage();
    }

    @AfterAll
    static void cleanUp(){
        System.out.println("down");
    }
}
