package com.github.wshackle.crcl4java.vaadin.webapp.client;

import com.github.wshackle.crcl4java.vaadin.webapp.JogButton;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Window;
import com.vaadin.client.MouseEventDetailsBuilder;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.shared.ui.Connect;

@Connect(JogButton.class)
public class JogButtonConnector extends AbstractComponentConnector {
    
    private JogButtonServerRpc rpc = RpcProxy.create(JogButtonServerRpc.class, this);
    
    public JogButtonConnector() {
        registerRpc(JogButtonClientRpc.class, new JogButtonClientRpc() {
            public void alert(String message) {
                // TODO: Do something useful
                Window.alert(message);
            }
        });

        getWidget().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event.getNativeEvent(),
                                getWidget().getElement());
                rpc.clicked(mouseDetails);
            }
        });
        
        getWidget().addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event.getNativeEvent(),
                                getWidget().getElement());
                rpc.mousedown(mouseDetails);
                getWidget().addStyleName("pressed");
                getWidget().removeStyleName("notpressed");
            }
        });
        
        getWidget().addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                final MouseEventDetails mouseDetails = MouseEventDetailsBuilder
                        .buildMouseEventDetails(event.getNativeEvent(),
                                getWidget().getElement());
                rpc.mouseup(mouseDetails);
                getWidget().removeStyleName("pressed");
                getWidget().addStyleName("notpressed");
            }
        });
    }
    
    @Override
    public JogButtonWidget getWidget() {
        return (JogButtonWidget) super.getWidget();
    }
    
    @Override
    public JogButtonState getState() {
        return (JogButtonState) super.getState();
    }
    
    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        // TODO: do something useful
        final String text = getState().text;
        getWidget().setText(text);
    }
}
