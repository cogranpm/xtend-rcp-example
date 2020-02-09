package com.parinherm.handlers.xtend

import org.eclipse.e4.core.di.annotations.CanExecute
import org.eclipse.e4.core.di.annotations.Execute
import org.eclipse.e4.ui.workbench.modeling.EPartService

class SaveHandler {
	
	@CanExecute
	def boolean canExecute(EPartService partService) {
		//ternary operator, and === to compare with null, as it must be an identity compare, not value
		return partService === null ? false : !partService.getDirtyParts().isEmpty()
	}

	@Execute
	def void execute(EPartService partService) {
		partService.saveAll(false)
	}
	
}