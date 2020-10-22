package sourcemaking.creational.abstractfactory.example2;

abstract class AbstractFactory {
    Expression prototype;

    public Expression makePhase() {
        return prototype.clone();
    }

    abstract Expression makeCompromise();
    abstract Expression makeGrade();
}

class PCFactory extends AbstractFactory {
    public PCFactory() {
        prototype = new PCPhase();
    }

    @Override
    Expression makeCompromise() {
        return new Expression("\"do it your way, any way or no way\"");
    }

    @Override
    Expression makeGrade() {
        return new Expression("\"you pass, self-esteem intact\"");
    }
}

class NotPCFactory extends AbstractFactory {
    public NotPCFactory() {
        prototype = new NotPCPhase();
    }

    @Override
    Expression makeCompromise() {
        return new Expression("\"take test, deal with the results\"");
    }

    @Override
    Expression makeGrade() {
        return new Expression("\"my way, or the highway\"");
    }
}
