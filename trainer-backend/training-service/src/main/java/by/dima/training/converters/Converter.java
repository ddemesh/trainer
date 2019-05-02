package by.dima.training.converters;

public interface Converter<S, D> {
    D convert(S source);
}
