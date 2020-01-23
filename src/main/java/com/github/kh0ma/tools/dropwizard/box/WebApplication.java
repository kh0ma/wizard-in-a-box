package com.github.kh0ma.tools.dropwizard.box;

import com.github.kh0ma.tools.dropwizard.box.config.BridgedConfigurationFactoryFactory;
import com.github.kh0ma.tools.dropwizard.box.config.ClasspathConfigurationSourceProvider;
import com.github.kh0ma.tools.dropwizard.box.config.ConfigurationBridge;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.CheckCommand;
import io.dropwizard.cli.Cli;
import io.dropwizard.cli.ServerCommand;
import io.dropwizard.lifecycle.setup.LifecycleEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.util.JarLocation;
import org.eclipse.jetty.util.component.LifeCycle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * A WebApplication decorates a (DropWizard) Application (GoF)
 *
 * @param <C> Dropwizard Configuration class
 */
public abstract class WebApplication<C extends Configuration> extends Application<C> implements ServletContextListener {
  private static ServletContext theServletContext;
  private final Application<C> dropwizardApplication;
  private final String[] args;
  private Environment dropwizardEnvironment;
  private ConfigurationBridge<C> configurationBridge;

  public static ServletContext servletContext() {
    return theServletContext;
  }

  public WebApplication(Application<C> dropwizardApplication, String configurationFileLocation) {
    this(dropwizardApplication, new String[]{"server", configurationFileLocation});
  }

  public WebApplication(Application<C> dropwizardApplication, String[] args) {
    this.dropwizardApplication = dropwizardApplication;
    this.args = args;
  }

  public void setConfigurationBridge(ConfigurationBridge<C> configurationBridge) {
    this.configurationBridge = configurationBridge;
  }

  public ConfigurationBridge getConfigurationBridge() {
    return configurationBridge;
  }

  @Override
  public void initialize(Bootstrap<C> bootstrap) {
    if (configurationBridge != null) {
      bootstrap.setConfigurationFactoryFactory(new BridgedConfigurationFactoryFactory<>(configurationBridge));
    }
    // Swaps the default FileConfigurationSourceProvider
    bootstrap.setConfigurationSourceProvider(new ClasspathConfigurationSourceProvider());
    dropwizardApplication.initialize(bootstrap);
  }

  @Override
  public String getName() {
    return dropwizardApplication.getName() + "-war";
  }

  @Override
  public void run(C configuration,
                  Environment environment) throws Exception {
    dropwizardEnvironment = environment;
    dropwizardApplication.run(configuration, environment);
  }

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    if (theServletContext != null) {
      throw new IllegalStateException("Multiple WebListeners extending WebApplication detected. Only one is allowed!");
    }
    theServletContext = sce.getServletContext();
    try {
      final Bootstrap<C> bootstrap = new Bootstrap<>(this);
      bootstrap.addCommand(new ServerCommand<>(this));
      bootstrap.addCommand(new CheckCommand<>(this));
      initialize(bootstrap);
      // Should by called after initialize to give an opportunity to set a custom metric registry
      bootstrap.registerMetrics();

      final Cli cli = new Cli(new JarLocation(getClass()), bootstrap, System.out, System.err);
      if (!cli.run(args)) {
        throw new RuntimeException("Initialization of Dropwizard failed ...");
      }
    } catch (Exception e) {
      throw new RuntimeException("Initialization of Dropwizard failed ...", e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    if (dropwizardEnvironment != null) {
      LifecycleEnvironment lifecycle = dropwizardEnvironment.lifecycle();
      if (lifecycle != null) {
        for (LifeCycle managed : lifecycle.getManagedObjects()) {
          try {
            managed.stop();
          } catch (Exception e) {
            throw new RuntimeException("Shutdown of Dropwizard failed ...", e);
          }
        }
      }
      dropwizardEnvironment = null;
    }
    theServletContext = null;
  }
}