package com.gossiperl.client.listener;

import com.gossiperl.client.exceptions.GossiperlClientException;
import com.gossiperl.client.OverlayWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultGossiperlClientListener implements GossiperlClientListener {

    private static Logger LOG = LoggerFactory.getLogger(DefaultGossiperlClientListener.class);

    public void connected(OverlayWorker worker) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Connected.");
    }

    public void disconnected(OverlayWorker worker) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Disconnected.");
    }

    public void event(OverlayWorker worker, String eventType, Object member, long heartbeat) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Received member " + member.toString() + " event " + eventType + " at " + heartbeat + ".");
    }

    public void subscribeAck( OverlayWorker worker, List<String> eventTypes ) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<eventTypes.size(); i++) {
            if (i > 0) {
                sb.append( ", " );
            }
            sb.append(eventTypes.get(i));
        }
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Subscribed to " + sb.toString() + ".");
    }

    public void unsubscribeAck( OverlayWorker worker, List<String> eventTypes ) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<eventTypes.size(); i++) {
            if (i > 0) {
                sb.append( ", " );
            }
            sb.append(eventTypes.get(i));
        }
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Unsubscribed from " + sb.toString() + ".");
    }

    public void forwardAck(OverlayWorker worker, String reply_id) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Received confirmation of a forward message. Message ID: " + reply_id + ".");
    }

    public void forwarded(OverlayWorker worker, String digestType, byte[] binaryEnvelope, String envelopeId) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Received forward digest " + digestType + ". Digest id: " + envelopeId + ".");
    }

    public void failed(OverlayWorker worker, GossiperlClientException exception) {
        LOG.info("[" + worker.getConfiguration().getClientName() + "] Encountered an error: " + exception.getMessage() + ".");
    }

}
