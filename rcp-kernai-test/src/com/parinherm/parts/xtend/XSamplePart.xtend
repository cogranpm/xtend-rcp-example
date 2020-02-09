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
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Text
import org.eclipse.swt.events.SelectionListener
import static org.eclipse.swt.events.SelectionListener.widgetSelectedAdapter

class XSamplePart {

	TableViewer tableViewer;

	@Inject
	MPart part;
	
	Text txtInput
	Button btnTest

	@PostConstruct
	def void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		btnTest = new Button(parent, SWT.PUSH)
		btnTest.text = "Click"
		btnTest.addSelectionListener(widgetSelectedAdapter([ e | 
			txtInput.text = "lambda brah"
		]))
		
		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Hey, it's working")
		txtInput.addModifyListener([e | part.setDirty(true)]);
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setInput(createInitialDataModel());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	

	@Focus
	def void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	def void save() {
		part.setDirty(false);
	}
	
	def List<String> createInitialDataModel() {
		return Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
	}
	
}