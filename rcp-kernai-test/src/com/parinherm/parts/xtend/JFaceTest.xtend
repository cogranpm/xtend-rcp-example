package com.parinherm.parts.xtend

import com.parinherm.entity.xtend.Person
import javax.annotation.PostConstruct
import javax.inject.Inject
import org.eclipse.core.databinding.Binding
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.observable.value.WritableValue
import org.eclipse.e4.ui.di.Focus
import org.eclipse.e4.ui.di.Persist
import org.eclipse.e4.ui.model.application.ui.basic.MPart
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Text

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

class JFaceTest {

	@Inject
	MPart part;
	
	Text txtFirstName
	Text txtLastName
	Button btnTest
	DataBindingContext ctx = new DataBindingContext()
	WritableList<Person> input
	WritableValue<Person> value = new WritableValue()
	ObservableListContentProvider contentProvider = new ObservableListContentProvider()		
	Person person = new Person()
		
		
	@PostConstruct
	def void createComposite(Composite parent) {
 		
 		person.firstName = "michael"
 		person.lastName = "tuck"
 		
 		btnTest = new Button(parent, SWT.PUSH)
		btnTest.text = "Click"
		
		txtFirstName = new Text(parent, SWT.BORDER)
		txtLastName = new Text(parent, SWT.BORDER)

		//lamba for this is cool

		txtFirstName.layoutData = new GridData(GridData.FILL_HORIZONTAL)
		txtLastName.layoutData = new GridData(GridData.FILL_HORIZONTAL)
		

		//lambda style event handler, thanks to static widgetSelectedAdapter method
		btnTest.addSelectionListener(widgetSelectedAdapter([ e | 
			println(person.lastName + person.firstName)
		]))
		
	
		//data bindings
		//move this to the list selection event
		ctx.dispose()
		val bindings = ctx.validationStatusProviders
		bindings.forEach[element | 
			ctx.removeBinding(element as Binding)
		]
		val firstNameOb = WidgetProperties.text(SWT.Modify).observe(txtFirstName)
		val firstNameMod = BeanProperties.value("firstName").observeDetail(value)
		val firstNameBinding = ctx.bindValue(firstNameOb, firstNameMod)

		val lastNameOb = WidgetProperties.text(SWT.Modify).observe(txtLastName)
		val lastNameMod = BeanProperties.value("lastName").observeDetail(value)
		val lastNameBinding = ctx.bindValue(lastNameOb, lastNameMod)
		
		value.value = person
		
 
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