package com.gg;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class 方法引用 {

    //主要简化Lambda
    //引用「静态方法」 ： ContainingClass::staticMethodName
    //引用一个特定对象的实例方法：containingObject::instanceMethodName
    //引用特定类型的任意对象的实例方法：ContainingType::methodName
    //引用构造函数： ClassName::new

    @Test
    void test01() {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(10));
        apples.add(new Apple(11));
        apples.add(new Apple(12));
        apples.add(new Apple(13));

        //调用方法传入list参数 和接口类，接口类的cover方法由Lambda实现
        coverAppleWeight(apples, (Apple a) -> a.getWeight());//满足 T->R 的Lambda
        //简化Lambda成方法引用
        coverAppleWeight(apples,Apple::getWeight);//满足 T->R 的Lambda

    }

    //定义一个方法用来提取指定集合中的苹果的重量：
    private static List<Integer> coverAppleWeight(List<Apple> apples, CoverConsumer consumer) {
        List<Integer> list = new ArrayList<>();
        for (Apple a : apples) {
            //调用接口的cover方法 由传进来的参数的Lambda表达式实现方法
            list.add(consumer.cover(a));
        }
        return list;
    }
}

//定义一个接口用于Lambda表达式
interface CoverConsumer {
    int cover(Apple apple);
}

//
class Apple {
    String name;
    int weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(int weight) {
        this.weight = weight;
    }
}

