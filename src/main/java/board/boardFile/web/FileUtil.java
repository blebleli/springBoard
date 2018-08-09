package board.boardFile.web;

public class FileUtil {

	
	//파일 업로드 경로
	public final static String fileUploadPath = "D:\\A_TeachingMaterial\\7.JspSpring\\fileUpload";
			//"C:\\Users\\HAN\\Pictures\\Saved Pictures";
		

	/**
	 * 
	 * Method   : getFileName 
	 * 최초작성일  : 2018. 7. 16. 
	 * 작성자 : PC06 
	 * 변경이력 : 
	 * @param contentDisposition
	 * @return 
	 * Method 설명 : part와Content-dispotition header로 부터
	 * 				업로드 파일명을 리턴한다.
	 * 
	 * ex :  form-data; name="uploadFile"; filename="sally.png"
	 * return : sally.png
	 */
	
	public static String getFileName(String contentDisposition){
	
		
		
		
		
		
	/*	
		download(path, new String(vo.getLog_content(), "utf-8"));
		
		
		byte[] content = Files.readAllBytes(
		Paths.get(workspaceLocal, projectCode, parentDir, selectedFileName));
		
		upload(projectCode, parentDir, selectedFileName, content);
	

		*/
		
		
		
		String[] cdSplit = contentDisposition.split("; ");
		String fileName = "";
		for(String str : cdSplit){
		 if(str.startsWith("filename")){
		 	fileName = str.substring("filename".length()+2, str.length()-1);
		 	break;
		 	}
		 }
		 return fileName;

		
		/*
		System.out.println("getFileName===================="+contentDisposition);
		String[] content = contentDisposition.split(";");
		String result="";
		String contentName="";
		String contentValue="";
		for (String contentStr : content) {
			String[] contentNameValue = contentStr.split("=");
			if (contentNameValue[0].trim().equals("filename")) {
				contentName = contentNameValue[0].trim();
				contentValue = contentNameValue[1].trim();
				result = contentValue;
				break;
			}
		}	
		return result;	
		*/
	}
}
