package moviecatalogservice.demo.models;

public class CatalogItem {
    private String name;
    private int age;
    
	public CatalogItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	public CatalogItem(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "CatalogItem [name=" + name + ", age=" + age + "]";
	}
    
    
	
}
