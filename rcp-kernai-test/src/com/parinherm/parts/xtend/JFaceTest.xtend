package com.parinherm.parts.xtend

import com.parinherm.entity.xtend.Person
import javax.annotation.PostConstruct
import javax.inject.Inject
import org.eclipse.core.databinding.AggregateValidationStatus
import org.eclipse.core.databinding.Binding
import org.eclipse.core.databinding.DataBindingContext
import org.eclipse.core.databinding.beans.typed.BeanProperties
import org.eclipse.core.databinding.observable.IChangeListener
import org.eclipse.core.databinding.observable.list.WritableList
import org.eclipse.core.databinding.observable.value.WritableValue
import org.eclipse.e4.ui.di.Focus
import org.eclipse.e4.ui.di.Persist
import org.eclipse.e4.ui.model.application.ui.basic.MPart
import org.eclipse.jface.databinding.swt.typed.WidgetProperties
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.swt.SWT
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.DateTime
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Spinner
import org.eclipse.swt.widgets.Text

import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

class JFaceTest {

	@Inject
	MPart part;
	
	Text txtFirstName
	Text txtLastName
	Label lblAmount
	Spinner spinAmount
	Label lblCreatedDate
	DateTime dteCreatedDate
	Label lblCreatedTime
	DateTime dteCreatedTime
	Label lblIsMember
	Button btnIsMember
	Button btnTest
	Label lblError
	
	DataBindingContext ctx = new DataBindingContext()
	WritableList<Person> input
	WritableValue<Person> value = new WritableValue()
	ObservableListContentProvider contentProvider = new ObservableListContentProvider()		
	Person person = new Person()
	boolean pauseDirtyListener = false
		
		
	IChangeListener listener = [
		if (!pauseDirtyListener){
			part.dirty = true
		}
		
	]		
		
	@PostConstruct
	def void createComposite(Composite parent) {
 		
 		person.firstName = "michael"
 		person.lastName = "tuck"
 		person.amount = 100
 		
 		lblError = new Label(parent, SWT.BORDER)
 		lblError.text = "Errors:"
 		
 		btnTest = new Button(parent, SWT.PUSH)
		btnTest.text = "Click"
		
		txtFirstName = new Text(parent, SWT.BORDER)
		txtLastName = new Text(parent, SWT.BORDER)
		
		spinAmount = new Spinner(parent, SWT.NONE)
		spinAmount.minimum = Integer.MIN_VALUE
		spinAmount.maximum = Integer.MAX_VALUE


		
		GridDataFactory.fillDefaults().grab(true, false).applyTo(lblError)
		txtFirstName.layoutData = new GridData(GridData.FILL_HORIZONTAL)
		txtLastName.layoutData = new GridData(GridData.FILL_HORIZONTAL)
		GridDataFactory.fillDefaults().grab(true, false).applyTo(spinAmount)
		

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
		
		val amountOb = WidgetProperties.spinnerSelection().observe(spinAmount)
		val amountMod = BeanProperties.value("amount").observeDetail(value)
		val amountBinding = ctx.bindValue(amountOb, amountMod)
		
		val errorOb = WidgetProperties.text().observe(lblError)
		val aggValStatus = new AggregateValidationStatus(ctx.bindings, AggregateValidationStatus.MAX_SEVERITY)
		
		val ob = ctx.validationStatusProviders
		ob.forEach[ element | 
			val b = element as Binding
			b.target.addChangeListener(listener)
		]
		
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