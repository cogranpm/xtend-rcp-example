package com.parinherm.rcp.xtend

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import org.eclipse.core.runtime.FileLocator
import org.eclipse.core.runtime.Path
import org.osgi.framework.BundleActivator
import org.osgi.framework.BundleContext
import org.slf4j.LoggerFactory

class Activator implements BundleActivator {
	
	override start(BundleContext bundleCtx) throws Exception {
		
		// this stuff is not working, the cast fails
		/*
		val ctx = LoggerFactory.getILoggerFactory() as LoggerContext
		var JoranConfigurator x = new JoranConfigurator => [
			context = ctx
		]
		ctx.reset()
		val logbackConfigFileUrl = FileLocator.find(bundleCtx.bundle, new Path("logback.xml"), null)
		x.doConfigure(logbackConfigFileUrl.openStream())
		* 
		*/
		 
		
	}
	
	override stop(BundleContext context) throws Exception {
		println("bundle stopped")
	}
	
}