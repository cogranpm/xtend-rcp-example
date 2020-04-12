package com.parinherm.parts.xtend;

import java.util.function.Consumer;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

@SuppressWarnings("all")
public class JFaceTest {
  @Inject
  private MPart part;
  
  private Text txtInput;
  
  private Button btnTest;
  
  @PostConstruct
  public void createComposite(final Composite parent) {
    Button _button = new Button(parent, SWT.PUSH);
    this.btnTest = _button;
    this.btnTest.setText("Click");
    Text _text = new Text(parent, SWT.BORDER);
    this.txtInput = _text;
    this.txtInput.setMessage("Hey, it\'s working");
    final ModifyListener _function = (ModifyEvent e) -> {
      this.part.setDirty(true);
    };
    this.txtInput.addModifyListener(_function);
    GridData _gridData = new GridData(GridData.FILL_HORIZONTAL);
    this.txtInput.setLayoutData(_gridData);
    final Consumer<SelectionEvent> _function_1 = (SelectionEvent e) -> {
      this.txtInput.setText("lambda brah");
    };
    this.btnTest.addSelectionListener(SelectionListener.widgetSelectedAdapter(_function_1));
  }
  
  @Focus
  public void setFocus() {
  }
  
  @Persist
  public void save() {
    this.part.setDirty(false);
  }
}
