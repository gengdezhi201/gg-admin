package com.gg;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeTest {

    @Test()
    void test01(){
        LocalDate ld = LocalDate.now();
        String date = ld.toString();
        System.out.println(date);
    }

    @Test()
    void test02(){
        LocalDate ld = LocalDate.now();
        int year = ld.getYear();
        Month m = ld.getMonth();
        int month = ld.getMonthValue();
        int day = ld.getDayOfMonth();
        int day1 = ld.getDayOfYear();
        System.out.print(m.firstDayOfYear(true));
        System.out.printf("%-2d - %-2d - %-2d \n %-2d",year,month,day,day1);
    }

    @Test
    void test03() {
        String dayAfterTommorrow = "2018-02-10";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
    }
}
