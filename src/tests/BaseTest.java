package tests;

import base.pages.SearchPage;
import base.utils.Handler;
import base.utils.Utils;
import logger.Logger;
import logger.LoggerFactory;


public class BaseTest
{

    public static Logger logger = LoggerFactory.getLogger();
    public SearchPage sp = new SearchPage();
    protected Handler handler = Utils.getHandler();
}
