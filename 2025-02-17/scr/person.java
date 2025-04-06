abstract class Person {
    String name;
    float annualIncome;
    public Person(String name, float annualIncome) {
        this.name = name;
        this.annualIncome = annualIncome;
    }
    public abstract float getTaxes();
}
