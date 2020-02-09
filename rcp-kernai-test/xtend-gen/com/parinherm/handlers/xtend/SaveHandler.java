package com.parinherm.handlers.xtend;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

@SuppressWarnings("all")
public class SaveHandler {
  @CanExecute
  public boolean canExecute(final EPartService partService) {
    boolean _xifexpression = false;
    if ((partService == null)) {
      _xifexpression = false;
    } else {
      boolean _isEmpty = partService.getDirtyParts().isEmpty();
      _xifexpression = (!_isEmpty);
    }
    return _xifexpression;
  }
  
  @Execute
  public void execute(final EPartService partService) {
    partService.saveAll(false);
  }
}
