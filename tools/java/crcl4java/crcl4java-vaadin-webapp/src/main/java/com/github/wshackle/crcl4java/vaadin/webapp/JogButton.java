package com.github.wshackle.crcl4java.vaadin.webapp;

import com.github.wshackle.crcl4java.vaadin.webapp.client.JogButtonClientRpc;
import com.github.wshackle.crcl4java.vaadin.webapp.client.JogButtonServerRpc;
import com.github.wshackle.crcl4java.vaadin.webapp.client.JogButtonState;
import com.vaadin.shared.MouseEventDetails;
import com.vaadin.ui.AbstractComponent;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class JogButton extends AbstractComponent {
    
    
    private final Set<Consumer<MouseEventDetails>> mouseDownConsumers = new HashSet<>();
    
    public void addMouseDownConsumer(Consumer<MouseEventDetails> consumer) {
        mouseDownConsumers.add(consumer);
    }
    
    public void removeMouseDownConsumer(Consumer<MouseEventDetails> consumer) {
        mouseDownConsumers.remove(consumer);
    }
    
    private final Set<Consumer<MouseEventDetails>> mouseUpConsumers = new HashSet<>();
    
    public void addMouseUpConsumer(Consumer<MouseEventDetails> consumer) {
        mouseUpConsumers.add(consumer);
    }
    
    public void removeMouseUpConsumer(Consumer<MouseEventDetails> consumer) {
        mouseUpConsumers.remove(consumer);
    }
    
    private JogButtonServerRpc rpc = new JogButtonServerRpc() {
        private int clickCount = 0;
        
        public void clicked(MouseEventDetails mouseDetails) {
//            // nag every 5:th click using RPC
//            if (++clickCount % 5 == 0) {
//                getRpcProxy(JogButtonClientRpc.class).alert(
//                        "Ok, that's enough!");
//            }
//            // update shared state
//            getState().text = "You have clicked " + clickCount + " times";
            clickCount++;
            System.out.println("mouseDetails = " + mouseDetails);
            System.out.println("clickCount = " + clickCount);
        }

        @Override
        public void mousedown(MouseEventDetails mouseDetails) {
            System.out.println("mouseDetails = " + mouseDetails);
            System.out.println("mousedown");
            for(Consumer<MouseEventDetails> consumer : mouseDownConsumers) {
                consumer.accept(mouseDetails);
            }
        }

        @Override
        public void mouseup(MouseEventDetails mouseDetails) {
            System.out.println("mouseDetails = " + mouseDetails);
            System.out.println("mouseup");
            for(Consumer<MouseEventDetails> consumer : mouseUpConsumers) {
                consumer.accept(mouseDetails);
            }
        }
    };
    
    public JogButton() {
        registerRpc(rpc);
    }
    
    public JogButton(String txt) {
        registerRpc(rpc);
        getState().text = txt;
    }
    
    @Override
    public JogButtonState getState() {
        return (JogButtonState) super.getState();
    }
}
