package DesignPattern.proxy;

interface People {
	public void learn();
}
class Student implements People {
	public Student() {
		super();
	}
	@Override
	public void learn() {
		System.out.println("Students learning mathematics");
	}
}
class UniversityStudent implements People {//代理就是简单地decorator
	People people;
	public UniversityStudent() {
		this.people = new Student();
	}
	@Override
	public void learn() {
		this.people.learn();//调用代理的class并做一些自己的行动
		System.out.println("University student studying advanced mathematics");
	}
}