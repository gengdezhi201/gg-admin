package com.gg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {
    /**
     * 拦截忽略注解 @InterceptorIgnore
     * 属性名	类型	默认值	描述
     * tenantLine	String	""	行级租户
     * dynamicTableName	String	""	动态表名
     * blockAttack	String	""	攻击 SQL 阻断解析器,防止全表更新与删除
     * illegalSql	String	""	垃圾SQL拦截
     * 该注解作用于 xxMapper.java 方法之上 各属性代表对应的插件 各属性不给值则默认为false 设置为 true 忽略拦截
     */

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     * overflow	boolean	false	溢出总页数后是否进行处理(默认不处理,参见 插件#continuePage 方法)
     * maxLimit	Long		单页分页条数限制(默认无限制,参见 插件#handlerLimit 方法)
     * dbType	DbType		数据库类型(根据类型获取应使用的分页方言,参见 插件#findIDialect 方法)
     * dialect	IDialect		方言实现类(参见 插件#findIDialect 方法)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

    /**
     * 乐观锁插件
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 防止全表更新与删除插件
     * 针对 update 和 delete 语句
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }
}