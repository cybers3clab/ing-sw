package is.decorator;

public class CharDecorator extends Decorator{
    private  final char c;
    public CharDecorator(PlainTextComp comp,char c){
        super(comp);
        this.c=c;
    }

    @Override
    public int row() {
        return super.row()+2;
    }

    @Override
    public int column() {
        return super.column()+2;
    }


    @Override
    public String getRow(int i) {
        if (i < 0 || i >= row()) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder();
        if(i==0 ||i== row()-1)
            sb.repeat(c,column());
        else sb.append(c).append(super.getRow(i-1)).append(c);
        return sb.toString();
    }

}
