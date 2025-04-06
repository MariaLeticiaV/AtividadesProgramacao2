public class Legal extends Person {
    int employeeCount;
    public Legal(String name, float annualIncome, int employeeCount) {
        super(name, annualIncome);
        this.employeeCount = employeeCount;
    }
    @Override
    public float getTaxes() {
        int taxRate = employeeCount > 10 ? 14 : 16;
        return annualIncome * taxRate / 100;
    }
}
