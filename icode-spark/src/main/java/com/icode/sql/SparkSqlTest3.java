package com.icode.sql;

import org.apache.spark.sql.SparkSession;

/**
 *
 *
 * @author caiyq
 * @date 2021/10/16 20:47
 */
public class SparkSqlTest3 {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
    }

    private static SparkSession createSpark() {
        // 在Spark中，所有功能的入口就是SparkSession，要创建SparkSession，使用SparkSession.builder()
        return SparkSession
                .builder()
                .appName("SparkSQL")
                .master("local[*]")
                .getOrCreate();
    }
}
