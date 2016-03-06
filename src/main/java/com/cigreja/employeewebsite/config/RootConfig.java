
package com.cigreja.employeewebsite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * RootConfig
 * @author Carlos Igreja
 * @since  Feb 21, 2016
 */
@Configuration
@Import(DataConfigJPA.class)
@ComponentScan("com.cigreja.employeewebsite.doa.jpa")
public class RootConfig {

}
