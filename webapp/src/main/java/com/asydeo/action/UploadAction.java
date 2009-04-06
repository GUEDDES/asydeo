package com.asydeo.action;

import java.io.IOException;
import java.net.HttpURLConnection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/asset/upload")
public class UploadAction extends BaseAction {
	private FileBean newAttachment;

	
	@DefaultHandler
	public Resolution upload() throws IOException {
		context.getResponse().sendError(500);
		return null;
	}
	
	public FileBean getNewAttachment() {
		return newAttachment;
	}

	public void setNewAttachment(FileBean newAttachment) {
		this.newAttachment = newAttachment;
	}
}
