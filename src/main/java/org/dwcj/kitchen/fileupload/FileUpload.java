package org.dwcj.kitchen.fileupload;

import com.basis.bbj.proxies.sysgui.BBjFileChooser;
import com.basis.bbj.proxies.sysgui.BBjWindow;
import com.basis.startup.type.BBjException;
import org.dwcj.Environment;
import org.dwcj.bridge.WindowAccessor;
import org.dwcj.component.DwcComponent;
import org.dwcj.component.event.EventSinkListenerRegistry;
import org.dwcj.component.window.Window;
import org.dwcj.concern.HasVisibility;
import org.dwcj.dispatcher.EventDispatcher;
import org.dwcj.dispatcher.EventListener;
import org.dwcj.dispatcher.ListenerRegistration;
import org.dwcj.kitchen.fileupload.event.FileUploadEvent;
import org.dwcj.kitchen.fileupload.sink.FileUploadEventSink;

public final class FileUpload extends DwcComponent {

  private BBjFileChooser control;
  private final EventSinkListenerRegistry<FileUploadEvent> fileUploadEventSinkListenerRegistry =
      new EventSinkListenerRegistry<>(new FileUploadEventSink(this, getEventDispatcher()),
          FileUploadEvent.class);

  public enum Theme {
    DEFAULT, DANGER, GRAY, INFO, PRIMARY, SUCCESS, WARNING, OUTLINED_DANGER, OUTLINED_DEFAULT, OUTLINED_GRAY, OUTLINED_INFO, OUTLINED_SUCCESS, OUTLINED_PRIMARY, OUTLINED_WARNING
  }

  boolean fAllowMulti = false;

  private EventDispatcher dispatcher = new EventDispatcher();

  @Override
  protected void onCreate(Window p) {

    try {
      BBjWindow w = WindowAccessor.getDefault().getBBjWindow(p);

      byte[] flags = new byte[] {(byte) 0x00, (byte) 0x06};
      control = w.addFileChooser("", flags);
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
    super.setComponentTheme(theme);
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
  protected void onAttach() {
    super.onAttach();
    setMultipleFilesAllowed(this.fAllowMulti);
  }


  /**
   * Add a {@link FileUploadEvent} listener for the component.
   *
   * @param listener the event listener to be added
   * @return The component itself
   */
  public ListenerRegistration<FileUploadEvent> addFileUploadListener(
      EventListener<FileUploadEvent> listener) {
    return this.fileUploadEventSinkListenerRegistry.addEventListener(listener);
  }

  /**
   * Alias for {@link #addFileUploadListener(EventListener) addButtonClickListener}.
   *
   * @param listener the event listener to be added
   * @return The component itself
   */
  public ListenerRegistration<FileUploadEvent> onFileUpload(
      EventListener<FileUploadEvent> listener) {
    return addFileUploadListener(listener);
  }



}
