package org.dwcj.kitchen.fileupload;

import com.basis.bbj.proxies.sysgui.BBjFileChooser;
import com.basis.bbj.proxies.sysgui.BBjWindow;
import com.basis.startup.type.BBjException;
import org.dwcj.Environment;
import org.dwcj.bridge.WindowAccessor;
import org.dwcj.component.AbstractDwcComponent;
import org.dwcj.component.event.EventDispatcher;
import org.dwcj.component.event.EventListener;
import org.dwcj.component.event.sink.EventSinkListenerRegistry;
import org.dwcj.component.window.AbstractWindow;
import org.dwcj.concern.HasEnable;
import org.dwcj.kitchen.fileupload.event.FileUploadEvent;
import org.dwcj.kitchen.fileupload.sink.FileUploadEventSink;

public final class FileUpload extends AbstractDwcComponent implements HasEnable {

  public enum Theme {
    DEFAULT, DANGER, GRAY, INFO, PRIMARY, SUCCESS, WARNING, OUTLINED_DANGER, OUTLINED_DEFAULT, OUTLINED_GRAY, OUTLINED_INFO, OUTLINED_SUCCESS, OUTLINED_PRIMARY, OUTLINED_WARNING
  }

  boolean fAllowMulti = false;

  private EventDispatcher dispatcher = new EventDispatcher();
  private EventSinkListenerRegistry<FileUploadEvent> uploadEventSinkListenerRegistry =
      new EventSinkListenerRegistry<>(new FileUploadEventSink(this, dispatcher),
          FileUploadEvent.class);

  @Override
  protected void create(AbstractWindow p) {

    try {
      BBjWindow w = WindowAccessor.getDefault().getBBjWindow(p);

      byte[] flags = new byte[] {(byte) 0x00, (byte) 0x06};
      control = w.addFileChooser("", flags);
      catchUp();
    } catch (Exception e) {
      Environment.logError(e);
    }
  }

  @Override
  public FileUpload setText(String text) {
    super.setText(text);
    return this;
  }

  @Override
  public FileUpload setVisible(Boolean visible) {
    super.setVisible(visible);
    return this;
  }

  @Override
  public FileUpload setEnabled(boolean enabled) {
    super.setComponentEnabled(enabled);
    return this;
  }

  @Override
  public boolean isEnabled() {
    return super.isComponentEnabled();
  }

  @Override
  public FileUpload setTooltipText(String text) {
    super.setTooltipText(text);
    return this;
  }

  @Override
  public FileUpload setAttribute(String attribute, String value) {
    super.setAttribute(attribute, value);
    return this;
  }

  @Override
  public FileUpload setStyle(String property, String value) {
    super.setStyle(property, value);
    return this;
  }

  @Override
  public FileUpload addClassName(String selector) {
    super.addClassName(selector);
    return this;
  }

  @Override
  public FileUpload removeClassName(String selector) {
    super.removeClassName(selector);
    return this;
  }

  public FileUpload setTheme(FileUpload.Theme theme) {
    super.setControlTheme(theme);
    return this;
  }

  public FileUpload setMultipleFilesAllowed(boolean fAllowMulti) {
    this.fAllowMulti = fAllowMulti;
    if (control != null) {
      try {
        ((BBjFileChooser) control).setMultiSelectionEnabled(fAllowMulti);
      } catch (BBjException e) {
        throw new RuntimeException(e);
      }
    }
    return this;
  }

  @Override
  protected void catchUp() throws IllegalAccessException {
    super.catchUp();
    setMultipleFilesAllowed(this.fAllowMulti);
  }


  /**
   * Add a {@link FileUploadEvent} listener for the component.
   *
   * @param listener the event listener to be added
   * @return The component itself
   */
  public FileUpload addFileUploadListener(EventListener<FileUploadEvent> listener) {
    this.uploadEventSinkListenerRegistry.addEventListener(listener);

    return this;
  }

  /**
   * Alias for {@link #addFileUploadListener(EventListener) addButtonClickListener}.
   *
   * @param listener the event listener to be added
   * @return The component itself
   *
   */
  public FileUpload onFileUpload(EventListener<FileUploadEvent> listener) {
    return addFileUploadListener(listener);
  }

  /**
   * Removes a {@link FileUploadEvent} listener from the component.
   *
   * @param listener the event listener to be removed
   * @return The component itself
   */
  public FileUpload removeClickListener(EventListener<FileUploadEvent> listener) {
    this.uploadEventSinkListenerRegistry.removeEventListener(listener);

    return this;
  }

}
