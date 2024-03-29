package com.parinherm.parts.xtend;

import com.parinherm.entity.xtend.Person;
import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.typed.BeanProperties;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class JFaceTest {
  @Inject
  private MPart part;
  
  private Text txtFirstName;
  
  private Text txtLastName;
  
  private Label lblAmount;
  
  private Spinner spinAmount;
  
  private Label lblCreatedDate;
  
  private DateTime dteCreatedDate;
  
  private Label lblCreatedTime;
  
  private DateTime dteCreatedTime;
  
  private Label lblIsMember;
  
  private Button btnIsMember;
  
  private Button btnTest;
  
  private Label lblError;
  
  private DataBindingContext ctx = new DataBindingContext();
  
  private WritableList<Person> input;
  
  private WritableValue<Person> value = new WritableValue<Person>();
  
  private ObservableListContentProvider contentProvider = new ObservableListContentProvider<Object>();
  
  private Person person = new Person();
  
  private boolean pauseDirtyListener = false;
  
  private IChangeListener listener = ((IChangeListener) (ChangeEvent it) -> {
    if ((!this.pauseDirtyListener)) {
      this.part.setDirty(true);
    }
  });
  
  @PostConstruct
  public void createComposite(final Composite parent) {
    this.person.setFirstName("michael");
    this.person.setLastName("tuck");
    this.person.setAmount(100);
    Label _label = new Label(parent, SWT.BORDER);
    this.lblError = _label;
    this.lblError.setText("Errors:");
    Button _button = new Button(parent, SWT.PUSH);
    this.btnTest = _button;
    this.btnTest.setText("Click");
    Text _text = new Text(parent, SWT.BORDER);
    this.txtFirstName = _text;
    Text _text_1 = new Text(parent, SWT.BORDER);
    this.txtLastName = _text_1;
    Spinner _spinner = new Spinner(parent, SWT.NONE);
    this.spinAmount = _spinner;
    this.spinAmount.setMinimum(Integer.MIN_VALUE);
    this.spinAmount.setMaximum(Integer.MAX_VALUE);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(this.lblError);
    GridData _gridData = new GridData(GridData.FILL_HORIZONTAL);
    this.txtFirstName.setLayoutData(_gridData);
    GridData _gridData_1 = new GridData(GridData.FILL_HORIZONTAL);
    this.txtLastName.setLayoutData(_gridData_1);
    GridDataFactory.fillDefaults().grab(true, false).applyTo(this.spinAmount);
    final Consumer<SelectionEvent> _function = (SelectionEvent e) -> {
      String _lastName = this.person.getLastName();
      String _firstName = this.person.getFirstName();
      String _plus = (_lastName + _firstName);
      InputOutput.<String>println(_plus);
    };
    this.btnTest.addSelectionListener(SelectionListener.widgetSelectedAdapter(_function));
    this.ctx.dispose();
    final IObservableList<ValidationStatusProvider> bindings = this.ctx.getValidationStatusProviders();
    final Consumer<ValidationStatusProvider> _function_1 = (ValidationStatusProvider element) -> {
      this.ctx.removeBinding(((Binding) element));
    };
    bindings.forEach(_function_1);
    final ISWTObservableValue<String> firstNameOb = WidgetProperties.<Text>text(SWT.Modify).observe(this.txtFirstName);
    final IObservableValue<Object> firstNameMod = BeanProperties.<Person, Object>value("firstName").<Person>observeDetail(this.value);
    final Binding firstNameBinding = this.ctx.<String, Object>bindValue(firstNameOb, firstNameMod);
    final ISWTObservableValue<String> lastNameOb = WidgetProperties.<Text>text(SWT.Modify).observe(this.txtLastName);
    final IObservableValue<Object> lastNameMod = BeanProperties.<Person, Object>value("lastName").<Person>observeDetail(this.value);
    final Binding lastNameBinding = this.ctx.<String, Object>bindValue(lastNameOb, lastNameMod);
    final ISWTObservableValue<Integer> amountOb = WidgetProperties.spinnerSelection().observe(this.spinAmount);
    final IObservableValue<Object> amountMod = BeanProperties.<Person, Object>value("amount").<Person>observeDetail(this.value);
    final Binding amountBinding = this.ctx.<Integer, Object>bindValue(amountOb, amountMod);
    final ISWTObservableValue<String> errorOb = WidgetProperties.<Label>text().observe(this.lblError);
    IObservableList<Binding> _bindings = this.ctx.getBindings();
    final AggregateValidationStatus aggValStatus = new AggregateValidationStatus(_bindings, AggregateValidationStatus.MAX_SEVERITY);
    final IObservableList<ValidationStatusProvider> ob = this.ctx.getValidationStatusProviders();
    final Consumer<ValidationStatusProvider> _function_2 = (ValidationStatusProvider element) -> {
      final Binding b = ((Binding) element);
      b.getTarget().addChangeListener(this.listener);
    };
    ob.forEach(_function_2);
    this.value.setValue(this.person);
  }
  
  @Focus
  public void setFocus() {
  }
  
  @Persist
  public void save() {
    this.part.setDirty(false);
  }
}
