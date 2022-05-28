package com.icode.sql;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.Collections;

/**
 * 数据集类似于RDD，但是，不使用Java序列化或Kryo，而是使用专门的编码器对对象进行序列化以便通过网络进行处理或传输。
 * 虽然编码器和标准序列化都负责将对象转换为字节，但编码器是动态生成的代码，并且使用Spark运行执行的操作（如过滤，排序和散列）格式，而无需将字节反序列化回对象。
 *
 * @author caiyq
 * @date 2021/10/16 20:47
 */
public class SparkSqlTest2 {

    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        SparkSession spark = createSpark();
        Encoder<Person> encoder = Encoders.bean(Person.class);
        spark.read().json("data/user.json").as(encoder).show();
    }

    private static void test2() {
        SparkSession spark = createSpark();
        Encoder<Integer> encoder = Encoders.INT();
        Dataset<Integer> ds = spark.createDataset(Arrays.asList(1, 2, 3), encoder);
        ds.map((MapFunction<Integer, Integer>) value -> value + 1, encoder).show();
    }

    private static void test1() {
        SparkSession spark = createSpark();
        Person person = new Person("coco", 19);
        Encoder<Person> encoder = Encoders.bean(Person.class);
        spark.createDataset(Collections.singletonList(person), encoder).show();
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
