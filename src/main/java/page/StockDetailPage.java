package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StockDetailPage extends BasePage{

    By zixuan = By.xpath("//*[@resource-id='com.xueqiu.android:id/floating_action_view' and 2]/android.widget.LinearLayout/android.widget.RelativeLayout[last()]/android.widget.TextView");

    By deleteSelect = By.xpath("//*[@text='删除自选']");

    By back = By.id("action_back");

    By negative = By.id("md_buttonDefaultNegative");

    private boolean isSelected(){
        System.out.println(this.getSelectBtnText());
        if ("加自选".equalsIgnoreCase(this.getSelectBtnText())){
            return false;
        }else {
            return true;
        }
    }

    public String getSelectBtnText(){
        try{
            return Driver.getCurrentDriver().findElement(zixuan).getText();
        }catch (Exception e){
            Driver.getCurrentDriver().findElement(negative).click();
            return Driver.getCurrentDriver().findElement(zixuan).getText();
        }

    }

    public void addSelect(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isSelected()){
            Driver.getCurrentDriver().findElement(zixuan).click();
        }else {
            throw  new RuntimeException("this stock status is "+this.getSelectBtnText());
        }
    }

    public void deleteSelected(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isSelected()){
            Driver.getCurrentDriver().findElement(zixuan).click();
            Driver.getCurrentDriver().findElement(deleteSelect).click();
        }else{
            throw new RuntimeException("this stock status is "+this.getSelectBtnText());
        }
    }

    public SearchPage backSearchPage(){
        try {
            Driver.getCurrentDriver().findElement(back).click();
        }catch (Exception e){
            Driver.getCurrentDriver().findElement(negative).click();
            Driver.getCurrentDriver().findElement(back).click();
        }
        return new SearchPage();
    }

}
