package com.prometheus.application.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

public class ModuloDBShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

	/**
	 * doEqualSharding在WHERE使用=作为条件分片键。算法中使用shardingValue.getValue()获取等=后的值 
	 * doInSharding在WHERE使用IN作为条件分片键。算法中使用shardingValue.getValues()获取IN后的值 
	 * doBetweenSharding在WHERE使用BETWEEN作为条件分片键。算法中使用shardingValue.getValueRange()获取BETWEEN后的值
	 */
	@Override
	public Collection<String> doBetweenSharding(Collection<String> dataBaseNames, ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(dataBaseNames.size());
		Range<Long> range = shardingValue.getValueRange();
		for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String dataBaseName : dataBaseNames) {
                if (dataBaseName.endsWith(i % 2 + "")) {
                    result.add(dataBaseName);
                }
            }
        }
		return result;
	}

	@Override
	public String doEqualSharding(Collection<String> dataBaseNames, ShardingValue<Long> shardingValue) {
		for(String dataBaseName : dataBaseNames){
			if(dataBaseName.endsWith(Long.parseLong(shardingValue.getValue().toString())%2 + "")){
				return dataBaseName;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> dataBaseNames, ShardingValue<Long> shardingValue) {
		Collection<String> result = new LinkedHashSet<String>(dataBaseNames.size());
		for(Long value : shardingValue.getValues()){
			for(String dataBaseName : dataBaseNames){
				if(dataBaseName.endsWith(value % 2 + "")){
					result.add(dataBaseName);
				}
			}
		}
		return result;
	}

}
