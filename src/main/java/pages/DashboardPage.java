package pages;

import com.microsoft.playwright.Page;

public class DashboardPage {

    Page page;

    public DashboardPage(Page page){

        this.page=page;

    }

    public String getTitle(){

        return page.title();

    }

}
