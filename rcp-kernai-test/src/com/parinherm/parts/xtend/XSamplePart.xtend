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
		
		//testing out some xtend stuff
		//define a function object 
		//first line defines a function object which can be curried
		val (int, int)=>int makeadder = [a, b | a + b]
		val adder2 = makeadder.curry(2)
		println(adder2.apply(2))
		
		//ObservableBean is a class in entity package in this project
		//shows how to do a simple bindable entity, property notification is built in
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
		
		val person = new ObservableBean => [
			firstName = "paul"
			lastName = "mustertom"
		]
		
		//template strings, to get the guillements just type the completion keys M - /
		val tmpstring = '''
		Well Hello there person of name: «person.firstName» «person.lastName»
		'''
		println(tmpstring)
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
		val data = createInitialDataModel()
		//map is a library function in xtend that provides an extension to list
		val udata = data.map[ String item | item.toUpperCase]
		//functional style
		println(udata.head)
		println(udata.tail)
		println(udata.empty)
		val sortedudata = udata.sort
		tableViewer.input = sortedudata
		tableViewer.table.layoutData = new GridData(GridData.FILL_BOTH)
	}
	

	@Focus
	def void setFocus() {
		tableViewer.table.setFocus()
	}

	@Persist
	def void save() {
		
		
		
		
		part.dirty = false
	}
	
	def List<String> createInitialDataModel() {
		//using a list literal
		return #["hawthorn", "eagles", "port power", "adelaide crows", "dockers"]

	}
	
	
	
	
}