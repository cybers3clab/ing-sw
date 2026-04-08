package is.decorator;

public class SimpleText implements PlainTextComp{
    private  String text;
    public SimpleText(String text){
        this.text=text;
    }
    @Override
    public String getRow(int i) {
        if(i!=0)throw  new IllegalArgumentException();
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int column() {
        return text.length();
    }

    @Override
    public int row() {
        return 1;
    }
}
