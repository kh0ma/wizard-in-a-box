package com.github.kh0ma.tools.dropwizard.box.datasource;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.db.DataSourceFactory;

@JsonTypeName("default")
public class DefaultDataSourceFactory extends DataSourceFactory implements ManagedDataSourceFactory {
}
