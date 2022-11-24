package com.qacg.dealers.api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

/**
 * @author Edoardo Cruz
 * @version 1.0.0
 * @since 08.08.2022
 */
@Configuration(proxyBeanMethods = false)
@Role(value = BeanDefinition.ROLE_INFRASTRUCTURE)
public class DozerConfig {

	@Bean
	public Mapper dozerMapper() {
		List<String> mappingList = new ArrayList<>();
		mappingList.add("dozer/mapping.xml");
		return DozerBeanMapperBuilder.create().withMappingFiles(mappingList).build();
	}

}
