package com.github.wshackle.crcl4java.vaadin.webapp.client;

import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.communication.ServerRpc;

public interface JogButtonServerRpc extends ServerRpc {

    // TODO: example API
    public void clicked(MouseEventDetails mouseDetails);
    public void mousedown(MouseEventDetails mouseDetails);
    public void mouseup(MouseEventDetails mouseDetails);
    
}
