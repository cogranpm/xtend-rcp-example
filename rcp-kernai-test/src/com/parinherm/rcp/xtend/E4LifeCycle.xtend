package com.parinherm.rcp.xtend

import javax.inject.Inject
import org.eclipse.e4.core.contexts.IEclipseContext
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate
import org.eclipse.e4.ui.workbench.lifecycle.PreSave
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals
import org.eclipse.e4.core.services.log.Logger


@SuppressWarnings("restriction")
class E4LifeCycle {
	
	@Inject
	var Logger logger
	
	@PostContextCreate
	def postContextCreate(IEclipseContext workbenchContext) {
		logger.info("lifecycle.create")
	//cancel
	}
	
	

	@PreSave
	def preSave(IEclipseContext workbenchContext) {
		logger.info("lifecycle save")
	}

	@ProcessAdditions
	def processAdditions(IEclipseContext workbenchContext) {
		logger.info("lifecycle additions")
	}

	@ProcessRemovals
	def processRemovals(IEclipseContext workbenchContext) {
		logger.info("lifecycle removals")
	}
}