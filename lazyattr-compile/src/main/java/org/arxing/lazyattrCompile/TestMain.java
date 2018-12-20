package org.arxing.lazyattrCompile;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.arxing.lazyattrAnnotation.Valueable;

import java.lang.reflect.Field;

public class TestMain {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<? extends Enum> cls = Gravity.class;

        String r = Stream.of(cls.getEnumConstants()).peek(e->{
            if(e instanceof Valueable){
                int val = ((Valueable) e).val();
                System.out.println(val);
            }
        }).map(Enum::toString).collect(Collectors.joining(", "));
        System.out.println(r);

        Class c = Gravity.class;
        System.out.println(c.getInterfaces().length);

    }

    public static class GG implements Valueable{

        @Override public int val() {
            return 0;
        }
    }
}
