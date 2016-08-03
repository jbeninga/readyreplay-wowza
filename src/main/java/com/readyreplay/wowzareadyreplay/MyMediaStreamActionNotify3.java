package com.readyreplay.wowzareadyreplay;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.wowza.wms.amf.AMFPacket;
import com.wowza.wms.logging.WMSLoggerFactory;
import com.wowza.wms.media.model.MediaCodecInfoAudio;
import com.wowza.wms.media.model.MediaCodecInfoVideo;
import com.wowza.wms.stream.IMediaStream;
import com.wowza.wms.stream.IMediaStreamActionNotify3;

// @SpringBootApplication
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
		WMSLoggerFactory.getLogger(null).info("onPublish: " + stream.getName());
		// Post to RR replay server the time in the stream
		Date now = new Date();
		 
		RestTemplate restTemplate = new RestTemplate();
                // Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		// WMSLoggerFactory.getLogger(null).info("onPublish: " + quote.toString());
		 
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
