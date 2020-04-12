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
import org.eclipse.xtext.xbase.lib.FunctionExtensions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
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
    final Function2<Integer, Integer, Integer> _function = (Integer a, Integer b) -> {
      return Integer.valueOf(((a).intValue() + (b).intValue()));
    };
    final Function2<? super Integer, ? super Integer, ? extends Integer> makeadder = _function;
    final Function1<Integer, Integer> adder2 = FunctionExtensions.<Integer, Integer, Integer>curry(makeadder, Integer.valueOf(2));
    InputOutput.<Integer>println(adder2.apply(Integer.valueOf(2)));
    ObservableBean _observableBean = new ObservableBean();
    final Procedure1<ObservableBean> _function_1 = (ObservableBean it) -> {
      final PropertyChangeListener _function_2 = (PropertyChangeEvent it_1) -> {
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
      it.addPropertyChangeListener(_function_2);
      it.setFirstName("Max");
      it.setLastName("Mustermann");
      it.setFirstName("John");
      it.setLastName("Doe");
    };
    ObjectExtensions.<ObservableBean>operator_doubleArrow(_observableBean, _function_1);
    ObservableBean _observableBean_1 = new ObservableBean();
    final Procedure1<ObservableBean> _function_2 = (ObservableBean it) -> {
      it.setFirstName("paul");
      it.setLastName("mustertom");
    };
    final ObservableBean person = ObjectExtensions.<ObservableBean>operator_doubleArrow(_observableBean_1, _function_2);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Well Hello there person of name: ");
    String _firstName = person.getFirstName();
    _builder.append(_firstName);
    _builder.append(" ");
    String _lastName = person.getLastName();
    _builder.append(_lastName);
    _builder.newLineIfNotEmpty();
    final String tmpstring = _builder.toString();
    InputOutput.<String>println(tmpstring);
    GridLayout _gridLayout = new GridLayout(1, false);
    parent.setLayout(_gridLayout);
    Button _button = new Button(parent, SWT.PUSH);
    this.btnTest = _button;
    this.btnTest.setText("Click");
    final Consumer<SelectionEvent> _function_3 = (SelectionEvent e) -> {
      this.txtInput.setText("lambda brah");
    };
    this.btnTest.addSelectionListener(SelectionListener.widgetSelectedAdapter(_function_3));
    Text _text = new Text(parent, SWT.BORDER);
    this.txtInput = _text;
    this.txtInput.setMessage("Hey, it\'s working");
    final ModifyListener _function_4 = (ModifyEvent e) -> {
      this.part.setDirty(true);
    };
    this.txtInput.addModifyListener(_function_4);
    GridData _gridData = new GridData(GridData.FILL_HORIZONTAL);
    this.txtInput.setLayoutData(_gridData);
    TableViewer _tableViewer = new TableViewer(parent);
    this.tableViewer = _tableViewer;
    this.tableViewer.setContentProvider(ArrayContentProvider.getInstance());
    final List<String> data = this.createInitialDataModel();
    final Function1<String, String> _function_5 = (String item) -> {
      return item.toUpperCase();
    };
    final List<String> udata = ListExtensions.<String, String>map(data, _function_5);
    InputOutput.<String>println(IterableExtensions.<String>head(udata));
    InputOutput.<Iterable<String>>println(IterableExtensions.<String>tail(udata));
    InputOutput.<Boolean>println(Boolean.valueOf(udata.isEmpty()));
    final List<String> sortedudata = IterableExtensions.<String>sort(udata);
    this.tableViewer.setInput(sortedudata);
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
    this.part.setDirty(false);
  }
  
  public List<String> createInitialDataModel() {
    return Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("hawthorn", "eagles", "port power", "adelaide crows", "dockers"));
  }
}
