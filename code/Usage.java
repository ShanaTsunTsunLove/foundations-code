public class Usage {
    public static main(String[] args) {
        F<Object, Void> print = new F<Object, Void>() {
            public Void apply(Object x) {
                System.out.println(x);
                return null;
            }
        };

        Monoid<Integer> intMon = Usage.intAddMonoid();
        Monoid<String> strMon = Usage.stringConcatMonoid();
        Monoid<F<A, A>> compMon = Usage.funcMonoid();

        List<Integer> someInts = new LinkedList<Integer>();
        someInts.add(2);
        someInts.add(3);
        someInts.add(4);
        FList<Integer> integerList(someInts);
        System.out.println(intMon.foldl(integerList));
        print(intMon.foldl(integerList));
    }


    public static Monoid<Integer> intAddMonoid() {
        F<Integer, F<Integer, Integer>> add =
            new F<Integer, F<Integer, Integer>>() {
            public F<Integer, Integer> apply(final Integer x) {
                F<Integer, Integer> f = new F<Integer, Integer>() {
                    public Integer apply(final Integer y) {
                        return x + y;
                    }
                };
            }
        };
        return Monoid<Integer>.monoid(add, 0);
    }

    public static Monoid<String> stringConcatMonoid() {
        F<String, F<String, String>> concat =
            new F<String, F<String, String>>() {
            public F<String, String> apply(final String x) {
                F<String, String> f = new F<String, String>() {
                    public String apply(final String y) {
                        return x + y;
                    }
                };
            }
        };
        return Monoid<String>.monoid(concat, "");
    }


    public <A> static Monoid<F<A, A>> funcMonoid {
        F<F<A, A>, F<F<A, A>, F<A, A>>> comp =
            new F<F<A, A>, F<F<A, A>, F<A, A>>>() {
            public F<F<A, A>, F<A, A>> apply(final F<A, A> f) {
                F<F<A, A>, F<A, A>> g = new F<A, A>() {
                    public F<A, A> apply(final F<A, A> h) {
                        return Function.compose(f, h);
                    }
                };
                return g;
            }
        };

        F<A, A> id = new F<A, A>() {
            public A apply(final A x) {
                return x;
            }
        }
        return Monoid<F<A, A>>.monoid(comp, id);
    }
}
