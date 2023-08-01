package org.dwcj.kitchen.fileupload.event;

import org.dwcj.component.AbstractDwcComponent;
import org.dwcj.component.event.Event;

import java.util.Map;

/**
 * An event which is fired when the user approves the upload
 *
 * @author Stephan Wald
 * @since 23.02
 */
public class FileUploadEvent extends Event<AbstractDwcComponent> {

  /**
   * Creates a new event.
   *
   * @param abstractDwcComponent the component
   * @param payload the event map
   */
  public FileUploadEvent(AbstractDwcComponent abstractDwcComponent, Map<String, Object> payload) {
    super(abstractDwcComponent, payload);
  }

}

