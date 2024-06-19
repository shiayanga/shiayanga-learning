package com.github.algorithm.random;

import com.github.algorithm.pojo.WeightedRandomPojo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class WeightedRandom {
    private TreeMap<Double, String> weightMap = new TreeMap<>();

    public static void main(String[] args) {
        ArrayList<WeightedRandomPojo> weightedRandomPojos = new ArrayList<>();
        WeightedRandomPojo weightedRandomPojo1 = new WeightedRandomPojo("1", 1d);
        WeightedRandomPojo weightedRandomPojo2 = new WeightedRandomPojo("2", 2d);
        WeightedRandomPojo weightedRandomPojo3 = new WeightedRandomPojo("5", 5d);
        WeightedRandomPojo weightedRandomPojo4 = new WeightedRandomPojo("10", 10d);
        weightedRandomPojos.add(weightedRandomPojo1);
        weightedRandomPojos.add(weightedRandomPojo2);
        weightedRandomPojos.add(weightedRandomPojo3);
        weightedRandomPojos.add(weightedRandomPojo4);
//        WeightedRandom weightedRandom = new WeightedRandom();
//        if (weightedRandom.weightMap.isEmpty()) {
//            weightedRandom.setMap(weightedRandomPojos);
//        }
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            String text = getText(weightedRandomPojos);
            if (count.get(text) != null) {
                Integer num = count.get(text);
                count.put(text, num + 1);
                continue;
            }
            count.put(text, 1);
        }
        BigDecimal totalTimes = new BigDecimal(10000);
        for (String s : count.keySet()) {
            Integer nums = count.get(s);
            BigDecimal bigDecimal = new BigDecimal(nums);
            BigDecimal decimal = bigDecimal.divide(totalTimes, 20, RoundingMode.HALF_UP);
            System.out.println(s + "\t命中\t" + count.get(s) + "次\t占比\t" + decimal.doubleValue() * 100 + "%");
        }
    }


    public boolean setMap(List<WeightedRandomPojo> weightList) {
        if (this.weightMap.isEmpty()) {
            Double totalWeight = 0d;
            for (WeightedRandomPojo weightedRandomPojo : weightList) {
                Double weight = weightedRandomPojo.getWeight();
                Double lastWeight = this.weightMap.isEmpty() ? 0 : this.weightMap.lastKey();
                this.weightMap.put(weight + lastWeight, weightedRandomPojo.getText());
                totalWeight += weight;
            }
            System.out.println("总权重：" + totalWeight);
        }
        return true;
    }

    public static String getText(List<WeightedRandomPojo> weightList) {
        TreeMap<Double, String> weightMap1 = new TreeMap<>();
        for (WeightedRandomPojo weightedRandomPojo : weightList) {
            Double weight = weightedRandomPojo.getWeight();
            Double lastWeight = weightMap1.isEmpty() ? 0 : weightMap1.lastKey();
            weightMap1.put(weight + lastWeight, weightedRandomPojo.getText());
        }
        double random = weightMap1.lastKey() * Math.random();
        NavigableMap<Double, String> tailedMap = weightMap1.tailMap(random, false);
        return weightMap1.get(tailedMap.firstKey());
    }

    public String getText() {
        if (weightMap.isEmpty()) {
            return null;
        }
        double random = this.weightMap.lastKey() * Math.random();
        NavigableMap<Double, String> tailedMap = this.weightMap.tailMap(random, false);
        return weightMap.get(tailedMap.firstKey());
    }
}
