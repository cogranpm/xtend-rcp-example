package com.parinherm.rcp.xtend;

import javax.inject.Inject;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.e4.ui.workbench.lifecycle.PreSave;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessRemovals;

@SuppressWarnings("restriction")
public class E4LifeCycle {
  @Inject
  private Logger logger;
  
  @PostContextCreate
  public void postContextCreate(final IEclipseContext workbenchContext) {
    this.logger.info("lifecycle.create");
  }
  
  @PreSave
  public void preSave(final IEclipseContext workbenchContext) {
    this.logger.info("lifecycle save");
  }
  
  @ProcessAdditions
  public void processAdditions(final IEclipseContext workbenchContext) {
    this.logger.info("lifecycle additions");
  }
  
  @ProcessRemovals
  public void processRemovals(final IEclipseContext workbenchContext) {
    this.logger.info("lifecycle removals");
  }
}
