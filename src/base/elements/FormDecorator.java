package base.elements;


public class FormDecorator extends Form {

    protected final Form decoratedForm;
    protected String pageName;

    public FormDecorator(Form decoratedForm) {
        this.decoratedForm = decoratedForm;
    }


    @Override
    public void fill(String param1, String param2, String param3) {

    }

    @Override
    public void fill() {
        decoratedForm.fill();
    }

    @Override
    public String getPageName() {
        return decoratedForm.getPageName();
    }
}
