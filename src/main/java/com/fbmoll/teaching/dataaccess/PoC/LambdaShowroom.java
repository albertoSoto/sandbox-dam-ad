package com.fbmoll.teaching.dataaccess.PoC;

import com.fbmoll.teaching.dataaccess.data.Product;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * com.fbmoll.teaching.dataaccess.PoC
 * Class LambdaShowroom
 * 18/11/2020
 *
 * @author berto (alberto.soto@gmail.com)
 */
public class LambdaShowroom {
    /**
     * Lambdas are everywhere!
     * They have been implemented in collections and we use them quite often, like forEach method (L30)
     *
     * @param times
     * @return
     */
    public String getData(int times) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(9);
        numbers.add(8);
        numbers.add(1);
        numbers.forEach(currentNumber -> {
            stringBuilder.append(String.format("como mola %s", currentNumber));
            //String.format("el mensaje que %s y %s quieran ","Pepito", "Juanito");
        });
        //numbers.forEach(printNumber);
        return stringBuilder.toString();
    }

    /**
     * This is a consumer property which can be accessed from other methods.
     * The tipical use is creating it inside a method, like an inner function
     */
    private Consumer<Integer> printNumber = (n) -> {
        System.out.println(String.format("como mola %s", n));
    };
    private Consumer<Product> bookFlight = (flight) -> {
        System.out.println(String.format("%s Reserved", flight));
    };

    public boolean doCheckOut(List<Product> shopItems) {
        shopItems.forEach(item -> {
            /*
            if (!confirmProduct(item, () -> {
             //TODO chequear
            })) {
                throw new NullPointerException();
            }*/
        });
        return false;
    }

    public Boolean confirmProduct(Product product, Function<Product, Boolean> fn) {
        //bookFlight.accept(products.get(0));
        System.out.println("parte inicial");
        boolean rtn = fn.apply(product);
        System.out.println("parte final");
        return rtn;
    }

}
