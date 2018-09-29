package com.paradise.common;

import java.math.BigDecimal;

public class BigdecimalPlay {

    public static void main(String[] args) {
        System.out.println(play(483840000L));
    }

    private static String play(Long durationInMillis){
        BigDecimal duration = new BigDecimal(durationInMillis);
        duration = duration.divide(new BigDecimal( 60 * 60 * 1000),1,BigDecimal.ROUND_HALF_UP);
        return duration.toString() + "天";
    }
}
