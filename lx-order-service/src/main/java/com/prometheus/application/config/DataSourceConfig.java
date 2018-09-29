package com.prometheus.application.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.prometheus.application.sharding.ModuloDBShardingAlgorithm;
import com.prometheus.application.sharding.ModuloTableShardingAlgorithm;

@Configuration
public class DataSourceConfig {

	/**
	 * 配置数据源0-主数据库
	 * @return
	 */
	@Bean(name="dataSourceMaster0")
	@ConfigurationProperties(prefix="spring.datasource.master")
	public DataSource dataSourceMaster(){
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 配置数据源1-主数据库
	 * @return
	 */
	@Bean(name="dataSourceSlave1")
	@ConfigurationProperties(prefix="spring.datasource.slave")
	public DataSource dataSourceSlave(){
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 配置数据源规则，即将多个数据源交给sharding-jdbc管理，并且可以设置默认的数据源，
     * 当表没有配置分库规则时会使用默认的数据源
	 * @param dataSourceMaster
	 * @param dataSourceSlave
	 * @return
	 */
	@Bean(name="dataSourceRule")
	public DataSourceRule dataSourceRule( @Qualifier("dataSourceMaster0") DataSource dataSourceMaster0,
			@Qualifier("dataSourceSlave1") DataSource dataSourceSlave1 ){
		Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
		dataSourceMap.put("dataSourceMaster0", dataSourceMaster0);
        dataSourceMap.put("dataSourceSlave1", dataSourceSlave1);
        return new DataSourceRule(dataSourceMap, "dataSourceMaster0"); //设置默认库，两个库以上时必须设置默认库。默认库的数据源名称必须是dataSourceMap的key之一
	}
	
	/**
	 * 配置数据源分库策略和分表策略，具体策略需要自己实现
	 * @param dataSourceRule
	 * @return
	 */
	@Bean
	public ShardingRule shardingRule(DataSourceRule dataSourceRule){
		TableRule orderTableRule = TableRule.builder("t_order").actualTables(Arrays.asList("t_order_0", "t_order_1"))
				.dataSourceRule(dataSourceRule).build();
		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(dataSourceRule)
				.tableRules(Arrays.asList(orderTableRule))
				.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new ModuloDBShardingAlgorithm()))
				.tableShardingStrategy(new TableShardingStrategy("order_id", new ModuloTableShardingAlgorithm()))
				.build();
		return shardingRule;
	}
	
	/**
	 * 创建sharding-jdbc的数据源DataSource，MybatisAutoConfiguration会使用此数据源
	 * @param shardingRule
	 * @return
	 * @throws SQLException
	 */
	@Bean(name="dataSource")
	@Primary
	public DataSource shardingDataSource(ShardingRule shardingRule) throws SQLException{
		return ShardingDataSourceFactory.createDataSource(shardingRule);
		
	}
	
	/**
     * 需要手动配置事务管理器
     * @param dataSource
     * @return
     */
	@Bean
	public DataSourceTransactionManager transactitonManager(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
