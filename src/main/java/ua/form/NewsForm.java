package ua.form;

import org.springframework.web.multipart.MultipartFile;

public class NewsForm {

	private int id;

	private String name;
	
	private String nameRU;
	
	private String nameUA;
	
	private String content;
	
	private String contentRU;
	
	private String contentUA;
	
	private String path;
	
	private int version;
	
	private MultipartFile file;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameRU() {
		return nameRU;
	}

	public void setNameRU(String nameRU) {
		this.nameRU = nameRU;
	}

	public String getNameUA() {
		return nameUA;
	}

	public void setNameUA(String nameUA) {
		this.nameUA = nameUA;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentRU() {
		return contentRU;
	}

	public void setContentRU(String contentRU) {
		this.contentRU = contentRU;
	}

	public String getContentUA() {
		return contentUA;
	}

	public void setContentUA(String contentUA) {
		this.contentUA = contentUA;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
