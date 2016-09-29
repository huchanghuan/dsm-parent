package com.iwancool.dsm.utils.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CompleteMultipartUploadResult;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;

/**
 * 阿里云OSS上传
 * @ClassName AliYunOssClient
 * @Description TODO
 * @author huchanghuan
 * @Date 2016年9月9日 下午2:46:40
 * @version 1.0.0
 */
public class AliYunOssClient {
	private static final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	private static final String accessKeyId  = "YXkVqjMKDG58zRLZ";
	private static final String accessSecret = "8ZC3mSQ0pIZiIFkykyN3jG1ZeUugXN";
	private static final String bucket = "duoshoucat";
	/*private static final String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
	private static final String accessKeyId  = "EtS2l4kt2cs1elhN";
	private static final String accessSecret = "T6IDp9mk1Zf2Q0dgGV1IPB17KBrHpw";
	private static final String bucket = "hch-oss";*/
	private static final int partSize =  2 * 1024 * 1024;   //分片大小
	
	private static OSSClient oc = null;
	public static AliYunOssClient me = new AliYunOssClient();
	
	private AliYunOssClient(){
		init();
	}
	
	/**
	 * 检查bucket
	 * @author	huchanghuan
	 * @param bucketName
	 * @since   1.0.0
	 */
	public void checkBucket(String bucketName) {
		CreateBucketRequest bucketRequest = new CreateBucketRequest(bucketName);
		if (oc.doesBucketExist(bucketName)) 
			return;
		
		//设置bucket权限为公共读私有写
		bucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
		oc.createBucket(bucketRequest);
	}
	
	/**
	 * 关闭
	 * @author	huchanghuan
	 * @since   1.0.0
	 */
	public void close() {
		oc.shutdown();
	}
	
	
	/**
	 * 创建文件夹
	 * @author	huchanghuan
	 * @param folderName
	 * @since   1.0.0
	 */
	public void createFolder(String folderName) {
		
		if(!oc.doesObjectExist(bucket, folderName)){
			oc.putObject(bucket, folderName, new ByteArrayInputStream(new byte[0]));
		}
	}
	
	/**
	 * 上传
	 * @author	huchanghuan
	 * @param in
	 * @param key
	 * @return
	 * @since   1.0.0
	 */
	public String upload(InputStream in, String key) {
	 	PutObjectResult result = oc.putObject((PutObjectRequest) new PutObjectRequest(bucket, key, in));//.withProgressListener(new PutObjectProgressListener()));
	 	return null != result && null != result.getETag() ? result.getETag() : "";
	}
	
	/**
	 * 分片上传
	 * @author	huchanghuan
	 * @param in
	 * @param key
	 * @return
	 * @throws IOException 
	 * @since   1.0.0
	 */
	public String multipartUpload(InputStream in, String key) throws IOException {

	    //1、初始化一个分片上传任务（InitiateMultipartUpload）
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucket, key);
		InitiateMultipartUploadResult result = oc.initiateMultipartUpload(request);
		String uploadId = result.getUploadId();
	    //2、逐个或并行上传分片（UploadPart）
	    List<PartETag> partETags = new ArrayList<PartETag>();
	    UploadPartRequest uploadPartRequest = new UploadPartRequest();
	    uploadPartRequest.setBucketName(bucket);
	    uploadPartRequest.setKey(key);
	    uploadPartRequest.setUploadId(uploadId);
	    
	   
	    
	    	uploadPartRequest.setInputStream(in);
		    // 设置分片大小，除最后一个分片外，其它分片要大于100KB
		    uploadPartRequest.setPartSize(partSize);
		    // 设置分片号，范围是1~10000，
		    uploadPartRequest.setPartNumber(1);
		    UploadPartResult uploadPartResult = oc.uploadPart(uploadPartRequest);
		    partETags.add(uploadPartResult.getPartETag());	
		    System.out.println(uploadPartResult.getETag());
	    
	    
	    
	    //3、完成分片上传
	    //排序分片顺序并上传
	    Collections.sort(partETags, new Comparator<PartETag>() {

			@Override
			public int compare(PartETag o1, PartETag o2) {
				
				return o1.getPartNumber() - o2.getPartNumber();
			}
		});
	    
	    CompleteMultipartUploadRequest uploadRequest = new CompleteMultipartUploadRequest(bucket, key, uploadId, partETags);
	    CompleteMultipartUploadResult uploadResult = oc.completeMultipartUpload(uploadRequest);
	    return null != uploadResult && null != uploadResult.getETag() ? uploadResult.getETag() : "";
	}
	
	/**
	 * 获得文件夹名称
	 * @Description (TODO
	 * @param clazz
	 * @return
	 */
	private List<String> getFoldersName(Class<Folders> clazz) {
		List<String> folders = new ArrayList<String>();
		try {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				folders.add((String)field.get(clazz));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return folders;
	}
	
	/**
	 * 初始化文件夹
	 * @author	huchanghuan
	 * @param folders
	 * @since   1.0.0
	 */
	private void initFolder(List<String> folders) {
		if (null != folders && !folders.isEmpty()) {
			for (String folderName : folders) {
				createFolder(folderName);
			}
		}
	}
	
	/**
	 * 初始化
	 * @author	huchanghuan
	 * @since   1.0.0
	 */
	private void init () {
		//默认配置
		ClientConfiguration config = new ClientConfiguration();
		oc = new OSSClient(endpoint, accessKeyId, accessSecret, config);
				
		//检查bucket
		checkBucket(bucket);
				
		//初始化文件夹
		List<String> folders = getFoldersName(Folders.class);
		initFolder(folders);
	}

	static class PutObjectProgressListener implements ProgressListener {
	    private long bytesWritten = 0;
	    private long totalBytes = -1;
	    private boolean succeed = false;
	    @Override
	    public void progressChanged(ProgressEvent progressEvent) {
	        long bytes = progressEvent.getBytes();
	        System.out.println(bytes);
	        ProgressEventType eventType = progressEvent.getEventType();
	        switch (eventType) {
	        case TRANSFER_STARTED_EVENT:
	            System.out.println("Start to upload......");
	            break;
	        case REQUEST_CONTENT_LENGTH_EVENT:
	            this.totalBytes = bytes;
	            System.out.println(this.totalBytes + " bytes in total will be uploaded to OSS");
	            break;
	        case REQUEST_BYTE_TRANSFER_EVENT:
	            this.bytesWritten += bytes;
//	            if (this.totalBytes != -1) {
	                int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
	                System.out.println(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
	            /*} else {
	                System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
	            }*/
	            break;
	        case TRANSFER_COMPLETED_EVENT:
	            this.succeed = true;
	            System.out.println("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
	            break;
	        case TRANSFER_FAILED_EVENT:
	            System.out.println("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
	            break;
	        default:
	            break;
	        }
	    }
	    public boolean isSucceed() {
	        return succeed;
	    }
		
	}
	
	public static void main(String[] args) throws Exception {
		AliYunOssClient client = AliYunOssClient.me;
		
		
		FileInputStream fis = new FileInputStream("E:\\pic\\01.png");
		String md5 = client.upload(fis, "picture/01.png");
		System.out.println(md5);
	}
}
