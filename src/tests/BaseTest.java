package tests;

import base.utils.Handler;
import base.utils.Utils;
import logger.Logger;
import logger.LoggerFactory;


public class BaseTest {

    public static Logger logger = LoggerFactory.getLogger();
    protected Handler handler = Utils.getHandler();
}
