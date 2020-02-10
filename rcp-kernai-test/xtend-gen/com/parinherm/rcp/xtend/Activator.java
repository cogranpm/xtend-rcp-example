package com.parinherm.rcp.xtend;

import org.eclipse.xtext.xbase.lib.InputOutput;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

@SuppressWarnings("all")
public class Activator implements BundleActivator {
  @Override
  public void start(final BundleContext bundleCtx) throws Exception {
  }
  
  @Override
  public void stop(final BundleContext context) throws Exception {
    InputOutput.<String>println("bundle stopped");
  }
}
