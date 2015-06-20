package base.elements;


public abstract class Form {
    /**
     * fills the form
     */
    public abstract void fill(String param1, String param2, String param3);

    /**
     * fills the form
     */
    public abstract void fill();

    /**
     * @return page name, where form is situated
     */
    public abstract String getPageName();
}
