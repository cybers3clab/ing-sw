import is.decorator.CharDecorator;
import is.decorator.PlainTextComp;
import is.decorator.SimpleText;

public class Test {

    public static void main(String[] args) {
        SimpleText pt = new SimpleText("pippo");
        CharDecorator st1=new CharDecorator(pt,'+');
        CharDecorator st2=new CharDecorator(st1,'-');
        printAll(st2);
        pt.setText("plutone e i suoi fratelli");
        printAll(st1);

    }
    static  void printAll(PlainTextComp... comp){
        for(PlainTextComp c:comp)
            for(int i=0;i<c.row();i++){
                System.out.println(c.getRow(i));

            }
    }

}
