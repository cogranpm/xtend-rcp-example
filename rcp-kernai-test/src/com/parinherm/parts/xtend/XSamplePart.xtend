package com.parinherm.parts.xtend

import java.util.Arrays
import java.util.List
import javax.annotation.PostConstruct
import javax.inject.Inject
import org.eclipse.e4.ui.di.Focus
import org.eclipse.e4.ui.di.Persist
import org.eclipse.e4.ui.model.application.ui.basic.MPart
import org.eclipse.jface.viewers.ArrayContentProvider
import org.eclipse.jface.viewers.TableViewer
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Text

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter
import com.parinherm.entity.xtend.ObservableBean

class XSamplePart {

	TableViewer tableViewer;

	@Inject
	MPart part;
	
	Text txtInput
	Button btnTest

	@PostConstruct
	def void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false))

		btnTest = new Button(parent, SWT.PUSH)
		btnTest.text = "Click"

		//lambda style event handler, thanks to static widgetSelectedAdapter method
		btnTest.addSelectionListener(widgetSelectedAdapter([ e | 
			txtInput.text = "lambda brah"
		]))
		
		txtInput = new Text(parent, SWT.BORDER)
		txtInput.message = "Hey, it's working"
		//lamba for this is cool
		txtInput.addModifyListener([e | part.setDirty(true)])
		txtInput.layoutData = new GridData(GridData.FILL_HORIZONTAL)

		tableViewer = new TableViewer(parent)
		tableViewer.contentProvider = ArrayContentProvider.getInstance()
		tableViewer.input = createInitialDataModel()
		tableViewer.table.layoutData = new GridData(GridData.FILL_BOTH)
	}
	

	@Focus
	def void setFocus() {
		tableViewer.table.setFocus()
	}

	@Persist
	def void save() {
		
		//lets try the observable now
		
		new ObservableBean => [
			
			// 2. add an observer 
			addPropertyChangeListener [
				println('''property «propertyName» changed from «oldValue» to «newValue»''')
			]
			
			// 3. invoke some setters
			firstName = "Max"
			lastName = "Mustermann"
			
			firstName = "John"
			lastName = "Doe"
		]
		
		
		part.dirty = false
	}
	
	def List<String> createInitialDataModel() {
		//using a list literal
		return #["Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5"]

	}
	
}