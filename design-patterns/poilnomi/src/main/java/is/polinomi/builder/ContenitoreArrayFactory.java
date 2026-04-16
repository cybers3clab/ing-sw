package is.polinomi.builder;

class ContenitoreArrayFactory implements ContenitoreFactory {
    @Override
    public ContenitoreArray createContainer() {
        return new ContenitoreArray();
    }
}
