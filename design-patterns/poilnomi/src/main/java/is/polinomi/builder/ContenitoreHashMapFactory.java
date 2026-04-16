package is.polinomi.builder;

class ContenitoreHashMapFactory implements ContenitoreFactory {
    @Override
    public ContenitoreMap createContainer() {
        return new ContenitoreHashMap();
    }
}