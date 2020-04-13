package com.parinherm.entity.xtend;

import com.parinherm.annotations.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.LocalTime;

@Observable
@SuppressWarnings("all")
public class Person {
  private String firstName;
  
  private String lastName;
  
  private int amount;
  
  private String state;
  
  private LocalDate createdDate;
  
  private LocalTime createdDateTime;
  
  private boolean isMember;
  
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
  
  public int getAmount() {
    return this.amount;
  }
  
  public void setAmount(final int amount) {
    int _oldValue = this.amount;
    this.amount = amount;
    _propertyChangeSupport.firePropertyChange("amount", _oldValue, amount);
  }
  
  public String getState() {
    return this.state;
  }
  
  public void setState(final String state) {
    String _oldValue = this.state;
    this.state = state;
    _propertyChangeSupport.firePropertyChange("state", _oldValue, state);
  }
  
  public LocalDate getCreatedDate() {
    return this.createdDate;
  }
  
  public void setCreatedDate(final LocalDate createdDate) {
    LocalDate _oldValue = this.createdDate;
    this.createdDate = createdDate;
    _propertyChangeSupport.firePropertyChange("createdDate", _oldValue, createdDate);
  }
  
  public LocalTime getCreatedDateTime() {
    return this.createdDateTime;
  }
  
  public void setCreatedDateTime(final LocalTime createdDateTime) {
    LocalTime _oldValue = this.createdDateTime;
    this.createdDateTime = createdDateTime;
    _propertyChangeSupport.firePropertyChange("createdDateTime", _oldValue, createdDateTime);
  }
  
  public boolean getIsMember() {
    return this.isMember;
  }
  
  public void setIsMember(final boolean isMember) {
    boolean _oldValue = this.isMember;
    this.isMember = isMember;
    _propertyChangeSupport.firePropertyChange("isMember", _oldValue, isMember);
  }
  
  private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
  
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
