package com.parinherm.entity.xtend;

import com.parinherm.annotations.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Observable
@SuppressWarnings("all")
public class ObservableBean {
  private String firstName;
  
  private String lastName;
  
  public String getFirstName() {
    return this.firstName;
  }
  
  public void setFirstName(final String firstName) {
    String _oldValue = this.firstName;
    this.firstName = firstName;
    _propertyChangeSupport.firePropertyChange("firstName", _oldValue, firstName);
  }
  
  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(final String lastName) {
    String _oldValue = this.lastName;
    this.lastName = lastName;
    _propertyChangeSupport.firePropertyChange("lastName", _oldValue, lastName);
  }
  
  private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
  
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
