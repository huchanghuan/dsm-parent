/**
 * Program : FastJsonHttpMessageConverter.java Author : chenshilong Create :
 * 2014-3-14 下午3:41:17 Copyright 2013 by Embedded Internet Solutions Inc., All
 * rights reserved. This software is the confidential and proprietary
 * information of Embedded Internet Solutions Inc.("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * Embedded Internet Solutions Inc.
 */

package com.iwancool.dsm.support.spring.fastJson.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 使spring mvc与fastjson结合
 * 
 * @author long
 * @version 1.0.0
 * @2014-3-14 下午3:41:17
 */
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

	public final static Charset UTF8 = Charset.forName("UTF-8");

	private Charset charset = UTF8;

	private SerializerFeature[] serializerFeature;

	@Override
	protected boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream in = inputMessage.getBody();

		byte[] buf = new byte[1024];
		for (;;) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}

			if (len > 0) {
				baos.write(buf, 0, len);
			}
		}

		byte[] bytes = baos.toByteArray();
		if (charset == UTF8) {
			return JSON.parseObject(bytes, clazz);
		} else {
			return JSON.parseObject(bytes, 0, bytes.length, charset.newDecoder(), clazz);
		}
	}

	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		OutputStream out = outputMessage.getBody();
		byte[] bytes;

		if (charset == UTF8) {
			if (serializerFeature != null) {
				bytes = JSON.toJSONBytes(obj, serializerFeature);
			} else {
				bytes = JSON.toJSONBytes(obj, SerializerFeature.WriteDateUseDateFormat);
			}

		} else {
			String text;
			if (serializerFeature != null) {
				text = JSON.toJSONString(obj, serializerFeature);
			} else {
				text = JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
			}
			bytes = text.getBytes(charset);
		}

		out.write(bytes);
	}

}
