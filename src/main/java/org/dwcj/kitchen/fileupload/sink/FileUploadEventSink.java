package org.dwcj.kitchen.fileupload.sink;

import com.basis.bbj.proxies.BBjClientFileSystem;
import com.basis.bbj.proxies.event.BBjEvent;
import com.basis.bbj.proxies.event.BBjFileChooserApproveEvent;
import com.basis.bbj.proxyif.SysGuiEventConstants;
import com.basis.startup.type.BBjException;
import org.dwcj.App;
import org.dwcj.Environment;
import org.dwcj.component.DwcComponent;
import org.dwcj.component.button.event.ButtonClickEvent;
import org.dwcj.component.event.sink.AbstractDwcEventSink;
import org.dwcj.dispatcher.EventDispatcher;
import org.dwcj.exceptions.DwcjRuntimeException;
import org.dwcj.kitchen.fileupload.FileUpload;
import org.dwcj.kitchen.fileupload.event.FileUploadEvent;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class will map the event to a {@link FileUploadEvent}.
 *
 * @author Stephan Wald
 * @since 23.02
 */
public final class FileUploadEventSink extends AbstractDwcEventSink {

  public FileUploadEventSink(DwcComponent component, EventDispatcher dispatcher) {
    super(component, dispatcher, SysGuiEventConstants.ON_FILECHOOSER_APPROVE);
  }

  /**
   * Handles the BBj event and dispatches a new {@link ButtonClickEvent}.
   *
   * @param ev A BBj Filechooser Approve Event, but also having done the actual copy from client
   */
  @Override
  public void handleEvent(BBjEvent ev) {

    final BBjClientFileSystem clientfs;
    try {
      clientfs = Environment.getCurrent().getBBjAPI().getThinClient().getClientFileSystem();
    } catch (BBjException e) {
      throw new DwcjRuntimeException(e);
    }

    HashMap<String, Object> map = new HashMap<>();

    HashMap<String, String> files = new HashMap<>();
    Iterator it = ((BBjFileChooserApproveEvent) ev).getSelectedFiles().iterator();
    while (it.hasNext()) {
      String filename = it.next().toString();
      String pathname = null;
      try {
        pathname = clientfs.getClientFile(filename).copyFromClient();
      } catch (BBjException e) {
        App.consoleError("Could not upload " + filename);
        continue;
      }
      files.put(filename, pathname);
    }
    map.put("filenames", files);

    FileUploadEvent dwcEv = new FileUploadEvent((FileUpload) getComponent(), map);
    getEventDispatcher().dispatchEvent(dwcEv);
  }
}
