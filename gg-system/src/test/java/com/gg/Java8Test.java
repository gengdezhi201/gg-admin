package com.gg;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSONObject;
import com.gg.model.system.domain.SysDict;
import com.gg.util.StringUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Test {


    @Test()
    void test01(){
        //Lambda表达式
        //parameter -> expression body
        //parameter多个时需要()   body为多行时需要{}
        List<Map> list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        map.put("c","3");
        map.put("d","4");
        //遍历map
        map.forEach((k,v)->System.out.println(k+"-"+v));
        list.add(map);
        //遍历list
        list.forEach(m->m.put("r","s"));
        //经典用法
        Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
    }

    @Test()
    void test02(){
        //方法引用
        //方法引用可以被看作仅仅 「调用特定方法」 的 Lambda 的一种快捷写法。
        Arrays.asList("a","b","c").forEach(System.out::println);
        Arrays.asList( "a", "b", "d" ).sort(String::compareTo);
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7).stream().filter(i -> i > 3).collect(Collectors.toList()));
    }

    @Test
    void test03(){
        List<String> list = Arrays.asList( "a", "b", "d" );
        System.out.println(list.stream().findFirst().get());
//        list= list.stream().filter(StringUtils::isNotEmpty).collect(Collectors.toList());
        list.forEach(System.out::println);

    }

    @Test
    void test04(){
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6);

        Stream<Integer> stream2 = Stream.iterate(0, (x) -> x + 3).limit(4);
//        stream2.forEach(System.out::println);

        Stream<Double> stream3 = Stream.generate(Math::random).limit(3);
//        stream3.forEach(System.out::println);


//        Stream.iterate("", n -> "-"+n)
//                .limit(20)
//                .map(n -> n + n)
//                .forEach(System.out::println);

        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    @Test
    void test05() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
        list.stream().map(x->x);
        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 6).findFirst();
        // 匹配任意（适用于并行流）
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();
        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("匹配第一个值：" + findFirst.get());
        System.out.println("匹配任意一个值：" + findAny.get());
        System.out.println("是否存在大于6的值：" + anyMatch);
        list.parallelStream().filter(x -> x > 6).forEach(System.out::println);
    }

    @Test
    void test06(){
        System.out.println(LocalDateTime.now());
    }

}
