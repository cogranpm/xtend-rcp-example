package com.parinherm.parts.xtend;

import com.parinherm.entity.xtend.ObservableBean;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class XSamplePart {
  private TableViewer tableViewer;
  
  @Inject
  private MPart part;
  
  private Text txtInput;
  
  private Button btnTest;
  
  @PostConstruct
  public void createComposite(final Composite parent) {
    GridLayout _gridLayout = new GridLayout(1, false);
    parent.setLayout(_gridLayout);
    Button _button = new Button(parent, SWT.PUSH);
    this.btnTest = _button;
    this.btnTest.setText("Click");
    final Consumer<SelectionEvent> _function = (SelectionEvent e) -> {
      this.txtInput.setText("lambda brah");
    };
    this.btnTest.addSelectionListener(SelectionListener.widgetSelectedAdapter(_function));
    Text _text = new Text(parent, SWT.BORDER);
    this.txtInput = _text;
    this.txtInput.setMessage("Hey, it\'s working");
    final ModifyListener _function_1 = (ModifyEvent e) -> {
      this.part.setDirty(true);
    };
    this.txtInput.addModifyListener(_function_1);
    GridData _gridData = new GridData(GridData.FILL_HORIZONTAL);
    this.txtInput.setLayoutData(_gridData);
    TableViewer _tableViewer = new TableViewer(parent);
    this.tableViewer = _tableViewer;
    this.tableViewer.setContentProvider(ArrayContentProvider.getInstance());
    this.tableViewer.setInput(this.createInitialDataModel());
    Table _table = this.tableViewer.getTable();
    GridData _gridData_1 = new GridData(GridData.FILL_BOTH);
    _table.setLayoutData(_gridData_1);
  }
  
  @Focus
  public void setFocus() {
    this.tableViewer.getTable().setFocus();
  }
  
  @Persist
  public void save() {
    ObservableBean _observableBean = new ObservableBean();
    final Procedure1<ObservableBean> _function = (ObservableBean it) -> {
      final PropertyChangeListener _function_1 = (PropertyChangeEvent it_1) -> {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("property ");
        String _propertyName = it_1.getPropertyName();
        _builder.append(_propertyName);
        _builder.append(" changed from ");
        Object _oldValue = it_1.getOldValue();
        _builder.append(_oldValue);
        _builder.append(" to ");
        Object _newValue = it_1.getNewValue();
        _builder.append(_newValue);
        InputOutput.<String>println(_builder.toString());
      };
      it.addPropertyChangeListener(_function_1);
      it.setFirstName("Max");
      it.setLastName("Mustermann");
      it.setFirstName("John");
      it.setLastName("Doe");
    };
    ObjectExtensions.<ObservableBean>operator_doubleArrow(_observableBean, _function);
    this.part.setDirty(false);
  }
  
  public List<String> createInitialDataModel() {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5"));
  }
}
