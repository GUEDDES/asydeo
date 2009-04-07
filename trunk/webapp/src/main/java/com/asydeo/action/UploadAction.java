package com.asydeo.action;

import java.io.IOException;
import java.io.InputStream;

import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.util.FileUtils;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/asset/upload")
public class UploadAction extends BaseAction {
	private FileBean newAttachment;

	@DefaultHandler
	public Resolution upload() throws IOException {
		try {
			m().enterCriticalSection(Lock.WRITE);
			String lang =  FileUtils.guessLang(newAttachment.getFileName());
			InputStream is = newAttachment.getInputStream();
			Thread.sleep(2000);
			m().read(is,null,lang);
			context.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
			context.getResponse().sendError(500, "error");
		} finally {
			m().leaveCriticalSection();
		}
		return null;
	}

	public FileBean getNewAttachment() {
		return newAttachment;
	}

	public void setNewAttachment(FileBean newAttachment) {
		this.newAttachment = newAttachment;
	}
}
