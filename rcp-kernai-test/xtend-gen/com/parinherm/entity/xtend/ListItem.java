package com.parinherm.entity.xtend;

import com.parinherm.annotations.Observable;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Observable
@SuppressWarnings("all")
public class ListItem {
  private String description;
  
  private String code;
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(final String description) {
    String _oldValue = this.description;
    this.description = description;
    _propertyChangeSupport.firePropertyChange("description", _oldValue, description);
  }
  
  public String getCode() {
    return this.code;
  }
  
  public void setCode(final String code) {
    String _oldValue = this.code;
    this.code = code;
    _propertyChangeSupport.firePropertyChange("code", _oldValue, code);
  }
  
  private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);
  
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    this._propertyChangeSupport.removePropertyChangeListener(listener);
  }
}
