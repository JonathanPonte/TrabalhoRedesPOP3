package model;



public class FilesDownload {

	private String bases64;
	private String fileNames;

	public FilesDownload(String bases64, String fileNames) {
		super();
		this.bases64 = bases64;
		this.fileNames = fileNames;
	}

	public FilesDownload() {
		
	}
	
	public String getBases64() {
		return bases64;
	}

	public void setBases64(String bases64) {
		this.bases64 = bases64;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

}
