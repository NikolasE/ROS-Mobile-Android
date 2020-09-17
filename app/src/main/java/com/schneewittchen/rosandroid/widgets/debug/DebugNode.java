package com.schneewittchen.rosandroid.widgets.debug;

import android.util.Log;

import com.schneewittchen.rosandroid.widgets.base.BaseData;
import com.schneewittchen.rosandroid.widgets.base.BaseNode;

import org.ros.internal.message.Message;
import org.ros.internal.message.RawMessage;
import org.ros.internal.node.client.MasterClient;
import org.ros.internal.node.response.Response;
import org.ros.master.client.TopicType;
import org.ros.node.ConnectedNode;
import org.ros.node.topic.Subscriber;

import java.util.List;

import std_msgs.Empty;


/**
 * TODO: Description
 *
 * @author Nils Rottmann
 * @version 1.0.0
 * @created on 17.08.20
 * @updated on 17.09.20
 * @modified by Nils Rottmann
 */

public class DebugNode extends BaseNode {

    private static final String TAG = DebugNode.class.getSimpleName();

    @Override
    public void onStart(ConnectedNode connectedNode) {

        try{
            widget.validMessage = true;
            this.setWidget(widget);

            Subscriber<Message> subscriber = connectedNode.newSubscriber(
                    widget.subPubNoteEntity.topic,
                    widget.subPubNoteEntity.messageType);

            subscriber.addMessageListener(msgType -> {
                DebugData data = new DebugData(msgType);
                data.setId(widget.id);
                listener.onNewData(data);
            });
        } catch(Exception e) {
            widget.validMessage = false;
            this.setWidget(widget);
            e.printStackTrace();
        }

    }

    @Override
    public void onNewData(BaseData data) {
    }
}