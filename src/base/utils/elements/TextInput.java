package base.utils.elements;


public interface TextInput extends Element {
    void type(String text);

    void clear();

    void clearAndType(String text);
}