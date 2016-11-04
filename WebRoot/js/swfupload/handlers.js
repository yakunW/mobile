
/* This is an example of how to cancel all the files queued up.  It's made somewhat generic.  Just pass your SWFUpload
object in to this method and it loops through cancelling the uploads. */
function cancelQueue(instance) {
	document.getElementById(instance.customSettings.cancelButtonId).disabled = true;
	instance.stopUpload();
	var stats;
	
	do {
		stats = instance.getStats();
		instance.cancelUpload();
	} while (stats.files_queued !== 0);
	
}


/* **********************
   Event Handlers
   These are my custom event handlers to make my
   web application behave the way I went when SWFUpload
   completes different tasks.  These aren't part of the SWFUpload
   package.  They are part of my application.  Without these none
   of the actions SWFUpload makes will show up in my application.
   ********************** */
function fileDialogStart() {
	/* I don't need to do anything here */
	//this.startUpload();   //�������������˵����ϴ�������һ���ϴ�
}
function fileQueued(file) {
	try {
		// You might include code here that prevents the form from being submitted while the upload is in
		// progress.  Then you'll want to put code in the Queue Complete handler to "unblock" the form
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Read Complete...");
		progress.toggleCancel(true, this);

	} catch (ex) {
		this.debug(ex);
	}

}

function fileQueueError(file, errorCode, message) {
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("��һ�����ϴ��˹����ļ�.\n" + (message === 0 ? "���Ѿ��ﵽ�ļ��ϴ�����." : "Y�����ѡ������ " + (message > 1 ? "�ϴ� " + message + " �ļ�." : "һ���ļ�.")));
			return;
		}

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			progress.setStatus("�ļ�����.");
			this.debug("�������: �ļ�����, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			progress.setStatus("�������ϴ�0�ֽڵ��ļ�.");
			this.debug("�������: Z�ļ�Ϊ0�ֽ�, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			progress.setStatus("�Ƿ����ļ�����.");
			this.debug("�������: �Ƿ����ļ�����, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			alert("��ѡ���˹�����ļ�.  " +  (message > 1 ? "��ֻ����� " +  message + " �����ļ�" : "����ʱ����������κ��ļ�."));
			break;
		default:
			if (file !== null) {
				progress.setStatus("�޷�����Ĵ���");
			}
			this.debug("E�������: " + errorCode + ", �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

function fileDialogComplete(numFilesSelected, numFilesQueued) {
	try {
		if (this.getStats().files_queued > 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = false;
		}
		
		/* I want auto start and I can do that here */
		//this.startUpload();   //ע������������䲻�Զ��ϴ�         
	
	} catch (ex)  {
        this.debug(ex);
	}
}

function uploadStart(file) {
	try {
		/* I don't want to do any file validation or anything,  I'll just update the UI and return true to indicate that the upload should start */
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setStatus("Uploading...");
		progress.toggleCancel(true, this);
	}
	catch (ex) {
	}
	
	return true;      
}

function uploadProgress(file, bytesLoaded, bytesTotal) {

	try {
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);

		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setProgress(percent);
		progress.setStatus("Uploading...");
		
	} catch (ex) {
		this.debug(ex);
	}
}

function uploadSuccess(file, serverData) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setComplete();
		progress.setStatus("OK.");
		progress.toggleCancel(false);
		


	} catch (ex) {
		this.debug(ex);
	}
}

function uploadComplete(file) {
	try {
		/*  I want the next upload to continue automatically so I'll call startUpload here */
		if (this.getStats().files_queued === 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = true;
		} else {	
			this.startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}

}

function uploadError(file, errorCode, message) {
	try {
		var progress = new FileProgress(file, this.customSettings.progressTarget);
		progress.setError();
		progress.toggleCancel(false);

		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			progress.setStatus("�ϴ�����: " + message);
			this.debug("�������: HTTP ����, �ļ���: " + file.name + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
			progress.setStatus("���ô���");
			this.debug("�������: û�к�̨�ļ�, �ļ���: " + file.name + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			progress.setStatus("�ϴ�ʧ��.");
			this.debug("�������: �ϴ�ʧ��, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			progress.setStatus("������ (IO) ����");
			this.debug("�������: IO ����, �ļ���: " + file.name + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			progress.setStatus("��ȫ����");
			this.debug("�������: ��ȫ����, �ļ���: " + file.name + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			progress.setStatus(".");
			this.debug("�������: �����ϴ�����, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
			progress.setStatus("�ļ�û�ҵ�.");
			this.debug("�������: �ļ�û�ҵ�, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			progress.setStatus("��֤ʧ��.  Upload skipped.�ϴ��Թ�.");
			this.debug("�������: �ļ���ͨ����֤, �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			progress.setStatus("ȡ��");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			progress.setStatus("ֹͣ");
			break;
		default:
			progress.setStatus("�޷�����Ĵ���: " + error_code);
			this.debug("�������: " + errorCode + ", �ļ���: " + file.name + ", �ļ���С: " + file.size + ", ��Ϣ: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}