package com.icode.sql;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * SparkSession
 *
 * @author caiyq
 * @date 2021/10/16 9:50
 */
public class SparkSqlTest {

    public static void main(String[] args) {
        test3();
    }

    private static void test3() {
        SparkSession spark = createSpark();
        Dataset<Row> ds = spark.read().json("data/user.json");
        // Spark SQL中的临时视图是会话范围的，如果创建它的会话终止，将会消失。
        // 如果您希望在所有会话之间共享一个临时视图并保持活动状态，直到Spark应用程序终止，则可以创建一个全局临时视图。
        // 全局临时视图与系统保存的数据库绑定global_temp，我们必须使用限定的名称来引用它，例如SELECT * FROM global_temp.view1
        ds.createOrReplaceGlobalTempView("user");
        spark.newSession().sql("select * from global_temp.user").show();
    }

    private static void test2() {
        SparkSession spark = createSpark();
        Dataset<Row> ds = spark.read().json("data/user.json");
        // SparkSession上的sql函数允许应用程序能以编程方式运行SQL查询，并将结果返回为Dataset<Row>
        ds.createOrReplaceTempView("user");
        spark.sql("select * from user").show();
    }

    private static void test1() {
        SparkSession spark = createSpark();
        // 使用一个SparkSession，应用程序可以从现有的RDD，Hive表或Spark数据源创建DataFrame
        spark.read().json("data/user.json").show();
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
