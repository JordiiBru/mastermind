package utils;

import java.util.Objects;

public class Pair<T1, T2> implements Comparable<Pair<T1, T2>> {
    private final T1 first;
    private final T2 second;

    public Pair() {
        this.first = null;
        this.second = null;
    }

    public Pair(T1 i1, T2 i2) {
        this.first = i1;
        this.second = i2;
    }

    public T1 first() {
        return first;
    }

    public T2 second() {
        return second;
    }

    public boolean equals(Pair<T1,T2> p) {
        return (first.equals(p.first()) && second.equals(p.second()));
    }

    @Override
    public int compareTo(Pair<T1, T2> other) {
        return Integer.compare((Integer) other.second(), (Integer) this.second);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }
}
