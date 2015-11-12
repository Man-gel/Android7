package com.example.directorio;

public class Person
{
	public String name;
	public String phone;
	
	public Person()
	{
		this.name = "";
		this.phone = "";
	}

	public Person( String nameCntc, String phNumber)
	{
		this.name = nameCntc;
		this.phone = phNumber;
	}
	
	public Person( Person p)
	{
		this.name = p.name;
		this.phone = p.phone;
	}
	
	
	public boolean equals(Person p)
	{
		if(p.name.equals(this.name))
			return true;
		return false;		
	}
}