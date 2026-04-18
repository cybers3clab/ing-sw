package is.polinomi.test;

import is.polinomi.Monomio;
import is.polinomi.Polinomio;
import is.polinomi.bridge.PolinomioConcreto;
import is.polinomi.bridge.PolinomioIntegrable;
import is.polinomi.builder.PolinomioBuilderIF;
import is.polinomi.factorymethod.PolinomioTreeMap;
import is.polinomi.prototype.PolinomioFactory;
import is.polinomi.prototype.PolinomioLL;

//import is.polinomi.factorymethod.PolinomioLL;
//import is.polinomi.bridge.PolinomioArrayFactory;

public class ProvaPolinomi {
    public static void main(String[] args) {

        //Polinomio p1 = PolinomioFactory.createPolinomio();
        // new PolinomioArray(10);
        Polinomio p1 = new PolinomioTreeMap();
        //Polinomio p1 =new PolinomioConcreto();
        p1 = p1.add(new Monomio(2.0, 2)).add(new Monomio(2.0, 1));

        Polinomio p2 =PolinomioFactory.createPolinomio();
               // new PolinomioLL();
        p2 = p2.add(new Monomio(4, 1));

        p2 = p2.add(new Monomio(3, 0));
        p2 = p2.add(new Monomio(1.5, 2));

        System.out.println("p1=" + p1);

        System.out.println(p1.getClass());

        System.out.println("p2=" + p2);
        System.out.println(p2.getClass());

        Polinomio prod = p1.mul(p2);
        System.out.println("prod=" + prod);
        System.out.println(prod.getClass());

        prod = p2.mul(p1);
        System.out.println("prod=" + prod);
        System.out.println(prod.getClass());

        if (true) return;
        Polinomio p3 = p1.add(p2);

        System.out.println("p3=" + p3);

        System.out.println(p3.getClass());

        System.out.println(((PolinomioLL) p2).clone());
        //PolinomioConcreto.setPolinomioImplFactory(new PolinomioArrayFactory());


        Polinomio p4 = new PolinomioConcreto();
        p4 = p4.add(new Monomio(3, 0));
        p4 = p4.add(new Monomio(4, 1));

        System.out.println("p4=" + p4);
        Polinomio p5 = new PolinomioIntegrable(p4).integrate();
        System.out.println("p5=" + p5);

        Polinomio p6 = p1.derive().mul(new PolinomioIntegrable(p4).integrate().integrate());
        System.out.println("p6=" + p6);

        System.out.println(p4.mul(p5));

        PolinomioBuilderIF builder = new is.polinomi.builder.PolinomioConcreto.Builder();
        builder.add(new Monomio(5, 6))
                .add(new Monomio(3, 1));
        Polinomio p10=builder.getPolinomio();
        builder.add(new Monomio(4,4));
        Polinomio p11=builder.getPolinomio();


        System.out.println("p10=" + p10);
        System.out.println("p11=" + p11);
        Polinomio p12=p11.add(p2);

        System.out.println("p12=" + p12);
        System.out.println("--------------------");
        testList();

    }

    static void testList(){
        Polinomio p1= new PolinomioLL();
        Polinomio p2= new is.polinomi.factorymethod.PolinomioLL();
        Polinomio p3= new PolinomioConcreto();

        p1=p1.add(new Monomio(3,4)).add(new Monomio(5,1));
        p2=p2.add(new Monomio(3,4)).add(new Monomio(5,1));
        p3=p3.add(new Monomio(3,4)).add(new Monomio(5,1));

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

    }


}
