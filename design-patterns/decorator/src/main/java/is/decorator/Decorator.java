package is.decorator;

public abstract class Decorator implements PlainTextComp {
    private final PlainTextComp comp;
    public Decorator(PlainTextComp dec){
        comp=dec;
    }

    @Override
    public String getRow(int i) {
        return comp.getRow(i);
    }

    @Override
    public int column() {
        return comp.column();
    }

    @Override
    public int row() {
        return comp.row();
    }
}
