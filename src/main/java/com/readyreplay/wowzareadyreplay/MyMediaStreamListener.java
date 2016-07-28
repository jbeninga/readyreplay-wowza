package com.readyreplay.wowzareadyreplay;

import com.wowza.wms.amf.AMFPacket;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.stream.IMediaStreamNotify;

public class MyMediaStreamListener implements IMediaStreamNotify {

	@Override
	public void onMediaStreamCreate(IMediaStream stream) {
		WMSLoggerFactory.getLogger(null).info("onMediaStreamCreate: " + stream.getSrc());

	}

	@Override
	public void onMediaStreamDestroy(IMediaStream stream) {
		WMSLoggerFactory.getLogger(null).info("onMediaStreamDestroy: " + stream.getSrc());

	}

}
