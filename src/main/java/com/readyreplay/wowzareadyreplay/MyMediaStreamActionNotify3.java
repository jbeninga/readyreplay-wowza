package com.readyreplay.wowzareadyreplay;

import java.util.Date;

import com.wowza.wms.amf.AMFPacket;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.stream.IMediaStreamActionNotify3;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation;

public class MyMediaStreamActionNotify3 implements IMediaStreamActionNotify3 {

	@Override
	public void onMetaData(IMediaStream stream, AMFPacket metaDataPacket)
	{
		WMSLoggerFactory.getLogger(null).info("onMetaData: " + stream.getName());
	}

	@Override
	public void onPauseRaw(IMediaStream stream, boolean isPause, double location)
	{
		WMSLoggerFactory.getLogger(null).info("onPauseRaw: " + stream.getName());
	}

	@Override
	public void onPlay(IMediaStream stream, String streamName, double playStart, double playLen, int playReset)
	{
		WMSLoggerFactory.getLogger(null).info("onPlay: " + stream.getName());
	}

	@Override
	public void onPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
	{
		// Post to RR replay server the time in the stream
		Date now = new Date();
		
		String input = "{\"stream\":\"" + stream.getName() + "\",\"time\":\"" + now.getTime() + "\"}";
		 
		WMSLoggerFactory.getLogger(null).info("onPublish: " + input);

		Client client = ClientBuilder.newClient();

		WebTarget resource = client.target("http://localhost:5000/api/stream");

		Invocation.Builder request = resource.request();
		request.accept(MediaType.APPLICATION_JSON);

//		Response response = request.post(Response.class, input);
		Response response = request.get();

		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			WMSLoggerFactory.getLogger(null).info("onPublish: Success! " + response.getStatus());
			WMSLoggerFactory.getLogger(null).info("onPublish: " + response.readEntity(String.class));
		    //System.out.println("Success! " + response.getStatus());
		    //System.out.println(response.getEntity());
		} else {
			WMSLoggerFactory.getLogger(null).info("onPublish: ERROR! " + response.getStatus());
			WMSLoggerFactory.getLogger(null).info("onPublish: " + response.getEntity());
		    //System.out.println("ERROR! " + response.getStatus());    
		    //System.out.println(response.getEntity());
		}
		
		WMSLoggerFactory.getLogger(null).info("onPublish: " + now);
	}

	@Override
	public void onUnPublish(IMediaStream stream, String streamName, boolean isRecord, boolean isAppend)
	{
		WMSLoggerFactory.getLogger(null).info("onUnPublish: " + stream.getName());
	}

	@Override
	public void onPause(IMediaStream stream, boolean isPause, double location)
	{
		WMSLoggerFactory.getLogger(null).info("onPause: " + stream.getName());
	}

	@Override
	public void onSeek(IMediaStream stream, double location)
	{
		WMSLoggerFactory.getLogger(null).info("onSeek: " + stream.getName());
	}

	@Override
	public void onStop(IMediaStream stream)
	{
		WMSLoggerFactory.getLogger(null).info("onStop: " + stream.getName());
	}

	@Override
	public void onCodecInfoVideo(IMediaStream stream, MediaCodecInfoVideo codecInfoVideo)
	{
		WMSLoggerFactory.getLogger(null).info("onCodecInfoVideo: " + stream.getName());
	}

	@Override
	public void onCodecInfoAudio(IMediaStream stream, MediaCodecInfoAudio codecInfoAudio)
	{
		WMSLoggerFactory.getLogger(null).info("onCodecInfoAudio: " + stream.getName());
	}

}
