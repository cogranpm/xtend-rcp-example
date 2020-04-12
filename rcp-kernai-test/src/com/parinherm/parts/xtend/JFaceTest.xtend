package com.parinherm.parts.xtend

import javax.annotation.PostConstruct
import javax.inject.Inject
import org.eclipse.e4.ui.di.Focus
import org.eclipse.e4.ui.di.Persist
import org.eclipse.e4.ui.model.application.ui.basic.MPart
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Text
import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

class JFaceTest {

	@Inject
	MPart part;
	
	Text txtInput
	Button btnTest
		
	@PostConstruct
	def void createComposite(Composite parent) {
 		btnTest = new Button(parent, SWT.PUSH)
		btnTest.text = "Click"
		
		txtInput = new Text(parent, SWT.BORDER)
		txtInput.message = "Hey, it's working"
		//lamba for this is cool
		txtInput.addModifyListener([e | part.setDirty(true)])
		txtInput.layoutData = new GridData(GridData.FILL_HORIZONTAL)

		//lambda style event handler, thanks to static widgetSelectedAdapter method
		btnTest.addSelectionListener(widgetSelectedAdapter([ e | 
			txtInput.text = "lambda brah"
		]))
		
	
 
 	}
 	
 	@Focus
	def void setFocus() {
		//tableViewer.table.setFocus()
	}

	@Persist
	def void save() {
		part.dirty = false
	}
 	
 
}