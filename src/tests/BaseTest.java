package tests;

import base.pages.SearchPage;
import base.utils.Handler;
import base.utils.Utils;


public class BaseTest
{

    public SearchPage sp = new SearchPage();
    protected Handler handler = Utils.getHandler();
}
