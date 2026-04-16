package is.polinomi.builder;

class ContenitoreTreeMapFactory implements ContenitoreFactory {
    @Override
    public ContenitoreMap createContainer() {
        return new ContenitoreTreeMap();
    }
}