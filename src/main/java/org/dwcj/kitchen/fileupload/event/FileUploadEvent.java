package org.dwcj.kitchen.fileupload.event;

import org.dwcj.component.DwcComponent;
import org.dwcj.component.button.DwcButton;
import org.dwcj.component.event.ComponentEvent;
import org.dwcj.kitchen.fileupload.FileUpload;


import java.util.Map;

/**
 * An event which is fired when the user approves the upload
 *
 * @author Stephan Wald
 * @since 23.02
 */
public class FileUploadEvent extends ComponentEvent<FileUpload> {

  /**
   * Creates a new event.
   *
   * @param abstractDwcComponent the component
   * @param payload the event map
   */
  public FileUploadEvent(FileUpload component, Map<String, Object> payload) {
    super(component, payload);
  }

}

